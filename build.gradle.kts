plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.9.0"
}

group = "com.github.kevindagame"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven(url = "https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation("com.github.Revxrsal.Lamp:common:3.1.5")
    implementation("com.github.Revxrsal.Lamp:bukkit:3.1.5")

}

val targetJavaVersion = 17

tasks {
    processResources {
        // replace stuff in plugin.yml, nothing else
        filesMatching("plugin.yml") {
            // replace version
            expand("projectName" to rootProject.name, "version" to version)
        }
    }

    runServer {
        minecraftVersion("1.20.1")
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)

        options.compilerArgs = options.compilerArgs + "-parameters"
        options.isFork = true
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    shadowJar {
        minimize()
        relocate("kotlin", "com.github.kevindagame.blockmirror.libs.kotlin")
    }

    build {
        dependsOn(shadowJar)
    }
}

kotlin {
    jvmToolchain(17)
}
