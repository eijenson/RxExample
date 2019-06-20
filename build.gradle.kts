plugins {
    application
    kotlin("jvm") version "1.3.11"
}

application {
    mainClassName = "MainKt"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.reactivex.rxjava2:rxjava:2.2.0")
    implementation("io.reactivex.rxjava2:rxkotlin:2.2.0")
    implementation("com.google.code.gson:gson:2.8.5")

}

repositories {
    jcenter()
}
