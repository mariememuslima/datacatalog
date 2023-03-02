package insert

import insert.metastore.insertUpdates._
import objects.objectsCatalog.{ColumnC, DatabaseC, TableC}
import objects.objectsMetastore.{Column, Database, Table}
//import insert.metastore.insertUpdates.{updateDatabaseDescription,updateDatabaseName,updateDatabaseLocation}
import insert.metastore.insertionMetastore.{insertColumnsM, insertDatabaseM, insertTablesM}

object insertUpdateMetastore {

  def updateDatabase(database: Database,databaseC: DatabaseC): Unit = {
    if (database.name!=databaseC.name) {
      // insert into modified
      insertDatabaseM(databaseC.id, databaseC.name, databaseC.location, databaseC.sources, databaseC.date)
      // update in the catalog
      updateDatabaseName(database.id, database.name)
      // change the status
    }
    if (database.description!=databaseC.description) {
      // insert into modified
      insertDatabaseM(databaseC.id, databaseC.name, databaseC.location, databaseC.sources, databaseC.date)
      // update in the catalog
      updateDatabaseDescription(database.id, database.description)
      // change the status
    }
    if (database.location!=databaseC.sources) {
      // insert into modified
      insertDatabaseM(databaseC.id, databaseC.name, databaseC.location, databaseC.sources, databaseC.date)
      // update in the catalog
      updateDatabaseLocation(database.id, database.location)
      // change the status
    }
  }

  def updateTable(table: Table,tableC: TableC): Unit= {
    if (table.name != tableC.name) {
      // insert into modified
      insertTablesM(tableC.id, tableC.name, tableC.location,tableC.frequency,tableC.date,tableC.iddb,0)
      // update in the catalog
        updateTableName(table.id, table.name)
      // change the status
    }
    if (table.location != tableC.location) {
      // insert into modified
      insertTablesM(tableC.id, tableC.name, tableC.location,tableC.frequency,tableC.date,tableC.iddb,0)
      // update in the catalog
      updateTableLocation(table.id, table.location)
      // change the status
  }
  }

  def updateColumn(column: Column,columnC: ColumnC): Unit= {
    if (column.name != columnC.name) {
      // insert into modified
      insertColumnsM(columnC.id, columnC.name, columnC.tag, columnC.description, columnC.filter,columnC.formule,columnC.dataType, columnC.idtable, columnC.date)
      // update in the catalog
      updateColumnName(column.id, column.name)
      // change the status
    }
    if (column.description != columnC.description) {
      // insert into modified
      insertColumnsM(columnC.id, columnC.name, columnC.tag, columnC.description, columnC.filter, columnC.formule, columnC.dataType, columnC.idtable, columnC.date)
      // update in the catalog
      updateColumnDescription(column.id, column.name)
      // change the status
    }
    if (column.dataType != columnC.dataType) {
      // insert into modified
      insertColumnsM(columnC.id, columnC.name, columnC.tag, columnC.description, columnC.filter, columnC.formule, columnC.dataType, columnC.idtable, columnC.date)
      // update in the catalog
      updateColumnDataType(column.id, column.dataType)
      // change the status
    }
  }
  /*databaseC match {
        case r if (r.name!=database.name) => {
          // add to archived
          // modified
        }
        case s if (s.description!=database.description) => insertDatabase(database.id, database.name, database.location, 1)
        case t if (t.location != database.location) => insertDatabase(database.id, database.name, database.location, 1)
        case u if (u.date != database.date) => insertDatabase(database.id, database.name, database.location, 1)
      }*/

}
