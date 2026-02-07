import cats.syntax.either._


def computation[T](param:T):Either[String,Double] = param match
  case t:Int => (t*0.5).asRight
  case d:Double => (d*0.5).asRight
  case _ => "Not a good param".asLeft

val either1:Either[String,Int] = Left("4")

val either2:Either[String,Int] = Right(4)


computation("10")

3.asLeft

import cats.syntax.functor._
import cats.syntax.monad._
import cats.syntax.flatMap._
8
val f = computation(10).flatMap(a => a.asRight)

val positive = (i:Int) => i.asRight[String].ensure("Should be positive")(_ > 0)



positive(-3)
positive(2)

123.asRight[String].swap

Either.catchOnly[NumberFormatException]("123a".toInt)

//Either.catchOnly[NumberFormatException](3/0)


45 +3242
