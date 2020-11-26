package by.epamtc.jwd.auth.model.ajax_entity;

public class AjaxHazardousDisease implements java.io.Serializable {
    private static final long serialVersionUID = -2770563794735370987L;

    private int diseaseId;
    private String diseaseName;

    public AjaxHazardousDisease() {
    }

    public AjaxHazardousDisease(int diseaseId, String diseaseName) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxHazardousDisease that = (AjaxHazardousDisease) o;

        if (diseaseId != that.diseaseId) {
            return false;
        }
        return (diseaseName != null) ? diseaseName.equals(that.diseaseName)
                                     : that.diseaseName == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + diseaseId;
        hash = 31 * hash + (diseaseName != null ? diseaseName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxHazardousDisease{" +
                "diseaseId=" + diseaseId +
                ", diseaseName='" + diseaseName + '\'' +
                '}';
    }
}
