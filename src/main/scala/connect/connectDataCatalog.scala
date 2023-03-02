package connect

import java.sql.{Connection, DriverManager}

object connectDataCatalog {
  def getConnectionTry(): Connection = {
    val url = "jdbc:mysql://localhost:3306/try"
    val driver = "com.mysql.cj.jdbc.Driver"
    val user = "marieme"
    val password = "Hive@123"

    Class.forName(driver)
    DriverManager.getConnection(url, user, password)
  }

}
