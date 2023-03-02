ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "hive_Scala"
  )
libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.32",
  "org.apache.httpcomponents" % "httpclient" % "4.5.13",
  "org.apache.thrift" % "libthrift" % "0.18.0",
  "org.slf4j" % "slf4j-simple" % "2.0.6",
  "org.apache.spark" %% "spark-sql" % "3.2.2" % "provided",
  "org.apache.spark" %% "spark-core" % "3.2.2" % "provided",
  "org.apache.spark" %% "spark-hive" % "3.3.1" % "provided",
  "org.apache.hive" % "hive-jdbc" % "3.1.3"
)