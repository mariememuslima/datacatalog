package connect

import java.sql.{Connection, DriverManager}

object connectHive extends App{
  // ________________________________________________________________________________________________________________________
  // Connecting to databases
  // ________________________________________________________________________________________________________________________

  def getConnectionMetastore(): Connection = {
    val url = "jdbc:mysql://localhost:3306/metastore"
    val driver = "com.mysql.cj.jdbc.Driver"
    val user = "marieme"
    val password = "Hive@123"

    Class.forName(driver)
    DriverManager.getConnection(url, user, password)
  }
  def getConnection(): Connection = {
    val url = "jdbc:mysql://localhost:3306/example"
    val driver = "com.mysql.cj.jdbc.Driver"
    val user = "marieme"
    val password = "Hive@123"

    Class.forName(driver)
    DriverManager.getConnection(url, user, password)
  }

  def getConnectionTry(): Connection = {
    val url = "jdbc:mysql://localhost:3306/try"
    val driver = "com.mysql.cj.jdbc.Driver"
    val user = "marieme"
    val password = "Hive@123"

    Class.forName(driver)
    DriverManager.getConnection(url, user, password)
  }

  def connectToHive(): Unit = {

    // Define the JDBC URL for Hive
    val url = "jdbc:hive2://localhost:10000/default"

    // Define the Hive user credentials
    val username = "marieme" // or hdoop
    val password = "Hive@123" // or hadoop

    // Register the JDBC driver for Hive
    Class.forName("org.apache.hive.jdbc.HiveDriver")

    // Create a JDBC connection to Hive
    val connection = DriverManager.getConnection(url, username, password)

    // Create a new database
    val statement = connection.createStatement()
    statement.execute("CREATE DATABASE IF NOT EXISTS test")

    // Do some work with the connection
    println("done")


    // Close the JDBC connection to Hive
    connection.close()
  }
  // connectToHive()
}
