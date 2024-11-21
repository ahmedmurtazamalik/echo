package app.studentsocietyapp.model;

public class SocietyMember {
    private int memberId;
    private String role;
    private int societyId;
    private int studentId;
    private String status;

    public SocietyMember(int memberId, String role, int societyId, int studentId, String status) {
        this.memberId = memberId;
        this.role = role;
        this.societyId = societyId;
        this.studentId = studentId;
        this.status = status;
    }

    // Getters and setters
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getSocietyId() { return societyId; }
    public void setSocietyId(int societyId) { this.societyId = societyId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
