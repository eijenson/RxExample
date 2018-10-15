import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class DoOnNext {
    fun run() {
        example()
        exampleFlatMap()
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
        Observable.create<String> { emitter ->
            emitter.onNext("1")
            emitter.onNext("2")
            emitter.onError(RuntimeException())
            emitter.onNext("3")
            emitter.onComplete()
        }.doOnNext {
            println("doOnNext:$it")
        }.flatMap {
            Observable.just("rererere")
        }.subscribeBy(
                onError = { println("onError$it") },
                onNext = { println("onNext$it") },
                onComplete = { println("onComplete") }
        )
    }
}