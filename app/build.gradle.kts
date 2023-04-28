
plugins {
    alias(libs.plugins.com.android.application)
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.agromart"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.agromart"
        minSdk = 22
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation ("com.daimajia.androidanimations:library:2.4@aar")
    implementation (platform("com.google.firebase:firebase-bom:31.5.0"))
    implementation ("com.google.firebase:firebase-database-ktx")
}

