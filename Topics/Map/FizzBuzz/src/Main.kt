fun iterator(map: Map<String, Int>) {
    // put your code here
    for ((k,v) in map) {
        if (v%3 == 0) {
            println("Fizz")
        } else if (v%5 == 0) {
            println("Buzz")
        } else {
            println("FizzBuzz")
        }
    }
}