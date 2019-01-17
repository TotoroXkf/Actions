import com.avos.avoscloud.AVOSCloud
import com.avos.avoscloud.AVObject
import java.net.Inet4Address;
import java.net.NetworkInterface;


fun main(args: Array<String>) {
    //todo 上传ip到云端

    // 参数依次为 AppId、AppKey、MasterKey
    AVOSCloud.initialize("KW6VheWbtqFJLoUJp9FWgNkS-gzGzoHsz", "pkdvhhcIr0w0HwLW7amBeFtv", "b4LrGwruyu8CSzfgE3QqcVob");
    AVOSCloud.setDebugLogEnabled(true);
    val test = AVObject("Test")
    test.put("IpAddress", getIp())
    test.save()
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
    return "...."
}