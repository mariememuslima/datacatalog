package read.files
import connect.connectMetastore.getConnectionMetastore
import objects.objectsMetastore.Database
object databases {
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
        database = Database(dbId, name, dbLocationUri, desc) :: database
      }
    } finally {
      connexion.close()
    }
    database
  }*/
}
