// Futures

import scala.concurrent.Future
// Does not compile
// Future("foo")


import scala.concurrent.ExecutionContext.Implicits.global

val f = Future("foo")
f.foreach(println)
f.map(_.toUpperCase)
f.foreach(println)
f.map(_.toUpperCase).foreach(println)

// onComplete
import scala.util.{Success, Failure}

f.onComplete{
  case Success(msg) => println(msg)
  case Failure(e) => println(s"Error $e")
}

// Await

import scala.concurrent.Await
import scala.concurrent.duration._

val msg = Await.result(f, 3.millis)
println(msg)


// What if Future is blocking?
import scala.concurrent.blocking

val blockingFuture = Future {
  blocking {
    Thread.sleep(3.seconds.toMillis)
    "Expensive"
  }
}

blockingFuture.onComplete{
  case Success(msg) => println(msg)
  case Failure(e) => println(s"Error $e")
}

// Failing
val failingFuture = Future {
  blocking {
    Thread.sleep(3.seconds.toMillis)
    throw new RuntimeException("Ooops!")
  }
}

failingFuture.onComplete{
  case Success(msg) => println(msg)
  case Failure(e) => println(s"Error $e")
}

// Using Futures in for comprehensions

def giveMeFuture(when: Duration, msg: String): Future[String] = Future {
  blocking {
    Thread.sleep(when.toMillis)
    msg.toUpperCase
  }
}

// Warning! this will NOT trigger both futures in parallel
val composed = for {
  msg1 <- giveMeFuture(3.seconds, "foo")
  msg2 <- giveMeFuture(3.seconds, "bar")
} yield msg1 + msg2

composed.foreach(println)

val f1 = giveMeFuture(3.seconds, "foz")
val f2 = giveMeFuture(3.seconds, "baz")

val otherComposed = for {
  msg1 <- f1
  msg2 <- f2
} yield msg1 + msg2

otherComposed.foreach(println)

// Hacky way!

val hacky = for {
  _ <- Future.unit
  f1 = giveMeFuture(3.seconds, "foobar")
  f2 = giveMeFuture(3.seconds, "fozbaz")
  msg1 <- f1
  msg2 <- f2
} yield msg1 + msg2

hacky.foreach(println)
