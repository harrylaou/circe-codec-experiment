object codecs { // scalastyle:ignore

  import scala.language.experimental.macros
  import scala.reflect.macros.blackbox

  import io.circe.generic.extras.Configuration

  type Codec[A] = io.circe.Codec[A]

  implicit val config: Configuration = Configuration.default.withDefaults

  def deriveCodec[T]: Codec[T] = macro deriver_impl[T]

  def deriver_impl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Codec[T]] = {
    import c.universe._
    val T = weakTypeOf[T]

    val tree = q"""io.circe.derivation.deriveCodec[$T]"""

    c.Expr[Codec[T]](tree)

  }

}
