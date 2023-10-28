buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        //classpath(Dependencies.hiltAgp)
    }

}

plugins {
    id(Dependencies.hiltProjectLevel) version Versions.hilt apply false
    //id(Dependencies.serializationProjectLevel) version Versions.serialization apply false
    kotlin("plugin.serialization") version Versions.serialization
}
