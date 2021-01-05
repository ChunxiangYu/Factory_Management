public class worker {
    private int W_number;
    private String L_name;
    private String F_name;
    private double Salary;
    private int age;
    private int workingYear;
    private String gender;
    private String isRetire;

    public int getW_number() {
        return W_number;
    }

    public void setW_number(int w_number) {
        W_number = w_number;
    }

    public String getL_name() {
        return L_name;
    }

    public void setL_name(String l_name) {
        L_name = l_name;
    }

    public String getF_name() {
        return F_name;
    }

    public void setF_name(String f_name) {
        F_name = f_name;
    }

    public int getWorkingYear() {
        return workingYear;
    }

    public void setWorkingYear(int workingYear) {
        this.workingYear = workingYear;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public String getIsRetire(){
        return isRetire;
    }

    public void setIsRetire(String isRetire){
        this.isRetire = isRetire;
    }


}
