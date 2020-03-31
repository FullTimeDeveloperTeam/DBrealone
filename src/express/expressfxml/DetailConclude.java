package express.expressfxml;

public class DetailConclude {
    public DetailConclude(String countFour, String countSix, String countTen, String countTicketFour, String countTicketSix, String countTicketTen, String countTicketAll, String priceSum) {
        this.countFour = countFour;
        this.countSix = countSix;
        this.countTen = countTen;
        this.countTicketFour = countTicketFour;
        this.countTicketSix = countTicketSix;
        this.countTicketTen = countTicketTen;
        this.countTicketAll = countTicketAll;
        this.priceSum = priceSum;
    }

    String countFour,countSix,countTen,countTicketFour,countTicketSix,countTicketTen,countTicketAll,priceSum;

    public String getCountFour() {
        return countFour;
    }

    public void setCountFour(String countFour) {
        this.countFour = countFour;
    }

    public String getCountSix() {
        return countSix;
    }

    public void setCountSix(String countSix) {
        this.countSix = countSix;
    }

    public String getCountTen() {
        return countTen;
    }

    public void setCountTen(String countTen) {
        this.countTen = countTen;
    }

    public String getCountTicketFour() {
        return countTicketFour;
    }

    public void setCountTicketFour(String countTicketFour) {
        this.countTicketFour = countTicketFour;
    }

    public String getCountTicketSix() {
        return countTicketSix;
    }

    public void setCountTicketSix(String countTicketSix) {
        this.countTicketSix = countTicketSix;
    }

    public String getCountTicketTen() {
        return countTicketTen;
    }

    public void setCountTicketTen(String countTicketTen) {
        this.countTicketTen = countTicketTen;
    }

    public String getCountTicketAll() {
        return countTicketAll;
    }

    public void setCountTicketAll(String countTicketAll) {
        this.countTicketAll = countTicketAll;
    }

    public String getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(String priceSum) {
        this.priceSum = priceSum;
    }
}
