import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LeetCodeFileHandler {
    public static void main(String[] args) throws IOException {
        File file = new File("file/file.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        File newFile = new File("file/newFile.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));

        String line = bufferedReader.readLine();
        while (line != null) {
            if (line.startsWith("面试题")) {
                line = bufferedReader.readLine();
                continue;
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ' ') {
                    bufferedWriter.write(line.substring(0, i));
                    bufferedWriter.newLine();
                    break;
                }
            }
            line = bufferedReader.readLine();
        }

        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
