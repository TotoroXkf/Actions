import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TaskList {
    @Qualifier("spring")
    @Autowired
    private Task spring;

    @Qualifier("android")
    @Autowired
    private Task android;

    public Task getSpring() {
        return spring;
    }

    public Task getAndroid() {
        return android;
    }
}
