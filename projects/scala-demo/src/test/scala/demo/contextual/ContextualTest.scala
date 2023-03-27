package demo.contextual

import org.scalatest._
import flatspec._
import matchers._

class ContextualTest extends AnyFlatSpec with should.Matchers :
    val jim = Student("jim",17)
    val hesso = new University{val name="HES-SO"}

    "A student " should "apply to university" in {
        jim.applyToUniversity(hesso) shouldBe ("HES-SO")
    }

    "A child student " should "not be old enough" in {
        jim.isOldEnough shouldBe (false)
    }
  
    given University = hesso 

    val tim = jim.copy(name = "tim")

        
    "students" should "be able to resign" in {
        jim.resign("tired of studying")

        val lily = jim.copy(name="lily")

        lily.resign("too easy")
    }


    "a student" should "obtain the diploma" in {
        import Geography.given

        import Geography.{given,*}

        jim.obtainDiploma
    }

    val dude = Architect("Dude",2000)

    "an architect" should "be taxed at 30%" in {
        dude.computeTax shouldBe (2000*0.3)
    }

    "a young student" should "be taxed at 50" in {
        jim.computeTax shouldBe(50)
    }

    "a list of taxable people" should "be able to compute tax for all" in {
        def computeAll[T:Taxable](people:List[T])=
            people.map(_.computeTax)

        computeAll(List(jim,tim))
    }

    def lengtho(i:Int) = 3
    def square(a:String) = 5
    List(lengtho compose square)

//length o square

    "an student" should "be seen as architect" in {
        Architect.hireArchitect(jim,800)
    }