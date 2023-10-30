buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        //classpath(Dependencies.javapoet)
    }

}

plugins {
    id(Dependencies.hiltProjectLevel) version Versions.hilt apply false
    kotlin("plugin.serialization") version Versions.serialization
}
