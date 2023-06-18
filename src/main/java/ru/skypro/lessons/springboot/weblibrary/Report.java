package ru.skypro.lessons.springboot.weblibrary;
import jakarta.persistence.*;

@Entity
@Table(name="report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_report;
    private int pos_name;
    private int count_employees;
    private int max_salary;
    private int min_salary;
    private int avg_salary;
    public Report(){

    }

    public Report(int id_report, int pos_name, int count_employees, int max_salary, int min_salary, int avg_salary) {
        this.id_report = id_report;
        this.pos_name = pos_name;
        this.count_employees = count_employees;
        this.max_salary = max_salary;
        this.min_salary = min_salary;
        this.avg_salary = avg_salary;
    }

    public int getId_report() {
        return id_report;
    }

    public void setId_report(int id_report) {
        this.id_report = id_report;
    }

    public int getPos_name() {
        return pos_name;
    }

    public void setPos_name(int pos_name) {
        this.pos_name = pos_name;
    }

    public int getCount_employees() {
        return count_employees;
    }

    public void setCount_employees(int count_employees) {
        this.count_employees = count_employees;
    }

    public int getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(int max_salary) {
        this.max_salary = max_salary;
    }

    public int getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(int min_salary) {
        this.min_salary = min_salary;
    }

    public int getAvg_salary() {
        return avg_salary;
    }

    public void setAvg_salary(int avg_salary) {
        this.avg_salary = avg_salary;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id_report=" + id_report +
                ", pos_name='" + pos_name + '\'' +
                ", count_employees=" + count_employees +
                ", max_salary=" + max_salary +
                ", min_salary=" + min_salary +
                ", avg_salary=" + avg_salary +
                '}';
    }
}
