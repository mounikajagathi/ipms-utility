package in.ults.gisurvey.ui.report;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import java.nio.MappedByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class ReportViewModel extends BaseViewModel<ReportNavigator> {

    public String dbDate;
    public String todayDate;
    public String deviceId;
    public MutableLiveData<String> wardNumbers = new MutableLiveData<>();
    public MutableLiveData<String> totalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialDoTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialDoTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialDcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialDcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialPdcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialPdcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialGlTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialGlTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialNcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialNcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialOgTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialOgTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialOwrTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialOwrTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> residentialRbTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallResidentialRbTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialDoTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialDoTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialDcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialDcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialPdcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialPdcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialGlTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialGlTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialNcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialNcTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialOgTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialOgTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> nonResidentialOwrTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallNonResidentialOwrTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> demolishedTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallDemolishedTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> unusableTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallUnusableTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> unwantedTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallUnwantedTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> landmarkTotalPropertyCount = new MutableLiveData<>();
    public MutableLiveData<String> teleCallLandmarkTotalPropertyCount = new MutableLiveData<>();


    public ReportViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){

        super(dataManager, schedulerProvider);
    }

    public void setDate() {
        DateFormat df = new SimpleDateFormat(AppConstants.REPORT_VIEW_DATE_FORMAT);
        todayDate = df.format(Calendar.getInstance().getTime());

        DateFormat dbdf = new SimpleDateFormat(AppConstants.SURVEY_REPORT_DATE_FORMAT, Locale.ENGLISH);
        String date = dbdf.format(Calendar.getInstance().getTime());
        dbDate = date.concat("%");
    }

    public void setDeviceId() {
        deviceId = getDataManager().getSeries();
    }

    public void getDailyWardNumbersDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getWardNumbersWithDate(true,true,dbDate)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(wardNumbersList ->{
                    if(wardNumbersList != null){
                        setWardNumbers(wardNumbersList);
                    }
                })
                .subscribe());
    }


    public void setWardNumbers(List numbers){

        String temp = TextUtils.join(",",numbers);
        wardNumbers.setValue(temp);

    }

    public void getDailyTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,true,dbDate,"%%","%%", "%%","%%","%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }



    public void setPropertyCount(Integer count){

        totalPropertyCount.setValue(String.valueOf(count));
    }



    public void getDailyResidentialTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,true,dbDate,"%%","Residential", "%%","%%","%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialPropertyCount(Integer count) {

        residentialTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(true,true,dbDate,"%%","Residential", "%%","%%","%%",AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialPropertyCount(Integer count) {

        teleCallResidentialTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialDoTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_OPEN,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialDoPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialDoPropertyCount(Integer count) {

        residentialDoTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialDoTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_OPEN,buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialDoPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialDoPropertyCount(Integer count) {

        teleCallResidentialDoTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialDcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_DC,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialDcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialDcPropertyCount(Integer count) {

        residentialDcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialDcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_DC,buildingStatuses, AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialDcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialDcPropertyCount(Integer count) {

        teleCallResidentialDcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialPdcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_PDC,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialPdcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialPdcPropertyCount(Integer count) {

        residentialPdcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialPdcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_PDC,buildingStatuses, AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialPdcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialPdcPropertyCount(Integer count) {

        teleCallResidentialPdcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialGlTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_GL,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialGlPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialGlPropertyCount(Integer count) {

        residentialGlTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialGlTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_GL,buildingStatuses, AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialGlPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialGlPropertyCount(Integer count) {

        teleCallResidentialGlTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialNcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_NC,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialNcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialNcPropertyCount(Integer count) {

        residentialNcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialNcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%",AppConstants.DOOR_STATUS_NC,buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialNcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialNcPropertyCount(Integer count) {

        teleCallResidentialNcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialOgTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,AppConstants.BUILDING_STATUS_ONGOING,"%%",buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialOgPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialOgPropertyCount(Integer count) {

        residentialOgTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialOgTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,AppConstants.BUILDING_STATUS_ONGOING,"%%",buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialOgPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialOgPropertyCount(Integer count) {

        teleCallResidentialOgTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialOwrTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF,"%%",buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialOwrPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialOwrPropertyCount(Integer count) {

        residentialOwrTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialOwrTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF,"%%",buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialOwrPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialOwrPropertyCount(Integer count) {

        teleCallResidentialOwrTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyResidentialRbTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%","%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setResidentialRbPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setResidentialRbPropertyCount(Integer count) {

        residentialRbTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallResidentialRbTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL, AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING,"%%","%%",AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallResidentialRbPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallResidentialRbPropertyCount(Integer count) {

        teleCallResidentialRbTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyNonResidentialTotalPropertyCountDailyReport(){

        String[] buildingStatus = {"Demolished","Unusable"};
        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%","%%",buildingStatus)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialPropertyCount(Integer count) {

        nonResidentialTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialTotalPropertyCountDailyReport(){

        String[] buildingStatus = {"Demolished","Unusable"};
        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%","%%",buildingStatus, AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialPropertyCount(Integer count) {

        teleCallNonResidentialTotalPropertyCount.setValue(String.valueOf(count));
    }


    public void getDailyNonResidentialDoTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_OPEN,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialDoPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialDoPropertyCount(Integer count) {

        nonResidentialDoTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialDoTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_OPEN,buildingStatuses, AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialDoPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialDoPropertyCount(Integer count) {

        teleCallNonResidentialDoTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyNonResidentialDcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_DC,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialDcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialDcPropertyCount(Integer count) {

        nonResidentialDcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialDcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_DC,buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialDcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialDcPropertyCount(Integer count) {

        teleCallNonResidentialDcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyNonResidentialPdcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_PDC,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialPdcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialPdcPropertyCount(Integer count) {

        nonResidentialPdcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialPdcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_PDC,buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialPdcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialPdcPropertyCount(Integer count) {

        teleCallNonResidentialPdcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyNonResidentialGlTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_GL,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialGlPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialGlPropertyCount(Integer count) {

        nonResidentialGlTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialGlTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_GL,buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialGlPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialGlPropertyCount(Integer count) {

        teleCallNonResidentialGlTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyNonResidentialNcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_NC,buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialNcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialNcPropertyCount(Integer count) {

        nonResidentialNcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialNcTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {AppConstants.BUILDING_STATUS_ONGOING,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,"%%",AppConstants.DOOR_STATUS_NC,buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialNcPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialNcPropertyCount(Integer count) {

        teleCallNonResidentialNcTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyNonResidentialOgTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,AppConstants.BUILDING_STATUS_ONGOING,"%%",buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialOgPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialOgPropertyCount(Integer count) {

        nonResidentialOgTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialOgTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,AppConstants.BUILDING_STATUS_ONGOING,"%%",buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialOgPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialOgPropertyCount(Integer count) {

        teleCallNonResidentialOgTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyNonResidentialOwrTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF,"%%",buildingStatuses)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setNonResidentialOwrPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setNonResidentialOwrPropertyCount(Integer count) {

        nonResidentialOwrTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallNonResidentialOwrTotalPropertyCountDailyReport(){

        String[] buildingStatuses = {};

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,AppConstants.BUILDING_TYPE_RESIDENTIAL,AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF,"%%",buildingStatuses,AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallNonResidentialOwrPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallNonResidentialOwrPropertyCount(Integer count) {

        teleCallNonResidentialOwrTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyDemolishedTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,"%%","%%",AppConstants.BUILDING_STATUS_DEMOLISHED,"%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setDemolishedPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setDemolishedPropertyCount(Integer count) {

        demolishedTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallDemolishedTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,"%%","%%",AppConstants.BUILDING_STATUS_DEMOLISHED,"%%",AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallDemolishedPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallDemolishedPropertyCount(Integer count) {

        teleCallDemolishedTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyUnusableTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,"%%","%%", AppConstants.BUILDING_STATUS_UNUSABLE,"%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setUnusablePropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setUnusablePropertyCount(Integer count) {

        unusableTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallUnusableTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_BUILDING,"%%","%%", AppConstants.BUILDING_STATUS_UNUSABLE,"%%",AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallUnusablePropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallUnusablePropertyCount(Integer count) {

        teleCallUnusableTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyUnwantedTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,true,dbDate,AppConstants.POINT_STATUS_UNWANTED,"%%","%%","%%","%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setUnwantedPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setUnwantedPropertyCount(Integer count) {

        unwantedTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallUnwantedTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_UNWANTED,"%%","%%","%%","%%",AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallUnwantedPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallUnwantedPropertyCount(Integer count) {

        teleCallUnwantedTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyLandmarkTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,true,dbDate,AppConstants.POINT_STATUS_LANDMARK,"%%", "%%","%%","%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setLandmarkPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setLandmarkPropertyCount(Integer count) {

        landmarkTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void getDailyTeleCallLandmarkTotalPropertyCountDailyReport(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(true,true,dbDate,AppConstants.POINT_STATUS_LANDMARK,"%%", "%%","%%","%%",AppConstants.TELE_CALL)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        setTeleCallLandmarkPropertyCount(propertyCount);
                    }
                })
                .subscribe());
    }

    public void setTeleCallLandmarkPropertyCount(Integer count) {

        teleCallLandmarkTotalPropertyCount.setValue(String.valueOf(count));
    }

    public void onCloseClick(){
        getNavigator().onCloseButtonClick();
    }

    public void generateReport(){
        setDate();
        setDeviceId();
        getDailyTotalPropertyCountDailyReport();
        getDailyResidentialTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialTotalPropertyCountDailyReport();
        getDailyResidentialDoTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialDoTotalPropertyCountDailyReport();
        getDailyResidentialDcTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialDcTotalPropertyCountDailyReport();
        getDailyResidentialPdcTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialPdcTotalPropertyCountDailyReport();
        getDailyResidentialGlTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialGlTotalPropertyCountDailyReport();
        getDailyResidentialNcTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialNcTotalPropertyCountDailyReport();
        getDailyResidentialOgTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialOgTotalPropertyCountDailyReport();
        getDailyResidentialOwrTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialOwrTotalPropertyCountDailyReport();
        getDailyResidentialRbTotalPropertyCountDailyReport();
        getDailyTeleCallResidentialRbTotalPropertyCountDailyReport();
        getDailyNonResidentialTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialTotalPropertyCountDailyReport();
        getDailyNonResidentialDoTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialDoTotalPropertyCountDailyReport();
        getDailyNonResidentialDcTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialDcTotalPropertyCountDailyReport();
        getDailyNonResidentialPdcTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialPdcTotalPropertyCountDailyReport();
        getDailyNonResidentialGlTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialGlTotalPropertyCountDailyReport();
        getDailyNonResidentialNcTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialNcTotalPropertyCountDailyReport();
        getDailyNonResidentialOgTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialOgTotalPropertyCountDailyReport();
        getDailyNonResidentialOwrTotalPropertyCountDailyReport();
        getDailyTeleCallNonResidentialOwrTotalPropertyCountDailyReport();
        getDailyDemolishedTotalPropertyCountDailyReport();
        getDailyTeleCallDemolishedTotalPropertyCountDailyReport();
        getDailyUnusableTotalPropertyCountDailyReport();
        getDailyTeleCallUnusableTotalPropertyCountDailyReport();
        getDailyUnwantedTotalPropertyCountDailyReport();
        getDailyTeleCallUnwantedTotalPropertyCountDailyReport();
        getDailyLandmarkTotalPropertyCountDailyReport();
        getDailyTeleCallLandmarkTotalPropertyCountDailyReport();
        getDailyWardNumbersDailyReport();

    }
}
