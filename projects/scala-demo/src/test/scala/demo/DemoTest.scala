package demo

import org.scalatest._
import flatspec._
import matchers._

class DemoTest extends AnyFlatSpec with should.Matchers {
    val dog = Dog("rex")
    
    "A dog " should "bark" in {
        dog.bark shouldBe ("warf!")
    }

    it should "be a dog" in{
        dog shouldBe a [Dog]
    }
}
