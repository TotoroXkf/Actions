import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.FileWriter

fun main() {
    val path = "./files"
    val dir = File(path)
    val fileArray = dir.listFiles()!!

    for (file in fileArray) {
        handleFile(file)
    }
}

fun handleFile(file: File) {
    val outPath = "../OutFiles/" + file.name + ".md"
    val newFile = File(outPath)
    file.createNewFile()
    val writer = FileWriter(newFile)

    val reader = FileReader(file)
    reader.forEachLine { line ->
        if (file.startsWith("#")) {

        } else {
        }
    }

}

