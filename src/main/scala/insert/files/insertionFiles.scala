package insert.files
import connect.connectDataCatalog.getConnectionTry
import objects.objectsMetastore.Database

import java.sql.Date
object insertionFiles {
  def insertColumns(id: Int, name: String, description: String, datatype: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO colonnes (id, name, description, datatype) VALUES (?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, description)
      statement.setString(4, datatype)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("column exists")
      }
    } finally {
      connection.close()
    }
  }

  def insertDatabase(id: Int, name: String, location: String, date: Date, source: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement(s"INSERT INTO db_db (id,name, loc, date, sources) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4, date)
      statement.setInt(5, source)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("db exists")
      }
    } finally {
      connection.close()
    }
  }

  def insertTables(id: Int, name: String, location: String, date: Date, iddr: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO tables (id,name, loc, date, iddb) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4, date)
      statement.setInt(5, iddr)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("table exists")
      }
    } finally {
      connection.close()
    }
  }

  def insertTableFreq(id: Int, frequency: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("UPDATE tables SET status = ? WHERE id = ?")
      statement.setString(1, frequency)
      statement.setInt(2, id)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("error update frequency")
      }
    } finally {
      connection.close()
    }
  }

  def insertDatabases(database: Database): Unit = {
    database.name match {
      case r if r.contains("refined") => insertDatabase(database.id, database.name, database.location, database.date, 2)
      case s if s.contains("trusted") => insertDatabase(database.id, database.name, database.location,database.date, 1)
      case t if t == "default" => println("default database")
      case _ => insertDatabase(database.id, database.name, database.location, database.date,  0)
    }
  }


}
