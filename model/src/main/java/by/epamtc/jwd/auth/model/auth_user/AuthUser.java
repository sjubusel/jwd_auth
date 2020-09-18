package by.epamtc.jwd.auth.model.auth_user;

public class AuthUser {
    private int id;
    private String login;
    private String password;
    private Role role;
    private int staffId;
    private int userId;

    public AuthUser() {
    }

    public AuthUser(int id, String login, String password, Role role, int staffId, int userId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.staffId = staffId;
        this.userId = userId;
    }
    public AuthUser( String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthUser authUser = (AuthUser) o;

        if (id != authUser.id) {
            return false;
        }
        if (staffId != authUser.staffId) {
            return false;
        }
        if (userId != authUser.userId) {
            return false;
        }
        if (!login.equals(authUser.login)) {
            return false;
        }
        if (!password.equals(authUser.password)) {
            return false;
        }
        return role == authUser.role;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (login != null ? login.hashCode() : 0);
        hash = 31 * hash + (password != null ? password.hashCode() : 0);
        hash = 31 * hash + (role != null ? role.hashCode() : 0);
        hash = 31 * hash + staffId;
        hash = 31 * hash + userId;
        return hash;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", staffId=" + staffId +
                ", userId=" + userId +
                '}';
    }
}
