package co.ke.imbank.data.product.local.document

import androidx.appsearch.annotation.Document
import androidx.appsearch.annotation.Document.Id
import androidx.appsearch.annotation.Document.LongProperty
import androidx.appsearch.annotation.Document.Namespace
import androidx.appsearch.annotation.Document.Score
import androidx.appsearch.annotation.Document.StringProperty
import androidx.appsearch.app.AppSearchSchema

@Document
data class ProductDocument(
    @Namespace val namespace: String,
    @Id val id: String,
    @Score val score: Int,

    @StringProperty val currencyCode: String,
    @StringProperty val currencySymbol: String,
    @StringProperty(indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES) val description: String,
    @StringProperty val imageLocation: String,
    @StringProperty(indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES) val name: String,
    @LongProperty val price: Int,
    @LongProperty val quantity: Int,
    @StringProperty val status: String

)