package express.expressfxml;


public class DetailWorkEmployee {
    String work_id,emp_id,emp_name,work_duty,work_box,work_date;

    public DetailWorkEmployee(String work_id, String emp_id, String emp_name, String work_duty, String work_box, String work_date) {
        this.work_id = work_id;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.work_duty = work_duty;
        this.work_box = work_box;
        this.work_date = work_date;
    }

    public DetailWorkEmployee(){

    }

    public String getWork_id() {
        return work_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getWork_duty() {
        return work_duty;
    }

    public String getWork_box() {
        return work_box;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_id(String work_id) {
        this.work_id = work_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setWork_duty(String work_duty) {
        this.work_duty = work_duty;
    }

    public void setWork_box(String work_box) {
        this.work_box = work_box;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }
}
