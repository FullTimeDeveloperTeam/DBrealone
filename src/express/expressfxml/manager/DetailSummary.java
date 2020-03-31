package express.expressfxml.manager;

public class DetailSummary {
    public DetailSummary(String id, String duty, String box, String fourWheel, String sixWheel, String tenWheel, String specialWheel, String sum, String date) {
        this.id = id;
        this.duty = duty;
        this.box = box;
        FourWheel = fourWheel;
        SixWheel = sixWheel;
        TenWheel = tenWheel;
        SpecialWheel = specialWheel;
        Sum = sum;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFourWheel() {
        return FourWheel;
    }

    public void setFourWheel(String fourWheel) {
        FourWheel = fourWheel;
    }

    public String getSixWheel() {
        return SixWheel;
    }

    public void setSixWheel(String sixWheel) {
        SixWheel = sixWheel;
    }

    public String getTenWheel() {
        return TenWheel;
    }

    public void setTenWheel(String tenWheel) {
        TenWheel = tenWheel;
    }

    public String getSpecialWheel() {
        return SpecialWheel;
    }

    public void setSpecialWheel(String specialWheel) {
        SpecialWheel = specialWheel;
    }

    public String getSum() {
        return Sum;
    }

    public void setSum(String sum) {
        Sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String id,duty,box,FourWheel,SixWheel,TenWheel,SpecialWheel,Sum,date;
}
