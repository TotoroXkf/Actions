import java.io.*

const val readFilePath = "files/罗马人的故事第一部.md"
val writeFilePath = "new_files/" + readFilePath.substring(readFilePath.indexOf("/") + 1)

var lineNumber = 1
val inputStream = FileInputStream(readFilePath)
val bufferedReader = BufferedReader(InputStreamReader(inputStream))
val outStream = FileOutputStream(writeFilePath)
var bufferedWriter = BufferedWriter(OutputStreamWriter(outStream))

fun main() {
    createHead()
    createBody()

    bufferedReader.close()
    bufferedWriter.close()
}

fun createHead() {
    //第一行，书名
    var line = readLine()
    writeLine(line, 2)

    //空行
    readLine()

    //第三行，作者
    line = readLine()
    writeLine("**作者：$line**")

    //下面3行无用
    readLine()
    readLine()
    readLine()
}

fun createBody() {

}

fun scan(){
    val result = ArrayList<Int>()
    bufferedReader.readLine()
    result.add(lineNumber)
}

fun readLine(): String = bufferedReader.readLineAndUpdateLineNumber()

fun writeLine(line: String, newLines: Int = 1) {
    bufferedWriter.writeLineAndEnter(line, newLines)
}

fun BufferedWriter.writeLineAndEnter(line: String, newLines: Int = 1) {
    write(line)
    for (count in 0 until newLines) {
        newLine()
    }
}

fun BufferedReader.readLineAndUpdateLineNumber(): String {
    val line = readLine()
    lineNumber++
    return line
}