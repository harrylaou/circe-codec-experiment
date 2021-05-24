import enumeratum._
import org.scalacheck.{Arbitrary, Gen}

import scala.collection.immutable

case class Id(value: String)    extends AnyVal
case class Name(number: String) extends AnyVal

case class Friend(
  id: Id,
  name: Name
)

case class Phone(value: String) extends AnyVal
case class Email(value: String) extends AnyVal
case class Picture(src: String, name: String, height: Int, width: Int)
case class Balance(value: Double) extends AnyVal
case class Age(value: Int)        extends AnyVal
case class Address(value: String) extends AnyVal
case class Tag(value: String)     extends AnyVal

sealed trait EyeColor extends EnumEntry

object EyeColor extends Enum[EyeColor] {
  val values: immutable.IndexedSeq[EyeColor] = findValues
  case object Blue  extends EyeColor
  case object Brown extends EyeColor
  case object Grey  extends EyeColor
  case object Black extends EyeColor
  case object Green extends EyeColor
}

sealed trait Gender extends EnumEntry

object Gender extends Enum[Gender] {
  val values: immutable.IndexedSeq[Gender] = findValues
  case object M extends Gender
  case object F extends Gender

}
case class Coordinates(latitude: Double, longitude: Double)

case class User(
  _id: Id,
  isActive: Boolean,
  balance: Balance,
  picture: Picture,
  age: Age,
  eyeColor: EyeColor,
  name: Name,
  gender: Gender,
  email: Email,
  phone: Phone,
  address: Address,
  coordinates: Coordinates,
  tags: List[Tag],
  friends: List[Friend]
)

object Random {

  import com.danielasfregola.randomdatagenerator.RandomDataGenerator._
  import Arbitrary._
  implicit val genId: Arbitrary[Id]           = Arbitrary(Gen.alphaNumStr.map(Id))
  implicit val genName: Arbitrary[Name]       = Arbitrary(Gen.alphaNumStr.map(Name))
  implicit val genPhone: Arbitrary[Phone]     = Arbitrary(Gen.alphaNumStr.map(Phone))
  implicit val genEmail: Arbitrary[Email]     = Arbitrary(Gen.alphaNumStr.map(Email))
  implicit val genAddress: Arbitrary[Address] = Arbitrary(Gen.alphaNumStr.map(Address))
  implicit val genTag: Arbitrary[Tag]         = Arbitrary(Gen.alphaNumStr.map(Tag))
  implicit val genAge: Arbitrary[Age]         = Arbitrary(Gen.chooseNum(18, 99).map(Age))
  implicit val genBalance: Arbitrary[Balance] = Arbitrary(Gen.chooseNum[Double](-500, 500).map(Balance))

  implicit val arbPicture: Arbitrary[Picture]   = Arbitrary(Gen.resultOf(Picture))
  implicit val arbEyeColor: Arbitrary[EyeColor] = Arbitrary(Gen.oneOf(EyeColor.values))
  implicit val arbGender: Arbitrary[Gender]     = Arbitrary(Gen.oneOf(Gender.values))

  implicit val arbA: Arbitrary[User] = Arbitrary(Gen.resultOf(User))
  val users: List[User]              = random[User](10000).toList
}
