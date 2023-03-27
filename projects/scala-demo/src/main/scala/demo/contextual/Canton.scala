package demo.contextual

trait Country:
    val code:String

case class Canton(name:String)
    
    
object Geography:
    given Country = new Country{val code="CH"}
