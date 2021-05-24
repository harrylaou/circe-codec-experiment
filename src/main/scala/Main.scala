object Main extends App {

  def time[R](name: String, block: => R): R = {
    println(s"Staring $name")
    val t0     = System.nanoTime()
    val result = block // call-by-name
    val t1     = System.nanoTime()
    println(s"Elapsed time for $name: " + (t1 - t0).toDouble / 1000000000 + "sec")
    result
  }

  val users = Random.users

  (0 until 10).foreach { _ =>
    {
      time("Automatic Derivation", ParserAutomatic.run(users))

      time("SemiAutomatic Derivation", ParserSemiAutomatic.run(users))
    }
  }
}
