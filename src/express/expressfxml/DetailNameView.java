package express.expressfxml;

public class DetailNameView {

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

    String duty,box,date;

    public DetailNameView( String duty, String box, String date) {
        this.duty = duty;
        this.box = box;
        this.date = date;
    }
}
