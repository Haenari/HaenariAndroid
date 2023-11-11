object Dependencies {

    object AndroidX {
        private const val default = "androidx"

        private const val activityVersion = "1.8.0"
        private const val annotationVersion = "1.6.0"
        private const val appCompatVersion = "1.6.1"
        private const val constraintLayoutVersion = "2.1.4"
        private const val dataStoreVersion = "1.0.0"
        private const val fragmentVersion = "1.6.1"
        private const val ktxVersion = "1.9.0"
        private const val roomVersion = "2.5.2"
        private const val splashScreenVersion = "1.0.1"

        const val ACTIVITY = "${default}.activity:activity-ktx:${activityVersion}"
        const val ANNOTATION = "${default}.annotation:annotation:${annotationVersion}"
        const val APP_COMPAT = "${default}.appcompat:appcompat:${appCompatVersion}"
        const val CONSTRAINT_LAYOUT = "${default}.constraintlayout:constraintlayout:${constraintLayoutVersion}"
        const val DATA_STORE = "${default}.datastore:datastore:${dataStoreVersion}"
        const val DATA_STORE_CORE = "${default}.datastore:datastore-core:${dataStoreVersion}"
        const val DATA_STORE_PREFERENCES = "${default}.datastore:datastore-preferences:${dataStoreVersion}"
        const val DATA_STORE_PREFERENCES_CORE = "${default}.datastore:datastore-preferences-core:${dataStoreVersion}"
        const val FRAGMENT = "${default}.fragment:fragment-ktx:${fragmentVersion}"
        const val KTX = "${default}.core:core-ktx:${ktxVersion}"
        const val ROOM_COMPILER = "${default}.room:room-compiler:${roomVersion}"
        const val ROOM_KTX = "${default}.room:room-ktx:${roomVersion}"
        const val ROOM_RUNTIME = "${default}.room:room-runtime:${roomVersion}"
        const val ROOM_TEST = "${default}.room:room-testing:${roomVersion}"
        const val SPLASH_SCREEN = "${default}.core:core-splashscreen:${splashScreenVersion}"
    }

    object Google {
        private const val default = "com.google"

        private const val hiltVersion = "2.48.1"
        private const val materialVersion = "1.9.0"
        private const val protobufVersion = "3.23.0"

        const val HILT = "${default}.dagger:hilt-android:${hiltVersion}"
        const val HILT_COMPILER = "${default}.dagger:hilt-compiler:${hiltVersion}"
        const val MATERIAL = "${default}.android.material:material:${materialVersion}"
        const val PROTOBUF = "${default}.protobuf:protobuf-javalite:${protobufVersion}"
    }

    object Kotlin {
        private const val default = "org.jetbrains.kotlinx"

        private const val coroutineVersion = "1.6.4"

        const val COROUTINE = "${default}:kotlinx-coroutines-android:${coroutineVersion}"
    }

    object SquareUp {
        private const val default = "com.squareup"

        private const val okHttpVersion = "4.10.0"
        private const val retrofitVersion = "2.9.0"

        const val OKHTTP3 = "${default}.okhttp3:okhttp:${okHttpVersion}"
        const val OKHTTP3_INTERCEPTOR = "${default}.okhttp3:logging-interceptor:${okHttpVersion}"
        const val RETROFIT = "${default}.retrofit2:retrofit:${retrofitVersion}"
        const val RETROFIT_CONVERTER_GSON = "${default}.retrofit2:converter-gson:${retrofitVersion}"
    }

    object Test {
        private const val junitVersion = "4.13.2"

        const val JUNIT = "junit:junit:${junitVersion}"
    }

    object AndroidTest {
        private const val default = "androidx.test"

        private const val espressoVersion = "3.5.1"
        private const val junitVersion = "1.1.5"

        const val ESPRESSO = "${default}.espresso:espresso-core:${espressoVersion}"
        const val JUNIT = "${default}.ext:junit:${junitVersion}"
    }
}