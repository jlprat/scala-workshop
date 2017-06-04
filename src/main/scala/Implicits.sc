// Implicit Parameters

trait Log {
  def info(msg: String): Unit
  def error(msg: String): Unit
}

class MyLog extends Log {
  override def info(msg: String): Unit = {
    println(s"info> $msg")
  }

  override def error(msg: String): Unit = {
    println(s"error> $msg")
  }
}



def doBusiness(input: String)(implicit log: Log): String = {
  log.info(input)
  // do some business
  input.toUpperCase
}

def doOtherBusiness(input: String)(implicit log: Log): String = {
  // some checks that fail
  log.error(input)
  // do some other business
  input.reverse
}


implicit val log = new MyLog()

doBusiness("hi")
doOtherBusiness("bye")

val otherLog = new Log {
  override def info(msg: String): Unit = {
    println(s"info>|$msg|")
  }

  override def error(msg: String): Unit = {
    println(s"error>|$msg|")
  }
}

// can be also explicitely passed
doBusiness("hi")(otherLog)
doOtherBusiness("ho")(otherLog)
