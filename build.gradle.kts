plugins {
    application
    kotlin("jvm") version "1.2.61"
}

application {
    mainClassName = "MainKt"
}

dependencies {
    compile(kotlin("stdlib"))
    compile("io.reactivex.rxjava2:rxjava:2.2.0")
    compile("io.reactivex.rxjava2:rxkotlin:2.2.0")
}

repositories {
    jcenter()
}
