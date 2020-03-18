buildscript {
  repositories {
    maven(url = "https://plugins.gradle.org/m2/")
  }
  dependencies {
    val kotlin_version = "1.3.70"
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlin_version")
    classpath("se.patrikerdes:gradle-use-latest-versions-plugin:0.2.13")
    classpath("com.github.ben-manes:gradle-versions-plugin:0.27.0")
  }
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")


  dependencies {
    val api by configurations
    val annotationProcessor by configurations
    val compileOnly by configurations
    api(kotlin("stdlib-jdk8"))
    api(kotlin("reflect"))

    api(platform(kotlin("bom")))
    api(platform("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR3"))
    api(platform("org.springframework.boot:spring-boot-dependencies:2.3.0.M3"))
    api(platform("com.fasterxml.jackson:jackson-bom:2.10.3"))

    api("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    compileOnly("org.projectlombok:lombok:1.18.12")
    annotationProcessor("org.projectlombok:lombok:1.18.12")
  }

  tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
  }

  tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
  }

  tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
    with(options) {
      isFork = true
      encoding = "UTF-8"
      forkOptions.jvmArgs!! += "-Duser.country=US"
      compilerArgs.plusAssign("-Xlint:deprecation")
    }
  }


  tasks.withType<Test> {
    useJUnitPlatform { }
  }

}

allprojects {

  group = "cn.mmooo"
  version = "1.0.0"

  repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/central/")
    maven("https://mirrors.huaweicloud.com/repository/maven/")
    maven("http://repo.maven.apache.org/maven2")
    jcenter()
  }

  apply(plugin = "com.github.ben-manes.versions")
  apply(plugin = "se.patrikerdes.use-latest-versions")

  tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    resolutionStrategy {
      componentSelection {
        all {
          val rejected = listOf(
            "alpha",
            "beta",
            "snapshots",
            "preview",
            "eap")
            .any { candidate.version.contains(it, true) }
          if (rejected) {
            reject("Release candidate")
          }
        }
      }
    }
// optional parameters
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
  }

}
