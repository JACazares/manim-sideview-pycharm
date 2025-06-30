plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.3"
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
}

group = "com.manimsideview"
version = "0.1.0"

repositories {
    mavenCentral()
}

intellij {
    type.set("PY")
    version.set("2023.3")
}

kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("241.*")
    }
}
