import com.avos.avoscloud.AVOSCloud
import com.avos.avoscloud.AVObject

fun leanCloudInit() {
    val applicationId = "KW6VheWbtqFJLoUJp9FWgNkS-gzGzoHsz"
    val clientKey = "pkdvhhcIr0w0HwLW7amBeFtv"
    val masterKey = "b4LrGwruyu8CSzfgE3QqcVob"

    AVOSCloud.initialize(applicationId, clientKey, masterKey)
    AVOSCloud.setDebugLogEnabled(true);

}

fun uploadIpAddress(ip: String) {
    val objectId = "5c4034cc7565710068a13f4f"

    val ipObject = AVObject.createWithoutData("IP", objectId)
    ipObject.put("IpAddress", ip)
    ipObject.save()
    println("上传成功")
}