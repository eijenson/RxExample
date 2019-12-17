package RxTest/*class LateInit{
    var user:RxTest.User // コンパイルエラー
    var user2:RxTest.User? = null // エラーにはならないけどNullable😵
    lateinit var user3:RxTest.User //Good👍
    lateinit val user4:RxTest.User // valはfinal扱いなので、あとから代入はできないためコンパイルエラー
}*/

class NonNull {
    // lateinit var でNonNull
// lazy でNonNull →ただし、lifecycleに巻き込まれると死ぬ→kotlin Android Extensionsを使おう
// エルビス演算子でスマートキャスト
// let内でNonNull
    val emptyList = kotlin.collections.emptyList<String>()

    fun run() {
        showUser()
        //showUser()
        //onCreate2()
        //onCreate3()
    }

    var user: User? = User("John", 10)

    fun showUser() {
        val a = user?.also {
            val showName = it.name
            val showAge = it.age
            println("名前:$showName 年齢:$showAge")
        } ?: throw Exception()
        print(a)
    }


    fun printUser(key: String, user: User) {
        println("$key: $user")
    }

    fun getIntent(): Intent {
        return Intent()
    }
}

data class User(val name: String, val age: Int)

class Intent {
    val map = mapOf<String, Any?>("name" to "Tom", "age" to 23)
    fun getStringExtra(key: String): String? {
        try {
            return map.get(key) as String?
        } catch (e: ClassCastException) {
            return null
        }
    }
}