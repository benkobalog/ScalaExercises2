package HKT

trait ToHtml[A] {
  def write(a: A): String
}

object ToHtml {
  def toHtml[A](a: A)(implicit writer: ToHtml[A]): String = {
    writer.write(a)
  }

  def apply[A](fn: A => String): ToHtml[A] = {
    new ToHtml[A]() {
      override def write(a: A): String = fn(a)
    }
  }

  implicit val intHtml: ToHtml[Int] =
    ToHtml(a => s"<int>$a</int>")

  implicit val stringHtml: ToHtml[String] =
    ToHtml(a => s"<string>$a</string>")

  implicit def defaultHtml[A]: ToHtml[A] =
    ToHtml(a => s"<value>$a</value>")

}
