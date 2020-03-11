import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    @Qualifier("spring")
    public Task springTask() {
        Task task = new Task();
        task.setName("Spring");
        return task;
    }

    @Bean
    @Qualifier("android")
    public Task androidTask() {
        Task task = new Task();
        task.setName("Android");
        return task;
    }

    @Bean
    public TaskList taskList() {
        return new TaskList();
    }
}
