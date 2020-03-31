package formylove.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.google.gson.Gson
import formylove.base.GithubUpdateBody

object GithubHelper {
    private val shaMap = hashMapOf<String, String>()
    
    /**
     * 解析 Github 文件内容成为 byte 数组
     */
    private fun parseToByte(url: String): ByteArray {
        val response = RetrofitHelper.getGithubService().getFileContent(url).execute()
        if (response.isSuccessful) {
            val body = response.body()!!
            shaMap[url] = body.sha
            return Base64.decode(body.content, Base64.DEFAULT)
        }
        return byteArrayOf()
    }
    
    /**
     * 解析 Github 文件内容成为 Bitmap
     */
    fun parseToBitmap(url: String): Bitmap {
        val byteArray = parseToByte(url)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
    
    /**
     * 解析 Github 文件内容成为对象
     */
    fun <T> parseToObject(url: String, clazz: Class<T>): T {
        val byteArray = parseToByte(url)
        val json = String(byteArray)
        return Gson().fromJson(json, clazz)
    }
    
    /**
     * 更新 Github 的文件内容
     */
    fun <T> updateContent(url: String, data: T, message: String = "api update"): Boolean {
        val json = Gson().toJson(data) ?: return false
        val base64 = Base64.encodeToString(json.toByteArray(), Base64.DEFAULT)
        val sha = shaMap[url] ?: return false
        val githubUpdateBody = GithubUpdateBody(
            message = message,
            content = base64,
            sha = sha
        )
        val response =
            RetrofitHelper.getGithubService().updateFileContent(url, githubUpdateBody).execute()
        if (response.isSuccessful) {
            return true
        }
        return false
    }
}