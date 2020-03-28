package express.expressfxml;


public class DetailEmployee {

    public DetailEmployee(String id, String name, String duty, String box, String date) {
        this.id = id;
        this.name = name;
        this.duty = duty;
        this.box = box;
        this.date = date;
    }

    public DetailEmployee(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String id,name,duty,box,date;

}
