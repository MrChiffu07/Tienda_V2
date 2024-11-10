plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapplication20"
    compileSdk = 35  // Si es necesario, actualiza a un SDK mayor

    defaultConfig {
        applicationId = "com.example.myapplication20"
        minSdk = 26  // Requiere minSdk 26 para los iconos adaptativos
        targetSdk = 35  // Actualiza al SDK más reciente que deseas utilizar
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17  // Actualiza a Java 17
        targetCompatibility = JavaVersion.VERSION_17  // Actualiza a Java 17
    }

    kotlinOptions {
        jvmTarget = "17"  // Asegúrate de que Kotlin compile con Java 17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.firebase.database)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gridlayout)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.annotation)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.location)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Lifecycle dependencies
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Glide dependency
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Gson
    implementation("com.google.code.gson:gson:2.9.1")

    // Dots Indicator
    implementation("com.tbuonomo:dotsindicator:5.0")
}
