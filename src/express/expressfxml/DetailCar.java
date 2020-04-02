package express.expressfxml;

public class DetailCar {
    String id,ticket,price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DetailCar(String id, String ticket, String price) {
        this.id = id;
        this.ticket = ticket;
        this.price = price;
    }


}
