import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.realm.kotlin)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    val keystoreFile = project.rootProject.file("keys.properties")
    val properties = Properties()
    properties.load(keystoreFile.inputStream())

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "PUBLIC_API_KEY",
                properties.getProperty("PUBLIC_API_KEY") ?: ""
            )
            buildConfigField(
                "String",
                "PRIVATE_API_KEY",
                properties.getProperty("PRIVATE_API_KEY") ?: ""
            )
        }
        debug {
            buildConfigField(
                "String",
                "PUBLIC_API_KEY",
                properties.getProperty("PUBLIC_API_KEY") ?: ""
            )
            buildConfigField(
                "String",
                "PRIVATE_API_KEY",
                properties.getProperty("PRIVATE_API_KEY") ?: ""
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":designsystem"))
    // realm
    api(libs.mongodb.realm)
    api(libs.kotlinx.coroutines.core)

    // ktor
    api(libs.ktor.client)
    api(libs.ktor.cio)
    api(libs.ktor.client.content.negotiation)
    api(libs.ktor.serialization.kotlinx.json)

    // koin
    api(libs.koin.android)
    api(libs.koin.core)
    api(libs.koin.ktor)
    api(libs.koin.compose)
    api(libs.koin.androidx.compose)

    // Lifecycle
    api(libs.lifecycle.viewmodel)
    api(libs.lifecycle.viewmodel.compose)

    // Serialization
    api(libs.kotlinx.serialization.json)

    // Coil
    api(libs.coil)
    api(libs.coil.compose)

    // Compose
    api(libs.navigation.compose)
    api(libs.material.compose)

    // Tests
    testImplementation(libs.koin.test)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockK.test)
    testImplementation(libs.okhttp3.test)
    testImplementation(libs.turbine.test)
}