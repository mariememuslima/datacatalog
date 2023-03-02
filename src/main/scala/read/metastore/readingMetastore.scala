package read.metastore
import connect.connectMetastore.getConnectionMetastore
import objects.objectsMetastore.{Column, Database, Table}
object readingMetastore {
  def readColMetastore(): List[Column] = {
    val connexion = getConnectionMetastore()
    var column = List.empty[Column]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT COLUMNS_V2.*,SDS.SD_ID FROM COLUMNS_V2 JOIN SDS ON COLUMNS_V2.CD_ID=SDS.CD_ID JOIN TBLS ON  TBLS.TBL_ID=SDS.SD_ID;")
      while (resultSet.next()) {
        val cdId = resultSet.getInt("CD_ID")
        val tbId = resultSet.getInt("SDS.SD_ID")
        val comment = resultSet.getString("COMMENT")
        val columnName = resultSet.getString("COLUMN_NAME")
        val dataType = resultSet.getString("TYPE_NAME")
        val integerIdx = resultSet.getInt("SDS.SD_ID")
        column = Column(cdId, columnName, "", dataType, comment,tbId, integerIdx) :: column
      }
    } finally {
      connexion.close()
    }
    column
  }

  def readDbMetastore(): List[Database] = {
    val connexion = getConnectionMetastore()
    var database = List.empty[Database]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("select DBS.DB_ID,DB_LOCATION_URI,NAME,TBL_ID,TBL_NAME,from_unixtime(CREATE_TIME),DBS.DESC from DBS,TBLS WHERE DBS.DB_ID=TBLS.DB_ID;")
      while (resultSet.next()) {
        val dbId = resultSet.getInt("DB_ID")
        val desc = resultSet.getString("DESC")
        val dbLocationUri = resultSet.getString("DB_LOCATION_URI")
        val name = resultSet.getString("NAME")
        val date = resultSet.getDate("from_unixtime(CREATE_TIME)")
        database = Database(dbId, name, dbLocationUri, desc, date) :: database
      }
    } finally {
      connexion.close()
    }
    database
  }

  def readTableMetastore(): List[Table] = {
    val connexion = getConnectionMetastore()
    var table = List.empty[Table]
    try {
      val statement = connexion.createStatement()
      val resultSet = statement.executeQuery("SELECT TBL_ID,from_unixtime(CREATE_TIME),TBLS.DB_ID,LAST_ACCESS_TIME,OWNER,TBLS.OWNER_TYPE,RETENTION,SD_ID,TBL_NAME,TBL_TYPE,DB_LOCATION_URI,NAME FROM TBLS,DBS WHERE TBLS.DB_ID=DBS.DB_ID;")
      while (resultSet.next()) {
        val tableId = resultSet.getInt("TBL_ID")
        val createTime = resultSet.getDate("from_unixtime(CREATE_TIME)")
        val dbId = resultSet.getInt("DB_ID")
        val tableName = resultSet.getString("TBL_NAME")
        val dbLocation = resultSet.getString("DB_LOCATION_URI")
        table = Table(tableId, tableName, s"$dbLocation/$tableName", "  ", dbId, createTime) :: table
      }
    } finally {
      connexion.close()
    }
    // println(table)
    table
  }
}
