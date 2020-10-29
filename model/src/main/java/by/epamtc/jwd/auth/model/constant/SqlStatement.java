package by.epamtc.jwd.auth.model.constant;

public final class SqlStatement {
    public static final String SELECT_AUTH_USER = "SELECT au.id, p.first_name, " +
            "p.middle_name, p.last_name, aur.auth_user_role_name, " +
            "au.person_id, au.staff_id, au.password " +
            "FROM hospital.auth_user au " +
            "JOIN hospital.persons p ON au.person_id = p.person_id " +
            "JOIN hospital.auth_user_roles aur " +
            "ON au.role_id = aur.auth_user_role_id " +
            "WHERE au.login = ?";

    private SqlStatement() {
    }
}
