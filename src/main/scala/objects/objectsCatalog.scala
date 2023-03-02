package objects
import java.sql.Date
object objectsCatalog {
  case class DatabaseC(id: Integer, name: String, location: String, sources: Integer, description: String, date: Date, archived: Boolean)

  case class TableC(id: Integer, name: String, location: String, frequency: String, iddb: Int, date: Date, archived: Boolean)

  case class ColumnC(id: Int, name: String, tag: String, description: String, dataType: String, filter: String, formule: String, idtable: Int, date: Date, archived: Boolean)

  case class DatabaseCA(id: Integer,idProjet: Integer, name: String, location: String, sources: Integer, description: String, date: Date, archived: Boolean, modified: Boolean)

  case class TableCA(id: Integer, idTable: Integer, name: String, location: String, frequency: String, iddb: Int, date: Date, archived: Boolean, modified: Boolean)

  case class ColumnCA(id: Int, idColumn: Integer, name: String, tag: String, description: String, dataType: String, filter: String, formule: String, idtable: Int, date: Int, archived: Boolean, modified: Boolean)
}
