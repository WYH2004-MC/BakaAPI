import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "top.wemc"
version = "2.2.4-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Files
    implementation(fileTree(mapOf("dir" to "libs/implementation", "include" to listOf("*.jar"))))
    compileOnly(fileTree(mapOf("dir" to "libs/compileOnly", "include" to listOf("*.jar"))))

    implementation("com.zaxxer:HikariCP:4.0.3")
}

tasks {

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    val fatJar by named("shadowJar", ShadowJar::class) {
        archiveFileName.set("${project.name}-${project.version}.jar")

        dependencies {
            exclude(dependency("org.slf4j:.*"))
        }
        minimize()
    }

    artifacts {
        add("archives", fatJar)
    }

    processResources {
        inputs.property("version", project.version)
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
    }

    build {
        dependsOn(shadowJar)
    }
}

