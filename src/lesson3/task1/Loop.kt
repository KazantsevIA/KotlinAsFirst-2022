@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.numberRevert
import lesson1.task1.sqr
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21
fun factorialq(n: Int): Int = if (n < 2) 1 else n * factorialq(n - 1)


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}


/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 1
    var m = n
    while (m / 10 != 0) {
        m /= 10
        count++
    }
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var num1 = 1
    var num2 = 1
    var flag = true
    for (i in 3..n) {
        if (flag) {
            num1 += num2
            flag = false
        } else {
            num2 += num1
            flag = true
        }
    }
    return maxOf(num1, num2)
    //if (n < 3) return 1
    //return fib(n - 1) + fib(n - 2)
}


/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in (n / 2) downTo 2) {
        if (n % i == 0) return i
    }
    return 1
}


/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var num = x
    var count = 0
    while (num != 1) {
        if (num % 2 == 0)
            num /= 2
        else
            num = (3 * num) + 1
        count++
    }
    return count
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val ma = maxOf(n, m)
    val mi = minOf(n, m)
    var gcd = 0
    for (i in mi downTo 1) {
        if (mi % i == 0)
            if (ma % i == 0) {
                gcd = i
                break
            }
    }
    return (m * n) / gcd
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..minOf(n, m)) {
        if (minOf(n, m) % i == 0) {
            if (maxOf(n, m) % i == 0)
                return false
        }
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var result: Long = 0
    while (number != 0) {
        result += number % 10
        number /= 10
        result *= 10
    }
    return (result / 10).toInt()
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = (revert(n) == n)


/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n / 10
    val result = n % 10
    while (number != 0) {
        if (number % 10 != result)
            return true
        else
            number /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var sinus = 0.0
    var pow = x % (2 * PI)
    val xx = pow
    var fact = 1.0
    for (i in (3..Int.MAX_VALUE) step 2) {
        sinus += pow / fact
        if (abs(pow) / fact < eps)
            break
        pow = -(pow * sqr(xx))
        fact *= (i - 1) * i

    }
    return sinus + pow / fact
}


/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var cosin = 1.0
    var pow = -sqr(x % (2 * PI))
    val xx = (x % (2 * PI))
    var fact = 2.0
    for (i in (4..Int.MAX_VALUE) step 2) {
        cosin += pow / fact
        if (abs(pow / fact) < eps)
            break
        pow = -pow * sqr(xx)
        fact *= (i - 1) * i
    }
    return cosin
}


/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun longRevert(n: Long): Long {
    var number = n
    var result: Long = 0
    while (number != 0.0.toLong()) {
        result += number % 10
        number /= 10
        result *= 10
    }
    return (result / 10)
}

fun squareSequenceDigit(n: Int): Int {
    var num: Long = 0
    var count: Int = n
    for (i in 1..Int.MAX_VALUE) {
        num = i.toLong() * i.toLong()
        //flag = 0
        //if (num % 10 == 0.0.toLong()) {
        num = num * 10 + 1
        //    flag = 1
        //}
        num = longRevert(num)
        //println(num)
        while (num != 1.0.toLong()) {
            if (count == 1) {
                return (num % 10).toInt()
            }
            num /= 10
            count--
        }
    }
    return num.toInt()
}


/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun longFib(n: Long): Long {
    var num1: Long = 1
    var num2: Long = 1
    var flag = true
    for (i in 3..n) {
        if (flag) {
            num1 += num2
            flag = false
        } else {
            num2 += num1
            flag = true
        }
    }
    return maxOf(num1, num2)
}

fun fibSequenceDigit(n: Int): Int {
    var num: Long = 0
    var count: Int = n
    for (i in 1..Int.MAX_VALUE) {
        num = longFib(i.toLong())
        num = num * 10 + 1
        num = longRevert(num)
        while (num != 1.0.toLong()) {
            if (count == 1) {
                return (num % 10).toInt()
            }
            num /= 10
            count--
        }
    }
    return num.toInt()
}

fun main(args: Array<String>) {
    /* println(factorialq(0))
     println(factorial(0))
     println(9 / 10)
     println(fib(5))
     println(revert(2147447412).toDouble()) */
    print(lesson3.task1.sin(-18.832102629018816, 1e-10))
    //println(fibSequenceDigit(234))
}