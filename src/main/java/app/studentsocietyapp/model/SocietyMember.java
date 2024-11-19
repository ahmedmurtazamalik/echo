package app.studentsocietyapp.model;

public class SocietyMember {
    private int memberId;
    private String role;
    private int societyId;
    private int studentId;

    public SocietyMember(int memberId, String role, int societyId, int studentId) {
        this.memberId = memberId;
        this.role = role;
        this.societyId = societyId;
        this.studentId = studentId;
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
}
