import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

class Zip {
    fun run() {
        exampleNormal()
        exampleFailure()
        exampleForceSuccess()
    }

    fun exampleForceSuccess() {
        val success = Single.create<Int> { it.onSuccess(1) }
        val error = Single.create<Int> { it.onError(RuntimeException()) }

        println("---ForceSuccess---")
        Single.zip(listOf(success, error.onErrorReturn { -1 })) { it ->
            it.map { ele ->
                if (ele == -1) null else ele
            }
        }
                .subscribeBy(
                        onSuccess = { println(it) },
                        onError = { println("Error$it") }
                )

    }

    fun exampleFailure() {

        val success = Single.create<Int> { it.onSuccess(1) }
        val error = Single.create<Int> { it.onError(RuntimeException()) }

        println("---Failure---")
        Single.zip(success, error, BiFunction { t1: Int, t2: Int ->
            "" + t1 + t2
        }).subscribeBy(
                onSuccess = { println(it) },
                onError = { println("Error$it") }
        )

    }

    fun exampleNormal() {
        val listInt = listOf(1, 2, 3, 4, 5).toObservable()
        val listStr = listOf("One", "Two", "Three", "Four", "Five").toObservable()

        println("---normal---")
        Observable.zip(listInt, listStr, BiFunction { t1: Int, t2: String ->
            "" + t1 + t2
        }).subscribe {
            println(it)
        }

    }
}