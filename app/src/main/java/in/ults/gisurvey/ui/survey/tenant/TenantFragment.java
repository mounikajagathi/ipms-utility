package in.ults.gisurvey.ui.survey.tenant;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.databinding.FragmentTenantBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.PostOfficeListAdapter;
import in.ults.gisurvey.utils.adapters.StateListAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class TenantFragment extends BaseFragment<FragmentTenantBinding, TenantViewModel> implements TenantNavigator {

    public static final String TAG = TenantFragment.class.getSimpleName();


    static final int TENANT_NAME_ERROR = 1;
    static final int TENANT_HOUSE_ERROR = 3;
    static final int TENANT_STREET_ERROR = 4;
    static final int TENANT_SURVEY_NUMBER_ERROR = 5;
    static final int TENANT_STATE_ERROR = 6;
    static final int TENANT_POST_OFFICE_ERROR = 7;
    static final int TENANT_PIN_ERROR = 8;
    static final int TENANT_MOBILE_ERROR = 9;
    static final int TENANT_EMAIL_ERROR = 10;
    static final int TENANT_LAND_LINE_ERROR = 11;
    static final int TENANT_AMOUNT_ERROR = 12;
    static final int TENANT_NATIVE_ERROR = 13;
    static final int TENANT_STATUS_ERROR = 14;

    private TenantViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    CommonDropDownAdapter nativeAdapter;

    @Inject
    CommonDropDownAdapter statusAdapter;

    @Inject
    StateListAdapter stateListAdapter;

    @Inject
    PostOfficeListAdapter postOfficeListAdapter;

    public static TenantFragment newInstance() {
        Bundle args = new Bundle();
        TenantFragment fragment = new TenantFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tenant;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public TenantViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(TenantViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_tenant);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        getViewDataBinding().etState.setAdapter(stateListAdapter);
        getViewDataBinding().etPostOffice.setAdapter(postOfficeListAdapter);
        getViewDataBinding().etNative.setAdapter(nativeAdapter);
        getViewDataBinding().etTenantStatus.setAdapter(statusAdapter);


        getViewDataBinding().etState.setOnItemClickListener((parent, view, position, id) -> {
            String state = stateListAdapter.getItem(position);
            viewModel.setTenantState(state);
            if (state != null && IPMSApp.getAppInstance().getPostOffice().containsKey(state)) {
                ArrayList<CommonItem> postOffice = IPMSApp.getAppInstance().getPostOffice().get(state);
                viewModel.setPostOfficeList(postOffice);
            }
        });

        getViewDataBinding().etPostOffice.setOnItemClickListener((parent, view, position, id) -> {
            CommonItem postOffice = postOfficeListAdapter.getItem(position);
            if (postOffice != null && postOffice.getSubContent() != null) {
                viewModel.setTenantPostOffice(postOffice.getContent());
                viewModel.setTenantPinCode(postOffice.getSubContent());
            }

        });

        getViewDataBinding().etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String data = String.valueOf(s);
                getViewDataBinding().etPostOffice.setEnabled(false);
                getViewDataBinding().etPinCode.setEnabled(false);
                if (data.length() == 0) {
                    viewModel.setTenantPostOffice("");
                    viewModel.setTenantPinCode("");
                } else if (data.equalsIgnoreCase(AppConstants.NR_UPPERCASE)) {
                    viewModel.setTenantPostOffice(AppConstants.NR_UPPERCASE);
                    viewModel.setTenantPinCode(AppConstants.NR_UPPERCASE);
                } else {
                    getViewDataBinding().etPostOffice.setEnabled(true);
                    getViewDataBinding().etPinCode.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getViewDataBinding().etPostOffice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String data = String.valueOf(s);
                if (data.length() == 0) {
                    viewModel.setTenantPinCode("");
                } else if (data.equalsIgnoreCase(AppConstants.NR_UPPERCASE)) {
                    viewModel.setTenantPinCode(AppConstants.NR_UPPERCASE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getViewDataBinding().etNative.setOnItemClickListener((parent, view, position, id) -> {
            String tenantNative = IPMSApp.getAppInstance().getLocMainItem().getTenantNatives().get(position).getContent();
            viewModel.setTenantNativeRelatedData(tenantNative);
        });

        viewModel.loadData();
        viewModel.getCurrentSurveyProperty();
    }

    /**
     * to validate fields and save tenant details
     */
    @Override
    public void saveTenantDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String tenantName = Objects.requireNonNull(getViewDataBinding().etTenantName.getText()).toString().trim();
        String tenantHouseNameNumber = Objects.requireNonNull(getViewDataBinding().etHouseName.getText()).toString().trim();
        String tenantStreetPlaceName = Objects.requireNonNull(getViewDataBinding().etStreetName.getText()).toString().trim();
        String tenantSurveyNumber = Objects.requireNonNull(getViewDataBinding().etTenantSurveyNumber.getText()).toString().trim();
        String tenantState = Objects.requireNonNull(getViewDataBinding().etState.getText()).toString().trim();
        String tenantPostOffice = Objects.requireNonNull(getViewDataBinding().etPostOffice.getText()).toString().trim();
        String tenantPincode = Objects.requireNonNull(getViewDataBinding().etPinCode.getText()).toString().trim();
        String tenantLandLine = Objects.requireNonNull(getViewDataBinding().etLandLine.getText()).toString();
        String tenantMobile = Objects.requireNonNull(getViewDataBinding().etPhoneNumber.getText()).toString();
        String tenantEmail = Objects.requireNonNull(getViewDataBinding().etEmail.getText()).toString();
        String tenantAmount = Objects.requireNonNull(getViewDataBinding().etAmount.getText()).toString();
        String tenantNative = Objects.requireNonNull(getViewDataBinding().etNative.getText()).toString();
        String tenantStatus = Objects.requireNonNull(getViewDataBinding().etTenantStatus.getText()).toString();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(tenantName, tenantHouseNameNumber, tenantStreetPlaceName, tenantState, tenantPostOffice, tenantPincode, tenantLandLine, tenantMobile, tenantEmail, tenantAmount, tenantNative, tenantSurveyNumber, tenantStatus)) {
                viewModel.insertTenantDetails(tenantName, tenantHouseNameNumber, tenantStreetPlaceName, tenantState, tenantPostOffice, tenantPincode, tenantLandLine, tenantMobile, tenantEmail, tenantAmount, tenantNative, tenantSurveyNumber, tenantStatus,true);
            }

        }else {
            //Partial Saving
            //No need od validation
            viewModel.insertTenantDetails(tenantName, tenantHouseNameNumber, tenantStreetPlaceName, tenantState, tenantPostOffice, tenantPincode, tenantLandLine, tenantMobile, tenantEmail, tenantAmount, tenantNative, tenantSurveyNumber, tenantStatus,false);
        }
    }

    /**
     * set validation error message
     *
     * @param code
     * @param error
     */
    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case TENANT_NAME_ERROR:
                getViewDataBinding().layoutTenantName.setError(error);
                getViewDataBinding().layoutTenantName.getParent().requestChildFocus
                        (getViewDataBinding().layoutTenantName,getViewDataBinding().layoutTenantName);
                break;
        case TENANT_HOUSE_ERROR:
                getViewDataBinding().layoutHouseName.setError(error);
                getViewDataBinding().layoutHouseName.getParent().requestChildFocus
                        (getViewDataBinding().layoutHouseName,getViewDataBinding().layoutHouseName);
                break;
        case TENANT_STREET_ERROR:
                getViewDataBinding().layoutStreetName.setError(error);
                getViewDataBinding().layoutStreetName.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetName,getViewDataBinding().layoutStreetName);
                break;
        case TENANT_SURVEY_NUMBER_ERROR:
                getViewDataBinding().layoutTenantSurveyNumber.setError(error);
                getViewDataBinding().layoutTenantSurveyNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutTenantSurveyNumber,getViewDataBinding().layoutTenantSurveyNumber);
                break;
        case TENANT_STATE_ERROR:
                getViewDataBinding().layoutState.setError(error);
                getViewDataBinding().layoutState.getParent().requestChildFocus
                        (getViewDataBinding().layoutState,getViewDataBinding().layoutState);
                break;
        case TENANT_POST_OFFICE_ERROR:
                getViewDataBinding().layoutPostOffice.setError(error);
                getViewDataBinding().layoutPostOffice.getParent().requestChildFocus
                        (getViewDataBinding().layoutPostOffice,getViewDataBinding().layoutPostOffice);
                break;
        case TENANT_PIN_ERROR:
                getViewDataBinding().layoutPinCode.setError(error);
                getViewDataBinding().layoutPinCode.getParent().requestChildFocus
                        (getViewDataBinding().layoutPinCode,getViewDataBinding().layoutPinCode);
                break;
        case TENANT_MOBILE_ERROR:
                getViewDataBinding().layoutPhoneNumber.setError(error);
                getViewDataBinding().layoutPhoneNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutPhoneNumber,getViewDataBinding().layoutPhoneNumber);
                break;
        case TENANT_LAND_LINE_ERROR:
                getViewDataBinding().layoutLandLine.setError(error);
                getViewDataBinding().layoutLandLine.getParent().requestChildFocus
                        (getViewDataBinding().layoutLandLine,getViewDataBinding().layoutLandLine);
                break;
        case TENANT_EMAIL_ERROR:
                getViewDataBinding().layoutEmail.setError(error);
                getViewDataBinding().layoutEmail.getParent().requestChildFocus
                        (getViewDataBinding().layoutEmail,getViewDataBinding().layoutEmail);
                break;
        case TENANT_AMOUNT_ERROR:
                getViewDataBinding().layoutAmount.setError(error);
                getViewDataBinding().layoutAmount.getParent().requestChildFocus
                        (getViewDataBinding().layoutAmount,getViewDataBinding().layoutAmount);
                break;
        case TENANT_NATIVE_ERROR:
                getViewDataBinding().layoutNative.setError(error);
                getViewDataBinding().layoutNative.getParent().requestChildFocus
                        (getViewDataBinding().layoutNative,getViewDataBinding().layoutNative);
                break;
        case TENANT_STATUS_ERROR:
                getViewDataBinding().layoutTenantStatus.setError(error);
                getViewDataBinding().layoutTenantStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutTenantStatus,getViewDataBinding().layoutTenantStatus);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutTenantName.setErrorEnabled(false);
        getViewDataBinding().layoutHouseName.setErrorEnabled(false);
        getViewDataBinding().layoutStreetName.setErrorEnabled(false);
        getViewDataBinding().layoutTenantSurveyNumber.setErrorEnabled(false);
        getViewDataBinding().layoutState.setErrorEnabled(false);
        getViewDataBinding().layoutPostOffice.setErrorEnabled(false);
        getViewDataBinding().layoutPinCode.setErrorEnabled(false);
        getViewDataBinding().layoutPhoneNumber.setErrorEnabled(false);
        getViewDataBinding().layoutLandLine.setErrorEnabled(false);
        getViewDataBinding().layoutEmail.setErrorEnabled(false);
        getViewDataBinding().layoutAmount.setErrorEnabled(false);
        getViewDataBinding().layoutNative.setErrorEnabled(false);
        getViewDataBinding().layoutTenantStatus.setErrorEnabled(false);

    }

    @Override
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
    }


    @Override
    public void navigateToTaxPage() {
        getBaseActivity().showTaxFragment(true);
    }

    @Override
    public void navigateToEstablishmentPage() {
        getBaseActivity().showEstablishmentFragment(true);
    }

    @Override
    public void navigateToMemberPage() {
        getBaseActivity().showMemberFragment(true);
    }

    @Override
    public void navigateToLiveHoodPage() {
        getBaseActivity().showLiveHoodFragment(true);
    }

    @Override
    public void navigateToMorePage() {
            getBaseActivity().showMoreFragment(true);
    }

    @Override
    public void navigateToBuildingPage() {
        getBaseActivity().showBuildingFragment(true);
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
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
    public void showSurveyNoNRNoLandPopUp() {
        PopupMenu popup = new PopupMenu(getBaseActivity(), getViewDataBinding().nrTenantSurveyNumber);
        popup.inflate(R.menu.nr_no_land_menu);
        popup.setOnMenuItemClickListener(item1 -> {
            switch (item1.getItemId()) {
                case R.id.NR:
                    viewModel.tenantSurveyNumber.setValue(AppConstants.NR_UPPERCASE);
                    break;
                case R.id.NOLand:
                    viewModel.tenantSurveyNumber.setValue(AppConstants.NO_LAND);
                    break;
            }
            popup.dismiss();
            return false;
        });
        popup.show();
    }

}