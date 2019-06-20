package RxTest

import RxTest.entity.convert
import RxTest.entity.exampleUserEntityList
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

class Map {
    fun run() {
        exampleMap()
        exampleUserConvert()
    }

    private val listInt = listOf(0, 1, 2, 3, 4).toObservable()

    private fun exampleMap() {
        println(object {}.javaClass.enclosingMethod.name)
        listInt.map { it * 2 }
                .subscribeBy(
                        onNext = { println("onNext:$it") },
                        onComplete = { println("complete") },
                        onError = { println("onError:$it") }
                )
    }

    private fun exampleUserConvert() {
        println(object {}.javaClass.enclosingMethod.name)
        exampleUserEntityList.forEach { println("before:$it") }
        exampleUserEntityList.map { it.convert() }
                .subscribeBy(
                        onNext = { println("onNext:$it") },
                        onComplete = { println("complete") },
                        onError = { println("onError:$it") }
                )
    }
}