package by.epamtc.jwd.auth.model.report;

import java.util.ArrayList;
import java.util.List;

public class HospitalReport implements java.io.Serializable {
    private static final long serialVersionUID = -5568312638891061321L;

    private List<HospitalDepartmentReport> contents = new ArrayList<>();

    public HospitalReport() {
    }

    public HospitalReport(List<HospitalDepartmentReport> contents) {
        this.contents = contents;
    }

    public void addReportPart(HospitalDepartmentReport hdReport) {
        contents.add(hdReport);
    }

    public boolean removeReportPart(HospitalDepartmentReport hdReport) {
        return contents.remove(hdReport);
    }

    public List<HospitalDepartmentReport> getContents() {
        return contents;
    }

    public void setContents(List<HospitalDepartmentReport> contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HospitalReport hospitalReport = (HospitalReport) obj;

        if (contents.size() != hospitalReport.contents.size()) {
            return false;
        }
        for (int i = 0; i < contents.size(); i++) {
            if (!contents.get(i).equals(hospitalReport.contents.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if (contents == null) {
            return 31 * hash;
        }
        for (HospitalDepartmentReport hdReport : contents) {
            hash = 31 * hash + (hdReport == null ? 0 : hdReport.hashCode());
        }
        return contents != null ? contents.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HospitalReport{" +
                "contents=" + contents +
                '}';
    }
}
