import java.util.List;

public class Solution {
    public static final String PATH = "files/B组整理(3).xlsx";
    public static final String OUT_PATH = "files/new_file.xlsx";

    public static void main(String[] args) {
        List<List<String>> data = Utils.readExcel(PATH);
        Utils.handleData(data);
        Utils.createNewFile(data, OUT_PATH);
    }
}
