import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        TaskList taskList = (TaskList) context.getBean("taskList");
        System.out.println(taskList.getSpring().getName());
        System.out.println(taskList.getAndroid().getName());
    }
}
