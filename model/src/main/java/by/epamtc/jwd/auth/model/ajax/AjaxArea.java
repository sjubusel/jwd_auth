package by.epamtc.jwd.auth.model.ajax;

import java.io.Serializable;

public class AjaxArea implements Serializable {
    private static final long serialVersionUID = 1066729437007591964L;

    private int areaId;
    private String areaName;
    private String regionName;

    public AjaxArea(int areaId, String areaName, String regionName) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.regionName = regionName;
    }

    public AjaxArea() {
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxArea ajaxArea = (AjaxArea) o;

        if (areaId != ajaxArea.areaId) {
            return false;
        }
        if ((areaName != null) ? !areaName.equals(ajaxArea.areaName)
                               : (ajaxArea.areaName != null)) {
            return false;
        }
        return (regionName != null)
               ? regionName.equals(ajaxArea.regionName)
               : ajaxArea.regionName == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + areaId;
        hash = 31 * hash + (areaName != null ? areaName.hashCode() : 0);
        hash = 31 * hash + (regionName != null ? regionName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxArea{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
