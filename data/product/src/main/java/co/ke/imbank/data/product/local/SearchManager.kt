package co.ke.imbank.data.product.local

import android.content.Context
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.SearchSpec
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import co.ke.imbank.data.product.local.document.ProductDocument
import co.ke.imbank.data.product.mapper.toProductDocument
import co.ke.imbank.data.product.mapper.toProductDomain
import co.ke.imbank.domain.product.model.ProductDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchManager(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
    scope: CoroutineScope
) {

    var session: AppSearchSession? = null

    init {
        scope.launch {
            init()
        }
    }

    suspend fun init() {
        withContext(ioDispatcher) {
            val sessionFuture = LocalStorage.createSearchSessionAsync(
                LocalStorage.SearchContext.Builder(
                    context, "moneyswift"
                ).build()
            )

            val setSchemaRequest =
                SetSchemaRequest.Builder().addDocumentClasses(ProductDocument::class.java).build()

            session = sessionFuture.get()

            session?.setSchemaAsync(setSchemaRequest)

        }
    }

    suspend fun addProductList(productDomainList: List<ProductDomain>): Boolean {
        return withContext(ioDispatcher) {
            session?.putAsync(
                PutDocumentsRequest.Builder()
                    .addDocuments(productDomainList.map { it.toProductDocument() }).build()
            )?.get()?.isSuccess == true
        }
    }

    suspend fun searchProductList(query: String): List<ProductDomain> {
        return withContext(ioDispatcher) {
            val searchSpec =
                SearchSpec.Builder().setSnippetCount(10).addFilterNamespaces("my_products")
                    .setRankingStrategy(SearchSpec.RANKING_STRATEGY_SYSTEM_USAGE_COUNT).build()

            val result = session?.search(
                query, searchSpec
            ) ?: return@withContext emptyList()

            val page = result.nextPageAsync.get()

            page.mapNotNull { searchResult ->
                if (searchResult.genericDocument.schemaType == ProductDocument::class.java.simpleName) {
                    searchResult.getDocument(ProductDocument::class.java).toProductDomain()
                } else null

            }
        }
    }

    suspend fun fetchProductListFromLocalStorage(): List<ProductDomain> {
        return withContext(ioDispatcher) {
            val searchSpec = SearchSpec.Builder().addFilterNamespaces("my_products")
                .setRankingStrategy(SearchSpec.RANKING_STRATEGY_SYSTEM_USAGE_COUNT).build()

            val result = session?.search(
                "", searchSpec
            ) ?: return@withContext emptyList()

            val recipients = mutableListOf<ProductDocument>()

            do {
                val page = result.nextPageAsync.get()
                recipients.addAll(page.mapNotNull { searchResult ->
                    if (searchResult.genericDocument.schemaType == ProductDocument::class.java.simpleName) {
                        searchResult.getDocument(ProductDocument::class.java)
                    } else null
                })
            } while (result.nextPageAsync.get().isNotEmpty())

            recipients.map { it.toProductDomain() }
        }
    }

    fun closeSession() {
        session?.close()
        session = null
    }

}