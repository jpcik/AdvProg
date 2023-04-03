
import cats.Foldable
import cats.instances.list
import cats.instances.seq
import cats.syntax.option._

val list = (1 to 10).toList

Foldable[List].foldLeft(list,0)(_ - _)

Foldable[List].fold(list)


Foldable[List].foldMap(list)(_.toString)


Foldable[List].forall(list)(_ <= 3)


import cats.syntax.foldable._

list.combineAll

list.foldMap(_.toString)


val comp = (Foldable[List] compose Foldable[Option])

comp.combineAll(List(4.some,5.some))