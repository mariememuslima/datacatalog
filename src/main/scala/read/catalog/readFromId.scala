package read.catalog
import objects.objectsCatalog.{ColumnC, DatabaseC, TableC}
import connect.connectDataCatalog.getConnectionTry
import java.sql.Date

object readFromId {

  def getDbFromId(id: Int): DatabaseC = {
    val connexion = getConnectionTry()
    var database = DatabaseC(0, " ", "loc", 0, " ", new Date(System.currentTimeMillis()), false)
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT * from db_db WHERE id=$id""")
      if (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val loc = resultSet.getString("loc")
        val sources = resultSet.getInt("sources")
        val description = resultSet.getString("description")
        val date = resultSet.getDate("date")
        val archived = resultSet.getBoolean("archived")
        database = DatabaseC(id, name, loc, sources, description, date, archived)
      }
    } finally {
      connexion.close()
    }
    database
  }

  def getTableFromId(id: Int): TableC = {
    val connexion = getConnectionTry()
    var table = TableC(0, "", "", "", 0, new Date(System.currentTimeMillis()), false)
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT * from tables WHERE id=$id""")
      if (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val loc = resultSet.getString("loc")
        val frequency = resultSet.getString("frequency")
        val date = resultSet.getDate("date")
        val archived = resultSet.getBoolean("archived")
        val iddb = resultSet.getInt("iddb")
        table = TableC(id, name, loc, frequency, iddb, date, archived)
      }
    } finally {
      connexion.close()
    }
    table
  }

  def getColumnFromId(id: Int, name: String): ColumnC = {
    val connexion = getConnectionTry()
    var column = ColumnC(0, "", "", "", "", "", "", 0, new Date(System.currentTimeMillis()), false)
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT * from colonnes WHERE id=$id AND name='$name'""")
      if (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val tag = resultSet.getString("tag")
        val description = resultSet.getString("description")
        val dataType = resultSet.getString("datatype")
        val filter = resultSet.getString("filter")
        val formule = resultSet.getString("formule")
        val date = resultSet.getDate("date")
        val archived = resultSet.getBoolean("archived")
        val idTable = resultSet.getInt("idtables")
        column = ColumnC(id, name, tag, description, dataType, filter, formule, idTable, date, archived)
      }
    } finally {
      connexion.close()
    }
    println(id)
    column
  }

  def getDbFrequency(name: String): String = {
    val connexion = getConnectionTry()
    var id: String = null
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT frequency from tables WHERE name='$name'""")
      if (resultSet.next()) {
        id = resultSet.getString("frequency")
      }
    } finally {
      connexion.close()
    }
    id
  }


}
