package in.ults.gisurvey.data.model.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocMainItem {

    @Expose
    @SerializedName("district")
    ArrayList<District> districts;

    @Expose
    @SerializedName("building_zone")
    ArrayList<CommonItem> buildingZones;

    @Expose
    @SerializedName("road_width")
    ArrayList<CommonItem> roadWidths;

    @Expose
    @SerializedName("road_type")
    ArrayList<CommonItem> roadTypes;

    @Expose
    @SerializedName("tenant_native")
    ArrayList<CommonItem> tenantNatives;

    @Expose
    @SerializedName("job")
    ArrayList<CommonItem> jobs;

    @Expose
    @SerializedName("job_type")
    ArrayList<CommonItem> jobType;

    @Expose
    @SerializedName("education")
    ArrayList<CommonItem> educations;

    @Expose
    @SerializedName("marital_status")
    ArrayList<CommonItem> martialStatuses;

    @Expose
    @SerializedName("disease")
    ArrayList<CommonItem> diseases;

    @Expose
    @SerializedName("disability")
    ArrayList<CommonItem> disabilities;
    @Expose
    @SerializedName("pension")
    ArrayList<CommonItem> pensions;
    @Expose
    @SerializedName("religion")
    ArrayList<CommonItem> religions;
    @Expose
    @SerializedName("ration_card")
    ArrayList<CommonItem> rationCards;
    @Expose
    @SerializedName("gender")
    ArrayList<CommonItem> gender;
    @Expose
    @SerializedName("building_status")
    ArrayList<CommonItem> buildingStatus;
    @Expose
    @SerializedName("building_type")
    ArrayList<CommonItem> buildingType;
    @Expose
    @SerializedName("building_under")
    ArrayList<CommonItem> buildingUnder;
    @Expose
    @SerializedName("door_status")
    ArrayList<CommonItem> doorStatus;
    @Expose
    @SerializedName("wall_type")
    ArrayList<CommonItem> wallType;
    @Expose
    @SerializedName("wall_type_nr")
    ArrayList<CommonItem> wallTypeNr;

    @Expose
    @SerializedName("wall_type_na")
    ArrayList<CommonItem> wallTypeNa;

    @Expose
    @SerializedName("wall_type_nr_na")
    ArrayList<CommonItem> wallTypeNrNa;

    @Expose
    @SerializedName("roof_type")
    ArrayList<CommonItem> roofType;
    @Expose
    @SerializedName("floor_type")
    ArrayList<CommonItem> floorType;

    @Expose
    @SerializedName("landmark_category")
    ArrayList<CommonItem> landMarkCategory;

    @Expose
    @SerializedName("water_source_type")
    ArrayList<CommonItem> waterSourceType;

    @Expose
    @SerializedName("full_month")
    ArrayList<CommonItem> fullMonth;

    @Expose
    @SerializedName("informed_by")
    ArrayList<CommonItem> informedBy;

    @Expose
    @SerializedName("establishment_type")
    ArrayList<CommonItem> establishmentType;

    @Expose
    @SerializedName("new_property_id_remark")
    ArrayList<CommonItem> newPropertyIdRemark;

    @Expose
    @SerializedName("tenant_status")
    ArrayList<CommonItem> tenantStatus;

    @Expose
    @SerializedName("toilet_waste_disposal")
    ArrayList<CommonItem> toiletWasteDisposal;

    @Expose
    @SerializedName("water_connection_type")
    ArrayList<CommonItem> waterConnectionType;

    @Expose
    @SerializedName("water_supply_duration")
    ArrayList<CommonItem> waterSupplyDuration;

    @Expose
    @SerializedName("pet_type")
    ArrayList<CommonItem> petType;

    @Expose
    @SerializedName("type_of_land")
    ArrayList<CommonItem> typeOfLand;

    @Expose
    @SerializedName("vehicle_type")
    ArrayList<CommonItem> vehicleType;
    @Expose
    @SerializedName("vehicle_usage")
    ArrayList<CommonItem> vehicleUsage;

    @Expose
    @SerializedName("caste_temp")
    ArrayList<CommonItem> casteTemp;
    @Expose
    @SerializedName("other_facility")
    ArrayList<CommonItem> otherFacility;

    @Expose
    @SerializedName("religion_temp")
    ArrayList<CommonItem> religionTemp;

    @Expose
    @SerializedName("well_details")
    ArrayList<CommonItem> wellDetails;

    @Expose
    @SerializedName("other_water_source")
    ArrayList<CommonItem> otherWaterSource;

    @Expose
    @SerializedName("occupation")
    ArrayList<CommonItem> occupations;

    @Expose
    @SerializedName("survey_type")
    ArrayList<CommonItem> surveyType;

    @Expose
    @SerializedName("building_under_any_scheme")
    ArrayList<CommonItem> buildingUnderAnyScheme;


    @Expose
    @SerializedName("common_options_yes_no")
    ArrayList<CommonItem> commonOptionsYesNO;

    @Expose
    @SerializedName("common_options_yes_no_nr")
    ArrayList<CommonItem> commonOptionsYesNONR;

    @Expose
    @SerializedName("ownership_of_edu_building")
    ArrayList<CommonItem> ownershipOfEduBuilding;

    @Expose
    @SerializedName("plastic_waste_management_type")
    ArrayList<CommonItem> plasticWasteManagementType;

    @Expose
    @SerializedName("liquid_waste_management_type")
    ArrayList<CommonItem> liquidWasteManagementType;

    @Expose
    @SerializedName("organic_waste_management_type")
    ArrayList<CommonItem> organicWasteManagementType;

    public ArrayList<CommonItem> getPlasticWasteManagementType() {
        return plasticWasteManagementType;
    }

    public void setPlasticWasteManagementType(ArrayList<CommonItem> plasticWasteManagementType) {
        this.plasticWasteManagementType = plasticWasteManagementType;
    }

    public ArrayList<CommonItem> getLiquidWasteManagementType() {
        return liquidWasteManagementType;
    }

    public void setLiquidWasteManagementType(ArrayList<CommonItem> liquidWasteManagementType) {
        this.liquidWasteManagementType = liquidWasteManagementType;
    }

    public ArrayList<CommonItem> getOrganicWasteManagementType() {
        return organicWasteManagementType;
    }

    public void setOrganicWasteManagementType(ArrayList<CommonItem> organicWasteManagementType) {
        this.organicWasteManagementType = organicWasteManagementType;
    }

    public ArrayList<CommonItem> getCommonOptionsYesNO() {
        return commonOptionsYesNO;
    }

    public ArrayList<CommonItem> getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(ArrayList<CommonItem> surveyType) {
        this.surveyType = surveyType;
    }

    public ArrayList<CommonItem> getOccupations() {
        return occupations;
    }

    public void setOccupations(ArrayList<CommonItem> occupations) {
        this.occupations = occupations;
    }

    public ArrayList<CommonItem> getWallTypeNa() {
        return wallTypeNa;
    }

    public ArrayList<CommonItem> getWallTypeNrNa() {
        return wallTypeNrNa;
    }

    public ArrayList<CommonItem> getVehicleUsage() {
        return vehicleUsage;
    }

    public void setVehicleUsage(ArrayList<CommonItem> vehicleUsage) {
        this.vehicleUsage = vehicleUsage;
    }

    public ArrayList<CommonItem> getOtherFacility() {
        return otherFacility;
    }

    public ArrayList<CommonItem> getWallTypeNr() {
        return wallTypeNr;
    }



    public ArrayList<CommonItem> getOwnershipOfEduBuilding() {
        return ownershipOfEduBuilding;
    }

    public void setOwnershipOfEduBuilding(ArrayList<CommonItem> ownershipOfEduBuilding) {
        this.ownershipOfEduBuilding = ownershipOfEduBuilding;
    }

    public ArrayList<CommonItem> getBuildingUnderAnyScheme() {
        return buildingUnderAnyScheme;
    }

    public void setBuildingUnderAnyScheme(ArrayList<CommonItem> buildingUnderAnyScheme) {
        this.buildingUnderAnyScheme = buildingUnderAnyScheme;
    }

    public ArrayList<CommonItem> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(ArrayList<CommonItem> vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ArrayList<CommonItem> getTypeOfLand() {
        return typeOfLand;
    }

    public void setTypeOfLand(ArrayList<CommonItem> typeOfLand) {
        this.typeOfLand = typeOfLand;
    }

    public ArrayList<CommonItem> getPetType() {
        return petType;
    }

    public void setPetType(ArrayList<CommonItem> petType) {
        this.petType = petType;
    }

    public ArrayList<CommonItem> getWaterSupplyDuration() {
        return waterSupplyDuration;
    }

    public void setWaterSupplyDuration(ArrayList<CommonItem> waterSupplyDuration) {
        this.waterSupplyDuration = waterSupplyDuration;
    }

    public ArrayList<CommonItem> getWellDetails() {
        return wellDetails;
    }

    public ArrayList<CommonItem> getOtherWaterSource() {
        return otherWaterSource;
    }

    public ArrayList<CommonItem> getWaterConnectionType() {
        return waterConnectionType;
    }

    public void setWaterConnectionType(ArrayList<CommonItem> waterConnectionType) {
        this.waterConnectionType = waterConnectionType;
    }

    public ArrayList<CommonItem> getTenantStatus() {
        return tenantStatus;
    }

    public ArrayList<CommonItem> getCasteTemp() {
        return casteTemp;
    }

    public ArrayList<CommonItem> getReligionTemp() {
        return religionTemp;
    }

    public ArrayList<CommonItem> getToiletWasteDisposal() {
        return toiletWasteDisposal;
    }

    public void setToiletWasteDisposal(ArrayList<CommonItem> toiletWasteDisposal) {
        this.toiletWasteDisposal = toiletWasteDisposal;
    }

    public ArrayList<CommonItem> getNewPropertyIdRemark() {
        return newPropertyIdRemark;
    }

    public void setNewPropertyIdRemark(ArrayList<CommonItem> newPropertyIdRemark) {
        this.newPropertyIdRemark = newPropertyIdRemark;
    }

    public ArrayList<CommonItem> getEstablishmentType() {
        return establishmentType;
    }

    public void setEstablishmentType(ArrayList<CommonItem> establishmentType) {
        this.establishmentType = establishmentType;
    }

    GridItem gridItem;

    public ArrayList<CommonItem> getRoofType() {
        return roofType;
    }

    public void setRoofType(ArrayList<CommonItem> roofType) {
        this.roofType = roofType;
    }

    public ArrayList<CommonItem> getFloorType() {
        return floorType;
    }

    public void setFloorType(ArrayList<CommonItem> floorType) {
        this.floorType = floorType;
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public ArrayList<CommonItem> getBuildingZones() {
        return buildingZones;
    }

    public ArrayList<CommonItem> getRoadWidths() {
        return roadWidths;
    }

    public ArrayList<CommonItem> getRoadTypes() {
        return roadTypes;
    }

    public ArrayList<CommonItem> getTenantNatives() {
        return tenantNatives;
    }

    public ArrayList<CommonItem> getJobs() {
        return jobs;
    }

    public ArrayList<CommonItem> getJobType() {
        return jobType;
    }

    public ArrayList<CommonItem> getEducations() {
        return educations;
    }

    public ArrayList<CommonItem> getMartialStatuses() {
        return martialStatuses;
    }

    public ArrayList<CommonItem> getDiseases() {
        return diseases;
    }

    public ArrayList<CommonItem> getDisabilities() {
        return disabilities;
    }

    public ArrayList<CommonItem> getPensions() {
        return pensions;
    }

    public ArrayList<CommonItem> getReligions() {
        return religions;
    }

    public ArrayList<CommonItem> getRationCards() {
        return rationCards;
    }

    public ArrayList<CommonItem> getGender() {
        return gender;
    }

    public GridItem getGridItem() {
        return gridItem;
    }

    public ArrayList<CommonItem> getBuildingStatus() {
        return buildingStatus;
    }

    public ArrayList<CommonItem> getBuildingType() {
        return buildingType;
    }

    public ArrayList<CommonItem> getBuildingUnder() {
        return buildingUnder;
    }

    public ArrayList<CommonItem> getDoorStatus() {
        return doorStatus;
    }

    public ArrayList<CommonItem> getWallType() {
        return wallType;
    }

    public ArrayList<CommonItem> getLandMarkCategory() {
        return landMarkCategory;
    }

    public ArrayList<CommonItem> getWaterSourceType() {
        return waterSourceType;
    }

    public ArrayList<CommonItem> getFullMonth() {
        return fullMonth;
    }

    public ArrayList<CommonItem> getInformedBy() {
        return informedBy;
    }

    public ArrayList<CommonItem> getCommonOptionsYesNONR() {
        return commonOptionsYesNONR;
    }

    public String[] getDistrictValues() {
        if (getDistricts() != null) {
            String[] data = new String[getDistricts().size()];
            for (int i = 0; i < getDistricts().size(); i++) {
                data[i] = getDistricts().get(i).getDistrictName();
            }
            return data;
        }
        return new String[0];
    }


    public ArrayList<LocalBody> getLocalBodyList(String districtName) {
        if (getDistricts() != null) {
            for (District district : getDistricts())
                if (district != null && district.getDistrictName() != null && district.getDistrictName().equalsIgnoreCase(districtName)) {
                    return district.localBodyArrayList;
                }
        }
        return new ArrayList<>();
    }

    public LocalBody getSelectedLocalBody(String districtName, String localBodyName) {
        if (getDistricts() != null) {
            for (District district : getDistricts())
                if (district != null && district.getDistrictName() != null && district.getDistrictName().equalsIgnoreCase(districtName)) {
                    if (district.getLocalBodyArrayList() != null) {
                        for (LocalBody localBody : district.getLocalBodyArrayList()) {
                            if (localBody != null && localBody.getLocalBodyName() != null && localBody.getLocalBodyName().equalsIgnoreCase(localBodyName)) {
                                return localBody;
                            }
                        }
                    }
                }
        }
        return null;
    }


    public String[] getLocalBodyValues(String districtName) {
        ArrayList<LocalBody> localBodyArrayList = getLocalBodyList(districtName);
        if (localBodyArrayList != null) {
            String[] data = new String[localBodyArrayList.size()];
            for (int i = 0; i < localBodyArrayList.size(); i++) {
                data[i] = localBodyArrayList.get(i).getLocalBodyName();
            }
            return data;
        }
        return new String[0];
    }


}
