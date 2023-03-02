import connect.connectHive.{ getConnectionMetastore,getConnectionTry}
import objects.caseObjects._
import java.sql.Date

object readTable extends App{


  // ________________________________________________________________________________________________________________________
  // Read Tables
  // ________________________________________________________________________________________________________________________
  def readTableHive(): List[Table] = {
    val connexion = getConnectionMetastore()
    var table = List.empty[Table]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT TBL_ID,from_unixtime(CREATE_TIME),TBLS.DB_ID,LAST_ACCESS_TIME,OWNER,TBLS.OWNER_TYPE,RETENTION,SD_ID,TBL_NAME,TBL_TYPE,DB_LOCATION_URI,NAME FROM TBLS,DBS WHERE TBLS.DB_ID=DBS.DB_ID;")
      while (resultSet.next()) {
        val tableId = resultSet.getInt("TBL_ID")
        val createTime = resultSet.getDate("from_unixtime(CREATE_TIME)")
        val dbId = resultSet.getInt("DB_ID")
        val lastAccessTime = resultSet.getInt("LAST_ACCESS_TIME")
        val owner = resultSet.getString("OWNER")
        val ownerType = resultSet.getString("TBLS.OWNER_TYPE")
        val sdId = resultSet.getInt("SD_ID")
        val tableName = resultSet.getString("TBL_NAME")
        val tableType = resultSet.getString("TBL_TYPE")
        val dbLocation = resultSet.getString("DB_LOCATION_URI")
        val nameDb = resultSet.getString("NAME")
        println(s"Information cdId: $tableId, createTime : $createTime, dbId : $dbId, lastAccessTime : $lastAccessTime, owner : $owner, ownerType : $ownerType, sdId : $sdId, tableName : $tableName, tableType: $tableType, dbLocation: $dbLocation/$tableName/, nameDb : $nameDb")
        table = Table(tableId, tableName, "  ", s"$dbLocation/$tableName", "  ", dbId, createTime) :: table
      }
    } finally {
      connexion.close()
    }
    // println(table)
    table
  }
  val tls = readTableHive()
  println(tls)

  def readColHive(): List[Column] = {
    val connexion = getConnectionMetastore()
    var column = List.empty[Column]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM COLUMNS_V2")
      while (resultSet.next()) {
        val cdId = resultSet.getInt("CD_ID")
        val comment = resultSet.getString("COMMENT")
        val columnName = resultSet.getString("COLUMN_NAME")
        val dataType = resultSet.getString("TYPE_NAME")
        val integerIdx = resultSet.getInt("INTEGER_IDX")
        //println(s"Information cdId: $cdId, comment: $comment, columnName: $columnName, typeName : $typeName, integerIdx : $integerIdx")
        column = Column(cdId, columnName, "", dataType, comment, 1, integerIdx) :: column
      }
    } finally {
      connexion.close()
    }
    column
  }
  /*val cl = readColHive()
  println("cl")
  println(cl)*/
  def readDbHive(): List[Database] = {
    val connexion = getConnectionMetastore()
    var database = List.empty[Database]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM DBS")
      while (resultSet.next()) {
        val dbId = resultSet.getInt("DB_ID")
        val desc = resultSet.getString("DESC")
        val dbLocationUri = resultSet.getString("DB_LOCATION_URI")
        val name = resultSet.getString("NAME")
        val ownerName = resultSet.getString("OWNER_NAME")
        val ownerType = resultSet.getString("OWNER_TYPE")
        val ctlgName = resultSet.getString("CTLG_NAME")
        // println(s"Information dbId: $dbId, desc : $desc, dbLocationUri : $dbLocationUri, name : $name, ownerName : $ownerName, ownerType : $ownerType, ctlgName : $ctlgName")
        database = Database(dbId, name, dbLocationUri, desc) :: database
      }
    } finally {
      connexion.close()
    }
    database
  }
  /*val db = readDbHive()
  println("db")
  println(db)*/
  def readFromHive(): Unit = {
    val connexion = getConnectionMetastore()
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT DBS.DB_ID,DB_LOCATION_URI,NAME,TBL_ID,TBL_NAME,COLUMN_NAME,TYPE_NAME,COMMENT FROM DBS JOIN TBLS ON DBS.DB_ID=TBLS.DB_ID JOIN COLUMNS_V2 ON TBLS.TBL_ID=COLUMNS_V2.CD_ID")
      while (resultSet.next()) {
        val dbId = resultSet.getString("DBS.DB_ID")
        val dbLocationUri = resultSet.getString("DB_LOCATION_URI")
        val nameDatabase = resultSet.getString("NAME")
        val tblId = resultSet.getString("TBL_ID")
        val tableName = resultSet.getString("TBL_NAME")
        val columnName = resultSet.getString("COLUMN_NAME")
        val typeCol = resultSet.getString("TYPE_NAME")
        val comment = resultSet.getString("COMMENT")
        println(s"Information dbId: $dbId, nameDatabase: $nameDatabase, tblId: $tblId, tableName : $tableName, column name : $columnName typeCol :$typeCol, comment: $comment, dbLocationUri : $dbLocationUri")
      }
    } finally {
      connexion.close()
    }
  }
  /*println("allDatas")
  readFromHive()*/
  def readDb(): List[Dt] = {
    val connexion = getConnectionTry()
    var dt = List.empty[Dt]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM db")
      while (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val loc = resultSet.getString("loc")
        val date = resultSet.getDate("date")
        val sources = resultSet.getString("sources")
        val archived = resultSet.getBoolean("archived")
        println(s"DT Id: $id, name: $name, description: $loc, date: $date, archived: $archived, sources : $sources")
        dt = Dt(id, name, loc, date) :: dt
      }
    } finally {
      connexion.close()
    }
    dt
  }
  //val readD= readDb()
  def readTt(): List[Tt] = {
    val connexion = getConnectionTry()
    var tt = List.empty[Tt]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM tables")
      while (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val loc = resultSet.getString("loc")
        val date = resultSet.getDate("date")
        val iddb = resultSet.getInt("iddb")
        val projet = resultSet.getInt("idprojet")
        val archived = resultSet.getBoolean("archived")
        println(s"Tt Id: $id, name: $name, description: $loc, date: $date, archived: $archived")
        tt = Tt(id, name, loc, date, iddb) :: tt
      }
    } finally {
      connexion.close()
    }
    tt
  }


  def readColonnes(): List[Colonnes] = {
    val connexion = getConnectionTry()
    var colonnes = List.empty[Colonnes]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM colonnes")
      while (resultSet.next()) {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val tag = resultSet.getString("loc")
        val description = resultSet.getString("date")
        val datatype = resultSet.getString("iddb")
        val filter = resultSet.getString("iddb")
        val formule = resultSet.getString("iddb")
        val date = resultSet.getDate("date")
        val idTables = resultSet.getInt("idtables")
        val archived = resultSet.getBoolean("archived")
        println(s"Tt Id: $id, name: $name, tag: $tag, datatype: $datatype, filter: $filter, formule: $formule, description: $description, date: $date, archived: $archived")
        colonnes = Colonnes(id, name,tag,description,datatype,filter,formule,date,idTables) :: colonnes
      }
    } finally {
      connexion.close()
    }
    colonnes
  }

}
