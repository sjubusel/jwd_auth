package by.epamtc.jwd.auth.model.auth_info;

public enum Role {
    USER(1, "ПАЦИЕНТ (ПОЛЬЗОВАТЕЛЬ)"),
    REGISTRAR(2, "МЕДИЦИНСКИЙ РЕГИСТРАТОР"),
    ADMISSION_AID(3, "САНИТАР ПРИЁМНОГО ОТДЕЛЕНИЯ"),
    AID(4, "САНИТАР СТАЦИОНАРНОГО ОТДЕЛЕНИЯ"),
    ADMISSION_NURSE(5, "МЕДИЦИНСКАЯ СЕСТРА ПРИЁМНОГО ОТДЕЛЕНИЯ"),
    NURSE(6, "МЕДИЦИНСКАЯ СЕСТРА СТАЦИОНАРНОГО ОТДЕЛЕНИЯ"),
    INTERN(7, "ВРАЧ-ИНТЕРН"),
    ADMISSION_DOCTOR(8, "ВРАЧ-СПЕЦИАЛИСТ ПРИЁМНОГО ОТДЕЛЕНИЯ"),
    DOCTOR(9, "ВРАЧ-СПЕЦИАЛИСТ СТАЦИОНАРНОГО ОТДЕЛЕНИЯ"),
    ADMISSION_CHIEF_DEPARTMENT(10, "ЗАВЕДУЮЩИЙ ПРИЁМНЫМ ОТДЕЛЕНИЕМ"),
    CHIEF_DEPARTMENT(11, "ЗАВЕДУЮЩИЙ СТАЦИОНАРНЫМ ОТДЕЛЕНИЕМ"),
    ADMIN(12, "АДМИНИСТРАТОР");

    private int roleId;
    private String roleDescription;

    Role(int roleId, String roleDescription) {
        this.roleId = roleId;
        this.roleDescription = roleDescription;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleDescription() {
        return roleDescription;
    }
}
