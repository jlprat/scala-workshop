// Partial Function vs Total Function
def total(input: String): String = input.toUpperCase()

val partial: PartialFunction[Int, Int] = {
  case x:Int if x > 0 => -x
}

List(1, 2, 3) collect partial


// Partial Application of Functions

def checkElement[A](elem: A, check: A => Boolean): Boolean = {
  check(elem)
}
checkElement("foo", (elem: String) => elem.size < 5)
checkElement("foo bar", (elem: String) => elem.size < 5)

val checkMaxSize = checkElement(_: String, (elem: String) => elem.size < 5)
checkMaxSize("foo")
checkMaxSize("foo bar")


// Curry to DRY

def checkElem[A](check: A => Boolean)(elem: A): Boolean = {
  check(elem)
}

checkElem((elem: String) => elem.size > 1)("foo")
checkElem((elem: String) => elem.size > 1)("f")

val checkMinSize: String => Boolean = checkElem((elem: String) => elem.size > 1)
checkMinSize("foo")
checkMinSize("f")


// Curry as Dependency Injection

case class Email(from: String, to: String, subject: String, message: String)

trait EmailProvider {
  def sendEmail(email: Email): Boolean
}

object Gmail extends EmailProvider {
  override def sendEmail(email: Email): Boolean = {
    println("Sent via Gmail")
    true
  }
}

object GMX extends EmailProvider {
  override def sendEmail(email: Email): Boolean = {
    println("Sent via GMX")
    true
  }
}


def sendEmail(emailProvider: EmailProvider)(email: Email): Boolean = {
  emailProvider.sendEmail(email)
}


val sendEmailViaGmail: Email => Boolean = sendEmail(Gmail)
val sendEmailViaGMX: Email => Boolean = sendEmail(GMX)

val email = Email("me@mail.com", "you@mail.com", "Hello", "Curry works")

sendEmailViaGmail(email)
sendEmailViaGMX(email)
