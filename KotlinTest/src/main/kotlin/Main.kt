import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    postItem(Item("Hello World"))
}

suspend fun postItem(item: Item): PostResult {
    val token = requestToken()
    val post = createPost(token, item)
    val postResult = processPost(post)
    return postResult
}

suspend fun processPost(post: String): PostResult {
    delay(500)
    return PostResult(post)
}

suspend fun createPost(token: String, item: Item): String {
    delay(500)
    return token + item.name
}

suspend fun requestToken(): String {
    delay(500)
    return "Xkf";
}


data class Item(val name: String)

data class PostResult(val post: String)