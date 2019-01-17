import com.avos.avoscloud.AVOSCloud
import com.avos.avoscloud.AVObject
import java.net.Inet4Address;
import java.net.NetworkInterface;


fun main(args: Array<String>) {
    //todo Android拉取ip
    println("开始初始化")
    leanCloudInit()
    uploadIpAddress(getIp())
    println("初始化结束")

}

