package in.ults.gisurvey.ui.survey.more;

import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.VehicleDetailsItem;
import in.ults.gisurvey.databinding.FragmentMoreBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewMultiSelectAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class MoreFragment extends BaseFragment<FragmentMoreBinding, MoreViewModel> implements MoreNavigator {

    public static final String TAG = MoreFragment.class.getSimpleName();

    static final int COMMON_ERROR = 1;
    static final int TYPE_LAND_ERROR = 2;
    static final int BUILDING_UNDER_SCHEME_ERROR = 3;
    static final int PLOT_AREA_ERROR = 4;
    static final int VEHICLE_DETAILS_ERROR = 5;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    FlexboxLayoutManager layoutManagerPerennialMonth;

    @Inject
    FlexRecyclerViewMultiSelectAdapter perennialMonthAdapter;

    @Inject
    CommonDropDownAdapter typeOfLandTypeAdapter;

    @Inject
    CommonDropDownAdapter buildingUnderScheme;

    @Inject
    VehicleAdapter vehicleAdapter;

    @Inject
    LinearLayoutManager vehicleLayoutManager;


    private MoreViewModel viewModel;

    public static MoreFragment newInstance() {
        Bundle args = new Bundle();
        MoreFragment moreFragment = new MoreFragment();
        moreFragment.setArguments(args);
        return moreFragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public MoreViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MoreViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_more);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            vehicleAdapter.updateSurveyOpenEditMode(false);
        }
        getViewDataBinding().rvVehicles.setLayoutManager(vehicleLayoutManager);
        getViewDataBinding().rvVehicles.setAdapter(vehicleAdapter);
        getViewDataBinding().etTypeOfLand.setAdapter(typeOfLandTypeAdapter);
        getViewDataBinding().etBuildingUnderScheme.setAdapter(buildingUnderScheme);

        viewModel.loadData();
        viewModel.getCurrentSurveyProperty();


    }

    @Override
    public void saveMoreDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String typeOfLand = AppConstants.NR_UPPERCASE; //Not needed this field for this particular build
        String buildingUnderScheme = Objects.requireNonNull(getViewDataBinding().etBuildingUnderScheme.getText()).toString().trim();
        String plotArea = Objects.requireNonNull(getViewDataBinding().etPlotArea.getText()).toString().trim();
        String thozhilurapp = "", kudumbasree = "", healthInsurance = "";

        if (getViewDataBinding().rgThozhilurapp.getCheckedRadioButtonId() != -1) {
            thozhilurapp = ((RadioButton) getViewDataBinding().rgThozhilurapp.findViewById(getViewDataBinding().rgThozhilurapp.getCheckedRadioButtonId())).getText().toString();
        }

        if (getViewDataBinding().rgKudumbasree.getCheckedRadioButtonId() != -1) {
            kudumbasree = ((RadioButton) getViewDataBinding().rgKudumbasree.findViewById(getViewDataBinding().rgKudumbasree.getCheckedRadioButtonId())).getText().toString();
        }

        if (getViewDataBinding().rgHealthInsurance.getCheckedRadioButtonId() != -1) {
            healthInsurance = ((RadioButton) getViewDataBinding().rgHealthInsurance.findViewById(getViewDataBinding().rgHealthInsurance.getCheckedRadioButtonId())).getText().toString();
        }

        String noOfVehicle="";
        if(!viewModel.buildingStatusOnGoingWithoutRoof.get()){
            noOfVehicle = Objects.requireNonNull(getViewDataBinding().etNoOfVehicles.getText()).toString().trim();
        }

        ArrayList<VehicleDetailsItem> vehicleDetails = vehicleAdapter.getDataList();
        if(!isPartial) {
            //Data submission
            //So validation is necessary
            //Now need to check member details submission completed or not
            //bcoz building sceheme v/s family member job checking doing here

            boolean isValidationAlertNeed=false;
            if(viewModel.isMemberVisible.get())
            {
                if(!viewModel.isMemberValidationok.get()){
                    isValidationAlertNeed=true;
                }
            }
            if(!isValidationAlertNeed){
                if (viewModel.validateFields(typeOfLand, buildingUnderScheme, plotArea, noOfVehicle, vehicleDetails, thozhilurapp, kudumbasree, healthInsurance)) {
                    if (viewModel.isBuildingSchemeApplicable(buildingUnderScheme)) {
                        viewModel.insertMoreDetails(typeOfLand, buildingUnderScheme, plotArea, noOfVehicle, vehicleDetails, thozhilurapp, kudumbasree, healthInsurance,true);

                    } else {
                        String finalThozhilurapp = thozhilurapp;
                        String finalKudumbasree = kudumbasree;
                        String finalHealthInsurance = healthInsurance;
                        String finalNoOfVehicle = noOfVehicle;
                        showDialog(getString(R.string.cmn_warning), getString(R.string.warning_building_scheme),
                                getString(R.string.cmn_continue), (dialog, which) -> {
                                    viewModel.insertMoreDetails(typeOfLand, buildingUnderScheme, plotArea, finalNoOfVehicle, vehicleDetails, finalThozhilurapp, finalKudumbasree, finalHealthInsurance,true);
                                }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());
                    }
                }
            }else{
                showToast(getString(R.string.more_family_data_submission_warning));
            }
        }else{
            //Partial Saving
            //No need od validation
            viewModel.insertMoreDetails(typeOfLand, buildingUnderScheme, plotArea, noOfVehicle, vehicleDetails, thozhilurapp, kudumbasree, healthInsurance,false);
        }


    }

    @Override
    public void disablePartialSave() {
        getViewDataBinding().btnPartialSave.setEnabled(false);
        getViewDataBinding().btnPartialSave.setBackgroundColor(getResources().getColor(R.color.cmn_inactive_btn_color));
    }

    @Override
    public void navigateToScreenSelection() {
        super.onFragmentBackPressed();
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case TYPE_LAND_ERROR:
                getViewDataBinding().layoutTypeOfLand.setError(error);
                getViewDataBinding().layoutTypeOfLand.getParent().requestChildFocus
                        (getViewDataBinding().layoutTypeOfLand,getViewDataBinding().layoutTypeOfLand);
                break;
            case PLOT_AREA_ERROR:
                getViewDataBinding().layoutPlotArea.setError(error);
                getViewDataBinding().layoutPlotArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutPlotArea,getViewDataBinding().layoutPlotArea);
                break;
            case BUILDING_UNDER_SCHEME_ERROR:
                getViewDataBinding().layoutBuildingUnderScheme.setError(error);
                getViewDataBinding().layoutBuildingUnderScheme.getParent().requestChildFocus
                        (getViewDataBinding().layoutBuildingUnderScheme,getViewDataBinding().layoutBuildingUnderScheme);
                break;
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
            case VEHICLE_DETAILS_ERROR:
                vehicleAdapter.setValidation(true);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutTypeOfLand.setErrorEnabled(false);
        getViewDataBinding().layoutPlotArea.setErrorEnabled(false);
        getViewDataBinding().layoutBuildingUnderScheme.setErrorEnabled(false);
        vehicleAdapter.setValidation(false);

    }

    @Override
    public void navigateToBuildingPage() {
        getBaseActivity().showBuildingFragment(true);
    }

    @Override
    public void navigateToImagePage() {
        getBaseActivity().showImageFragment(true);
    }

    @Override
    public void addVehicle() {
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfVehicles.getText()).toString());
        if (value > -1) {
            getViewDataBinding().etNoOfVehicles.setText(String.valueOf(value + 1));
            vehicleAdapter.addNewItem();
        }
    }

    @Override
    public void removeVehicle() {
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfVehicles.getText()).toString());
        if (value > 0) {
            getViewDataBinding().etNoOfVehicles.setText(String.valueOf(value - 1));
            vehicleAdapter.removeItem();
        }
    }

    @Override
    public void setCachedData(Property property) {
        if (!viewModel.doorStatusPDCDCGL.get() && !viewModel.isBuildStatusDemolishedUnusable.get()) {
            if(viewModel.establishmentUsageHouseFlatVilla.get()) {
                getViewDataBinding().rgThozhilurapp.check(property.thozhilurapp.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbThozhilurappYes : property.thozhilurapp.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbThozhilurappNo : -1);
                getViewDataBinding().rgKudumbasree.check(property.kudumbasree.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbKudumbasreeYes : property.kudumbasree.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbKudumbasreeNo : -1);
                getViewDataBinding().rgHealthInsurance.check(property.healthInsurance.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbHealthInsuranceYes : property.healthInsurance.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbHealthInsuranceNo : -1);
                vehicleAdapter.setDataList(property.vehicleDetails);

                if (viewModel.isWellSelected.get() && viewModel.isWellPerennial.get()) {
                    perennialMonthAdapter.setSelectedData(property.wellPerennialMonth);
                }
            }

        }
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }

}
