import java.net.Inet4Address
import java.net.NetworkInterface

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
    return "xxxx"
}