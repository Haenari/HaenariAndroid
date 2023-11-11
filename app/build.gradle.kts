plugins {
    id(Plugin.application)
    id(Plugin.kotlin)
    id(Plugin.kapt)
    id(Plugin.protobuf) version (Plugin.Version.protobuf)
    id(Plugin.hilt)
}

android {
    namespace = "com.haenari.haenari"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.haenari.haenari"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "WEATHER_SERVICE_KEY", "\"qedFiFxzLxPqXaXQgI/ho/3QwkH+yrAbafDh7sHia6t742ZhF0Nn5AhDKcl4bm5qWKj+xmcNmG6h1djZTiiB2g==\"")
        buildConfigField("String", "WEATHER_URL", "\"http://apis.data.go.kr/1360000/\"")
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
    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
}

dependencies {

    // androidX
    implementation(Dependencies.AndroidX.ACTIVITY)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidX.FRAGMENT)
    implementation(Dependencies.AndroidX.KTX)
    implementation(Dependencies.AndroidX.SPLASH_SCREEN)
    implementation(Dependencies.Google.MATERIAL)

    // hilt
    implementation(Dependencies.Google.HILT)
    kapt(Dependencies.Google.HILT_COMPILER)

    // annotation
    implementation(Dependencies.AndroidX.ANNOTATION)

    // coroutine
    implementation(Dependencies.Kotlin.COROUTINE)

    // datastore
    implementation(Dependencies.AndroidX.DATA_STORE)
    implementation(Dependencies.AndroidX.DATA_STORE_CORE)
    implementation(Dependencies.AndroidX.DATA_STORE_PREFERENCES)
    implementation(Dependencies.AndroidX.DATA_STORE_PREFERENCES_CORE)
    implementation(Dependencies.Google.PROTOBUF)

    // room
    implementation(Dependencies.AndroidX.ROOM_RUNTIME)
    implementation(Dependencies.AndroidX.ROOM_KTX)
    kapt(Dependencies.AndroidX.ROOM_COMPILER)
    annotationProcessor(Dependencies.AndroidX.ROOM_COMPILER)
    testImplementation(Dependencies.AndroidX.ROOM_TEST)

    // retrofit
    implementation(Dependencies.SquareUp.OKHTTP3)
    implementation(Dependencies.SquareUp.OKHTTP3_INTERCEPTOR)
    implementation(Dependencies.SquareUp.RETROFIT)
    implementation(Dependencies.SquareUp.RETROFIT_CONVERTER_GSON)

    testImplementation(Dependencies.Test.JUNIT)

    androidTestImplementation(Dependencies.AndroidTest.JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.ESPRESSO)
}