package in.ults.gisurvey.data.model.db;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.gisurvey.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class MemberDetailsItem {

    @SerializedName("name")
    private String memberName = "";

    @SerializedName("sex")
    private String memberGender = "";

    @SerializedName("age")
    private String memberAge = "";

    @SerializedName("job")
    private String memberJob = "";

    @SerializedName("job_loss")
    private String memberJobLoss = "";

    @SerializedName("job_type")
    private String memberJobtype = "";

    @SerializedName("maritstat")
    private String memberMartialStatus = "";

    @SerializedName("isThePersonNRK")
    private String isNRK = "";

    @SerializedName("isThePersonNRI")
    private String isNRI = "";

    @SerializedName("educategor")
    private String memberEducation = "";

    @SerializedName("edutype")
    private String memberEducationType = "";

    @SerializedName("disability")
    private String memberDisability = "";

    @SerializedName("disper")
    private String memberDisabilityPercent = "";

    @SerializedName("disease")
    private ArrayList<String> memberDisease = new ArrayList<>();

    @SerializedName("pension")
    private ArrayList<String> memberPension = new ArrayList<>();

    @SerializedName("is_student")
    private String isStudent = "";

    public String getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(String isStudent) {
        this.isStudent = isStudent;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(String memberAge) {
        this.memberAge = memberAge;
    }

    public String getMemberEducation() {
        return memberEducation;
    }

    public void setMemberEducation(String memberEducation) {
        this.memberEducation = memberEducation;
    }

    public String getMemberEducationType() {
        return memberEducationType;
    }

    public void setMemberEducationType(String memberEducationType) {
        this.memberEducationType = memberEducationType;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public String getMemberJob() {
        return memberJob;
    }

    public void setMemberJob(String memberJob) {
        this.memberJob = memberJob;
    }

    public String getMemberJobLoss() {
        return memberJobLoss;
    }

    public void setMemberJobLoss(String memberJobLoss) {
        this.memberJobLoss = memberJobLoss;
    }

    public String getMemberJobtype() {
        return memberJobtype;
    }

    public void setMemberJobtype(String memberJobtype) {
        this.memberJobtype = memberJobtype;
    }

    public String getMemberMartialStatus() {
        return memberMartialStatus;
    }

    public void setMemberMartialStatus(String memberMartialStatus) {
        this.memberMartialStatus = memberMartialStatus;
    }

    public String getMemberDisability() {
        return memberDisability;
    }

    public void setMemberDisability(String memberDisability) {
        this.memberDisability = memberDisability;
    }

    public String getMemberDisabilityPercent() {
        return memberDisabilityPercent;
    }

    public void setMemberDisabilityPercent(String memberDisabilityPercent) {
        this.memberDisabilityPercent = memberDisabilityPercent;
    }

    public ArrayList<String> getMemberPension() {
        return memberPension;
    }

    public void setMemberPension(ArrayList<String> memberPension) {
        this.memberPension = memberPension;
    }

    public ArrayList<String> getMemberDisease() {
        return memberDisease;
    }

    public void setMemberDisease(ArrayList<String> memberDisease) {
        this.memberDisease = memberDisease;
    }


    public String getIsNRK() {
        return isNRK;
    }

    public String getIsNRI() {
        return isNRI;
    }

    public void setIsNRK(String isNRK) {
        this.isNRK = isNRK;
    }

    public void setIsNRI(String isNRI) {
        this.isNRI = isNRI;
    }
}

