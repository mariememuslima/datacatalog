package insert.metastore

import connect.connectDataCatalog.getConnectionTry

import java.sql.Date

object insertionMetastore {
  def insertProject(name: String): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO projet (name) VALUES (?)")
      statement.setString(1, name)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("column exists")
      }
    } finally {
      connection.close()
    }
  }

  def insertSources(idTable: Int,idSource : Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO sources (idTable,idSource) VALUES (?,?)")
      statement.setInt(1, idTable)
      statement.setInt(2, idSource)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
      }
    } finally {
      connection.close()
    }
  }

  def insertColumns(id: Int, name: String, description: String, datatype: String, idTb : Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO colonnes (id, name, description, datatype, idtables) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, description)
      statement.setString(4, datatype)
      statement.setInt(5, idTb)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("column exists")
      }
    } finally {
      connection.close()
    }
  }

  def insertDatabase(id: Int, name: String, location: String, date: Date): Unit = {
    val connection = getConnectionTry()
    try {
      var source: Int = 0
      if (name.contains("refined")==true) {
        source = 2
      } else if (name.contains("trusted")){
        source = 1
      }
      val statement = connection.prepareStatement(s"INSERT INTO db_db (id,name, loc, date, sources) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4, date)
      statement.setInt(5, source)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {}
    } finally {
      connection.close()
    }

  }

  def insertTables(id: Int, name: String, location: String, date: Date, iddb: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO tables (id,name, loc, date, iddb) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4, date)
      statement.setInt(5, iddb)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {}
    } finally {
      connection.close()
    }
  }

  def insertColumnsA(id: Int, name: String, description: String, filter: String, formule: String, datatype: String, idTb: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO colonnes_archived (id, name, description, datatype, idtables, filter, formule) VALUES (?,?,?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, description)
      statement.setString(4, datatype)
      statement.setInt(5, idTb)
      statement.setString(6, filter)
      statement.setString(7, formule)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {}
    } finally {
      connection.close()
    }
  }

  def insertColumnsM(id: Int, name: String, tag: String, description: String, filter: String, formule: String, datatype: String, idTb: Int, date:Date): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO colonnes_archived (id_colonnes, name, tag, description, datatype, idtables, filter, formule, date, modified) VALUES (?,?,?,?,?,?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, tag)
      statement.setString(4, description)
      statement.setString(5, datatype)
      statement.setInt(6, idTb)
      statement.setString(7, filter)
      statement.setString(8, formule)
      statement.setDate(9, date)
      statement.setBoolean(10, true)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {}
    } finally {
      connection.close()
    }
  }

  def insertDatabaseA(id: Int, name: String, location: String, source: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement(s"INSERT INTO db_archived (id, idtable, name, loc, date, sources) VALUES (?,?,?,?,?)")
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

  def insertDatabaseM(id: Int, name: String, location: String, source: Int, date: Date): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement(s"INSERT INTO db_archived (id_db, name, loc, sources, date, modified) VALUES (?,?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setInt(4, source)
      statement.setDate(5, date)
      statement.setBoolean(6, true)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {}
    } finally {
      connection.close()
    }
  }

  def insertTablesA(id: Int, name: String, location: String, date: Date, iddb: Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO tables (id,name, loc, date, iddb) VALUES (?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4, new Date(System.currentTimeMillis()))
      statement.setInt(5, iddb)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {}
    } finally {
      connection.close()
    }
  }

  def insertTablesM(id: Int, name: String, location: String, frequency: String, date: Date, iddb: Int, idprojet:Int): Unit = {
    val connection = getConnectionTry()
    try {
      val statement = connection.prepareStatement("INSERT INTO tables_archived (idTb,name,loc,date,iddb,frequency,idprojet) VALUES (?,?,?,?,?,?,?)")
      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setString(3, location)
      statement.setDate(4,date)
      statement.setInt(5, iddb)
      statement.setString(6,frequency)
      statement.setInt(7,idprojet)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("table exists")
      }
    } finally {
      connection.close()
    }
  }


}
