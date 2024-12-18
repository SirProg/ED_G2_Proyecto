plugins {
  alias(libs.plugins.android.application)
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.example.dsproyect_p1"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.dsproyect_p1"
    minSdk = 33
    targetSdk = 34
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
  implementation(libs.activity)
  implementation(libs.constraintlayout)
  implementation(libs.places)
  testImplementation(libs.junit)
  androidTestImplementation(libs.ext.junit)
  androidTestImplementation(libs.espresso.core)

  // Gson: Json Library
  implementation ("com.google.code.gson:gson:2.11.0")

  // Hilt for Dependency Injection
  implementation("com.google.dagger:hilt-android:2.51.1")
  annotationProcessor("com.google.dagger:hilt-android-compiler:2.51.1")
}
