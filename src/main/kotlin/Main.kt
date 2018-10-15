import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val listInt = listOf(1, 2, 3, 4, 5).toObservable()
    val listStr = listOf("One", "Two", "Three", "Four", "Five").toObservable()
    val listInt2 = listOf(-1, -2, -3, -4, -5).toObservable()
    val list = listOf(listOf('a', 'b', 'c', 'd', null)).toObservable()


    val success = Single.create<Int> {
        it.onSuccess(1)
    }
    val error = Single.create<Int> {
        it.onError(RuntimeException())
    }

    val error2 = Single.create<Int> {
        it.onError(RuntimeException())
    }.onErrorReturn { -1 }

    println("---1---")

    Single.zip(listOf(success, error2)) {it ->
        it.map { ele ->
            if(ele == -1) null else ele
        }
    }
            .subscribeBy(
                    onSuccess = { println(it) },
                    onError = { println("Error$it") }
            )

    println("---2---")
    Single.zip(success, error, BiFunction { t1: Int, t2: Int ->
        "" + t1 + t2
    }).subscribeBy(
            onSuccess = { println(it) },
            onError = { println("Error$it") }
    )

    println("---3---")
    Observable.zip(listInt2, list, BiFunction { t1: Int, t2: List<Char?> ->
        "" + t1 + t2
    }).subscribe {
        println(it)
    }


    println("---4---")
    Observable.zip(listInt, listStr, BiFunction { t1: Int, t2: String ->
        "" + t1 + t2
    }).subscribe {
        println(it)
    }
    print("Hellp world")
}
