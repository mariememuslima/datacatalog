import model.createTables.createTls
import model.dropTable.dropTls
import model.{createTables,dropTable}


object main extends App {
  //------------------------------------------------------------
  // CREATE PART
  //-----------------------------------------------------------
  createTls
  // dropTls
  //

  // revoir les oinsertion par rappport aux modifications et aux archives (date etc)


  //------------------------------------------------------------
  // LOAD FROM METASTORE
  //-----------------------------------------------------------


  /*def loadDatas: Unit = {
    try { // Disclaiming variables
      // case class Database(id: Int, name: String, location: String, description: String)
      val db = readDbHive()
      // case class Table(id: Int, name: String, tag: String, location: String, description: String, iddb: Int, date: Date)
      val tbl = readTableHive()
      // case class Column(id: Int, name: String, tag: String, dataType: String, description: String, idtable: Int, date: Int)
      val col = readColHive()

      // Inserting the databases from the hive metastore
      for (data <- db) {
        insertDatabases(data)
      }
      // Inserting the tables from the hive metastore
      for (table <- tbl) {
        insertTables(table.id, table.name, table.location, table.iddb)
      }
      // Inserting the databases from the hive metastore
      for (column <- col) {
        insertColumns(column.id, column.name, column.description, column.dataType)
      }
      println("Everything's updated")
    }
  }*/

  //------------------------------------------------------------
  // LOAD FROM FILES
  //-----------------------------------------------------------
  //createTls
  //dropTls
  //

  //------------------------------------------------------------
  // UPDATE
  //-----------------------------------------------------------
  //createTls
  //dropTls
  //
}
