package in.ults.gisurvey.data.model.items;

/**
 * Created by anuag on 25-08-2020
 **/
public class ExcelDataItem {
    String uid, ownerAddressEng,newWard,newPropertyNo,newSubNo,oldWard,oldPropertyNo,oldSubNo,arZone,arAC,arFloorArea,arBuildingUsage,arRoadTypea,RoadName,arBuildingAge,arRoofDetail,arFloorDetails,arModification,arOccupierDetails,arTaxtTotal;

    public ExcelDataItem(String uid, String ownerAddressEng, String newWard, String newPropertyNo, String newSubNo, String oldWard, String oldPropertyNo, String oldSubNo, String arZone, String arAC, String arFloorArea, String arBuildingUsage, String arRoadTypea, String roadName, String arBuildingAge, String arRoofDetail, String arFloorDetails, String arModification, String arOccupierDetails, String arTaxtTotal) {
        this.uid = uid;
        this.ownerAddressEng = ownerAddressEng;
        this.newWard = newWard;
        this.newPropertyNo = newPropertyNo;
        this.newSubNo = newSubNo;
        this.oldWard = oldWard;
        this.oldPropertyNo = oldPropertyNo;
        this.oldSubNo = oldSubNo;
        this.arZone = arZone;
        this.arAC = arAC;
        this.arFloorArea = arFloorArea;
        this.arBuildingUsage = arBuildingUsage;
        this.arRoadTypea = arRoadTypea;
        RoadName = roadName;
        this.arBuildingAge = arBuildingAge;
        this.arRoofDetail = arRoofDetail;
        this.arFloorDetails = arFloorDetails;
        this.arModification = arModification;
        this.arOccupierDetails = arOccupierDetails;
        this.arTaxtTotal = arTaxtTotal;
    }

    public String getArTaxtTotal() {
        return arTaxtTotal;
    }

    public String getNewWard() {
        return newWard;
    }

    public String getNewPropertyNo() {
        return newPropertyNo;
    }

    public String getNewSubNo() {
        return newSubNo;
    }

    public String getOldWard() {
        return oldWard;
    }

    public String getOldPropertyNo() {
        return oldPropertyNo;
    }

    public String getOldSubNo() {
        return oldSubNo;
    }

    public String getUid() {
        return uid;
    }

    public String getOwnerAddressEng() {
        return ownerAddressEng;
    }

    public String getArZone() {
        return arZone;
    }

    public String getArFloorArea() {
        return arFloorArea;
    }

    public String getArBuildingUsage() {
        return arBuildingUsage;
    }

    public String getRoadName() {
        return RoadName;
    }

    public String getArRoadTypea() {
        return arRoadTypea;
    }

    public String getArBuildingAge() {
        return arBuildingAge;
    }

    public String getArRoofDetail() {
        return arRoofDetail;
    }

    public String getArFloorDetails() {
        return arFloorDetails;
    }

    public String getArModification() {
        return arModification;
    }

    public String getArOccupierDetails() {
        return arOccupierDetails;
    }

    public String getArAC() {
        return arAC;
    }
}
