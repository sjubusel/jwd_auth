package by.epamtc.jwd.auth.model.user_info;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MedicalHistoryPermission implements Serializable {
    private static final long serialVersionUID = -8809373230025191859L;

    private int permissionId;
    private int recipientId;
    private String recipientInfo;
    private LocalDateTime permissionDateTime;
    private LocalDateTime cancellationDateTime;
    private String cancellationDescription;

    public MedicalHistoryPermission() {
    }

    public MedicalHistoryPermission(int permissionId, int recipientId,
            String recipientInfo, LocalDateTime permissionDateTime,
            LocalDateTime cancellationDateTime, String cancellationDescription) {
        this.permissionId = permissionId;
        this.recipientId = recipientId;
        this.recipientInfo = recipientInfo;
        this.permissionDateTime = permissionDateTime;
        this.cancellationDateTime = cancellationDateTime;
        this.cancellationDescription = cancellationDescription;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientInfo() {
        return recipientInfo;
    }

    public void setRecipientInfo(String recipientInfo) {
        this.recipientInfo = recipientInfo;
    }

    public LocalDateTime getPermissionDateTime() {
        return permissionDateTime;
    }

    public void setPermissionDateTime(LocalDateTime permissionDateTime) {
        this.permissionDateTime = permissionDateTime;
    }

    public LocalDateTime getCancellationDateTime() {
        return cancellationDateTime;
    }

    public void setCancellationDateTime(LocalDateTime cancellationDateTime) {
        this.cancellationDateTime = cancellationDateTime;
    }

    public String getCancellationDescription() {
        return cancellationDescription;
    }

    public void setCancellationDescription(String cancellationDescription) {
        this.cancellationDescription = cancellationDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicalHistoryPermission that = (MedicalHistoryPermission) o;

        if (permissionId != that.permissionId) {
            return false;
        }
        if (recipientId != that.recipientId) {
            return false;
        }
        if ((recipientInfo != null) ? !recipientInfo.equals(that.recipientInfo)
                                    : (that.recipientInfo) != null) {
            return false;
        }
        if ((permissionDateTime != null) ? !permissionDateTime.equals(that.permissionDateTime)
                                         : (that.permissionDateTime != null)) {
            return false;
        }
        if (cancellationDateTime != null ? !cancellationDateTime.equals(that.cancellationDateTime)
                                         : that.cancellationDateTime != null) {
            return false;
        }
        return (cancellationDescription != null)
               ? cancellationDescription.equals(that.cancellationDescription)
               : (that.cancellationDescription == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + permissionId;
        hash = 31 * hash + recipientId;
        hash = 31 * hash + ((recipientInfo != null) ? recipientInfo.hashCode()
                                                    : 0);
        hash = 31 * hash + ((permissionDateTime != null) ? permissionDateTime.hashCode()
                                                         : 0);
        hash = 31 * hash + ((cancellationDateTime != null) ? cancellationDateTime.hashCode()
                                                           : 0);
        hash = 31 * hash + ((cancellationDescription != null) ? cancellationDescription.hashCode()
                                                              : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "MedicalHistoryPermission{" +
                "permissionId=" + permissionId +
                ", recipientId=" + recipientId +
                ", recipientInfo='" + recipientInfo + '\'' +
                ", permissionDateTime=" + permissionDateTime +
                ", cancellationDateTime=" + cancellationDateTime +
                ", cancellationDescription='" + cancellationDescription + '\'' +
                '}';
    }
}
