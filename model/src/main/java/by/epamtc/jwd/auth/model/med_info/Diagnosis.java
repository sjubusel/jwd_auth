package by.epamtc.jwd.auth.model.med_info;

import java.time.LocalDateTime;

public class Diagnosis implements java.io.Serializable {
    private static final long serialVersionUID = 4833216655758359536L;

    private DiagnosisOrigin diagnosisOrigin;
    private int diagnosisId;
    private LocalDateTime diagnosisDateTime;
    private int diseaseId;
    private String diseaseInfo;
    private String diagnosisDescription;
    private int diagnoseDoctorId;
    private String diagnoseDoctorInfo;
    private LocalDateTime cancellationDateTime;
    private int cancellationDoctorId;
    private String cancellationDoctorInfo;
    private String cancellationDiagnosisDescription;

    public Diagnosis(DiagnosisOrigin diagnosisOrigin, int diagnosisId,
            LocalDateTime diagnosisDateTime, int diseaseId, String diseaseInfo,
            String diagnosisDescription, int diagnoseDoctorId,
            String diagnoseDoctorInfo, LocalDateTime cancellationDateTime,
            int cancellationDoctorId, String cancellationDoctorInfo,
            String cancellationDiagnosisDescription) {
        this.diagnosisOrigin = diagnosisOrigin;
        this.diagnosisId = diagnosisId;
        this.diagnosisDateTime = diagnosisDateTime;
        this.diseaseId = diseaseId;
        this.diseaseInfo = diseaseInfo;
        this.diagnosisDescription = diagnosisDescription;
        this.diagnoseDoctorId = diagnoseDoctorId;
        this.diagnoseDoctorInfo = diagnoseDoctorInfo;
        this.cancellationDateTime = cancellationDateTime;
        this.cancellationDoctorId = cancellationDoctorId;
        this.cancellationDoctorInfo = cancellationDoctorInfo;
        this.cancellationDiagnosisDescription = cancellationDiagnosisDescription;
    }

    public Diagnosis() {
    }

    public DiagnosisOrigin getDiagnosisOrigin() {
        return diagnosisOrigin;
    }

    public void setDiagnosisOrigin(DiagnosisOrigin diagnosisOrigin) {
        this.diagnosisOrigin = diagnosisOrigin;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public LocalDateTime getDiagnosisDateTime() {
        return diagnosisDateTime;
    }

    public void setDiagnosisDateTime(LocalDateTime diagnosisDateTime) {
        this.diagnosisDateTime = diagnosisDateTime;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseInfo() {
        return diseaseInfo;
    }

    public void setDiseaseInfo(String diseaseInfo) {
        this.diseaseInfo = diseaseInfo;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public int getDiagnoseDoctorId() {
        return diagnoseDoctorId;
    }

    public void setDiagnoseDoctorId(int diagnoseDoctorId) {
        this.diagnoseDoctorId = diagnoseDoctorId;
    }

    public String getDiagnoseDoctorInfo() {
        return diagnoseDoctorInfo;
    }

    public void setDiagnoseDoctorInfo(String diagnoseDoctorInfo) {
        this.diagnoseDoctorInfo = diagnoseDoctorInfo;
    }

    public LocalDateTime getCancellationDateTime() {
        return cancellationDateTime;
    }

    public void setCancellationDateTime(LocalDateTime cancellationDateTime) {
        this.cancellationDateTime = cancellationDateTime;
    }

    public int getCancellationDoctorId() {
        return cancellationDoctorId;
    }

    public void setCancellationDoctorId(int cancellationDoctorId) {
        this.cancellationDoctorId = cancellationDoctorId;
    }

    public String getCancellationDoctorInfo() {
        return cancellationDoctorInfo;
    }

    public void setCancellationDoctorInfo(String cancellationDoctorInfo) {
        this.cancellationDoctorInfo = cancellationDoctorInfo;
    }

    public String getCancellationDiagnosisDescription() {
        return cancellationDiagnosisDescription;
    }

    public void setCancellationDiagnosisDescription(String cancellationDiagnosisDescription) {
        this.cancellationDiagnosisDescription = cancellationDiagnosisDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Diagnosis diagnosis = (Diagnosis) o;

        if (diagnosisId != diagnosis.diagnosisId) {
            return false;
        }
        if (diseaseId != diagnosis.diseaseId) {
            return false;
        }
        if (diagnoseDoctorId != diagnosis.diagnoseDoctorId) {
            return false;
        }
        if (cancellationDoctorId != diagnosis.cancellationDoctorId)
            return false;
        if (diagnosisOrigin != diagnosis.diagnosisOrigin) {
            return false;
        }
        if ((diagnosisDateTime != null)
            ? !diagnosisDateTime.equals(diagnosis.diagnosisDateTime)
            : (diagnosis.diagnosisDateTime != null)) {
            return false;
        }
        if ((diseaseInfo != null) ? !diseaseInfo.equals(diagnosis.diseaseInfo)
                                  : (diagnosis.diseaseInfo != null)) {
            return false;
        }
        if ((diagnosisDescription != null)
            ? !diagnosisDescription.equals(diagnosis.diagnosisDescription)
            : (diagnosis.diagnosisDescription != null)) {
            return false;
        }
        if ((diagnoseDoctorInfo != null)
            ? !diagnoseDoctorInfo.equals(diagnosis.diagnoseDoctorInfo)
            : (diagnosis.diagnoseDoctorInfo != null)) {
            return false;
        }
        if ((cancellationDateTime != null)
            ? !cancellationDateTime.equals(diagnosis.cancellationDateTime)
            : (diagnosis.cancellationDateTime != null)) {
            return false;
        }
        if ((cancellationDoctorInfo != null)
            ? !cancellationDoctorInfo.equals(diagnosis.cancellationDoctorInfo)
            : (diagnosis.cancellationDoctorInfo != null)) {
            return false;
        }
        return (cancellationDiagnosisDescription != null)
               ? cancellationDiagnosisDescription.equals(diagnosis.cancellationDiagnosisDescription)
               : (diagnosis.cancellationDiagnosisDescription == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (diagnosisOrigin != null ? diagnosisOrigin.hashCode()
                                                    : 0);
        hash = 31 * hash + diagnosisId;
        hash = 31 * hash + (diagnosisDateTime != null
                            ? diagnosisDateTime.hashCode()
                            : 0);
        hash = 31 * hash + diseaseId;
        hash = 31 * hash + (diseaseInfo != null ? diseaseInfo.hashCode() : 0);
        hash = 31 * hash + (diagnosisDescription != null
                            ? diagnosisDescription.hashCode()
                            : 0);
        hash = 31 * hash + diagnoseDoctorId;
        hash = 31 * hash + (diagnoseDoctorInfo != null
                            ? diagnoseDoctorInfo.hashCode()
                            : 0);
        hash = 31 * hash + (cancellationDateTime != null
                            ? cancellationDateTime.hashCode()
                            : 0);
        hash = 31 * hash + cancellationDoctorId;
        hash = 31 * hash + (cancellationDoctorInfo != null
                            ? cancellationDoctorInfo.hashCode()
                            : 0);
        hash = 31 * hash + (cancellationDiagnosisDescription != null
                            ? cancellationDiagnosisDescription.hashCode()
                            : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "diagnosisOrigin=" + diagnosisOrigin +
                ", diagnosisId=" + diagnosisId +
                ", diagnosisDateTime=" + diagnosisDateTime +
                ", diseaseId=" + diseaseId +
                ", diseaseInfo='" + diseaseInfo + '\'' +
                ", diagnosisDescription='" + diagnosisDescription + '\'' +
                ", diagnoseDoctorId=" + diagnoseDoctorId +
                ", diagnoseDoctorInfo='" + diagnoseDoctorInfo + '\'' +
                ", cancellationDateTime=" + cancellationDateTime +
                ", cancellationDoctorId=" + cancellationDoctorId +
                ", cancellationDoctorInfo='" + cancellationDoctorInfo + '\'' +
                ", cancellationDiagnosisDescription='"
                + cancellationDiagnosisDescription + '\'' +
                '}';
    }
}
