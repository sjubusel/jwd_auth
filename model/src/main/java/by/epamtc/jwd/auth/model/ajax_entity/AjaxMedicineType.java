package by.epamtc.jwd.auth.model.ajax_entity;

public class AjaxMedicineType implements java.io.Serializable{
    private static final long serialVersionUID = -7038705564577102064L;

    private int medicineTypeId;
    private String medicineName;

    public AjaxMedicineType(int medicineTypeId, String medicineName) {
        this.medicineTypeId = medicineTypeId;
        this.medicineName = medicineName;
    }

    public AjaxMedicineType() {
    }

    public int getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(int medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxMedicineType that = (AjaxMedicineType) o;

        if (medicineTypeId != that.medicineTypeId) {
            return false;
        }
        return (medicineName != null) ? medicineName.equals(that.medicineName)
                                      : that.medicineName == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + medicineTypeId;
        hash = 31 * hash + (medicineName != null ? medicineName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxMedicineType{" +
                "medicineTypeId=" + medicineTypeId +
                ", medicineName='" + medicineName + '\'' +
                '}';
    }
}
