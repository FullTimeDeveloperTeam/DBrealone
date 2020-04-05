package express.expressfxml.manager;

public class DetailSummary {
    String date;
    String id;
    String box;
    String duty;
    String sumVehicle;
    String sumPrice;

    public DetailSummary(String date, String id, String box, String duty, String sumVehicle, String sumPrice) {
        this.date = date;
        this.id = id;
        this.box = box;
        this.duty = duty;
        this.sumVehicle = sumVehicle;
        this.sumPrice = sumPrice;
    }


    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getBox() {
        return box;
    }

    public String getDuty() {
        return duty;
    }

    public String getSumVehicle() {
        return sumVehicle;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setSumVehicle(String sumVehicle) {
        this.sumVehicle = sumVehicle;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }
}
