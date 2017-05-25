val seq = Seq(1, 2, 3, 2)

val set = Set(1, 2, 3, 2)

val map = Map('a' -> 1, 'b' -> 2, 'c' -> 3, 'a' -> 11)


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
