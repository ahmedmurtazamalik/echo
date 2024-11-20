package app.studentsocietyapp.model;

public class Society {
    private int societyId;
    private String name;
    private String email;
    private int members;
    private String description;
    private boolean isApproved;
    private int accountId;

    public Society(int societyId, String name, String email, int members, String description, boolean isApproved, int accountId) {
        this.societyId = societyId;
        this.name = name;
        this.email = email;
        this.members = members;
        this.description = description;
        this.isApproved = isApproved;
        this.accountId = accountId;
    }

    // Getters and setters
    public int getSocietyId() { return societyId; }
    public void setSocietyId(int societyId) { this.societyId = societyId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getMembers() { return members; }
    public void setMembers(int members) { this.members = members; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean approved) { isApproved = approved; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
}
