package app.studentsocietyapp.model;

public class Account {
    private int accountId;
    private String username;
    private String password;
    private int accountType;

    public Account(int accountId, String username, String password, int accountType) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    // Getters and setters
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getAccountType() { return accountType; }
    public void setAccountType(int accountType) { this.accountType = accountType; }
}

