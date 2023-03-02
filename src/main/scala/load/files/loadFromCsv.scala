package load.files

import read.metastore.readById.{getTableId,getDbId, getProjectId}
import insert.metastore.insertionMetastore.{insertProject,insertSources}
import insert.metastore.insertUpdates.{updateTableFrequency,updateTableIdProject}

import scala.io.Source

object loadFromCsv extends App {
  case class CsvFile(projet: String, description : String, bdc : String, sources: String,tbc : String, frequency: String, bds: String,tbs : String)
  val path = ("/home/marieme/Téléchargements/All_Refined_Tables.csv")

  def listCsv(path: String): List[CsvFile]={
    var infos = List.empty[CsvFile]
    val source = Source.fromFile(path)
    val lines = source.getLines
    val csvData = lines.map(_.split(",").toList)
    // println("Use Case/Projet,Description,Base de donées cible,Niveau Table source,Table cible,frequence KPI,Base de données Source,Flux/Table source")
    println(csvData)
    var number = 1
    for (i <- csvData) {
      if (number!=1) {
        infos = CsvFile(i(0),i(1),i(2),i(3),i(4),i(5),i(6),i(7))::infos
      }
      number+=1
    }
    infos
  }
  val listToUpdate =  listCsv(path)

  def updateromFile(listToUpdate: List[CsvFile]):Unit = {
    for (info <- listToUpdate){
      // insert project
      insertProject(info.projet)
      // inserting sources
      // get id tables
      var idTbS = getTableId(info.tbs)
      var idTbC = getTableId(info.tbc)
      // insert tables sources
      insertSources(idTbC,idTbS)
      // get id project
      var idProject = getProjectId(info.projet)
      // insert frequency and project
      updateTableFrequency(idTbC,info.frequency)
      updateTableIdProject(idTbC,idProject)

    }
  }

  def insertFile(listToUpdate: List[CsvFile]): Unit = {
    for (i <- listToUpdate) {
      try {
        println(i)
        //val idTb = getTableId(i.tbc)
        //insertTableFreq(idTb, i.frequency)
        // sources
        //val idTbS = getTableId(i.tbs)
        //insertSources(idTb, idTbS)
      }
    }
  }
  insertFile(listToUpdate)

  def insertFileCSv(listToUpdate: List[CsvFile]): Unit = {
    for (i <- listToUpdate) {
      try {
        println(i)
        val idTb = getTableId(i.tbc)
        //insertTableFreq(idTb, i.frequency)
        // sources
        val idTbS = getTableId(i.tbs)
        //insertSources(idTb, idTbS)
      }
    }
  }
  // insertFile(listToUpdate)
}
