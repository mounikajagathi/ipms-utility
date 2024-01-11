package in.ults.gisurvey.ui.survey.owner;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.databinding.FragmentOwnerBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.PostOfficeListAdapter;
import in.ults.gisurvey.utils.adapters.StateListAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class OwnerFragment extends BaseFragment<FragmentOwnerBinding, OwnerViewModel> implements OwnerNavigator {

    public static final String TAG = OwnerFragment.class.getSimpleName();
    static final int OWNER_NAME_ERROR = 1;
    static final int OWNER_OCCUPATION_ERROR = 2;
    static final int OWNER_HOUSE_ERROR = 3;
    static final int OWNER_STREET_ERROR = 4;
    static final int OWNER_STATE_ERROR = 5;
    static final int OWNER_POST_OFFICE_ERROR = 6;
    static final int OWNER_PIN_ERROR = 7;
    static final int OWNER_MOBILE_ERROR = 8;
    static final int OWNER_EMAIL_ERROR = 9;
    static final int OWNER_LAND_LINE = 10;
    static final int TELE_CALL_ERROR = 11;

    private OwnerViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    PostOfficeListAdapter occupationAdapter;

    @Inject
    OwnerListAdapter ownerListAdapter;

    @Inject
    StateListAdapter stateListAdapter;

    @Inject
    PostOfficeListAdapter postOfficeListAdapter;


    public static OwnerFragment newInstance() {
        Bundle args = new Bundle();
        OwnerFragment fragment = new OwnerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_owner;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public OwnerViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(OwnerViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_owner);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        getViewDataBinding().etName.setAdapter(ownerListAdapter);
        getViewDataBinding().etOccupation.setAdapter(occupationAdapter);
        getViewDataBinding().etState.setAdapter(stateListAdapter);
        getViewDataBinding().etPostOffice.setAdapter(postOfficeListAdapter);
        getViewDataBinding().etName.setOnItemClickListener((parent, view, position, id) -> {
            Owner owner = ownerListAdapter.getItem(position);
            if (owner != null) {
                viewModel.setOwnerListData(owner.getOwnerOccupation(), owner.getOwnerHouseNameNumber(),
                        owner.getOwnerStreetPlaceName(),owner.getOwnerState(), owner.getOwnerPostOffice(), owner.getOwnerPincode(),
                        owner.getOwnerEmail(), owner.getOwnerLandLine(), owner.getOwnerMobile());
            }
        });

        getViewDataBinding().etState.setOnItemClickListener((parent, view, position, id) -> {
            String state = stateListAdapter.getItem(position);
            viewModel.setOwnerState(state);
            if (state != null && IPMSApp.getAppInstance().getPostOffice().containsKey(state)) {
                ArrayList<CommonItem> postOffice = IPMSApp.getAppInstance().getPostOffice().get(state);
                viewModel.setPostOfficeList(postOffice);
            }
        });

        getViewDataBinding().etPostOffice.setOnItemClickListener((parent, view, position, id) -> {
            CommonItem postOffice = postOfficeListAdapter.getItem(position);
            if (postOffice != null && postOffice.getSubContent() != null) {
                viewModel.setOwnerPostOffice(postOffice.getContent());
                viewModel.setOwnerPincode(postOffice.getSubContent());
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
                    viewModel.setOwnerPostOffice("");
                    viewModel.setOwnerPincode("");
                } else if (data.equalsIgnoreCase(AppConstants.NR_UPPERCASE)) {
                    viewModel.setOwnerPostOffice(AppConstants.NR_UPPERCASE);
                    viewModel.setOwnerPincode(AppConstants.NR_UPPERCASE);
                }else{
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
                    viewModel.setOwnerPincode("");
                } else if (data.equalsIgnoreCase(AppConstants.NR_UPPERCASE)) {
                    viewModel.setOwnerPincode(AppConstants.NR_UPPERCASE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        viewModel.loadData();
        viewModel.getCurrentSurveyProperty();
        viewModel.getOwnerList();
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
            case OWNER_NAME_ERROR:
                getViewDataBinding().layoutName.setError(error);
                getViewDataBinding().layoutName.getParent().requestChildFocus
                        (getViewDataBinding().layoutName,getViewDataBinding().layoutName);
                break;
            case OWNER_OCCUPATION_ERROR:
                getViewDataBinding().layoutOccupation.setError(error);
                getViewDataBinding().layoutOccupation.getParent().requestChildFocus
                        (getViewDataBinding().layoutOccupation,getViewDataBinding().layoutOccupation);
                break;
            case OWNER_HOUSE_ERROR:
                getViewDataBinding().layoutHouseName.setError(error);
                getViewDataBinding().layoutHouseName.getParent().requestChildFocus
                        (getViewDataBinding().layoutHouseName,getViewDataBinding().layoutHouseName);
                break;
            case OWNER_STREET_ERROR:
                getViewDataBinding().layoutStreetName.setError(error);
                getViewDataBinding().layoutStreetName.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetName,getViewDataBinding().layoutStreetName);
                break;
            case OWNER_STATE_ERROR:
                getViewDataBinding().layoutState.setError(error);
                getViewDataBinding().layoutState.getParent().requestChildFocus
                        (getViewDataBinding().layoutState,getViewDataBinding().layoutState);
                break;
            case OWNER_POST_OFFICE_ERROR:
                getViewDataBinding().layoutPostOffice.setError(error);
                getViewDataBinding().layoutPostOffice.getParent().requestChildFocus
                        (getViewDataBinding().layoutPostOffice,getViewDataBinding().layoutPostOffice);
                break;
            case OWNER_PIN_ERROR:
                getViewDataBinding().layoutPinCode.setError(error);
                getViewDataBinding().layoutPinCode.getParent().requestChildFocus
                        (getViewDataBinding().layoutPinCode,getViewDataBinding().layoutPinCode);
                break;
            case OWNER_MOBILE_ERROR:
                getViewDataBinding().layoutMobile.setError(error);
                getViewDataBinding().layoutMobile.getParent().requestChildFocus
                        (getViewDataBinding().layoutMobile,getViewDataBinding().layoutMobile);
                break;
            case OWNER_LAND_LINE:
                getViewDataBinding().layoutLandLine.setError(error);
                getViewDataBinding().layoutLandLine.getParent().requestChildFocus
                        (getViewDataBinding().layoutLandLine,getViewDataBinding().layoutLandLine);
                break;
            case OWNER_EMAIL_ERROR:
                getViewDataBinding().layoutEmail.setError(error);
                getViewDataBinding().layoutEmail.getParent().requestChildFocus
                        (getViewDataBinding().layoutEmail,getViewDataBinding().layoutEmail);
                break;
            case TELE_CALL_ERROR:
                getViewDataBinding().layoutTeleCallNumber.setError(error);
                getViewDataBinding().layoutTeleCallNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutTeleCallNumber,getViewDataBinding().layoutTeleCallNumber);
                break;
        }

    }

    /**
     * to validate fields and save owner details
     */
    @Override
    public void saveOwnerDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String ownerName = Objects.requireNonNull(getViewDataBinding().etName.getText()).toString().trim();
        String ownerOccupation = Objects.requireNonNull(getViewDataBinding().etOccupation.getText()).toString().trim();
        String ownerHouseNameNumber = Objects.requireNonNull(getViewDataBinding().etHouseName.getText()).toString().trim();
        String ownerStreetPlaceName = Objects.requireNonNull(getViewDataBinding().etStreetName.getText()).toString().trim();
        String ownerState = Objects.requireNonNull(getViewDataBinding().etState.getText()).toString().trim();
        String ownerPostOffice = Objects.requireNonNull(getViewDataBinding().etPostOffice.getText()).toString().trim();
        String ownerPincode = Objects.requireNonNull(getViewDataBinding().etPinCode.getText()).toString().trim();
        String ownerEmail = Objects.requireNonNull(getViewDataBinding().etEmail.getText()).toString().trim();
        String ownerLandLine = Objects.requireNonNull(getViewDataBinding().etLandLine.getText()).toString().trim();
        String ownerMobile = Objects.requireNonNull(getViewDataBinding().etMobile.getText()).toString().trim();
        String teleCallNumber = Objects.requireNonNull(getViewDataBinding().etTeleCallNumber.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(ownerName, ownerOccupation, ownerHouseNameNumber, ownerStreetPlaceName, ownerState, ownerPostOffice, ownerPincode, ownerEmail, ownerLandLine, ownerMobile, teleCallNumber)) {
                viewModel.saveOwnerDetails(ownerName, ownerOccupation, ownerHouseNameNumber, ownerStreetPlaceName, ownerState, ownerPostOffice, ownerPincode, ownerEmail, ownerLandLine, ownerMobile, teleCallNumber,true);
            }

        }else {
            //Partial Saving
            //No need od validation
            viewModel.saveOwnerDetails(ownerName, ownerOccupation, ownerHouseNameNumber, ownerStreetPlaceName, ownerState, ownerPostOffice, ownerPincode, ownerEmail, ownerLandLine, ownerMobile, teleCallNumber,false);
        }

    }

    @Override
    public void showAROwnerAddress() {
        showDialog(getString(R.string.owner_ar_address),viewModel.ownerAddressAR.getValue(), "",null,getString(R.string.cmn_close), (dialog, which) -> dialog.cancel());
    }

    @Override
    public void disablePartialSave() {
        getViewDataBinding().btnPartialSave.setEnabled(false);
        getViewDataBinding().btnPartialSave.setBackgroundColor(getResources().getColor(R.color.cmn_inactive_btn_color));
    }


    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutName.setErrorEnabled(false);
        getViewDataBinding().layoutOccupation.setErrorEnabled(false);
        getViewDataBinding().layoutHouseName.setErrorEnabled(false);
        getViewDataBinding().layoutStreetName.setErrorEnabled(false);
        getViewDataBinding().layoutState.setErrorEnabled(false);
        getViewDataBinding().layoutPostOffice.setErrorEnabled(false);
        getViewDataBinding().layoutPinCode.setErrorEnabled(false);
        getViewDataBinding().layoutEmail.setErrorEnabled(false);
        getViewDataBinding().layoutLandLine.setErrorEnabled(false);
        getViewDataBinding().layoutMobile.setErrorEnabled(false);
    }

    @Override
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
    }

    @Override
    public void navigateToScreenSelection() {
        super.onFragmentBackPressed();
    }

    @Override
    public void navigateToRoadPage() {
        getBaseActivity().showRoadFragment(true);
    }

    @Override
    public void navigateToTenantPage() {
        getBaseActivity().showTenantFragment(true);
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