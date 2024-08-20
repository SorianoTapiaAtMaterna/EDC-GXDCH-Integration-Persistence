plugins {
    `java-library`
    id("application")
    alias(libs.plugins.shadow)
}

dependencies {


   implementation(libs.edc.control.plane.api.client)
    implementation(libs.edc.control.plane.core)
    implementation(libs.edc.control.plane.api.client)
    implementation(libs.edc.dsp)
    implementation(libs.edc.configuration.filesystem)
    implementation(libs.edc.vault.filesystem)
    implementation(libs.edc.iam.mock)
    implementation(libs.edc.management.api)
    implementation(libs.edc.transfer.data.plane)
    implementation(libs.edc.transfer.pull.http.receiver)

    implementation(libs.edc.data.plane.selector.api)
    implementation(libs.edc.data.plane.selector.core)

    implementation(libs.edc.data.plane.api)
    implementation(libs.edc.data.plane.core)
    implementation(libs.edc.data.plane.http)

    // Control plane SQL store extensions:

    implementation(libs.edc.asset.index.sql)
    implementation(libs.edc.contract.definition.store.sql)
    implementation(libs.edc.contract.negotiation.store.sql)
    implementation(libs.edc.policy.definition.store.sql)
    implementation(libs.edc.transfer.process.store.sql)

   implementation(libs.edc.data.plane.kafka)

    implementation(project(":extensions:gxfs-catalog-extension"))

    // SQL persistency dependencies:

    api(libs.edc.core.spi)
    api(libs.edc.transaction.spi)
    api(libs.edc.contract.spi)
    api(libs.edc.asset.spi)
    api(libs.edc.transfer.spi)

    implementation(libs.edc.transaction.datasource.spi)
    implementation(libs.edc.transfer.spi)
    implementation(libs.edc.sql.core)
    implementation(libs.edc.sql.lease)
    implementation(libs.edc.sql.pool.apache.commons)
    implementation(libs.edc.util.lib)
    implementation(libs.postgresql.jdbc.driver)
    implementation(libs.edc.transaction.local)
    // To come with v0.8.2: implementation(libs.edc.sql.bootstrapper)

}

application {
    mainClass.set("$group.boot.system.runtime.BaseRuntime")
}

var distTar = tasks.getByName("distTar")
var distZip = tasks.getByName("distZip")

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveFileName.set("connector.jar")
    dependsOn(distTar, distZip)
}
