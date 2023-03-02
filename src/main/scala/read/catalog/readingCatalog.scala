package read.catalog
import connect.connectDataCatalog.getConnectionTry

import objects.objectsCatalog.{ColumnC,TableC,DatabaseC}

object readingCatalog extends App{
  def getTableFreq(name: String): String = {
    val connexion = getConnectionTry()
    var freq = ""
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT frequency from tables WHERE name='$name'""")
      if (resultSet.next()) {
        freq = resultSet.getString("frequency")
      }
    } finally {
      connexion.close()
    }
    freq
  }

  def readColumnsCatalog(): List[ColumnC] = {
    val connexion = getConnectionTry()
    var column = List.empty[ColumnC]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM colonnes")
      while (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val tag = resultSet.getString("tag")
        val description = resultSet.getString("description")
        val dataType = resultSet.getString("datatype")
        val filter = resultSet.getString("filter")
        val formule = resultSet.getString("formule")
        val idTables = resultSet.getInt("idtables")
        val date = resultSet.getDate("date")
        val archived = resultSet.getBoolean("archived")
        column = ColumnC(id, name,tag,description, dataType,filter, formule,idTables,date, archived) :: column
      }
    } finally {
      connexion.close()
    }
    column
  }

  def readDatabasesCatalog(): List[DatabaseC] = {
    val connexion = getConnectionTry()
    var database = List.empty[DatabaseC]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("select * from db_db")
      while (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val location = resultSet.getString("loc")
        val sources = resultSet.getInt("sources")
        val description = resultSet.getString("description")
        val archived = resultSet.getBoolean("archived")
        val date = resultSet.getDate("date")
        database = DatabaseC(id, name, location,sources, description, date,archived) :: database
      }
    } finally {
      connexion.close()
    }
    database
  }

  def readTablesCatalog(): List[TableC] = {
    val connexion = getConnectionTry()
    var table = List.empty[TableC]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM tables")
      while (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val location = resultSet.getString("loc")
        val frequency = resultSet.getString("frequency")
        val iddb = resultSet.getInt("iddb")
        val date = resultSet.getDate("date")
        val archived = resultSet.getBoolean("archived")
        table = TableC(id, name, location,frequency, iddb, date, archived) :: table
      }
    } finally {
      connexion.close()
    }
    table
  }

  def catalogDbId(name: String): Int = {
    val connexion = getConnectionTry()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT id from db_db WHERE name='$name'""")
      if (resultSet.next()) {
        id = resultSet.getInt("id")
      }
    } finally {
      connexion.close()
    }
    id
  }

  def catalogTableId(name: String): Int = {
    val connexion = getConnectionTry()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT id from tables WHERE name='$name'""")
      if (resultSet.next()) {
        id = resultSet.getInt("id")
      }
    } finally {
      connexion.close()
    }
    id
  }

  def catalogColumnId(number: Int, name: String): Int = {
    val connexion = getConnectionTry()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT id from colonnes WHERE id=$number AND name='$name '""")
      if (resultSet.next()) {
        id = resultSet.getInt("CD_ID")
      }
    } finally {
      connexion.close()
    }
    id
  }

}
