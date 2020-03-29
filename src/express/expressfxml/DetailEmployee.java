package express.expressfxml;


public class DetailEmployee  {
    String id,name,phone;

    public DetailEmployee(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public DetailEmployee(){

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
