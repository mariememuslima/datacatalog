package model
import connect.connectDataCatalog.getConnectionTry
object createTables extends App{
  def createTls ={
    val connection = getConnectionTry()
    try {
      val statement = connection.createStatement

      // Creating Tables

      statement.executeUpdate("CREATE TABLE IF NOT EXISTS projet (id INT AUTO_INCREMENT PRIMARY KEY, name varchar(255) UNIQUE, description text, date date, archived bool DEFAULT False)")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS db_db (id INT PRIMARY KEY, name varchar(255) UNIQUE, description varchar(255), loc varchar(255), sources INT, date date, archived bool DEFAULT False)")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS tables (id INT PRIMARY KEY, name varchar(255) UNIQUE, tag varchar(255), loc varchar(255), frequency varchar(50), date date, archived bool DEFAULT False, iddb integer REFERENCES db (id), idprojet int REFERENCES projet(id))")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS colonnes (id INT, name varchar(255), tag varchar(255), description varchar(255), datatype varchar(255), filter varchar(255), formule varchar(255), date date, archived bool DEFAULT False, idtables int REFERENCES tables (id))")
      statement.executeUpdate("ALTER TABLE colonnes ADD PRIMARY KEY (id, name)")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS sources (idTable INT, idSource INT)")

      // Creating Tables archived

      statement.executeUpdate("CREATE TABLE IF NOT EXISTS projet_archived (id INT AUTO_INCREMENT PRIMARY KEY, name varchar(255) UNIQUE, id_projet Integer REFERENCES projet(id), tag varchar(255), description text, date date, archived bool DEFAULT False, modified bool DEFAULT False)")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS db_archived (id INT AUTO_INCREMENT PRIMARY KEY, id_db integer REFERENCES db(id), name varchar(255) UNIQUE, loc varchar(255), sources INT, date date, archived bool DEFAULT False, modified bool DEFAULT False)")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS tables_archived (id INT AUTO_INCREMENT PRIMARY KEY, idTb INT  REFERENCES tables (id), name varchar(255) UNIQUE, loc varchar(255), tag varchar(255), frequency varchar(50), date date, archived bool DEFAULT False, modified bool DEFAULT False, iddb integer REFERENCES db_archived (id), idprojet int REFERENCES projet_archived(id))")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS colonnes_archived (id INT AUTO_INCREMENT PRIMARY KEY, id_colonnes INT, name varchar(255), tag varchar(255), description varchar(255), datatype varchar(255), filter varchar(255), formule varchar(255), date date, archived bool DEFAULT False, modified bool DEFAULT False, idtables int REFERENCES tables_archived (id))")
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS sources_archived (id INT, idTable INT, idSource INT)")
      println("Tables created")
    } finally {
      connection.close()
    }
  }
  createTls
}