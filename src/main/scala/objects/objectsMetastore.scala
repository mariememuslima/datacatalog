package objects
import java.sql.Date
object objectsMetastore {
  // ________________________________________________________________________________________________________________________
  // Defining case class to read on hive tables
  // ________________________________________________________________________________________________________________________
  case class Database(id: Int, name: String, location: String, description: String, date: Date)

  case class Table(id: Int, name: String, location: String, description: String, iddb: Int, date: Date)

  case class Column(id: Int, name: String, tag: String, dataType: String, description: String, idtable: Int, date: Int)
}
