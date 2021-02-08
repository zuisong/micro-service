subprojects {
  apply(plugin = "java")
  repositories {
    mavenLocal()
    maven("https://mirrors.huaweicloud.com/repository/maven/")
    maven("https://repo.maven.apache.org/maven2/")
  }

  val spring_boot_version: String by project
  val spring_cloud_dependencies_version: String by project
  val spock_version: String by project
  val kotlin_version: String by project


  dependencies {
    val implementation by configurations
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:$spring_cloud_dependencies_version"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:$spring_boot_version"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlin_version"))
    implementation(platform("org.spockframework:spock-bom:$spock_version"))
  }


  tasks.withType<Test> {
    useJUnitPlatform()
  }

  group = "cn.mmooo"
  version = "1.0.2"
}
