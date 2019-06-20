package serverWithError.model

import serverWithError.entity.UserApiData
import java.util.*

data class User(
        val id: String,
        val name: String,
        val age: Int,
        val birthDay: Date
)

class UserConverter() {
    fun convert(data: UserApiData): User {
        if (data.id == null) throw IllegalArgumentException("user_id is null")
        return User(
                data.id,
                data.name ?: "",
                data.age?.toIntOrNull() ?: 0,
                parseData(data.birthDay)
        )
    }

    private fun parseData(dateStr: String?): Date {
        return Date() // 省略
    }
}