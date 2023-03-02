package read.files
import connect.connectMetastore.getConnectionMetastore
import objects.objectsMetastore.Column

object columns {
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

}
