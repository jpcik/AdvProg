
val thing = new {}
thing.toString

// we import the Show trait
import cats.Show


// Show is meant to be a better toString
// We can use it for different types, like Int
val showInt = Show.apply[Int]
showInt.show(4)

Show[Int].show(4)

// Or with Boolean
val showBool = Show.apply[Boolean]
showBool.show(true)


// We can import some syntax sugar ro make it easier to use
import cats.syntax.show.toShow
7.show
4.show
true.show
List(4,5,6).show

// It is also possible to modify the behavior of show 
{
  // Here using this contextual given, we can do the modificaiton for Booleans
  given Show[Boolean] with 
    def show(b: Boolean): String = b match
      case true => "vrai"
      case false => "faux"

  false.show
}

// There is a simplified syntax, using the Show trait and a function
given Show[Double] = Show.show(d => s"$d*10^0")

34.5.show

// And we can do it also for our custom classes
case class Student(name:String, age:Int)

given Show[Student] = Show.show(s => s"${s.name}, ${s.age} years old")

val will = Student("will",21)
will.show





