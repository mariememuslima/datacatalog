package insert.metastore
import connect.connectDataCatalog.getConnectionTry
import java.sql.Date

object insertUpdates extends App {

  // Projects Part

  def updateProjectName(id: Int, name: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE projet SET name = ? WHERE id = ?")
      statement.setString(1, name)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateProjectDescription(id: Int, description: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE projet SET description = ? WHERE id = ?")
      statement.setString(1, description)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateProjectDate(id: Int, date: Date): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE date SET date = ? WHERE id = ?")
      statement.setDate(1, date)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateProjectArchived(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE projet SET archived = ? WHERE id = ?")
      statement.setBoolean(1,true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateArchivedProjectArchived(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE projet_archived SET archived = ? WHERE id = ?")
      statement.setBoolean(1,true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateProjectModified(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE projet_archived SET modified = ? WHERE id = ?")
      statement.setBoolean(1,true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  // Databases part

  def updateDatabaseName(id: Int, name: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE db_db SET name = ? , modified = ? WHERE id = ?")
      statement.setString(1, name)
      statement.setBoolean(2, true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateDatabaseDescription(id: Int, description: String): Unit = {
      val connection = getConnectionTry()
      try {
        val statement = connection.prepareStatement("UPDATE db_db SET description = ? , modified = ? WHERE id = ?")
        statement.setString(1, description)
        statement.setBoolean(2, true)
        statement.setInt(3, id)
        statement.executeUpdate()
      } finally {
        connection.close()
      }
    }

  def updateDatabaseLocation(id: Int, loc: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE db_db SET loc = ? , modified = ? WHERE id = ?")
      statement.setString(1,loc)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateDatabaseSources(id: Int, sources: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE db_db SET sources = ? , modified = ? WHERE id = ?")
      statement.setInt(1, sources)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateDatabaseDate(id: Int, date: Date): Unit = {
      val connection = getConnectionTry()
      try {
        val statement = connection.prepareStatement("UPDATE db_db SET date = ? WHERE id = ?")
        statement.setDate(1,date)
        statement.setInt(2, id)
        statement.executeUpdate()
        println("Datas updated")
      } finally {
        connection.close()
      }
    }

  def updateDatabaseArchived(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE db_db SET archived = ? WHERE id = ?")
      statement.setBoolean(1,true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateArchivedDatabaseArchived(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE db_archived SET archived = ? WHERE id = ?")
      statement.setBoolean(1,true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateDatabaseModified(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE db_archived SET modified = ? WHERE id = ?")
      statement.setBoolean(1,true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  // Tables part

  def updateTableName(id: Int, name: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET name = ? , modified = ? WHERE id = ?")
      statement.setString(1, name)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateTableTag(id: Int, tag: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET tag = ? , modified = ? WHERE id = ?")
      statement.setString(1, tag)
      statement.setBoolean(2, true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateTableLocation(id: Int, loc: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET loc = ? , modified = ? WHERE id = ?")
      statement.setString(1, loc)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateTableFrequency(id: Int, frequency: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET frequency = ? , modified = ? WHERE id = ?")
      statement.setString(1, frequency)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateTableIdProject(idTable: Int, idProject: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET idprojet = ? WHERE id = ?")
      statement.setInt(1, idProject)
      statement.setInt(3, idTable)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateTableFrequencyFromName(name: String, frequency: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET frequency = ? WHERE name = ?")
      statement.setString(1, frequency)
      statement.setString(2, name)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateTableDate(id: Int, date: Date): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET date = ? WHERE id = ?")
      statement.setDate(1, date)
      statement.setBoolean(2, true)
      statement.setInt(2, id)
      statement.executeUpdate()
      println("Datas updated")
    } finally {
      connection.close()
    }
  }

  def updateTableArchived(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET archived = ? WHERE id = ?")
      statement.setBoolean(1, true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateArchivedTableArchived(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables_archived SET archived = ? WHERE id = ?")
      statement.setBoolean(1, true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateTableModified(id: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables_archived SET modified = ? WHERE id = ?")
      statement.setBoolean(1, true)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  // Columns part

  def updateColumnName(id: Int, name: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET name = ? , archived = ? WHERE id = ?")
      statement.setString(1, name)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateColumnTag(id: Int, description: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET description = ? , archived = ? WHERE id = ?")
      statement.setString(1, description)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateColumnDescription(id: Int, description: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET description = ?, archived = ? WHERE id = ?")
      statement.setString(1, description)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateColumnDataType(id: Int, datatype: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET description = ? , archived = ? WHERE id = ?")
      statement.setString(1, datatype)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateColumnFilter(id: Int, datatype: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET description = ? , archived = ? WHERE id = ?")
      statement.setString(1, datatype)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateColumnFormule(id: Int, datatype: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET description = ? , archived = ? WHERE id = ?")
      statement.setString(1, datatype)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateColumnDate(id: Int, date: Date): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET date = ? , archived = ? WHERE id = ?")
      statement.setDate(1, date)
      statement.setBoolean(3, true)
      statement.setInt(2, id)
      statement.executeUpdate()
      println("Datas updated")
    } finally {
      connection.close()
    }
  }

  def updateColumnArchived(id: Int, archived: Boolean): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes SET archived = ? , archived = ? WHERE id = ?")
      statement.setBoolean(1, archived)
      statement.setBoolean(2, true)
      statement.setInt(3, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateArchivedColumnArchived(id: Int, archived: Boolean): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes_archived SET archived = ? WHERE id = ?")
      statement.setBoolean(1, archived)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def updateColumnModified(id: Int, modified: Boolean): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE colonnes_archived SET modified = ? WHERE id = ?")
      statement.setBoolean(1, modified)
      statement.setInt(2, id)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }
}
