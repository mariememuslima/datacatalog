package model
object createTry extends App {



  def refinedOrTrusted(name: String): Int = {
    var number = 0
    name match {
      case r if r.contains("refined") => number = 1
      case s if s.contains("trusted") => number = 0
      case _ => number = 2
    }
    number
  }




  def exactMatch(): Unit = {
    //if
  }

}
