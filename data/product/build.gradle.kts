plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("kapt")
}

android {
    namespace = "co.ke.imbank.data.product"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain:product"))
    implementation(project(":core:network"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //Retrofit
    implementation(libs.retrofit)
    //Kotlinx Serialization Json
    implementation(libs.kotlinx.serialization.json)
    //Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
//    //AppSearch
//    implementation(libs.androidx.appsearch)
//    kapt(libs.androidx.appsearch.compiler)
//    implementation(libs.androidx.appsearch.local.storage)

    testImplementation(libs.junit)
    //mockK
    testImplementation(libs.mockk)
    //Instantiator
    testImplementation(libs.instantiator)
    //Kotlin Coroutine Test
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}