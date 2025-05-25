package com.example.buildsrc1

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

abstract class FindUntranslatedStringsTask : DefaultTask() {
    @TaskAction
    fun findUntranslatedStrings() {
        val resDir = File(project.projectDir, "src/main/res")
        val defaultStringFile = File(resDir, "values/strings.xml")
        val defaultStrings = getStringXmlIdentities(defaultStringFile)
        val translatedDirs = resDir.listFiles { file ->
            file.isDirectory && file.name.startsWith("values-") && file.name != "values"
        } ?: emptyArray()
        val missingNames = mutableMapOf<String, MutableList<String>>()
        translatedDirs.forEach { dir ->
            val translatedFile = File(dir, "strings.xml")
            if (!translatedFile.exists()) {
                missingNames[dir.name] = defaultStrings.toMutableList()
                return@forEach
            }

            val translatedStrings = getStringXmlIdentities(translatedFile)
            val missing = defaultStrings.filterNot { it in translatedStrings }
            if (missing.isNotEmpty()) missingNames[dir.name] = missing.toMutableList()
        }
        if (missingNames.isNotEmpty()) {
            val stringBuilderErrorText = buildString {
                append("Missing translations:\n")
                missingNames.forEach { lang, names ->
                    append("=== $lang ===\n")
                    append(names.joinToString("\n"))
                    append("\n\n")
                }
            }
            throw GradleException(stringBuilderErrorText)
        }
    }

    private fun getStringXmlIdentities(file: File): List<String> {
        if (!file.exists()) return emptyList()
        val stringFromXml = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(file)
            .getElementsByTagName("string")
        val stringIdentities = stringFromXml.let { nodeList ->
            (0 until nodeList.length).mapNotNull { i ->
                val node = nodeList.item(i)
                val name = node.attributes?.getNamedItem("name")?.nodeValue ?: ""
                name
            }
        }
        return stringIdentities
    }
}
