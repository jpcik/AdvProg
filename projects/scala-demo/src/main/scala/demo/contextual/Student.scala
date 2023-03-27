package demo.contextual

trait University:
    val name:String

case class Student(name:String, age: Int):
    def study=
        println("studying very hard")

    def resign(reason:String)(using u:University) =
        s"good reason to leave $u.name"

    def obtainDiploma(using c:Country) =
        println(s"diploma in Country $c")


extension (s:Student)
    def applyToUniversity(u:University)=
        println(s"$s.name applied to $u")
        u.name
    
    def isOldEnough = s.age > 18


given Taxable[Student] with
    extension(s:Student) 
        def computeTax = if s.age > 18 then 1000 else 50

given Conversion[Student,Architect] with
    def apply(s:Student) = Architect(s.name,s.age*4000)