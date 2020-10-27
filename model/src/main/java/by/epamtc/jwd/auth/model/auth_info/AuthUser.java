package by.epamtc.jwd.auth.model.auth_info;

public class AuthUser implements java.io.Serializable {
    private static final long serialVersionUID = 5835991888957557124L;

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Role role;
    private int userId;
    private int staffId;

    public AuthUser() {
    }

    public AuthUser(int id, String firstName, String middleName,
            String lastName, Role role, int userId, int staffId) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.role = role;
        this.userId = userId;
        this.staffId = staffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        AuthUser authUser = (AuthUser) o;

        if (id != authUser.id) {
            return false;
        }
        if (((firstName == null) && (authUser.firstName != null))
                || ((firstName != null) && !firstName.equals(authUser.firstName))) {
            return false;
        }
        if (((middleName == null) && (authUser.middleName != null))
                || ((middleName != null) && !middleName.equals(authUser.middleName)))
            return false;
        if (((lastName == null) && (authUser.lastName != null))
                || ((lastName != null) && !lastName.equals(authUser.lastName))) {
            return false;
        }
        if ((role == null && authUser.role != null)
                || (role != null && role != authUser.role)) {
            return false;
        }
        if (userId != authUser.userId) {
            return false;
        }
        return staffId == authUser.staffId;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id;
        hash = 31 * hash + ((firstName == null) ? 0 : firstName.hashCode());
        hash = 31 * hash + ((middleName == null) ? 0 : middleName.hashCode());
        hash = 31 * hash + ((lastName == null) ? 0 : lastName.hashCode());
        hash = 31 * hash + ((role == null) ? 0 : role.hashCode());
        hash = 31 * hash + userId;
        hash = 31 * hash + staffId;
        return hash;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", userId=" + userId +
                ", staffId=" + staffId +
                '}';
    }
}
