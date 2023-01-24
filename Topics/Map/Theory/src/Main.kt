// You can experiment here, it won’t be checked

fun main(args: Array<String>) {

  val input = readln().split(" ")

  val inputMap = mutableMapOf<String, Int>()

  for (word in input) {
    inputMap[word] = word.length
  }

  println(inputMap)

}
