package update
//read
import read.metastore.readingMetastore.{readColMetastore, readDbMetastore, readTableMetastore}
import read.catalog.readingCatalog.{readColumnsCatalog, readDatabasesCatalog, readTablesCatalog}
import read.catalog.readFromId.{getColumnFromId, getDbFromId, getTableFromId}
// insert
import insert.metastore.insertionMetastore.{insertColumns, insertDatabase, insertDatabaseM, insertTables}
import insert.insertUpdateMetastore.{updateTable,updateDatabase,updateColumn}
// objects
import objects.objectsCatalog.{ColumnC, DatabaseC, TableC}
import objects.objectsMetastore.{Column, Database, Table}
object updateCatalog extends App {

  def updateCatalog(db: List[Database], tables: List[Table], columns: List[Column], dbC: List[DatabaseC], tablesC: List[TableC], columnsC: List[ColumnC]): Unit = {
    // ---------------------------------------------------------------------------------------------------------------
    // Databases
    // ---------------------------------------------------------------------------------------------------------------
    if (dbC.length == 0) {
      for (database <- db) {
        println("list empty, insert directly")
        insertDatabase(database.id,database.name,database.location,database.date)
      }
    } else {
      for (database <- db) {
        for (databaseC <- dbC) {
          if (database.id==databaseC.id && database.name==databaseC.name && database.location == databaseC.location) {
          println("DB OK update")
            // get id

            //insert into archived
            insertDatabaseM(databaseC.id,databaseC.name,databaseC.location,databaseC.sources,databaseC.date)
            // update database with bool true
        }else {
          println("OK DB verified inserted")
            insertDatabase(database.id,database.name,database.location,database.date)
        }
        }
        //println(database.id,database.date,database.name,database.location)
      }
    }
    // ---------------------------------------------------------------------------------------------------------------
    // Tables
    // ---------------------------------------------------------------------------------------------------------------
    if (tablesC.length==0){
      for (table <- tables) {
        println("list tables vide insert directly")
        insertTables(table.id, table.name, table.location, table.date, table.iddb)
      }
    }
    else{
      for (table <- tables) {
        for (tableC <- tablesC) {
          //println(tableC)
          if (table.name==tableC.name && table.location==tableC.location){
            // Update the database
            println("to update")
            // get id
            val tbl = getTableFromId(table.id)
            //insert into archived

            // update database with bool true
          }
          else  {
            println("Inserting new table from metastore")
            insertTables(table.id,table.name,table.location,table.date,table.iddb)
          }
        }
        //println(table.id,table.name,table.location,table.description,table.date,table.iddb,table.date)
      }
    }
    // ---------------------------------------------------------------------------------------------------------------
    // Columns
    // ---------------------------------------------------------------------------------------------------------------
    if (columnsC.length==0) {
      for (column <- columns) {
        println("columns vide insert directly")
        insertColumns(column.id, column.name, column.description, column.dataType, column.idtable)
      }
    } else {
      for (column <- columns) {
        for (columnC <- columnsC) {
          //println(columnC)
          if (column.name==columnC.name && column.id == columnC.id) {
            println("to update")
            // get id
            val col = getColumnFromId(column.id,column.name)

            //insert into archived

            // update database with bool true
          }
          else {
            println("Inserting new column from metastore")
            insertColumns(column.id,column.name,column.description,column.dataType, column.idtable)
          }
        }
      }
    }
  }
  //defining values

  val db = readDbMetastore()
  val tables = readTableMetastore()
  val columns = readColMetastore()

  val dbC = readDatabasesCatalog()
  val tablesC = readTablesCatalog()
  val columnsC = readColumnsCatalog()

  updateCatalog(db,tables,columns,dbC,tablesC, columnsC)

}
