package by.epamtc.jwd.auth.model.visit_info;

import by.epamtc.jwd.auth.model.user_info.TransportationStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class AdmissionDepartmentVisit implements java.io.Serializable {
    private static final long serialVersionUID = 7047326943363788423L;

    private int visitId;
    private LocalDateTime visitDateTime;
    private int patientId;
    private String patientShortInfo;
    private VisitReason visitReason;
    private String patientVisitDescriptionInfo;
    private int responsibleDoctorId;
    private String responsibleDoctorInfo;
    private TransportationStatus transportationStatus;
    private int responsibleNonDoctorStaffId;
    private String responsibleNonDoctorStaffInfo;
    private String patientComplaints;
    private VisitResult visitResult;
    private int hospitalizationDepartmentId;
    private String hospitalizationDepartmentInfo;
    private boolean isPrescriptionsComplete;

    public AdmissionDepartmentVisit(int visitId, LocalDateTime visitDateTime,
            int patientId, String patientShortInfo, VisitReason visitReason,
            String patientVisitDescriptionInfo, int responsibleDoctorId,
            String responsibleDoctorInfo, TransportationStatus transportationStatus,
            int responsibleNonDoctorStaffId, String responsibleNonDoctorStaffInfo,
            String patientComplaints, VisitResult visitResult,
            int hospitalizationDepartmentId, String hospitalizationDepartmentInfo,
            boolean isPrescriptionsComplete) {
        this.visitId = visitId;
        this.visitDateTime = visitDateTime;
        this.patientId = patientId;
        this.patientShortInfo = patientShortInfo;
        this.visitReason = visitReason;
        this.patientVisitDescriptionInfo = patientVisitDescriptionInfo;
        this.responsibleDoctorId = responsibleDoctorId;
        this.responsibleDoctorInfo = responsibleDoctorInfo;
        this.transportationStatus = transportationStatus;
        this.responsibleNonDoctorStaffId = responsibleNonDoctorStaffId;
        this.responsibleNonDoctorStaffInfo = responsibleNonDoctorStaffInfo;
        this.patientComplaints = patientComplaints;
        this.visitResult = visitResult;
        this.hospitalizationDepartmentId = hospitalizationDepartmentId;
        this.hospitalizationDepartmentInfo = hospitalizationDepartmentInfo;
        this.isPrescriptionsComplete = isPrescriptionsComplete;
    }

    public AdmissionDepartmentVisit() {
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
    }

    public void setVisitDateTime(LocalDateTime visitDateTime) {
        this.visitDateTime = visitDateTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientShortInfo() {
        return patientShortInfo;
    }

    public void setPatientShortInfo(String patientShortInfo) {
        this.patientShortInfo = patientShortInfo;
    }

    public VisitReason getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(VisitReason visitReason) {
        this.visitReason = visitReason;
    }

    public String getPatientVisitDescriptionInfo() {
        return patientVisitDescriptionInfo;
    }

    public void setPatientVisitDescriptionInfo(String patientVisitDescriptionInfo) {
        this.patientVisitDescriptionInfo = patientVisitDescriptionInfo;
    }

    public int getResponsibleDoctorId() {
        return responsibleDoctorId;
    }

    public void setResponsibleDoctorId(int responsibleDoctorId) {
        this.responsibleDoctorId = responsibleDoctorId;
    }

    public String getResponsibleDoctorInfo() {
        return responsibleDoctorInfo;
    }

    public void setResponsibleDoctorInfo(String responsibleDoctorInfo) {
        this.responsibleDoctorInfo = responsibleDoctorInfo;
    }

    public TransportationStatus getTransportationStatus() {
        return transportationStatus;
    }

    public void setTransportationStatus(TransportationStatus transportationStatus) {
        this.transportationStatus = transportationStatus;
    }

    public int getResponsibleNonDoctorStaffId() {
        return responsibleNonDoctorStaffId;
    }

    public void setResponsibleNonDoctorStaffId(int responsibleNonDoctorStaffId) {
        this.responsibleNonDoctorStaffId = responsibleNonDoctorStaffId;
    }

    public String getResponsibleNonDoctorStaffInfo() {
        return responsibleNonDoctorStaffInfo;
    }

    public void setResponsibleNonDoctorStaffInfo(String responsibleNonDoctorStaffInfo) {
        this.responsibleNonDoctorStaffInfo = responsibleNonDoctorStaffInfo;
    }

    public String getPatientComplaints() {
        return patientComplaints;
    }

    public void setPatientComplaints(String patientComplaints) {
        this.patientComplaints = patientComplaints;
    }

    public VisitResult getVisitResult() {
        return visitResult;
    }

    public void setVisitResult(VisitResult visitResult) {
        this.visitResult = visitResult;
    }

    public int getHospitalizationDepartmentId() {
        return hospitalizationDepartmentId;
    }

    public void setHospitalizationDepartmentId(int hospitalizationDepartmentId) {
        this.hospitalizationDepartmentId = hospitalizationDepartmentId;
    }

    public String getHospitalizationDepartmentInfo() {
        return hospitalizationDepartmentInfo;
    }

    public void setHospitalizationDepartmentInfo(String hospitalizationDepartmentInfo) {
        this.hospitalizationDepartmentInfo = hospitalizationDepartmentInfo;
    }


    public boolean isPrescriptionsComplete() {
        return isPrescriptionsComplete;
    }

    public void setPrescriptionsComplete(boolean prescriptionsComplete) {
        isPrescriptionsComplete = prescriptionsComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdmissionDepartmentVisit that = (AdmissionDepartmentVisit) o;

        if (visitId != that.visitId) {
            return false;
        }
        if (patientId != that.patientId) {
            return false;
        }
        if (!Objects.equals(patientShortInfo, that.patientShortInfo)) {
            return false;
        }
        if (responsibleDoctorId != that.responsibleDoctorId) {
            return false;
        }
        if (responsibleNonDoctorStaffId != that.responsibleNonDoctorStaffId) {
            return false;
        }
        if (hospitalizationDepartmentId != that.hospitalizationDepartmentId) {
            return false;
        }
        if (visitDateTime != null ? !visitDateTime.equals(that.visitDateTime)
                                  : that.visitDateTime != null) {
            return false;
        }
        if (visitReason != that.visitReason) {
            return false;
        }
        if ((patientVisitDescriptionInfo != null)
            ? !patientVisitDescriptionInfo.equals(that.patientVisitDescriptionInfo)
            : that.patientVisitDescriptionInfo != null) {
            return false;
        }
        if ((responsibleDoctorInfo != null)
            ? !responsibleDoctorInfo.equals(that.responsibleDoctorInfo)
            : that.responsibleDoctorInfo != null) {
            return false;
        }
        if (transportationStatus != that.transportationStatus) {
            return false;
        }
        if (responsibleNonDoctorStaffInfo != null
            ? !responsibleNonDoctorStaffInfo.equals(that.responsibleNonDoctorStaffInfo)
            : that.responsibleNonDoctorStaffInfo != null) {
            return false;
        }
        if (patientComplaints != null
            ? !patientComplaints.equals(that.patientComplaints)
            : that.patientComplaints != null) {
            return false;
        }
        if (visitResult != that.visitResult) {
            return false;
        }

        if (isPrescriptionsComplete != that.isPrescriptionsComplete) {
            return false;
        }

        return (hospitalizationDepartmentInfo != null)
               ? hospitalizationDepartmentInfo.equals(that.hospitalizationDepartmentInfo)
               : that.hospitalizationDepartmentInfo == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + visitId;
        hash = 31 * hash + (visitDateTime != null ? visitDateTime.hashCode() : 0);
        hash = 31 * hash + patientId;
        hash = 31 * hash + (patientShortInfo != null ? patientShortInfo.hashCode()
                                                     : 0);
        hash = 31 * hash + (visitReason != null ? visitReason.hashCode() : 0);
        hash = 31 * hash + (patientVisitDescriptionInfo != null
                            ? patientVisitDescriptionInfo.hashCode()
                            : 0);
        hash = 31 * hash + responsibleDoctorId;
        hash = 31 * hash + (responsibleDoctorInfo != null
                            ? responsibleDoctorInfo.hashCode()
                            : 0);
        hash = 31 * hash + (transportationStatus != null
                            ? transportationStatus.hashCode()
                            : 0);
        hash = 31 * hash + responsibleNonDoctorStaffId;
        hash = 31 * hash + (responsibleNonDoctorStaffInfo != null
                            ? responsibleNonDoctorStaffInfo.hashCode()
                            : 0);
        hash = 31 * hash + (patientComplaints != null
                            ? patientComplaints.hashCode()
                            : 0);
        hash = 31 * hash + (visitResult != null
                            ? visitResult.hashCode()
                            : 0);
        hash = 31 * hash + hospitalizationDepartmentId;
        hash = 31 * hash + (isPrescriptionsComplete ? 0 : 1);
        hash = 31 * hash + (hospitalizationDepartmentInfo != null
                            ? hospitalizationDepartmentInfo.hashCode()
                            : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AdmissionDepartmentVisit{" +
                "visitId=" + visitId +
                ", visitDateTime=" + visitDateTime +
                ", patientId=" + patientId +
                ", patientShortInfo='" + patientShortInfo + '\'' +
                ", visitReason=" + visitReason +
                ", patientVisitDescriptionInfo='" + patientVisitDescriptionInfo + '\'' +
                ", responsibleDoctorId=" + responsibleDoctorId +
                ", responsibleDoctorInfo='" + responsibleDoctorInfo + '\'' +
                ", transportationStatus=" + transportationStatus +
                ", responsibleNonDoctorStaffId=" + responsibleNonDoctorStaffId +
                ", responsibleNonDoctorStaffInfo='" + responsibleNonDoctorStaffInfo + '\'' +
                ", patientComplaints='" + patientComplaints + '\'' +
                ", visitResult=" + visitResult +
                ", hospitalizationDepartmentId=" + hospitalizationDepartmentId +
                ", hospitalizationDepartmentInfo='" + hospitalizationDepartmentInfo + '\'' +
                ", isPrescriptionsComplete=" + isPrescriptionsComplete +
                '}';
    }
}
