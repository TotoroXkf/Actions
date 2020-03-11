import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TaskList {
    private Task spring;
    private Task android;

    public Task getSpring() {
        return spring;
    }

    public Task getAndroid() {
        return android;
    }

    @Qualifier("android")
    @Autowired
    public void setAndroid(Task android) {
        this.android = android;
    }

    @Qualifier("spring")
    @Autowired
    public void setSpring(Task spring) {
        this.spring = spring;
    }
}
