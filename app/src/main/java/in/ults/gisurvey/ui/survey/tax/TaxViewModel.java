package in.ults.gisurvey.ui.survey.tax;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.NA_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.NR_UPPERCASE;

public class TaxViewModel extends BaseViewModel<TaxNavigator> {

    public final MutableLiveData<String> taxNumber = new MutableLiveData<>();
    public final MutableLiveData<String> taxAmount = new MutableLiveData<>();
    public final MutableLiveData<String> taxDate = new MutableLiveData<>();
    public final MutableLiveData<String> taxYear = new MutableLiveData<>();
    public final MutableLiveData<String> taxAnnualAmount = new MutableLiveData<>();
    public final ObservableBoolean isARTaxtTotalVisible = new ObservableBoolean(false);
    public final MutableLiveData<String> arTaxtTotal = new MutableLiveData<>("");

    private String buildingStatus = "";
    private String doorStatus = "";
    private String buildingType = "";
    private String buildingSubType = "";

    public TaxViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        taxYear.setValue("");
    }

    public void setTaxDate(String taxDate) {
        this.taxDate.setValue(taxDate);
    }

    public void setTaxYear(String taxYear) {
        this.taxYear.setValue(taxYear);
    }

    protected boolean validateFields(String taxNumber, String taxAmount, String taxDate, String taxAnnualAmount) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(taxNumber)) {
            getNavigator().validationError(TaxFragment.TAX_NUMBER_ERROR, getBaseActivity().getString(R.string.error_tax_number));
            return false;
        }else if (TextUtils.isEmpty(taxAmount)) {
            getNavigator().validationError(TaxFragment.TAX_AMOUNT_ERROR, getBaseActivity().getString(R.string.error_tax_amount));
            return false;
        }else if (TextUtils.isEmpty(taxDate)) {
            getNavigator().validationError(TaxFragment.TAX_DATE_ERROR, getBaseActivity().getString(R.string.error_tax_paid_date));
            return false;
        }else if (TextUtils.isEmpty(taxAnnualAmount)) {
            getNavigator().validationError(TaxFragment.TAX_ANNUL_TAX_AMOUNT_ERROR, getBaseActivity().getString(R.string.error_tax_annual_amount));
            return false;
        }
        return true;
    }

    /**
     * to save tax details in db  and navigate to next page
     *
     * @param taxNumber
     * @param taxAmount
     * @param taxYear
     * @param taxDate
     */
    void saveTaxDetails(String taxNumber, String taxAmount, String taxYear, String taxDate, String taxAnnualAmount,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertTaxDetails(taxNumber, taxAmount, taxYear, taxDate, taxAnnualAmount,isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    if (canIGoToEstablishmentDetails(buildingStatus, buildingSubType)) {
//                        getNavigator().navigateToEstablishmentPage();
//                    } else if (canIGoToMemberDetails(buildingStatus, buildingType, buildingSubType, doorStatus)) {
//                        getNavigator().navigateToMemberPage();
//                    } else if (canIGoToLiveHoodDetails(buildingStatus, buildingSubType,doorStatus)) {
//                        getNavigator().navigateToLiveHoodPage();
//                    }else if (canIGoToMoreDetails(buildingStatus,doorStatus,buildingType,buildingSubType)) {
//                        getNavigator().navigateToMorePage();
//                    } else if (canIGoToBuildingDetails(buildingStatus,doorStatus)) {
//                        getNavigator().navigateToBuildingPage();
//                    }  else {
                        getNavigator().navigateToScreenSelection();
//                    }
                })
                .subscribe());
    }

    /**
     * to fetch property details from db, when back pressed and to set fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        taxNumber.setValue(property.taxNumber);
        taxAmount.setValue(property.taxAmount);
        taxDate.setValue(property.taxDate);
        taxYear.setValue(property.taxYear);
        taxAnnualAmount.setValue(property.taxAnnualAmount);
        doorStatus = property.doorStatus;
        buildingStatus = property.buildingStatus;
        buildingType = property.buildingType;
        buildingSubType = property.buildingSubType;
        getNavigator().setCachedData(property);
        if(property.arTaxToatal.length()!=0&&!property.arTaxToatal.equalsIgnoreCase(NR_UPPERCASE)&&!property.arTaxToatal.equalsIgnoreCase(NA_UPPERCASE)){
            isARTaxtTotalVisible.set(true);
            arTaxtTotal.setValue(getBaseActivity().getString(R.string.tax_ar_annual_hint)+property.arTaxToatal);
        }
        if(property.isTaxValidationOk)
            getNavigator().disablePartialSave();
    }

    public void onNextClick() {
        getNavigator().saveTaxDetails(false);
    }

    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveTaxDetails(true);
    }

    public void onYearClick() {
        getNavigator().onChooseYear();
    }

    public void onNRClick(View v) {
//        if(buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING)){
//            getNavigator().showNANRMenu(v);
//        }else {
            switch (v.getId()) {
                case R.id.nrTaxNumber:
                    getNavigator().showNANROption();
                    break;
                case R.id.nrTaxAmount:
                    taxAmount.setValue(AppConstants.NR_UPPERCASE);
                    break;
                case R.id.nrTaxDate:
                case R.id.nrTaxYear:
                    taxDate.setValue(AppConstants.NR_UPPERCASE);
                    taxYear.setValue(AppConstants.NR_UPPERCASE);
                    break;
                case R.id.nrAnnualTax:
                    taxAnnualAmount.setValue(AppConstants.NR_UPPERCASE);
                    break;

            }
//        }

    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnTaxReceiptNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tax_receipt_no),InfoVideoNames.TAX_RECEIPT_NUMBER_INFO_VIDEO);
                break;
            case R.id.btnTaxAmountInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tax_amount),InfoVideoNames.TAX_AMOUNT_INFO_VIDEO);
                break;
            case R.id.btnTaxPaidDateInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tax_paid_date),InfoVideoNames.TAX_PAID_DATE_INFO_VIDEO);
                break;
            case R.id.btnTaxyearInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tax_year),InfoVideoNames.TAX_PAID_YEAR_INFO_VIDEO);
                break;
            case R.id.btnTaxAnnualAmountInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tax_annual_amount),InfoVideoNames.TAX_ANNUAL_AMOUNT_INFO_VIDEO);
                break;

        }
    }
}