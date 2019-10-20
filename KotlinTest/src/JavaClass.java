import java.util.ArrayList;

public class JavaClass {
    void addNewModel(ArrayList<Model> list, Model model) {
        list.add(model);
        // ......
    }

    public static void main(String[] args) {
        ArrayList<Model> list = new ArrayList<>();
        new JavaClass().addNewModel(list, new Model());
    }
}
