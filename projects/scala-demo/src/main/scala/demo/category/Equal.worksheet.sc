//import scala.language.strictEquality
(4:Any) == true

case class Doctor(name:String)
case class Nurse(name:String)
val doris = Doctor("doris")
val doris2 = Doctor("Doris")
val nick = Nurse("nick")

doris == nick

val list:List[Any] = List(8,21,6).map(Option(_))

list filter (i => i == 6)

val list2 = List(doris,nick).map(Option(_))
list2.filter(p => p == nick)


import cats.Eq
//import cats.instances.int.

Eq[Int].eqv(123,123)

//Eq[Int].eqv(123,"123")

import cats.syntax.eq._
123 === 123

123 =!= 234

//given Eq[Doctor] = 
  //Eq.instance[Doctor]{(a,b)=>a.name === b.name}

given Eq[Doctor] = 
  Eq.instance[Doctor]{(a,b)=>a.name.toLowerCase === b.name.toLowerCase}

doris === doris2

import cats.syntax.option._

Option(1) === 1.some

//doris === nick

{
  import scala.language.strictEquality
  //doris == nick
}

{
  import scala.language.strictEquality
  
  given CanEqual[Doctor,Nurse] = CanEqual.derived

  doris == nick
  //nick == doris
}
