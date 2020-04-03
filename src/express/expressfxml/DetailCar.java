package express.expressfxml;

public class DetailCar {
    String countCar,date,ticket,price;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getCountCar() {
        return countCar;
    }

    public void setCountCar(String countCar) {
        this.countCar = countCar;
    }

    public DetailCar(String date, String ticket, String price) {
        this.date = date;
        this.ticket = ticket;
        this.price = price;
    }

    public DetailCar(String countCar, String date, String ticket, String price) {
        this.countCar = countCar;
        this.date = date;
        this.ticket = ticket;
        this.price = price;
    }
}
