package by.epamtc.jwd.auth.model.ajax;

import java.io.Serializable;

public class AjaxSettlement implements Serializable {
    private static final long serialVersionUID = 7250127360097065181L;

    private int settlementId;
    private String settlementName;
    private String areaName;

    public AjaxSettlement(int settlementId, String settlementName,
            String areaName) {
        this.settlementId = settlementId;
        this.settlementName = settlementName;
        this.areaName = areaName;
    }

    public AjaxSettlement() {
    }

    public int getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(int settlementId) {
        this.settlementId = settlementId;
    }

    public String getSettlementName() {
        return settlementName;
    }

    public void setSettlementName(String settlementName) {
        this.settlementName = settlementName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxSettlement that = (AjaxSettlement) o;

        if (settlementId != that.settlementId) {
            return false;
        }
        if ((settlementName != null) ? !settlementName.equals(that.settlementName)
                                     : (that.settlementName != null)) {
            return false;
        }
        return (areaName != null) ? areaName.equals(that.areaName)
                                  : (that.areaName == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + settlementId;
        hash = 31 * hash + ((settlementName != null) ? settlementName.hashCode()
                                                   : 0);
        hash = 31 * hash + ((areaName != null) ? areaName.hashCode()
                                             : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxSettlement{" +
                "settlementId=" + settlementId +
                ", settlementName='" + settlementName + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
