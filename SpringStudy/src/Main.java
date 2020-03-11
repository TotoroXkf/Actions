import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TaskList taskList = context.getBean(TaskList.class);
        System.out.println(taskList.getAndroid().getName());
        System.out.println(taskList.getSpring().getName());
    }
}
