package by.epamtc.jwd.auth.model.auth_info;

import java.time.LocalDateTime;

public class AuthenticationInfo implements java.io.Serializable {
    private static final long serialVersionUID = -1220320134426424108L;

    private String login;
    private String password;

    public AuthenticationInfo() {
    }

    public AuthenticationInfo(String login, String password) {
        this.login = login;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthenticationInfo authInfo = (AuthenticationInfo) o;

        if (((login == null) && (authInfo.login != null))
                || ((login != null) && !login.equals(authInfo.login))) {
            return false;
        }
        return ((password != null) || (authInfo.password == null))
                && ((password == null) || password.equals(authInfo.password));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (login != null ? login.hashCode() : 0);
        hash = 31 * hash + (password != null ? password.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AuthenticationInfo{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
