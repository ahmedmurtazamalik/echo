package app.studentsocietyapp.persistence;

import java.sql.SQLException;

public abstract class PersistenceHandler {

    public abstract int[] authenticateUser(String username, String password) throws SQLException;
}
