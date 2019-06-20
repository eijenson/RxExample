package RxTest

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Single.create
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

class FlatMap {
    fun run() {
        checkError()
        //errorFlatMap()
        //exampleFlatMap()
        // exampleFlatMap2()
    }


    private fun checkError() {
        Single.create<String> {
            if (true) {
                it.onSuccess("aaa")
            } else {
                it.onError(Throwable())
            }
        }
    }

    private val listInt = listOf(0, 1, 2, 3, 4).toObservable()

    private fun errorFlatMap() {
        val nullableInt: Int? = null
        val integer: Int = if (nullableInt == null) {
            println("nullableInt is null")
            return
        } else {
            println("nullableInt is not null")
            nullableInt
        }
        val nullableInt2: Int? = null
        val integer2: Int = nullableInt2 ?: return
        val list = create<String> {
            //it.onSuccess("one")
            it.onError(IllegalArgumentException())
        }

        val completable = Completable.create {
            println("complate1")
            it.onComplete()
        }

        list.flatMapCompletable { completable }
                .subscribeBy(
                        onComplete = { println("complete") },
                        onError = { println("onError:$it") }
                )
    }

    private fun exampleFlatMap() {
        println(object {}.javaClass.enclosingMethod.name)
        listInt.flatMap {
            Observable.just(it * 2)
        }
                .subscribeBy(
                        onNext = { println("onNext:$it") },
                        onComplete = { println("complete") },
                        onError = { println("onError:$it") }
                )
    }

    private fun exampleFlatMap2() {
        println(object {}.javaClass.enclosingMethod.name)
        listInt.flatMap {
            Observable.create<String> { emitter ->
                val listStr = listOf("One", "Two", "Three", "Four", "Five")
                emitter.onNext(listStr[it])
            }
        }
                .subscribeBy(
                        onNext = { println("onNext:$it") },
                        onComplete = { println("complete") },
                        onError = { println("onError:$it") }
                )
    }
}