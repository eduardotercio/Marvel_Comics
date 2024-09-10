pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HQs Marvel"
include(":app")
include(":common")
include(":feature")
include(":feature:home")
include(":feature:favorites")
include(":feature:splash")
include(":feature:comic")
include(":designsystem")
