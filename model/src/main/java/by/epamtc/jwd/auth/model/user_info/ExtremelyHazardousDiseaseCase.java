package by.epamtc.jwd.auth.model.user_info;

import java.time.LocalDate;

public class ExtremelyHazardousDiseaseCase implements java.io.Serializable {
    private static final long serialVersionUID = 425587569019402624L;

    private int caseId;
    private int diseaseId;
    private String diseaseDescription;
    private LocalDate detectionDate;
    private String caseDescription;
    private LocalDate recoveryDate;

    public ExtremelyHazardousDiseaseCase(int caseId, int diseaseId,
            String diseaseDescription, LocalDate detectionDate,
            String caseDescription, LocalDate recoveryDate) {
        this.caseId = caseId;
        this.diseaseId = diseaseId;
        this.diseaseDescription = diseaseDescription;
        this.detectionDate = detectionDate;
        this.caseDescription = caseDescription;
        this.recoveryDate = recoveryDate;
    }

    public ExtremelyHazardousDiseaseCase() {
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public LocalDate getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(LocalDate detectionDate) {
        this.detectionDate = detectionDate;
    }

    public LocalDate getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(LocalDate recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExtremelyHazardousDiseaseCase that = (ExtremelyHazardousDiseaseCase) o;

        if (caseId != that.caseId) {
            return false;
        }
        if (diseaseId != that.diseaseId) {
            return false;
        }
        if (diseaseDescription != null ? !diseaseDescription.equals(that.diseaseDescription)
                                       : that.diseaseDescription != null) {
            return false;
        }
        if (caseDescription != null ? !caseDescription.equals(that.caseDescription)
                                    : that.caseDescription != null) {
            return false;
        }
        if (detectionDate != null ? !detectionDate.equals(that.detectionDate)
                                  : that.detectionDate != null) {
            return false;
        }
        return recoveryDate != null ? recoveryDate.equals(that.recoveryDate)
                                    : that.recoveryDate == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + caseId;
        hash = 31 * hash + diseaseId;
        hash = 31 * hash + (diseaseDescription != null ? diseaseDescription.hashCode()
                                                       : 0);
        hash = 31 * hash + (detectionDate != null ? detectionDate.hashCode()
                                                  : 0);
        hash = 31 * hash + (recoveryDate != null ? recoveryDate.hashCode()
                                                 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ExtremelyHazardousDiseaseCase{" +
                "caseId=" + caseId +
                ", diseaseId=" + diseaseId +
                ", diseaseDescription='" + diseaseDescription + '\'' +
                ", detectionDate=" + detectionDate +
                ", recoveryDate=" + recoveryDate +
                '}';
    }
}
