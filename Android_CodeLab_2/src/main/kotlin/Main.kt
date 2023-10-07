import java.util.*

fun main(args: Array<String>) {
//    for (str in args){
//        println(str)
//    }

//    val isUnit = println("abc")
//    println(isUnit)

//    val temp = 35
//    val msg = "Today is ${if(temp > 30) "hot asf" else if (temp < 10) "cold asf" else "normal"}"
//    println(msg)

    feedTheFish()

//    val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")
//    val eager = decorations.filter { it[1] == 'l' }
//    println("Eager: $eager")
//    val lazy = decorations.asSequence().filter { it[1] == 'l' }
//    println("Lazy: $lazy")
//    val forceEval = lazy.toList()
//    println("Force Evaluation: $forceEval")

//    val dirtyLevel = 20
//    val waterFilter = { dirty : Int -> dirty / 2}
//    println(waterFilter(dirtyLevel))
}

fun feedTheFish() {
    val day = randomDay()
//    val food = getFishFood(day)
    val food = getFishFood() // default argument
    println ("Today is $day and the fish eat $food")
    if (isMonday(day)) {
        println("Today is Monday")
    } else{
        println("Today is not Monday")
    }
}
fun isMonday(day: String) = day == "Monday"

fun randomDay() : String {
    val week = arrayOf ("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(week.size)]
}

fun getFishFood (day: String = "Monday") : String{
    var food: String? = null
    food = when (day) {
        "Monday" -> "flakes"
        "Tuesday" -> "pellets"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquito"
        "Saturday" -> "lettuce"
        "Sunday" -> "plankton"
        else -> "nothing"
    }
    return food
}
