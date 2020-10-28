package by.epamtc.jwd.auth.model.auth_info;

public enum Role {
    USER(1);

    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
