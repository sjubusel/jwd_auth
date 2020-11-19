package by.epamtc.jwd.auth.model.med_info;

import java.time.LocalDateTime;

public class Prescription implements java.io.Serializable {
    private static final long serialVersionUID = 7211203926607362597L;

    private DepartmentOrigin departmentOrigin;
    private int prescriptionId;
    private int originDocumentId;
    private LocalDateTime prescriptionDateTime;
    private int prescribingStaffId;
    private String prescribingStaffInfo;
    private String prescriptionDescription;
    private int responsibleStaffId;
    private String responsibleStaffInfo;
    private int executorStaffId;
    private String executorStaffInfo;
    private LocalDateTime executionDateTime;
    private String executionDescription;
    private boolean doesPatientAgree;
    private String patientDisagreementDescription;
    private LocalDateTime patientDisagreementDateTime;
    private boolean isPrescriptionComplete;
    private int patientId;
    private String patientInfo;

    public Prescription() {
    }

    public Prescription(DepartmentOrigin departmentOrigin, int prescriptionId,
            int originDocumentId, LocalDateTime prescriptionDateTime,
            int prescribingStaffId, String prescribingStaffInfo,
            String prescriptionDescription, int responsibleStaffId,
            String responsibleStaffInfo, int executorStaffId,
            String executorStaffInfo, LocalDateTime executionDateTime,
            String executionDescription, boolean doesPatientAgree,
            String patientDisagreementDescription,
            LocalDateTime patientDisagreementDateTime,
            boolean isPrescriptionComplete, int patientId, String patientInfo) {
        this.departmentOrigin = departmentOrigin;
        this.prescriptionId = prescriptionId;
        this.originDocumentId = originDocumentId;
        this.prescriptionDateTime = prescriptionDateTime;
        this.prescribingStaffId = prescribingStaffId;
        this.prescribingStaffInfo = prescribingStaffInfo;
        this.prescriptionDescription = prescriptionDescription;
        this.responsibleStaffId = responsibleStaffId;
        this.responsibleStaffInfo = responsibleStaffInfo;
        this.executorStaffId = executorStaffId;
        this.executorStaffInfo = executorStaffInfo;
        this.executionDateTime = executionDateTime;
        this.executionDescription = executionDescription;
        this.doesPatientAgree = doesPatientAgree;
        this.patientDisagreementDescription = patientDisagreementDescription;
        this.patientDisagreementDateTime = patientDisagreementDateTime;
        this.isPrescriptionComplete = isPrescriptionComplete;
        this.patientId = patientId;
        this.patientInfo = patientInfo;
    }

    public DepartmentOrigin getDepartmentOrigin() {
        return departmentOrigin;
    }

    public void setDepartmentOrigin(DepartmentOrigin departmentOrigin) {
        this.departmentOrigin = departmentOrigin;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getOriginDocumentId() {
        return originDocumentId;
    }

    public void setOriginDocumentId(int originDocumentId) {
        this.originDocumentId = originDocumentId;
    }

    public LocalDateTime getPrescriptionDateTime() {
        return prescriptionDateTime;
    }

    public void setPrescriptionDateTime(LocalDateTime prescriptionDateTime) {
        this.prescriptionDateTime = prescriptionDateTime;
    }

    public int getPrescribingStaffId() {
        return prescribingStaffId;
    }

    public void setPrescribingStaffId(int prescribingStaffId) {
        this.prescribingStaffId = prescribingStaffId;
    }

    public String getPrescribingStaffInfo() {
        return prescribingStaffInfo;
    }

    public void setPrescribingStaffInfo(String prescribingStaffInfo) {
        this.prescribingStaffInfo = prescribingStaffInfo;
    }

    public String getPrescriptionDescription() {
        return prescriptionDescription;
    }

    public void setPrescriptionDescription(String prescriptionDescription) {
        this.prescriptionDescription = prescriptionDescription;
    }

    public int getResponsibleStaffId() {
        return responsibleStaffId;
    }

    public void setResponsibleStaffId(int responsibleStaffId) {
        this.responsibleStaffId = responsibleStaffId;
    }

    public String getResponsibleStaffInfo() {
        return responsibleStaffInfo;
    }

    public void setResponsibleStaffInfo(String responsibleStaffInfo) {
        this.responsibleStaffInfo = responsibleStaffInfo;
    }

    public int getExecutorStaffId() {
        return executorStaffId;
    }

    public void setExecutorStaffId(int executorStaffId) {
        this.executorStaffId = executorStaffId;
    }

    public String getExecutorStaffInfo() {
        return executorStaffInfo;
    }

    public void setExecutorStaffInfo(String executorStaffInfo) {
        this.executorStaffInfo = executorStaffInfo;
    }

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }

    public String getExecutionDescription() {
        return executionDescription;
    }

    public void setExecutionDescription(String executionDescription) {
        this.executionDescription = executionDescription;
    }

    public boolean isDoesPatientAgree() {
        return doesPatientAgree;
    }

    public void setDoesPatientAgree(boolean doesPatientAgree) {
        this.doesPatientAgree = doesPatientAgree;
    }

    public String getPatientDisagreementDescription() {
        return patientDisagreementDescription;
    }

    public void setPatientDisagreementDescription(String patientDisagreementDescription) {
        this.patientDisagreementDescription = patientDisagreementDescription;
    }

    public LocalDateTime getPatientDisagreementDateTime() {
        return patientDisagreementDateTime;
    }

    public void setPatientDisagreementDateTime(LocalDateTime patientDisagreementDateTime) {
        this.patientDisagreementDateTime = patientDisagreementDateTime;
    }

    public boolean isPrescriptionComplete() {
        return isPrescriptionComplete;
    }

    public void setPrescriptionComplete(boolean prescriptionComplete) {
        isPrescriptionComplete = prescriptionComplete;
    }

    public boolean getIsPrescriptionComplete() {
        return isPrescriptionComplete;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(String patientInfo) {
        this.patientInfo = patientInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prescription that = (Prescription) o;

        if (prescriptionId != that.prescriptionId) {
            return false;
        }
        if (originDocumentId != that.originDocumentId) {
            return false;
        }
        if (prescribingStaffId != that.prescribingStaffId) {
            return false;
        }
        if (responsibleStaffId != that.responsibleStaffId) {
            return false;
        }
        if (executorStaffId != that.executorStaffId) {
            return false;
        }
        if (doesPatientAgree != that.doesPatientAgree) {
            return false;
        }
        if (isPrescriptionComplete != that.isPrescriptionComplete) {
            return false;
        }
        if (patientId != that.patientId) {
            return false;
        }
        if (departmentOrigin != that.departmentOrigin) {
            return false;
        }
        if (prescriptionDateTime != null
            ? !prescriptionDateTime.equals(that.prescriptionDateTime)
            : that.prescriptionDateTime != null) {
            return false;
        }
        if (prescribingStaffInfo != null
            ? !prescribingStaffInfo.equals(that.prescribingStaffInfo)
            : that.prescribingStaffInfo != null) {
            return false;
        }
        if (prescriptionDescription != null
            ? !prescriptionDescription.equals(that.prescriptionDescription)
            : that.prescriptionDescription != null) {
            return false;
        }
        if (responsibleStaffInfo != null
            ? !responsibleStaffInfo.equals(that.responsibleStaffInfo)
            : that.responsibleStaffInfo != null) {
            return false;
        }
        if (executorStaffInfo != null
            ? !executorStaffInfo.equals(that.executorStaffInfo)
            : that.executorStaffInfo != null) {
            return false;
        }
        if (executionDateTime != null
            ? !executionDateTime.equals(that.executionDateTime)
            : that.executionDateTime != null) {
            return false;
        }
        if (executionDescription != null
            ? !executionDescription.equals(that.executionDescription)
            : that.executionDescription != null) {
            return false;
        }
        if (patientDisagreementDescription != null
            ? !patientDisagreementDescription.equals(that.patientDisagreementDescription)
            : that.patientDisagreementDescription != null) {
            return false;
        }
        if (patientInfo != null ? !patientInfo.equals(that.patientInfo)
                                : that.patientInfo != null) {
            return false;
        }
        return patientDisagreementDateTime != null
               ? patientDisagreementDateTime.equals(that.patientDisagreementDateTime)
               : that.patientDisagreementDateTime == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (departmentOrigin != null
                            ? departmentOrigin.hashCode()
                            : 0);
        hash = 31 * hash + prescriptionId;
        hash = 31 * hash + originDocumentId;
        hash = 31 * hash + (prescriptionDateTime != null
                            ? prescriptionDateTime.hashCode()
                            : 0);
        hash = 31 * hash + prescribingStaffId;
        hash = 31 * hash + (prescribingStaffInfo != null
                            ? prescribingStaffInfo.hashCode()
                            : 0);
        hash = 31 * hash + (prescriptionDescription != null
                            ? prescriptionDescription.hashCode()
                            : 0);
        hash = 31 * hash + responsibleStaffId;
        hash = 31 * hash + (responsibleStaffInfo != null
                            ? responsibleStaffInfo.hashCode()
                            : 0);
        hash = 31 * hash + executorStaffId;
        hash = 31 * hash + (executorStaffInfo != null
                            ? executorStaffInfo.hashCode()
                            : 0);
        hash = 31 * hash + (executionDateTime != null
                            ? executionDateTime.hashCode()
                            : 0);
        hash = 31 * hash + (executionDescription != null
                            ? executionDescription.hashCode()
                            : 0);
        hash = 31 * hash + (doesPatientAgree ? 1 : 0);
        hash = 31 * hash + (patientDisagreementDescription != null
                            ? patientDisagreementDescription.hashCode()
                            : 0);
        hash = 31 * hash + (patientDisagreementDateTime != null
                            ? patientDisagreementDateTime.hashCode()
                            : 0);
        hash = 31 * hash + (isPrescriptionComplete ? 1 : 0);
        hash = 31 * hash + patientId;
        hash = 31 * hash + (patientInfo != null ? patientInfo.hashCode()
                                                : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "departmentOrigin=" + departmentOrigin +
                ", prescriptionId=" + prescriptionId +
                ", originDocumentId=" + originDocumentId +
                ", prescriptionDateTime=" + prescriptionDateTime +
                ", prescribingStaffId=" + prescribingStaffId +
                ", prescribingStaffInfo='" + prescribingStaffInfo + '\'' +
                ", prescriptionDescription='" + prescriptionDescription + '\'' +
                ", responsibleStaffId=" + responsibleStaffId +
                ", responsibleStaffInfo='" + responsibleStaffInfo + '\'' +
                ", executorStaffId=" + executorStaffId +
                ", executorStaffInfo='" + executorStaffInfo + '\'' +
                ", executionDateTime=" + executionDateTime +
                ", executionDescription='" + executionDescription + '\'' +
                ", doesPatientAgree=" + doesPatientAgree +
                ", patientDisagreementDescription='" +
                patientDisagreementDescription + '\'' +
                ", patientDisagreementDateTime=" + patientDisagreementDateTime +
                ", isPrescriptionComplete=" + isPrescriptionComplete +
                ", patientId=" + patientId +
                ", patientInfo='" + patientInfo + '\'' +
                '}';
    }
}
