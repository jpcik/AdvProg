import cats.Monoid

Monoid.combine("hey","you")

Monoid.combine(23,21)

Monoid.combine(-23.4,-35.4)

Monoid[Int].empty

Monoid.combineAll(List(3,4,5,6))

import cats.Semigroup

Semigroup.combine("something ","in the way")

val exam1=Option(3)
val exam2=Option(5)

Monoid.combine(exam1,exam2)

import cats.syntax.semigroup._

1 |+| 2

//true |+| false

{
  given Semigroup[Boolean] with
    def combine(a:Boolean,b:Boolean) = a && b

  true |+| false

  true |+| (true |+| false)

  (true |+| true) |+| false
}

val grades1stYear = (2d,2.5,1d)
val grades2ndYear = (3d,1.5,2.5)

grades1stYear |+| grades2ndYear

case class Grade(value:Double)

{
given Monoid[Grade] with
  def combine(a:Grade,b:Grade) = Grade((a.value+b.value)/2)
  def empty = Grade(0)


Grade(3) |+| Grade(5)

Grade(3) |+| (Grade(2) |+| Grade(5))

(Grade(3) |+| Grade(2)) |+| Grade(5)
}


given Monoid[Grade] with
  def combine(a:Grade,b:Grade) = if a.value > b.value then a else b 
  def empty = Grade(0)


Grade(3) |+| (Grade(2) |+| Grade(5))

(Grade(3) |+| Grade(2)) |+| Grade(5)


val grades = List(4,5.5,3.0,4,2,5).map(Grade(_))


grades.foldRight(Monoid[Grade].empty)(_ |+| _)


Monoid[Grade].combineAll(grades)


val opGrade1 = Option(Grade(5))
val opGrade3 = None
val opGrade2 = Option(Grade(3))

opGrade1 |+| opGrade2 |+| opGrade3



Monoid[Map[String, Int]].combineAll(List(Map("a" -> 1, "b" -> 2), Map("a" -> 3)))


Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6))

Semigroup[Int => Int].combine(_ + 1, _ * 10).apply(6)

val aMap = Map("foo" -> Map("bar" -> 5))
val anotherMap = Map("foo" -> Map("bar" -> 6))
val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(aMap, anotherMap)

combinedMap.get("foo")

