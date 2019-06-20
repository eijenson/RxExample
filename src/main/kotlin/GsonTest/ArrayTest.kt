package GsonTest

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.*


class ArrayTest {
    fun run() {
        val json = """{result:{array2:["aaa"]}}"""
        val result = Gson().fromJson(json, Result::class.java)

        println(result.result)
        println(result.result.array)
    }


    data class Result(
            @SerializedName("result")
            var result: Result2
    ) {
        data class Result2(
                @SerializedName("array2")
                var array2: List<String>,
                @SerializedName("array")
                var array: List<String> = Arrays.asList()
        )

    }
}


