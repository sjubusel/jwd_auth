package by.epamtc.jwd.auth.model.ajax;

import java.io.Serializable;

public class AjaxRegion implements Serializable {
    private static final long serialVersionUID = 2215716850311273260L;

    private int regionId;
    private String regionName;
    private String countryName;

    public AjaxRegion() {
    }

    public AjaxRegion(int regionId, String regionName, String countryName) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.countryName = countryName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

        AjaxRegion that = (AjaxRegion) o;

        if (regionId != that.regionId) {
            return false;
        }
        if ((regionName != null) ? (!regionName.equals(that.regionName))
                                 : (that.regionName != null)) {
            return false;
        }
        return (countryName != null) ? countryName.equals(that.countryName)
                                     : that.countryName == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + regionId;
        hash = 31 * hash + (regionName != null ? regionName.hashCode() : 0);
        hash = 31 * hash + (countryName != null ? countryName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxRegion{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
