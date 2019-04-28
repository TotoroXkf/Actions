import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.net.Inet4Address
import java.net.NetworkInterface

fun setupIp() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://api2.bmob.cn/1/classes/IP/PeCc888I")
        .addHeader("Content-Type", "application/json")
        .addHeader("X-Bmob-Application-Id", "2c3e4ae2e6b8e0abd27b500c95876716")
        .addHeader("X-Bmob-REST-API-Key", "8325c5621516619486f70c835a1e3347")
        .put(getBody())
        .build()
    val response = client.newCall(request).execute()
    if (response.isSuccessful) {
        response.body()?.let {
            println("成功上传IP。\n本机ip为${getIp()}")
        }
    }
    response.close()
}

fun getIp(): String {
    // 获得本机的所有网络接口
    val networkInterface = NetworkInterface.getNetworkInterfaces()
    while (networkInterface.hasMoreElements()) {
        val element = networkInterface.nextElement()
        // 获得与该网络接口绑定的 IP 地址，一般只有一个
        val addresses = element.inetAddresses
        while (addresses.hasMoreElements()) {
            val addr = addresses.nextElement()
            if (addr is Inet4Address) { // 只关心 IPv4 地址
                if (element.name.startsWith("wlan")) {
                    return addr.getHostAddress()
                }
            }
        }
    }
    return ""
}

private fun getBody(): RequestBody {
    val jsonObject = JsonObject()
    jsonObject.addProperty("ip", getIp())
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
}