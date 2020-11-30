package by.epamtc.jwd.auth.model.report;

public class HospitalDepartmentReport implements java.io.Serializable {
    private static final long serialVersionUID = -7103158474212983959L;

    private String deptName;
    private int vacantPlacesNumber;
    private int totalPlacesNumber;

    public HospitalDepartmentReport() {
    }

    public HospitalDepartmentReport(String deptName, int vacantPlacesNumber,
            int totalPlacesNumber) {
        this.deptName = deptName;
        this.vacantPlacesNumber = vacantPlacesNumber;
        this.totalPlacesNumber = totalPlacesNumber;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getVacantPlacesNumber() {
        return vacantPlacesNumber;
    }

    public void setVacantPlacesNumber(int vacantPlacesNumber) {
        this.vacantPlacesNumber = vacantPlacesNumber;
    }

    public int getTotalPlacesNumber() {
        return totalPlacesNumber;
    }

    public void setTotalPlacesNumber(int totalPlacesNumber) {
        this.totalPlacesNumber = totalPlacesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HospitalDepartmentReport hpReport = (HospitalDepartmentReport) o;
        return deptName.equals(hpReport.deptName)
                && (vacantPlacesNumber == hpReport.vacantPlacesNumber)
                && (totalPlacesNumber == hpReport.totalPlacesNumber);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + ((deptName == null) ? 0 : deptName.hashCode());
        hash = 31 * hash + vacantPlacesNumber;
        hash = 31 * hash + totalPlacesNumber;
        return hash;
    }

    @Override
    public String toString() {
        return "HospitalDepartmentReport{" +
                "deptName='" + deptName + '\'' +
                ", vacantPlacesNumber=" + vacantPlacesNumber +
                ", totalPlacesNumber=" + totalPlacesNumber +
                '}';
    }
}
