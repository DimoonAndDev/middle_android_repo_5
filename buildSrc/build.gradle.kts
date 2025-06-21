plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}
tasks.withType<ProcessResources>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE // или EXCLUDE
}
gradlePlugin {
    plugins {
        register("untranslatedStrings") {
            id = "com.example.untranslated-strings"
            implementationClass = "com.example.buildsrc1.FindUntranslatedStringsPlugin"
        }
    }
}