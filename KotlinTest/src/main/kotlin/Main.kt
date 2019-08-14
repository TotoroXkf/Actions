import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.*

fun main() = runBlocking<Unit> {
    postItem(Item("Xkf"))
}

suspend fun postItem(item: Item): PostResult {
    val token = requestToken()
    val post = createPost(token, item)
    val postResult = processPost(post)
    return postResult
}

suspend fun processPost(post: String): PostResult {
    delay(500)
    println("processPost")
    return PostResult(post)
}

suspend fun createPost(token: String, item: Item): String {
    delay(500)
    println("createPost")
    return token + item.name
}

suspend fun requestToken(): String {
    delay(500)
    println("requestToken")
    return "Xkf";
}


data class Item(val name: String)

data class PostResult(val post: String)