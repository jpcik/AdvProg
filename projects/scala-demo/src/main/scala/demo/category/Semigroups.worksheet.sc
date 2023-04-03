import cats.Semigroup

import cats.syntax.semigroup._

Semigroup[Int].combine(34,23)

3.combine(4)

Option(4) combine (Option(5))


val f = ((i:Int)=>i+2) combine ((i:Int)=>i*4) 

f(4)


val map1 = Map("GVA"->34,"VD"->12)
val map2 = Map("VS"->45,"VD"->15)

map1 combine map2