package app.studentsocietyapp.model;

public class SocietyMemberRow {
    private final String memberName;
    private final String role;
    private final String approvalStatus;

    public SocietyMemberRow(String memberName, String role, String approvalStatus) {
        this.memberName = memberName;
        this.role = role;
        this.approvalStatus = approvalStatus;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getRole() {
        return role;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }
}