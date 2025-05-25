package com.example.buildsrc1

import org.gradle.api.Plugin
import org.gradle.api.Project


class FindUntranslatedStringsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register(
            "findUntranslatedStrings",
            FindUntranslatedStringsTask::class.java
        ) {
            group = "verification"
            description = "Checks for missing string translations"
        }
    }
}


