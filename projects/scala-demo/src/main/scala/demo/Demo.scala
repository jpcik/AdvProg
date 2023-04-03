package demo

@main def hello() = 
    println("Guten Morgen!")

    import school.Teacher

    val jen = new Teacher("Jen",34)

    extension (t:Teacher)
        def isOld=t.getAge > 20

    println(jen.whoIsThis)

    println(jen.isOld)

    
case class Dog(name:String):
    def bark=s"warf!"
    def sayName=s"my name is $name"

    


@main def moreThings()=
    println("Welcome to my program")
    val d = Dog("charlie")
    println(d.bark)

    def calculateTicketPrice(age:Int) = 
        if age > 18 then 
            Some(25.0)
        else if age > 5 then 
            Some(15.5)
        else 
            None
    


    class Employee(var lastname:String)
    val dupont = Employee("Dupont")
    dupont.lastname="Dupond"

    val output = (1 to 500).map(i=>i*4).filter(i=>i<20).sum
    println(output)

    enum Brand:
        case Mercedes,Peugeot,Tern

    enum EngineType:
        case Diesel, Electric, Hybrid

    trait Vehicle:
        val brand:Brand
        val year:Int
        val passengers:Int
        val cost:Double
        val wheels:Int
        val engine:EngineType

        def computeTax = this match
            case c:Car =>
                if year > 2010 then
                    engine match
                    case EngineType.Hybrid => cost * 0.02
                    case EngineType.Electric => cost * 0.01
                    case EngineType.Diesel => cost * 0.1
                else cost * 0.2
            case b:Bike => cost * 0.01

    case class Car(brand:Brand,year:Int,passengers:Int,cost:Double,engine:EngineType) extends Vehicle:
        val wheels = 4

    case class Bike(brand:Brand,year:Int,passengers:Int,cost:Double,engine:EngineType) extends Vehicle:
        val wheels = 2


    val b = Bike(Brand.Tern,2020,1,5000,EngineType.Electric)
    println(b.computeTax)

