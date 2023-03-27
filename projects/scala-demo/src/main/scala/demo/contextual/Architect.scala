package demo.contextual

case class Architect(name: String, income:Double)

object Architect:
    def hireArchitect(a:Architect,offer:Double) =
        offer>a.income
        
given Taxable[Architect] with
    extension(a:Architect) def computeTax = a.income * 0.3


