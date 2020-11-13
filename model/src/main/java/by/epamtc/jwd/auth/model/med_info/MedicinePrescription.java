package by.epamtc.jwd.auth.model.med_info;

import java.time.LocalDateTime;

public class MedicinePrescription implements java.io.Serializable {
    private static final long serialVersionUID = 6227832058042782960L;

    private DepartmentOrigin departmentOrigin;
    private int prescriptionId;
    private int originDocumentId;
    private int medicineId;
    private String medicineInfo;
    private LocalDateTime prescriptionDateTime;
    private int prescribingStaffId;
    private String prescribingStaffInfo;
    private double dosageQuantity;
    private MedicineMeasureUnit dosageMeasureUnit;
    private LocalDateTime targetApplicationDateTime;
    private int executorStaffId;
    private String executorStaffInfo;
    private LocalDateTime executionDateTime;
    private String executionDescription;
    private boolean doesPatientAgree;
    private String patientDisagreementDescription;
    private LocalDateTime patientDisagreementDateTime;
    private boolean isPrescriptionComplete;

    public MedicinePrescription() {
    }

    public MedicinePrescription(DepartmentOrigin departmentOrigin,
            int prescriptionId, int originDocumentId, int medicineId,
            String medicineInfo, LocalDateTime prescriptionDateTime,
            int prescribingStaffId, String prescribingStaffInfo,
            double dosageQuantity, MedicineMeasureUnit dosageMeasureUnit,
            LocalDateTime targetApplicationDateTime, int executorStaffId,
            String executorStaffInfo, LocalDateTime executionDateTime,
            String executionDescription, boolean doesPatientAgree,
            String patientDisagreementDescription,
            LocalDateTime patientDisagreementDateTime,
            boolean isPrescriptionComplete) {
        this.departmentOrigin = departmentOrigin;
        this.prescriptionId = prescriptionId;
        this.originDocumentId = originDocumentId;
        this.medicineId = medicineId;
        this.medicineInfo = medicineInfo;
        this.prescriptionDateTime = prescriptionDateTime;
        this.prescribingStaffId = prescribingStaffId;
        this.prescribingStaffInfo = prescribingStaffInfo;
        this.dosageQuantity = dosageQuantity;
        this.dosageMeasureUnit = dosageMeasureUnit;
        this.targetApplicationDateTime = targetApplicationDateTime;
        this.executorStaffId = executorStaffId;
        this.executorStaffInfo = executorStaffInfo;
        this.executionDateTime = executionDateTime;
        this.executionDescription = executionDescription;
        this.doesPatientAgree = doesPatientAgree;
        this.patientDisagreementDescription = patientDisagreementDescription;
        this.patientDisagreementDateTime = patientDisagreementDateTime;
        this.isPrescriptionComplete = isPrescriptionComplete;
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

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(String medicineInfo) {
        this.medicineInfo = medicineInfo;
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

    public double getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(double dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public MedicineMeasureUnit getDosageMeasureUnit() {
        return dosageMeasureUnit;
    }

    public void setDosageMeasureUnit(MedicineMeasureUnit dosageMeasureUnit) {
        this.dosageMeasureUnit = dosageMeasureUnit;
    }

    public LocalDateTime getTargetApplicationDateTime() {
        return targetApplicationDateTime;
    }

    public void setTargetApplicationDateTime(LocalDateTime targetApplicationDateTime) {
        this.targetApplicationDateTime = targetApplicationDateTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicinePrescription that = (MedicinePrescription) o;

        if (prescriptionId != that.prescriptionId) {
            return false;
        }
        if (originDocumentId != that.originDocumentId) {
            return false;
        }
        if (medicineId != that.medicineId) {
            return false;
        }
        if (prescribingStaffId != that.prescribingStaffId) {
            return false;
        }
        if (Double.compare(that.dosageQuantity, dosageQuantity) != 0) {
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
        if (departmentOrigin != that.departmentOrigin) {
            return false;
        }
        if (medicineInfo != null ? !medicineInfo.equals(that.medicineInfo)
                                 : that.medicineInfo != null) {
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
        if (dosageMeasureUnit != that.dosageMeasureUnit) {
            return false;
        }
        if (targetApplicationDateTime != null
            ? !targetApplicationDateTime.equals(that.targetApplicationDateTime)
            : that.targetApplicationDateTime != null) {
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
        return patientDisagreementDateTime != null
               ? patientDisagreementDateTime.equals(that.patientDisagreementDateTime)
               : that.patientDisagreementDateTime == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        long temp;
        hash = 31 * hash + (departmentOrigin != null ? departmentOrigin.hashCode()
                                                     : 0);
        hash = 31 * hash + prescriptionId;
        hash = 31 * hash + originDocumentId;
        hash = 31 * hash + medicineId;
        hash = 31 * hash + (medicineInfo != null ? medicineInfo.hashCode()
                                                 : 0);
        hash = 31 * hash + (prescriptionDateTime != null
                            ? prescriptionDateTime.hashCode()
                            : 0);
        hash = 31 * hash + prescribingStaffId;
        hash = 31 * hash + (prescribingStaffInfo != null
                            ? prescribingStaffInfo.hashCode()
                            : 0);
        temp = Double.doubleToLongBits(dosageQuantity);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        hash = 31 * hash + (dosageMeasureUnit != null
                            ? dosageMeasureUnit.hashCode()
                            : 0);
        hash = 31 * hash + (targetApplicationDateTime != null
                            ? targetApplicationDateTime.hashCode()
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
        return hash;
    }

    @Override
    public String toString() {
        return "MedicinePrescription{" +
                "departmentOrigin=" + departmentOrigin +
                ", prescriptionId=" + prescriptionId +
                ", originDocumentId=" + originDocumentId +
                ", medicineId=" + medicineId +
                ", medicineInfo='" + medicineInfo + '\'' +
                ", prescriptionDateTime=" + prescriptionDateTime +
                ", prescribingStaffId=" + prescribingStaffId +
                ", prescribingStaffInfo='" + prescribingStaffInfo + '\'' +
                ", dosageQuantity=" + dosageQuantity +
                ", dosageMeasureUnit=" + dosageMeasureUnit +
                ", targetApplicationDateTime=" + targetApplicationDateTime +
                ", executorStaffId=" + executorStaffId +
                ", executorStaffInfo='" + executorStaffInfo + '\'' +
                ", executionDateTime=" + executionDateTime +
                ", executionDescription='" + executionDescription + '\'' +
                ", doesPatientAgree=" + doesPatientAgree +
                ", patientDisagreementDescription='" + patientDisagreementDescription + '\'' +
                ", patientDisagreementDateTime=" + patientDisagreementDateTime +
                ", isPrescriptionComplete=" + isPrescriptionComplete +
                '}';
    }
}
