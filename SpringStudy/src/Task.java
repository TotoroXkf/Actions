import java.util.Date;

public class Task {
    private String taskId;
    private String name;
    private String desc;
    private Date date;
    private String parentListId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParentListId() {
        return parentListId;
    }

    public void setParentListId(String parentListId) {
        this.parentListId = parentListId;
    }
}
