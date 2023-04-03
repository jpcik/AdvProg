import cats.Monad



val anOpt = Monad[Option].pure(5)

val otherOpt = Monad[Option].flatMap(anOpt)(e => Option(e + 2))

val anotherOpt = Monad[Option].map(otherOpt)(e => 300 + e)

val aList= Monad[List].pure(2)

val otherList = Monad[List].flatMap(List(4,6,2))(e => List(e-1,e,e+1))


Monad[Option].pure(25)

import cats.syntax.applicative._

25.pure[Option]


enum Unit(val code:String) :
  case Kelvin extends Unit("K")
  case Celsius extends Unit("C")
  case Farenheit extends Unit("F")

case class Temperature[T](value:T,unit:Unit=Unit.Celsius):
  override def toString() = s"${value.toString}°${unit.code}"

given Monad[Temperature] with
  def flatMap[A, B](fa: Temperature[A])(f: A => Temperature[B]):Temperature[B] =
    f(fa.value)
    
  def pure[A](x: A): Temperature[A] = Temperature(x)

  def tailRecM[A, B](a: A)(f: A => Temperature[Either[A, B]]): Temperature[B] = ???


Monad[Temperature].pure(34)

34.pure[Temperature]


val temp = Temperature(34)

Monad[Temperature].flatMap(temp)(t => Temperature(t+273.15,Unit.Kelvin))

import cats.syntax.monad._
import cats.syntax.flatMap._
import cats.syntax.functor._

temp.flatMap(e => Temperature(e+273.15,Unit.Kelvin))

val optTemp = Monad[Option] compose Monad[Temperature]

optTemp.pure(None)

optTemp.pure(45)

