@file:Suppress("VulnerableLibrariesLocal")

import org.gradle.api.JavaVersion.VERSION_21
import org.gradle.jvm.toolchain.JavaLanguageVersion.of

plugins {
    id("java")
    id("net.kyori.blossom") version "1.3.1"
    id("io.papermc.paperweight.userdev") version "1.7.3"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
}

project.group = "pl.minewars.example"
project.version = "1.0.0"

java {
    toolchain.languageVersion.set(of(21))
    sourceCompatibility = VERSION_21
    targetCompatibility = VERSION_21
}

blossom {
    replaceToken("{name}", project.name)
    replaceToken("{group}", project.group)
    replaceToken("{version}", project.version)
}

paper {

    name = project.name
    version = project.version as String
    authors = listOf(
        "MrStudios Industries"
    )

    main = "pl.minewars.example.plugin.Entrypoint"
    loader = "pl.minewars.example.plugin.loader.PluginDependencyLoader"

    apiVersion = "1.19"
    generateLibrariesJson = true

}

repositories {
    mavenCentral()
    maven("https://repo.panda-lang.org/releases/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {

    /* Paper */
    compileOnly("io.papermc.paper:paper-api:${project.property("paper.version")}")
    paperweight {
        paperDevBundle(rootProject.property("paper.version") as String)
    }

    /* Lite Commands */
    paperLibrary("dev.rollczi:litecommands-bukkit:${project.property("litecommands.version")}")
    paperLibrary("dev.rollczi:litecommands-adventure:${project.property("litecommands.version")}")

    /* Okaeri Configs */
    paperLibrary("eu.okaeri:okaeri-configs-yaml-bukkit:${project.property("okaeri.configs.version")}")
    paperLibrary("eu.okaeri:okaeri-configs-serdes-bukkit:${project.property("okaeri.configs.version")}")

    /* Mixin */
    compileOnly("org.spongepowered:mixin:${project.property("mixin.version")}")
    compileOnly("io.github.llamalad7:mixinextras-common:${project.property("mixin.extras.version")}")

    /* Lombok */
    compileOnly("org.projectlombok:lombok:${project.property("lombok.version")}")
    annotationProcessor("org.projectlombok:lombok:${project.property("lombok.version")}")

    /* JetBrains Annotations */
    compileOnly("org.jetbrains:annotations:${project.property("jetbrains.annotations.version")}")
    annotationProcessor("org.jetbrains:annotations:${project.property("jetbrains.annotations.version")}")

}

tasks {

    compileJava {
        options.encoding = "UTF-8"
    }

    processResources {

        filteringCharset = "UTF-8"

        inputs.property("name", project.name)
        inputs.property("group", project.group)
        inputs.property("version", project.version)

        expand(inputs.properties)

        rename("mixins.json", "${project.name}.mixins.json")

    }

}