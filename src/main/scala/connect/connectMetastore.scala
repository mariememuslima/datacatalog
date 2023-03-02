package connect
import java.sql.{Connection, DriverManager, ResultSet, Statement,SQLException}

object connectMetastore {
  def getConnectionMetastore(): Connection = {
    val url = "jdbc:mysql://localhost:3306/metastore"
    val driver = "com.mysql.cj.jdbc.Driver"
    val user = "marieme"
    val password = "Hive@123"

    Class.forName(driver)
    DriverManager.getConnection(url, user, password)
  }
}
