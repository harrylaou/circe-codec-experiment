import io.circe.{Decoder, Encoder}
import io.circe.syntax._
import io.circe.parser._

object Parser {

  def run(initial: List[User])(implicit enc: Encoder[User], dec: Decoder[User]): Unit = {
    var count: Long = 0
    val users: List[User] = initial.map(user => {
      val json: String = user.asJson.toString
      count = count + json.length
      parse(json).toOption.get.as[User].toOption.get
    })
    println(s"Total characters:$count for ${users.size}")
  }
}

object ParserAutomatic {
  import io.circe.generic.auto._

  def run(initial: List[User]): Unit = Parser.run(initial)
}

object ParserSemiAutomatic {
  import io.circe._, io.circe.generic.semiauto._
  implicit val encoderId: Encoder[Id] = deriveEncoder[Id]
  implicit val decoderId: Decoder[Id] = deriveDecoder[Id]

  implicit val encoderName: Encoder[Name] = deriveEncoder[Name]
  implicit val decoderName: Decoder[Name] = deriveDecoder[Name]

  implicit val encoderPhone: Encoder[Phone] = deriveEncoder[Phone]
  implicit val decoderPhone: Decoder[Phone] = deriveDecoder[Phone]

  implicit val encoderEmail: Encoder[Email] = deriveEncoder[Email]
  implicit val decoderEmail: Decoder[Email] = deriveDecoder[Email]

  implicit val encoderAddress: Encoder[Address] = deriveEncoder[Address]
  implicit val decoderAddress: Decoder[Address] = deriveDecoder[Address]

  implicit val encoderTag: Encoder[Tag] = deriveEncoder[Tag]
  implicit val decoderTag: Decoder[Tag] = deriveDecoder[Tag]

  implicit val encoderAge: Encoder[Age] = deriveEncoder[Age]
  implicit val decoderAge: Decoder[Age] = deriveDecoder[Age]

  implicit val encoderBalance: Encoder[Balance] = deriveEncoder[Balance]
  implicit val decoderBalance: Decoder[Balance] = deriveDecoder[Balance]

  implicit val encoderPicture: Encoder[Picture] = deriveEncoder[Picture]
  implicit val decoderPicture: Decoder[Picture] = deriveDecoder[Picture]

  implicit val encoder: Encoder[EyeColor] = deriveEncoder[EyeColor]
  implicit val decoder: Decoder[EyeColor] = deriveDecoder[EyeColor]

  implicit val encoderGender: Encoder[Gender] = deriveEncoder[Gender]
  implicit val decoderGender: Decoder[Gender] = deriveDecoder[Gender]

  implicit val encoderCoordinates: Encoder[Coordinates] = deriveEncoder[Coordinates]
  implicit val decoderCoordinates: Decoder[Coordinates] = deriveDecoder[Coordinates]

  implicit val encoderFriends: Encoder[Friend] = deriveEncoder[Friend]
  implicit val decoderFriends: Decoder[Friend] = deriveDecoder[Friend]

  implicit val encoderUser: Encoder[User] = deriveEncoder[User]
  implicit val decoderUser: Decoder[User] = deriveDecoder[User]

  def run(initial: List[User]): Unit = Parser.run(initial)
}
