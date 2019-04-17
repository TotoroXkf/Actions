import java.io.File
import java.io.FileOutputStream

fun writeToLocal(bytes: ByteArray, number: Int) {
    val dir = File("pictures")
    if (!dir.exists()) {
        dir.mkdir()
    }
    val file = File("pictures/$number.jpg")
    if (!file.exists()) {
        file.createNewFile()
    }
    val fileOutStream = FileOutputStream(file)
    fileOutStream.write(bytes)
    fileOutStream.close()
    println("写入完毕: " + file.path)
}