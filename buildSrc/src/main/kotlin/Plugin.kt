object Plugin {
    const val application = "com.android.application"
    const val library = "com.android.library"
    const val kotlin = "org.jetbrains.kotlin.android"
    const val kapt = "kotlin-kapt"
    const val hilt = "com.google.dagger.hilt.android"
    const val protobuf = "com.google.protobuf"
    const val protoc = "$protobuf:protoc:${Version.protoc}"

    object Version {
        const val application = "8.1.1"
        const val library = "8.1.1"
        const val kotlin = "1.8.22"
        const val hilt = "2.48.1"
        const val protobuf = "0.9.3"
        const val protoc = "3.25.0"
    }
}