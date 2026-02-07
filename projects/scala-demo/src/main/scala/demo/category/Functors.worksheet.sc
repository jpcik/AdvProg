import cats.Functor

Functor[Option].map(Some(2))(_ + 1)

Functor[Option].map(None:Option[Int])(_ + 1)

val funcOpt = Functor[Option]

val funcList = Functor[List]


val functCompose = (funcList compose funcOpt)

val list = List(3,6,4,2).map(Option(_))++List(None)

functCompose.map(list)(_ * 3)


case class Evaluation[A](grade:A,remark:String)


given Functor[Evaluation] with
  def map[A,B](fa: Evaluation[A])(f: A => B) = Evaluation(f(fa.grade),fa.remark)

val e1 = Evaluation(3.5,"not good")

import cats.syntax.functor._

e1.map(_.toInt)

val count_a = (word:String) => word.count(_ == 'a')

val liftedCount_a = Functor[Option].lift(count_a)

liftedCount_a(Some("tralalala"))

liftedCount_a(Some("popopopo"))

val source = List("Avatar", "the", "last","airbender")
val product = Functor[List].fproduct(source)(count_a).toMap

product.get("Avatar").getOrElse(0) 

