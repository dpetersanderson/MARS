
plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

version = "4.6"

application {
    // Define the main class for the application.
    mainClass = "Mars"
}

java {
    withJavadocJar()
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Javadoc> {

}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
    }
}
