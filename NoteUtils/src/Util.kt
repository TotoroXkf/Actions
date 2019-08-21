import java.io.*

const val DIFF_MARK = 1
const val DIFF_TITLE = 3
const val DIFF_PARAGRAPH = 5

const val readFilePath = "files/罗马人的故事第一部.md"
val writeFilePath = "new_files/" + readFilePath.substring(readFilePath.indexOf("/") + 1)

var lineNumber = 0
var reader = BufferedReader(InputStreamReader(FileInputStream(readFilePath)))
var writer = BufferedWriter(OutputStreamWriter(FileOutputStream(writeFilePath)))

val markSet = HashSet<Int>()
val titleSet = HashSet<Int>()
val paragraphSet = HashSet<Int>()

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

fun scan(): LinkedHashMap<Int, String> {
    resetReader()

    val result = LinkedHashMap<Int, String>()
    reader.forEachLine { line ->
        lineNumber++
        if (lineNumber <= 6) {
            return@forEachLine
        }
        if (line.length == 1 || line.isEmpty()) {
            return@forEachLine
        }
        result[lineNumber] = line
    }
    return result
}

fun analysis(data: LinkedHashMap<Int, String>) {
    var lastLine = 0
    data.forEach { lineNum, content ->
        if (lastLine == 0) {
            lastLine = lineNum
            return@forEach
        }
        when (lineNum - lastLine - 1) {
            DIFF_MARK -> println()
            DIFF_TITLE -> {
                titleSet.add(lastLine)
                lastLine = lineNum
            }
            DIFF_PARAGRAPH -> println()
        }
    }

    println()
}

fun readLine(): String = reader.readLineAndUpdateLineNumber()

fun writeLine(line: String, newLines: Int = 1) {
    writer.writeLineAndEnter(line, newLines)
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

fun resetReader() {
    reader.close()
    reader = BufferedReader(InputStreamReader(FileInputStream(readFilePath)))
    lineNumber = 0;
}

fun resetWriter() {
    writer.close()
    writer = BufferedWriter(OutputStreamWriter(FileOutputStream(writeFilePath)))
}

fun close() {
    reader.close()
    writer.close()
}