package express.expressfxml;

public class DetailNameView {
  /*  public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

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

    String name,duty,box,date;

    public DetailNameView( String duty, String box, String date) {
        //this.name = name;
        this.duty = duty;
        this.box = box;
        this.date = date;
    }
}
