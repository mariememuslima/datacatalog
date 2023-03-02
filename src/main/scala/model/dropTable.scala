package model
import connect.connectDataCatalog.getConnectionTry
object dropTable extends App{
  def dropTls = {
    val connection = getConnectionTry()
    try {
      val statement = connection.createStatement
      statement.executeUpdate("DROP TABLE IF EXISTS projet CASCADE")
      statement.executeUpdate("DROP TABLE IF EXISTS db CASCADE")
      statement.executeUpdate("DROP TABLE IF EXISTS tables CASCADE")
      statement.executeUpdate("DROP TABLE IF EXISTS colonnes CASCADE")
      statement.executeUpdate("DROP TABLE IF EXISTS files CASCADE")
      println("Tables dropped")
      println("Tables archived dropped")
    } finally {
      connection.close()
    }
  }
  dropTls
}