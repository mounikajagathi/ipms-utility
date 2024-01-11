package in.ults.gisurvey.ui.survey.establishment;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentEstablishmentBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class EstablishmentFragment extends BaseFragment<FragmentEstablishmentBinding, EstablishmentViewModel> implements EstablishmentNavigator {

    public static final String TAG = EstablishmentFragment.class.getSimpleName();
    static final int NAME_ERROR = 1;
    static final int TYPE_ERROR = 2;
    static final int IN_CHARGE_ERROR = 3;
    static final int IN_CHARGE_ROLE_ERROR = 4;
    static final int EMPLOYEE_COUNT_ERROR = 5;
    static final int LICENSE_NUMBER_ERROR = 6;
    static final int EMAIL = 7;
    static final int LANDLINE = 8;
    static final int MOBILE = 9;
    static final int ESTABLISHMENT_YEAR_ERROR = 10;
    static final int GST_STATUS_ERROR = 11;
    private int currentYear;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    CommonDropDownAdapter gstStatusAdapter;

    private EstablishmentViewModel viewModel;

    public static EstablishmentFragment newInstance() {
        Bundle args = new Bundle();
        EstablishmentFragment fragment = new EstablishmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_establishment;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public EstablishmentViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(EstablishmentViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_establishment);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        currentYear = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
        getViewDataBinding().etEstablishmentGSTStatus.setAdapter(gstStatusAdapter);
        getViewDataBinding().etEstablishmentYear.addTextChangedListener(establishmentYearListener);
        viewModel.loadData();
        viewModel.getCurrentSurveyProperty();
    }


    private final TextWatcher establishmentYearListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 4) {
                int yearData = Integer.parseInt(String.valueOf(s));
                if (yearData > currentYear) {
                    getViewDataBinding().etEstablishmentYear.removeTextChangedListener(establishmentYearListener);
                    getViewDataBinding().etEstablishmentYear.setText("");
                    getViewDataBinding().etEstablishmentYear.addTextChangedListener(establishmentYearListener);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    /**
     * to validate fields and save establishment details
     */
    @Override
    public void saveEstablishmentDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String establishmentName = Objects.requireNonNull(getViewDataBinding().etEstablishmentName.getText()).toString().trim();
        String establishmentType = Objects.requireNonNull(getViewDataBinding().etEstablishmentType.getText()).toString().trim();
        String inCharge = Objects.requireNonNull(getViewDataBinding().etInCharge.getText()).toString().trim();
        String inChargeRole = Objects.requireNonNull(getViewDataBinding().etInChargeRole.getText()).toString().trim();
        String establishmentYear = Objects.requireNonNull(getViewDataBinding().etEstablishmentYear.getText()).toString().trim();
        String employeeCount = Objects.requireNonNull(getViewDataBinding().etEmployeeCount.getText()).toString().trim();
        String licenseNumber = Objects.requireNonNull(getViewDataBinding().etLicenseNumber.getText()).toString().trim();
        String gstStatus = Objects.requireNonNull(getViewDataBinding().etEstablishmentGSTStatus.getText()).toString().trim();
        String establishmentEmail = Objects.requireNonNull(Objects.requireNonNull(getViewDataBinding().etEmail.getText()).toString());
        String establishmentLandline = Objects.requireNonNull(Objects.requireNonNull(getViewDataBinding().etLandLine.getText()).toString());
        String establishmentMobile = Objects.requireNonNull(Objects.requireNonNull(getViewDataBinding().etMobile.getText()).toString());

        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(establishmentName, establishmentType, inCharge, inChargeRole, establishmentYear, employeeCount, licenseNumber, gstStatus, establishmentEmail, establishmentLandline, establishmentMobile)) {
                viewModel.saveEstablishmentDetails(establishmentName, establishmentType, inCharge, inChargeRole, establishmentYear, employeeCount, licenseNumber, gstStatus, establishmentEmail, establishmentLandline, establishmentMobile,true);
            }

        }else {
            //Partial Saving
            //No need od validation
            viewModel.saveEstablishmentDetails(establishmentName, establishmentType, inCharge, inChargeRole, establishmentYear, employeeCount, licenseNumber, gstStatus, establishmentEmail, establishmentLandline, establishmentMobile,false);
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
            case NAME_ERROR:
                getViewDataBinding().layoutEstablishmentName.setError(error);
                getViewDataBinding().layoutEstablishmentName.getParent().requestChildFocus
                        (getViewDataBinding().layoutEstablishmentName, getViewDataBinding().layoutEstablishmentName);
                break;
             case TYPE_ERROR:
                getViewDataBinding().layoutEstablishmentType.setError(error);
                getViewDataBinding().layoutEstablishmentType.getParent().requestChildFocus
                        (getViewDataBinding().layoutEstablishmentType, getViewDataBinding().layoutEstablishmentType);
                break;
             case IN_CHARGE_ERROR:
                getViewDataBinding().layoutInCharge.setError(error);
                getViewDataBinding().layoutInCharge.getParent().requestChildFocus
                        (getViewDataBinding().layoutInCharge, getViewDataBinding().layoutInCharge);
                break;
             case IN_CHARGE_ROLE_ERROR:
                getViewDataBinding().layoutInChargeRole.setError(error);
                getViewDataBinding().layoutInChargeRole.getParent().requestChildFocus
                        (getViewDataBinding().layoutInChargeRole, getViewDataBinding().layoutInChargeRole);
                break;
             case EMPLOYEE_COUNT_ERROR:
                getViewDataBinding().layoutEmployeeCount.setError(error);
                getViewDataBinding().layoutEmployeeCount.getParent().requestChildFocus
                        (getViewDataBinding().layoutEmployeeCount, getViewDataBinding().layoutEmployeeCount);
                break;
             case LICENSE_NUMBER_ERROR:
                getViewDataBinding().layoutLicenseNumber.setError(error);
                getViewDataBinding().layoutLicenseNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutLicenseNumber, getViewDataBinding().layoutLicenseNumber);
                break;
            case EMAIL:
                getViewDataBinding().layoutEmail.setError(error);
                getViewDataBinding().layoutEmail.getParent().requestChildFocus
                        (getViewDataBinding().layoutEmail, getViewDataBinding().layoutEmail);
                break;
            case LANDLINE:
                getViewDataBinding().layoutLandLine.setError(error);
                getViewDataBinding().layoutLandLine.getParent().requestChildFocus
                        (getViewDataBinding().layoutLandLine, getViewDataBinding().layoutLandLine);
                break;
            case MOBILE:
                getViewDataBinding().layoutMobile.setError(error);
                getViewDataBinding().layoutMobile.getParent().requestChildFocus
                        (getViewDataBinding().layoutMobile, getViewDataBinding().layoutMobile);
                break;
            case ESTABLISHMENT_YEAR_ERROR:
                getViewDataBinding().layoutEstablishmentYear.setError(error);
                getViewDataBinding().layoutEstablishmentYear.getParent().requestChildFocus
                        (getViewDataBinding().layoutEstablishmentYear, getViewDataBinding().layoutEstablishmentYear);
                break;
            case GST_STATUS_ERROR:
                getViewDataBinding().layoutEstablishmentGSTStatus.setError(error);
                getViewDataBinding().layoutEstablishmentGSTStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutEstablishmentGSTStatus, getViewDataBinding().layoutEstablishmentGSTStatus);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutEstablishmentName.setErrorEnabled(false);
        getViewDataBinding().layoutEstablishmentType.setErrorEnabled(false);
        getViewDataBinding().layoutEstablishmentYear.setErrorEnabled(false);
        getViewDataBinding().layoutInCharge.setErrorEnabled(false);
        getViewDataBinding().layoutInChargeRole.setErrorEnabled(false);
        getViewDataBinding().layoutEmployeeCount.setErrorEnabled(false);
        getViewDataBinding().layoutLicenseNumber.setErrorEnabled(false);
        getViewDataBinding().layoutEmail.setErrorEnabled(false);
        getViewDataBinding().layoutLandLine.setErrorEnabled(false);
        getViewDataBinding().layoutMobile.setErrorEnabled(false);
        getViewDataBinding().layoutEstablishmentGSTStatus.setErrorEnabled(false);
    }
    @Override
    public void showLicenceNANRPopUp() {
        PopupMenu popupMenu = new PopupMenu(getBaseActivity(), getViewDataBinding().nrLicenseNumber);
        popupMenu.inflate(R.menu.na_nr_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.NR:
                    viewModel.licenseNumber.setValue(AppConstants.NR_UPPERCASE);
                    break;
                case R.id.NA:
                    viewModel.licenseNumber.setValue(AppConstants.NA_UPPERCASE);
                    break;
            }
            popupMenu.dismiss();
            return false;
        });
        popupMenu.show();
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
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
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
    public void navigateToBuildingPage() {
        getBaseActivity().showBuildingFragment(true);
    }

    @Override
    public void navigateToMorePage() {
        getBaseActivity().showMoreFragment(true);
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }
}