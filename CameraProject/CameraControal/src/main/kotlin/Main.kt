import com.avos.avoscloud.AVOSCloud
import com.avos.avoscloud.AVObject
import java.io.*
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.ServerSocket
import java.net.Socket
import java.util.*


fun main(args: Array<String>) {
    println("开始初始化")
    leanCloudInit()
    uploadIpAddress(getIp())
    println("初始化结束")
    println("等待所有设备连接，收集IP")
    runIpCollectTask()
    dispatchCommand()
    //todo 修复all的设置
}


