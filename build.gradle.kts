import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ktlint.integration)
    alias(libs.plugins.detekt)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(libs.bundles.testing)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    config.setFrom(rootProject.projectDir.path + "/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
}

ktlint {
    version.set(libs.versions.ktlint.plugin)
}
