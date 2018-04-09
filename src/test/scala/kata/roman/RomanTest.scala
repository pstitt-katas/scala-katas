package kata.roman

import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, PropSpec}

class RomanTest extends PropSpec with PropertyChecks with Matchers {

  property("examples") {
    val testCases = Table("examples",
      (1, "I"),
      (10, "X"),
      (11, "XI"),
      (100, "C"),
      (1000, "M"),
      (2, "II"),
      (12, "XII"),
      (22, "XXII"),
      (3, "III"),
      (1003, "MIII"),
      (9, "IX"),
      (5, "V"),
      (50, "L"),
      (5000, "-"),
      (4, "IV"),
      (444, "CDXLIV"),
      (6, "VI"),
      (66, "LXVI"),
      (7, "VII"),
      (776, "DCCLXXVI"),
      (8, "VIII"),
      (884, "DCCCLXXXIV"),
      (1984, "MCMLXXXIV")
    )

    forAll(testCases) { testCase =>
      val decimalValue = testCase._1
      val expected = RomanNumeral(testCase._2)
      val convertedFromDecimal = RomanNumeral(decimalValue)

      convertedFromDecimal shouldBe expected
    }
  }
}

object RomanNumeral {
  def apply(decimalValue: Int) = new RomanNumeral(decimalToRoman(decimalValue))

  def decimalToRoman(decimal: Int): String = {
    val units = placeString(0, decimal)
    val tens = placeString(1, decimal)
    val hundreds = placeString(2, decimal)
    val thousands = placeString(3, decimal)
    s"$thousands$hundreds$tens$units"
  }

  private val UNIT_SYMBOLS = Array("I", "X", "C", "M")
  private val FIVE_SYMBOLS = Array("V", "L", "D")

  private def placeString(powerOfTen: Int, decimal: Int): String = {
    var placeDigit = getPlaceDigit(powerOfTen, decimal)
    var placeTemplate = getPlaceTemplate(placeDigit)

    if (powerOfTen == 0) {
      placeTemplate
    }
    else {
      placeTemplate
        .replaceAll("X", getUnitSymbol(powerOfTen + 1))
        .replaceAll("V", getFiveSymbol(powerOfTen))
        .replaceAll("I", getUnitSymbol(powerOfTen))
    }
  }

  private def getPlaceTemplate(placeDigit: Int): String = {
    placeDigit match {
      case 1 => "I"
      case 2 => "II"
      case 3 => "III"
      case 4 => "IV"
      case 5 => "V"
      case 6 => "VI"
      case 7 => "VII"
      case 8 => "VIII"
      case 9 => "IX"
      case _ => ""
    }
  }

  private def getPlaceDigit(powerOfTen: Int, decimal: Int): Int = {
    scala.math.floor(decimal / scala.math.pow(10, powerOfTen)).toInt % 10
  }

  private def getUnitSymbol(placeUnit: Int): String = {
    if (placeUnit >= UNIT_SYMBOLS.length) {
      "-"
    }
    else {
      UNIT_SYMBOLS(placeUnit)
    }
  }

  private def getFiveSymbol(placeUnit: Int): String = {
    if (placeUnit >= FIVE_SYMBOLS.length) {
      "-"
    }
    else {
      FIVE_SYMBOLS(placeUnit)
    }
  }
}

case class RomanNumeral(value: String)
