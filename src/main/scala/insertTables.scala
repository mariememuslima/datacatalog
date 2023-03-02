import  connect.connectHive._
import objects.caseObjects._
import java.sql.Date
object insertTables {

  // ________________________________________________________________________________________________________________________
  // Insert
  // ________________________________________________________________________________________________________________________
  def insertDatas(name: String, tag: String, description: String): Unit = {
    //val idProject = getIdProjet(name)
    val connection = getConnection()
    try {
      val statement = connection.prepareStatement("INSERT INTO projet (name, tag,description) VALUES (?,?,?)")
      statement.setString(1, name)
      statement.setString(2, tag)
      statement.setString(3, description)
      statement.executeUpdate()
      println("Datas Inserted")
    }  finally {
      connection.close()
    }
  }
  //inserting data by making an instance of the function
  //insertDatas("equationIns","PII","C'est le projet equation IN")


  // ________________________________________________________________________________________________________________________
  // Insert all tables
  // ________________________________________________________________________________________________________________________

  def insertDatabase(id: Int, name: String, location: String, source: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement(s"INSERT INTO db (id,name, loc, date, sources) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4, new Date(System.currentTimeMillis()))
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

  def insertTables(id: Int, name: String, location: String, iddr: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO tables (id,name, loc, date, iddb) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4, new Date(System.currentTimeMillis()))
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

  def insertSources(id: Int, ids: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO sources (idTable, idSource) VALUES (?,?)")
      statement.setInt(1, id)
      statement.setInt(2, ids)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def refinedOrTrusted(name: String): Int = {
    var number = 0
    name match {
      case r if r.contains("refined") => number = 1
      case s if s.contains("trusted") => number = 0
      case _ => number = 2
    }
    number
  }

  def insertDatabases(database: Database): Unit = {
    database.name match {
      case r if r.contains("refined") => insertDatabase(database.id, database.name, database.location, 2)
      case s if s.contains("trusted") => insertDatabase(database.id, database.name, database.location, 1)
      case t if t == "default" => println("default database")
      case _ => insertDatabase(database.id, database.name, database.location, 0)
    }
  }

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


}
