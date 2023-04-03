package demo.category


import org.scalatest._
import flatspec._
import matchers._

//import cats.syntax.show._
//import cats.syntax.all._
import cats.Functor

class ContextualTest extends AnyFlatSpec with should.Matchers :
  "Show" should "be available for a class" in {

    
    import cats.Show

    val showInt = Show.apply[Int]



  }

  "Other" should "be available for a class" in {


//    given Functor[Toby] with
//      def map[A, B](fa: Toby[A])(f: A => B): Toby[B]
    


    trait Evaluation[A]:
      val grade:A

    object Evaluation:
      def apply[A](i:A) = new Evaluation[A] {val grade=i}

    //object Invalid extends Evaluation[Int]
      //def map[A,B](f: A => B): Evaluation[B]


    given Functor[Evaluation] with
      def map[A,B](fa: Evaluation[A])(f: A => B): Evaluation[B] = fa.grade match
        case (i:A) => Evaluation(f(i))
        //case _ => ???

    val exam = Evaluation[Int](4)

    import cats.syntax.all.toFunctorOps

    val eval = exam.map(_+1)

    val nada = Evaluation[String]("A")

    val grad:Evaluation[Option[Int]]=Evaluation(Some(4))

    val res = Functor[Evaluation].compose[Option].map(grad)(_*0.5)

    val f = (i:Int) => i*4
    val lista = List(Some(1),None,Some(3))
    val lista2 = Functor[List].compose[Option].map(lista).apply(f)

    val something:Option[String]= None

    val topit = something.map(_.size)

    val getit = Functor[Option].map(something)(_.size)

    println(lista2)
    

    import cats.syntax.all._

    val grade = 4.5
    val newgrade = if grade > 4 then grade.some else none

  }

   

  

