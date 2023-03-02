package read.metastore
import connect.connectMetastore.getConnectionMetastore
import connect.connectDataCatalog.getConnectionTry
import java.sql.Date
import objects.objectsCatalog.{DatabaseC,TableC,ColumnC}
object readById extends App {

  def getDbId(name: String): Int = {
    val connexion = getConnectionMetastore()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT DB_ID from DBS WHERE NAME='$name'""")
      if (resultSet.next()) {
        id = resultSet.getInt("DB_ID")
      }
    } finally {
      connexion.close()
    }
    id
  }

  def getTableId(name: String): Int = {
    val connexion = getConnectionMetastore()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT TBL_ID from TBLS WHERE NAME='$name'""")
      if (resultSet.next()) {
        id = resultSet.getInt("TBL_ID")
      }
    } finally {
      connexion.close()
    }
    id
  }

  def getColumnId(number : Int,name: String): Int = {
    val connexion = getConnectionMetastore()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT CD_ID from COLUMNS_V2 WHERE CD_ID=$number AND COLUMN_NAME='$name '""")
      if (resultSet.next()) {
        id = resultSet.getInt("CD_ID")
      }
    } finally {
      connexion.close()
    }
    println(id)
    id
  }

  def getProjectId(name: String): Int = {
    val connexion = getConnectionMetastore()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT id from project WHERE name='$name '""")
      if (resultSet.next()) {
        id = resultSet.getInt("id")
      }
    } finally {
      connexion.close()
    }
    id
  }

  /*
  def getDbFromId(id: Int): DatabaseC = {
    val connexion = getConnectionTry()
    var database = DatabaseC(0, " ", "loc",0, " " ,new Date(System.currentTimeMillis()),false)
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
        database = DatabaseC(id , name, loc, sources, description , date, archived )
      }
    } finally {
      connexion.close()
    }
    database
  }

  def getTableFromId(id: Int): TableC = {
    val connexion = getConnectionTry()
    var table = TableC(0,"","","",0,new Date(System.currentTimeMillis()),false)
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
        table = TableC(id, name,loc,frequency,iddb,date,archived)
      }
    } finally {
      connexion.close()
    }
    table
  }

  def getColumnFromId(id: Int, name: String): ColumnC = {
    val connexion = getConnectionTry()
    var column = ColumnC(0,"","","","","","",0, new Date(System.currentTimeMillis()),false)
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT * from colonnes WHERE id_col=$id AND name='$name'""")
      if (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val loc = resultSet.getString("loc")
        val description = resultSet.getString("description")
        val dataType = resultSet.getString("datatype")
        val filter = resultSet.getString("filter")
        val formule = resultSet.getString("formule")
        val date = resultSet.getDate("date")
        val archived = resultSet.getBoolean("archived")
        val idTable = resultSet.getInt("idtables")
        column = ColumnC(id,name,loc,description,dataType,filter,formule,idTable,date,archived)
      }
    } finally {
      connexion.close()
    }
    println(id)
    column
  }
  */

}
