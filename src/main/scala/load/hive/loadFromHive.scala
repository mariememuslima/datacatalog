package load.hive
import  connect.connectMetastore.getConnectionMetastore
import objects.objectsMetastore._
import connect.connectHive.getConnection
object loadFromHive extends App {

  def refinedOrTrusted(name:String): Int = {
    var number = 0
    // if (){}
    val text = "Hello, World!"
    name match {
      case r if r.contains("refined") => number=1
      case s if s.contains("trusted") => number=0
      case _ => number=3
    }
    number
  }
  //refinedOrTrusted("red_table")


  //val databases = readDbHive()
  //insertDatabase((databases))

  def getDbId(name:String): Int = {
    val connexion = getConnectionMetastore()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT DB_ID from DBS WHERE NAME='$name'""")
      if (resultSet.next()) {
        id = resultSet.getInt("DB_ID")
      }
    } finally {
      connexion.close()
    }
    id
  }
  def getTablesId(name:String): Int = {
    val connexion = getConnectionMetastore()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT TBL_ID from TBLS WHERE NAME='$name'""")
      if (resultSet.next()) {
        id = resultSet.getInt("TBL_ID")
      }
    } finally {
      connexion.close()
    }
    id
  }
  def getColumnsId(name:String): Unit = {
    val connexion = getConnectionMetastore()
    var id: Int = 0
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery(s"""SELECT DB_ID from COLUMNS_V2 WHERE NAME='$name'""")
      if (resultSet.next()) {
        id = resultSet.getInt("id")
      }
    } finally {
      connexion.close()
    }
    id
  }

  def readfromHive(): Unit = {
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
  /*def readDbHive(): List[Database] = {
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
        database = Database(dbId,name,dbLocationUri,desc):: database
      }
    } finally {
      connexion.close()
    }
    database
  }*/
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
        column = Column(cdId, columnName, "",dataType,comment, 1, integerIdx) :: column
      }
    } finally {
      connexion.close()
    }
    column
  }

  def insertColumns(id:Int, name:String, description:String, datatype:String): Unit = {
    val connection = getConnection()
    try {
      val statement = connection.prepareStatement("INSERT INTO colonnes (id, name, description, datatype) VALUES (?,?,?,?)")
      statement.setInt(1,id)
      statement.setString(2,name)
      statement.setString(3,description)
      statement.setString(4,datatype)
      statement.executeUpdate()
    } catch {
      case foo: java.sql.SQLIntegrityConstraintViolationException => {
        println("db exists")
      }
    } finally {
      connection.close()
    }
  }
  def readTableHive(): List[Table] = {
    val connexion = getConnectionMetastore()
    var table = List.empty[Table]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT TBL_ID,CREATE_TIME,TBLS.DB_ID,LAST_ACCESS_TIME,OWNER,TBLS.OWNER_TYPE,RETENTION,SD_ID,TBL_NAME,TBL_TYPE,DB_LOCATION_URI,NAME FROM TBLS,DBS WHERE TBLS.DB_ID=DBS.DB_ID;")
      while (resultSet.next()) {
        val tableId = resultSet.getInt("TBL_ID")
        val createTime = resultSet.getDate("from_unixtime(CREATE_TIME)")
        val dbId = resultSet.getInt("DB_ID")
        val tableName = resultSet.getString("TBL_NAME")
        val dbLocation = resultSet.getString("DB_LOCATION_URI")
        table=Table(tableId,tableName,s"$dbLocation/$tableName","  ",dbId,createTime)::table
      }
    } finally {
      connexion.close()
    }
    // println(table)
    table
  }

}
