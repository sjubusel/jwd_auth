package by.epamtc.jwd.auth.model.ajax_entity;

public class AjaxMedicine implements java.io.Serializable {
    private static final long serialVersionUID = 4330537270831869253L;

    private int medicineId;
    private String medicineInfo;

    public AjaxMedicine(int medicineId, String medicineInfo) {
        this.medicineId = medicineId;
        this.medicineInfo = medicineInfo;
    }

    public AjaxMedicine() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxMedicine that = (AjaxMedicine) o;

        if (medicineId != that.medicineId) {
            return false;
        }
        return (medicineInfo != null) ? medicineInfo.equals(that.medicineInfo)
                                      : that.medicineInfo == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + medicineId;
        hash = 31 * hash + ((medicineInfo != null) ? medicineInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxMedicine{" +
                "medicineId=" + medicineId +
                ", medicineInfo='" + medicineInfo + '\'' +
                '}';
    }
}
