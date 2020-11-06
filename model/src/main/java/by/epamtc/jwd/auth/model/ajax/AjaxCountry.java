package by.epamtc.jwd.auth.model.ajax;

public class AjaxCountry {
    private int countryId;
    private String countryName;

    public AjaxCountry() {
    }

    public AjaxCountry(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxCountry that = (AjaxCountry) o;

        if (countryId != that.countryId) {
            return false;
        }
        return (countryName != null) ? countryName.equals(that.countryName)
                                     : (that.countryName == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + countryId;
        hash = 31 * hash + ((countryName != null) ? countryName.hashCode()
                                                  : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxCountry{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
