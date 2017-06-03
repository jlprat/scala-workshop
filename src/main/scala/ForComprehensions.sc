// Redoing the last exercise
val first = Seq.tabulate(50)(identity)
val second = List(10, 100)

for {
  f <- first
  s <- second
} yield f * s


for {
  f <- first
  if f % 2 == 0
  s <- second
} yield f * s

// equivalent to
first.filter(x => x % 2 == 0).flatMap(f => second.map(s => f * s))


// Works also on other types

case class Person(name: String, surname: String)

def createPerson(maybeName: Option[String], maybeSurname: Option[String]): Option[Person] =
  for {
    name <- maybeName
    surname <- maybeSurname
  } yield Person(name, surname)
}

createPerson(Some("John"), Some("Doe"))
createPerson(Some("John"), None)
createPerson(None, Some("Doe"))
createPerson(None, None)