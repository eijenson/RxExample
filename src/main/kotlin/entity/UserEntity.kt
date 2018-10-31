package entity

import io.reactivex.rxkotlin.toObservable
import model.User
import model.UserId

data class UserEntity(
        val id: Int,
        val name: String
)

fun UserEntity.convert() = User(UserId(id), name)

val exampleUserEntityList = listOf(
        UserEntity(1, "佐藤"),
        UserEntity(2, "塩田"),
        UserEntity(3, "酢"),
        UserEntity(4, "醤野"),
        UserEntity(5, "味本")
).toObservable()