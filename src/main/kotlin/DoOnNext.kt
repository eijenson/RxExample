import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class DoOnNext {
    fun run() {
        //example()
        //exampleFlatMap()
        exampleFlatMap2()
    }

    fun example() {
        val list = mutableListOf<String>()
        Observable.create<String> { emitter ->
            emitter.onNext("1")
            emitter.onNext("2")
            emitter.onNext("3")
            emitter.onComplete()
        }.doOnNext {
            println("doOnNext:$it")
            list.add(it)
        }.subscribeBy(
                onError = { println("onError$it") },
                onNext = { println("onNext$it") },
                onComplete = { println("onComplete") }
        )
    }

    fun exampleFlatMap() {
        val list = listOf("One", "Two", "Three", RuntimeException(), "Five")
        println("---FlatMap---")
        Observable
                .fromIterable(list)
                .doOnNext {
                    println("-doOnNext:$it")
                }
                .flatMap {
                    Observable.create<Any> { emitter ->
                        if (it is Throwable) emitter.onError(it) else emitter.onNext("*$it*")
                    }
                }
                .subscribeBy(
                        onError =
                        { println("----onError$it") },
                        onNext =
                        { println("----onNext$it") },
                        onComplete =
                        { println("----onComplete") }
                )
    }


    fun exampleFlatMap2() {
        val list = listOf("One", "Two", "Three", RuntimeException(), "Five")
        println("---FlatMap---")
        Observable
                .fromIterable(list)
                .flatMap { ele ->
                    Observable.create<Any> { emitter ->
                        if (ele is Throwable) emitter.onError(ele) else emitter.onNext("*$ele*")
                    }.doOnNext {
                        println("-doOnNext:$ele")
                    }
                }
                .subscribeBy(
                        onError =
                        { println("----onError$it") },
                        onNext =
                        { println("----onNext$it") },
                        onComplete =
                        { println("----onComplete") }
                )
    }
}