// Creating Collections
val seq = Seq(1, 2, 3, 2)

val set = Set(1, 2, 3, 2)

val map = Map('a' -> 1, 'b' -> 2, 'c' -> 3, 'a' -> 11)

// Basic Operations
seq.head
seq.headOption
seq.length
seq.size
seq.isEmpty
seq(0)
seq.apply(0)
seq.sortWith(_ > _)
seq.contains(3)
0 +: seq
seq :+ 0
val other = Seq(5, 6, 7)
other ++ seq


// Functions that keep the general structure of the collection
val numbers = Seq.tabulate(50)(identity)
numbers.filter(x => x % 2 == 1)
numbers.map(x => x * 2)
numbers.take(10)
numbers.drop(10)
numbers.slice(10, 30)
numbers.reverse

def expand(n: Int): Seq[Int] = Seq.fill(n)(n)
numbers.flatMap(expand)


// Functions that transform the collection to something else
numbers.fold(0)((acc, x) => acc + x)
numbers.reduce(_ + _)
numbers.forall(x => x < 50)
numbers.forall(_ > 10)
numbers.exists(_ == 25)
numbers.groupBy(x => x % 2 == 0)
numbers.partition(_ > 25)

val letters = Seq.tabulate(26)(x => ('a' + x).toChar)
numbers.zip(letters)


// Duplicate the elements of a seq a given number of times

def duplicate(seq: Seq[Char], times: Int): Seq[Char] = seq.flatMap(x => List.fill(times)(x))

// Multiply all numbers of one list with all numbers of another one
val first = Seq.tabulate(50)(identity)
val second = List(10, 100)

first.flatMap(f => second.map(s => f * s))


// Streams

val lazyNumbers = (1 to 1000000000).toStream
val eagerNumbers = (1 to 1000000000).toSeq

eagerNumbers.map(_ * 2)
lazyNumbers.map(_ * 2)

// Calculate fibonacci numbers
val fibs: Stream[Long] = 0 #:: 1 #:: fibs.zip(fibs.tail).map { n => n._1 + n._2 }

fibs take 15 foreach println
