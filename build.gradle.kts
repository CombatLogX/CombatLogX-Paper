import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

fun getProp(propName: String): String {
    val propProvider = providers.gradleProperty(propName)
    return propProvider.orElse("").get()
}

val baseVersionProvider = providers.gradleProperty("version.plugin")
val jenkinsBuildProvider = providers.environmentVariable("BUILD_NUMBER").orElse("Unknown")
version = "${baseVersionProvider.get()}.${jenkinsBuildProvider.get()}"

plugins {
    id("java")
    id("com.gradleup.shadow") version "9.4.1"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public")
    maven("https://nexus.sirblobman.xyz/public")
}

dependencies {
    // CombatLogX API
    val apiVersion = getProp("version.api")
    implementation("com.github.combatlogx:api:${apiVersion}")

    // PaperMC
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
}

tasks {
    named("jar") {
        enabled = false
    }

    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("CombatLogX")
        archiveClassifier.set("")
    }

    named<ProcessResources>("processResources") {
        val pluginVersion = providers.provider { project.version.toString() }
        inputs.property("version", pluginVersion)

        filesMatching("paper-plugin.yml") {
            expand(mapOf("version" to pluginVersion.get()))
        }

        filesMatching("config.yml") {
            expand(mapOf("version" to pluginVersion.get()))
        }
    }
}
