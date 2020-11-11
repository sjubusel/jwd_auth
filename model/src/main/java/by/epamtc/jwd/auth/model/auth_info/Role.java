package by.epamtc.jwd.auth.model.auth_info;

public enum Role {
    USER(1),
    REGISTRAR(2),
    ADMISSION_AID(3),
    AID(4),
    ADMISSION_NURSE(5),
    NURSE(6),
    INTERN(7),
    ADMISSION_DOCTOR(8),
    DOCTOR(9),
    ADMISSION_CHIEF_DEPARTMENT(10),
    CHIEF_DEPARTMENT(11),
    ADMIN(12);

    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
