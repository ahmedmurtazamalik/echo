package app.studentsocietyapp.model;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private String batch;
    private String rollNumber;
    private String phone;
    private int accountId;

    public Student(int studentId, String name, String email, String batch, String rollNumber, String phone, int accountId) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.batch = batch;
        this.rollNumber = rollNumber;
        this.phone = phone;
        this.accountId = accountId;
    }

    // Getters and setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public int getAccountId() { return accountId; }
    public void setId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return name;
    }
}

