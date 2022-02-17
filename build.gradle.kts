plugins {
  id("org.jetbrains.kotlin.jvm").version("1.6.10")
  id("com.apollographql.apollo3").version("3.1.0")
}

repositories {
  mavenCentral()
}

apollo {
  packageName.set("example.com")
}

val updateGraphQlSchema by tasks.creating(com.apollographql.apollo3.gradle.internal.ApolloDownloadSchemaTask::class) {
  val location = "src/commonMain/graphql/com/example/schema.json"

  schema.set(location)
  endpoint.set("https://apollo-fullstack-tutorial.herokuapp.com/graphql")

  // Prettify schema
  doLast {
    val schemaFile = File("$projectDir/$location")
    val prettySchema = groovy.json.JsonOutput.prettyPrint(schemaFile.readText(Charsets.UTF_8))
    schemaFile.writeText(prettySchema, Charsets.UTF_8)
  }
}