package objects

import java.sql.Date

object objectsFiles {
  // ________________________________________________________________________________________________________________________
  // Defining case class to write on hive tables
  // ________________________________________________________________________________________________________________________

  case class Project(id: Int, name: String, tag: String, description: String, date: Date)

  case class Dr(id: Int, name: String, loc: String, date: Date)

  case class Tr(id: Int, name: String, loc: String, date: Date, idprojet: Int, iddr: Int)

  case class Colonnes(id: Int, name: String, tag: String, description: String, datatype: String, filter: String, formule: String, date: Date, idtr: Int)

  case class Dt(id: Int, name: String, loc: String, date: Date)

  case class Tt(id: Int, name: String, loc: String, date: Date, iddt: Int)

  case class Files(id: Int, name: String, loc: String, date: Date)

  case class Ressources(id: Int, idtrr: Int, idtr: Int, idtt: Int, idfiles: Int, contemporary: String)

  //------------------------------------------------------------------------------------------------------------
  // Defining case class to load from files
  // ________________________________________________________________________________________________________________________

  case class CsvFile(projet: String, bdc: String, tbc: String, sources: String, frequency: String, bds: String, tbs: String)


}
