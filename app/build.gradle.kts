plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.tienda_nativo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tienda_nativo"
        minSdk = 26
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17  // Java 17 en todas partes
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"  // Kotlin también usa Java 17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Librerías principales
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
    implementation("com.google.android.gms:play-services-maps:19.0.0")

    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.0.1")

    // Firebase Core
    implementation("com.google.firebase:firebase-core:21.1.0")

    // Dependencias para pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Dependencias de ciclo de vida
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Glide para carga de imágenes
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Gson (convertir objetos a JSON y viceversa)
    implementation("com.google.code.gson:gson:2.9.1")

    // Dots Indicator (indicadores de puntos)
    implementation("com.tbuonomo:dotsindicator:5.0")
}

// Si usas Firebase o Google Services, asegúrate de tener el archivo google-services.json en tu carpeta app/
apply(plugin = "com.google.gms.google-services")
