package by.epamtc.jwd.auth.model.auth_info;

public enum Role {
    USER(1), REGISTRAR(2), AID(3), NURSE(4), INTERN(5), DOCTOR(6),
    CHIEF_DEPARTMENT(7), ADMIN(8);

    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
