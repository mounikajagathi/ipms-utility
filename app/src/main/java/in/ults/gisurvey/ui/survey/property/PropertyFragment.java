package in.ults.gisurvey.ui.survey.property;


import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.data.model.items.ExcelDataItem;
import in.ults.gisurvey.databinding.FragmentPropertyBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.IPMSSpinner;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewSingleSelectAdapter;
import in.ults.gisurvey.utils.adapters.PostOfficeListAdapter;

import static in.ults.gisurvey.utils.AppConstants.CLEAR;
import static in.ults.gisurvey.utils.AppConstants.NA_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.NEW_PROP_ID_VERIFICATION;
import static in.ults.gisurvey.utils.AppConstants.NOT_NEEDED;
import static in.ults.gisurvey.utils.AppConstants.NR_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.OLD_PROP_ID_VERIFICATION;
import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class PropertyFragment extends BaseFragment<FragmentPropertyBinding, PropertyViewModel> implements PropertyNavigator {

    public static final String TAG = PropertyFragment.class.getSimpleName();

    static final int COMMON_ERROR = 1;
    static final int LANDMARK_NAME_ERROR = 2;
    static final int LANDMARK_CATEGORY_ERROR = 3;
    static final int LANDMARK_SUB_CATEGORY_ERROR = 4;
    static final int OWNERSHIP_EDUCATIONAL_ERROR = 5;
    static final int OLD_PROPERTY_ERROR = 6;
    static final int NEAR_PROPERTY_NUMBER_ERROR = 7;
    static final int NEW_PROPERTY_ERROR = 8;
    static final int ELECTRICITY_CONSUMER_ERROR = 9;
    static final int TOILET_WASTE_DISPOSAL_ERROR = 10;
    static final int NEW_PROPERTY_ID_REMARKS_ERROR = 11;
    static final int DRONE_ID_ERROR = 12;
    static final int OLD_PROPERTY_ID1_ERROR = 13;
    static final int OLD_PROPERTY_ID2_ERROR = 14;
    static final int OLD_PROPERTY_ID3_ERROR = 15;
    static final int NEW_PROPERTY_ID3_ERROR = 16;
    static final int NEW_PROPERTY_ID4_ERROR = 17;
    static final int NEAR_PROPERTY_ID3_ERROR = 18;
    static final int NEAR_PROPERTY_ID4_ERROR = 19;
    static final int ESTABLISHMENT_USAGE_ERROR = 20;
    static final int SURVEY_TYPE_ERROR = 21;

    @Inject
    ViewModelProviderFactory factory;
    private PropertyViewModel viewModel;

    @Inject
    FlexRecyclerViewSingleSelectAdapter buildingStatusAdapter;

    @Inject
    FlexRecyclerViewSingleSelectAdapter buildingTypeAdapter;

    @Inject
    FlexRecyclerViewSingleSelectAdapter buildingUnderAdapter;

    @Inject
    FlexRecyclerViewSingleSelectAdapter doorStatusAdapter;

    @Inject
    FlexRecyclerViewSingleSelectAdapter buildingSubTypeAdapter;

    @Inject
    FlexboxLayoutManager layoutManagerBuildingStatus;

    @Inject
    FlexboxLayoutManager layoutManagerBuildingType;

    @Inject
    FlexboxLayoutManager layoutManagerBuildingUnder;

    @Inject
    FlexboxLayoutManager layoutManagerBuildingSubType;


    @Inject
    FlexboxLayoutManager layoutManagerDoorStatus;

    @Inject
    CommonDropDownAdapter landmarkCategoryAdapter;

    @Inject
    CommonDropDownAdapter landmarkSubCategoryAdapter;

    @Inject
    CommonDropDownAdapter propertyRemarksAdapter;

    @Inject
    CommonDropDownAdapter ownershipEducationAdapter;

    @Inject
    CommonDropDownAdapter toiletWasteDisposalAdapter;

    @Inject
    PostOfficeListAdapter establishmentUsageAdapter;

    @Inject
    CommonDropDownAdapter surveyTypeAdapter;

    private String selectedSubType = "";
    private String selectedBuildingStatus = "";
    private String selectedDoorStatus = "";
    private String selectedBuildingSubType = "";
    private String selectedUid = "", selectedAddress = "", selectedOldPropWardNo = "", selectedOldPropPropNo = "", selectedOldPropSubNo = "", selectedNewPropWardNo = "", selectedNewPropPropNo = "", selectedNewPropSubNo = "", ownerStaus = "";
    private String selectedZone, selectedAC,selectedFloorArea,selectedBuildingUsage,selectedRoadType,selectedRoadName,selectedBuildingAge,selectedRoofDetails,selectedFloorDetails,selectedModification,selectedOccupierDetails,selectedArTaxTotal;

    public static PropertyFragment newInstance() {
        Bundle args = new Bundle();
        PropertyFragment fragment = new PropertyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public PropertyViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(PropertyViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_property);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if (!viewModel.isSurveyOpenEditMode()) {
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            buildingStatusAdapter.updateSurveyOpenEditMode(false);
            buildingTypeAdapter.updateSurveyOpenEditMode(false);
            buildingUnderAdapter.updateSurveyOpenEditMode(false);
            doorStatusAdapter.updateSurveyOpenEditMode(false);
            buildingSubTypeAdapter.updateSurveyOpenEditMode(false);

        }
        if (viewModel.getARFileLoc() == null) {
            enableOldPropertyId();
        }
        getViewDataBinding().etLandmarkCategory.setAdapter(landmarkCategoryAdapter);
        getViewDataBinding().etLandmarkSubCategory.setAdapter(landmarkSubCategoryAdapter);
        getViewDataBinding().rvBuildingStatus.setLayoutManager(layoutManagerBuildingStatus);
        getViewDataBinding().rvBuildingType.setLayoutManager(layoutManagerBuildingType);
        getViewDataBinding().rvBuildingSubType.setLayoutManager(layoutManagerBuildingSubType);
        getViewDataBinding().rvBuildingUnder.setLayoutManager(layoutManagerBuildingUnder);
        getViewDataBinding().rvDoorStatus.setLayoutManager(layoutManagerDoorStatus);
        getViewDataBinding().rvBuildingStatus.setAdapter(buildingStatusAdapter);
        getViewDataBinding().rvBuildingType.setAdapter(buildingTypeAdapter);
        getViewDataBinding().rvBuildingUnder.setAdapter(buildingUnderAdapter);
        getViewDataBinding().rvDoorStatus.setAdapter(doorStatusAdapter);
        getViewDataBinding().rvBuildingSubType.setAdapter(buildingSubTypeAdapter);
        getViewDataBinding().etRemarks.setAdapter(propertyRemarksAdapter);
        getViewDataBinding().etOwnershipEducational.setAdapter(ownershipEducationAdapter);
        getViewDataBinding().etToiletWasteDisposal.setAdapter(toiletWasteDisposalAdapter);
        getViewDataBinding().etEstablishmentUsage.setAdapter(establishmentUsageAdapter);
        getViewDataBinding().etSurveyType.setAdapter(surveyTypeAdapter);


        getViewDataBinding().etLandmarkCategory.setOnItemClickListener((parent, view, position, id) -> {
            getViewDataBinding().etLandmarkSubCategory.setText("");
            viewModel.setLandmarkSubCategory(Objects.requireNonNull(landmarkCategoryAdapter.getItem(position)).getSubList());
        });
        getViewDataBinding().etNewPropertyId3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If AR file is not selected then old property id not auto set it will get from user
                //if AR selected need to clear and disable old property id values on each change of new property
                if (getViewDataBinding().etNewPropertyId3.hasFocus())
                    updateUidOldPropId();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getViewDataBinding().etNewPropertyId4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If AR file is not selected then old property id not auto set it will get from user
                //if AR selected need to clear and disable old property id values on each change of new property
//                if (!s.equals(viewModel.newPropertyID4.getValue()))
                if (getViewDataBinding().etNewPropertyId4.hasFocus())
                    updateUidOldPropId();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buildingTypeAdapter.setViewItemClickListener(position -> {
            ArrayList<CommonItem> data = IPMSApp.getAppInstance().getLocMainItem().getBuildingType().get(position).getSubList();
            if (data != null) {
                viewModel.setBuildingTypeAvailable(data.size() != 0);
                viewModel.setBuildingSubTypeData(data);
                buildingSubTypeAdapter.resetItems();
                buildingSubTypeAdapter.setSelectedContent(selectedSubType);
                selectedSubType = "";
            }
            viewModel.setBuildingTypeRelatedData(buildingTypeAdapter.getDataList().get(position).getContent());
            viewModel.setBuildingSubTypeRelatedBuilding("");
        });


        buildingSubTypeAdapter.setViewItemClickListener(position -> {
            String buildingSubType = buildingSubTypeAdapter.getSelectedContent();
            ArrayList<CommonItem> data = IPMSApp.getAppInstance().getLocMainItem().getBuildingType().get(buildingTypeAdapter.getSelectedPosition()).getSubList().get(position).getSubUsageList();
            viewModel.setBuildingSubTypeAvailable(data.size() != 0);
            if (selectedBuildingSubType != null &&
                    selectedBuildingSubType.length() > 0 &&
                    !selectedBuildingSubType.equalsIgnoreCase(buildingSubType)) {
                buildingSubTypeAdapter.setSelectedContent(selectedBuildingSubType);
                showDialog(null, getString(R.string.msg_change_building_sub_type),
                        getString(android.R.string.yes), (dialog, which) -> {
                            buildingSubTypeAdapter.setSelectedContent(buildingSubType);
                            viewModel.clearBuildingSubTypeRelatedData(buildingSubType);
                            selectedBuildingSubType = "";
                        }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
            }
            viewModel.setBuildingSubTypeRelatedBuilding(buildingSubType);
            viewModel.setEstablishmentUsageList(data);

        });
        doorStatusAdapter.setViewItemClickListener(position -> {
            String doorStatus = doorStatusAdapter.getSelectedContent();
            if (selectedDoorStatus != null &&
                    selectedDoorStatus.length() > 0 &&
                    !selectedDoorStatus.equalsIgnoreCase(doorStatus)) {
                doorStatusAdapter.setSelectedContent(selectedDoorStatus);
                showDialog(null, getString(R.string.msg_change_door_status),
                        getString(android.R.string.yes), (dialog, which) -> {
                            doorStatusAdapter.setSelectedContent(doorStatus);
                            viewModel.clearDoorStatusRelatedData(doorStatus);
                            viewModel.setDoorStatusPDCGLDCNC(doorStatus);
                            selectedDoorStatus = "";
                        }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
            } else {
                viewModel.setDoorStatusPDCGLDCNC(doorStatus);
            }
        });

        buildingStatusAdapter.setViewItemClickListener(position -> {
            String selectedStatus = buildingStatusAdapter.getDataList().get(position).getContent();
            if (selectedBuildingStatus != null &&
                    selectedBuildingStatus.length() > 0 &&
                    !selectedBuildingStatus.equalsIgnoreCase(selectedStatus)) {
                buildingStatusAdapter.setSelectedContent(selectedBuildingStatus);
                showDialog(null, getString(R.string.msg_change_building_status),
                        getString(android.R.string.yes), (dialog, which) -> {
                            viewModel.clearBuildingStatusRelatedData(selectedStatus);
                            buildingStatusAdapter.setSelectedContent(selectedStatus);
                            setBuildingStatus(selectedStatus);
                            selectedBuildingStatus = "";
                        }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
            } else {
                setBuildingStatus(selectedStatus);
            }

            if (selectedStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF))
                setPropertyUsageButtonStatus(true);
            else {
                if (!selectedStatus.equalsIgnoreCase(selectedBuildingStatus)) {
                    setPropertyUsageButtonStatus(false);
                }
            }


        });

        buildingUnderAdapter.setViewItemClickListener(position -> {
            viewModel.setBuildingUnderRelatedData(buildingUnderAdapter.getDataList().get(position).getContent());
        });

        getViewDataBinding().rgLandMark.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbLandMarkYes) {
                viewModel.setLandmarkCategoryAvailable(true);
            } else {
                viewModel.setLandmarkCategoryAvailable(false);
            }
        });

        getViewDataBinding().rgLatrine.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbLatrineYes) {
                viewModel.setToiletAvailable(true);
            } else {
                viewModel.setToiletAvailable(false);
            }
        });

        getViewDataBinding().rgElectricity.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbElectricityYes) {
                viewModel.isElectricityAvailable(true);
            } else {
                viewModel.isElectricityAvailable(false);
            }
        });


        getViewDataBinding().etNewPropertyId1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setNewPropertyRelatedData(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getViewDataBinding().etSurveyType.setOnItemClickListener((parent, view, position, id) -> {
            if((surveyTypeAdapter.getItem(position)).getContent().equals(AppConstants.TELE_CALL)){
                viewModel.setTeleCall(true);
            }
            else{
                viewModel.setTeleCall(false);
            }
        });

        viewModel.loadData();
        viewModel.getCurrentSurveyProperty();
        viewModel.getSelectedWardNumber();

    }

    /**
     * If user selected AR file
     * update UID OLPDPROPID to empty
     * disble old prop value
     */
    private void updateUidOldPropId() {
        if (viewModel.getARFileLoc() != null && viewModel.isPointStatusBuilding()) {
            viewModel.updateOldPropIdUIDOnNewPropIdChange();
            if (!viewModel.isBuildStatusOnGoing.get() && !viewModel.isPropertyStatusOngoingWithoutRoof.get()) {
                disableOldPropertyId();
            }

        }
    }

    private void setBuildingStatus(String selectedStatus) {
        viewModel.setBuildStatusRelatedData(selectedStatus);
        viewModel.setLandmarkCategoryAvailable(getViewDataBinding().rgLandMark.getCheckedRadioButtonId() == R.id.rbLandMarkYes);
        if (viewModel.isBuildStatusDemolishedUnusableOnGoing.get()) {
            viewModel.landmarkCategory.setValue("");
            viewModel.landmarkSubCategory.setValue("");
            viewModel.setLandmarkCategoryAvailable(false);
        }
        if (viewModel.isBuildStatusDemolishedUnusable.get()) {
            selectedDoorStatus = "";
            doorStatusAdapter.clearSelectedItem();
            buildingUnderAdapter.clearSelectedItem();
            buildingTypeAdapter.clearSelectedItem();
            selectedBuildingSubType = "";
            buildingSubTypeAdapter.clearSelectedItem();
            getViewDataBinding().rgBuildingUsage.clearCheck();
            getViewDataBinding().rgMainBuilding.clearCheck();
            getViewDataBinding().rgElectricity.clearCheck();
            viewModel.consumerNumber.setValue("");//clear the value when "are you sure to change the ..."
            getViewDataBinding().rgLatrine.clearCheck();
            getViewDataBinding().rgBathroom.clearCheck();
            viewModel.toiletWasteDisposal.setValue("");//clear the value when "are you sure to change the ..."
            getViewDataBinding().rgLandMark.clearCheck();
            getViewDataBinding().rgAirConditioner.clearCheck();
            viewModel.ownershipEducational.setValue("");//clear the value when "are you sure to change the ..."
            viewModel.setLandmarkCategoryAvailable(false);
        }
        if (viewModel.isPropertyStatusOngoingWithoutRoof.get()) {
            viewModel.oldPropertyID1.setValue("");
            viewModel.oldPropertyID2.setValue("");
            viewModel.oldPropertyID3.setValue("");
            viewModel.setLandmarkCategoryAvailable(false);
            getViewDataBinding().rgAirConditioner.clearCheck();
            getViewDataBinding().rgLandMark.clearCheck();

        }
        if (viewModel.isBuildStatusOnGoing.get()) {
            viewModel.oldPropertyID1.setValue("");
            viewModel.oldPropertyID2.setValue("");
            viewModel.oldPropertyID3.setValue("");
            viewModel.setLandmarkCategoryAvailable(false);
        }
    }


    @Override
    public void navigateToNextPage() {
//        getBaseActivity().showOwnerFragment(true);
        getBaseActivity().showSelectionFragment(true);
    }

    @Override
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
    }

    @Override
    public void setSelectedData(String buildingStatus, String doorStatus, String buildingSubType) {
        selectedBuildingStatus = buildingStatus;
        selectedDoorStatus = doorStatus;
        selectedBuildingSubType = buildingSubType;
    }


    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
            case LANDMARK_NAME_ERROR:
                getViewDataBinding().layoutLandmarkName.setError(error);
                getViewDataBinding().layoutLandmarkName.getParent().requestChildFocus
                        (getViewDataBinding().layoutLandmarkName, getViewDataBinding().layoutLandmarkName);
                break;
            case LANDMARK_CATEGORY_ERROR:
                getViewDataBinding().layoutLandmarkCategory.setError(error);
                getViewDataBinding().layoutLandmarkCategory.getParent().requestChildFocus
                        (getViewDataBinding().layoutLandmarkCategory, getViewDataBinding().layoutLandmarkCategory);
                break;
            case LANDMARK_SUB_CATEGORY_ERROR:
                getViewDataBinding().layoutLandmarkSubCategory.setError(error);
                getViewDataBinding().layoutLandmarkSubCategory.getParent().requestChildFocus
                        (getViewDataBinding().layoutLandmarkSubCategory, getViewDataBinding().layoutLandmarkSubCategory);
                break;
            case OWNERSHIP_EDUCATIONAL_ERROR:
                getViewDataBinding().layoutOwnershipEducational.setError(error);
                getViewDataBinding().layoutOwnershipEducational.getParent().requestChildFocus
                        (getViewDataBinding().layoutOwnershipEducational, getViewDataBinding().layoutOwnershipEducational);
                break;
            case OLD_PROPERTY_ID1_ERROR:
                getViewDataBinding().layoutOldropertyId1.setError(error);
                getViewDataBinding().layoutOldropertyId1.getParent().requestChildFocus
                        (getViewDataBinding().layoutOldropertyId1, getViewDataBinding().layoutOldropertyId1);
                break;
            case OLD_PROPERTY_ID2_ERROR:
                getViewDataBinding().layoutOldPropertyId2.setError(error);
                getViewDataBinding().layoutOldPropertyId2.getParent().requestChildFocus
                        (getViewDataBinding().layoutOldPropertyId2, getViewDataBinding().layoutOldPropertyId2);
                break;
            case OLD_PROPERTY_ID3_ERROR:
                getViewDataBinding().layoutOldPropertyId3.setError(error);
                getViewDataBinding().layoutOldPropertyId3.getParent().requestChildFocus
                        (getViewDataBinding().layoutOldPropertyId3, getViewDataBinding().layoutOldPropertyId3);
                break;
            case NEW_PROPERTY_ID3_ERROR:
                getViewDataBinding().layoutNewPropertyId3.setError(error);
                getViewDataBinding().layoutNewPropertyId3.getParent().requestChildFocus
                        (getViewDataBinding().layoutNewPropertyId3, getViewDataBinding().layoutNewPropertyId3);
                break;
            case NEW_PROPERTY_ID4_ERROR:
                getViewDataBinding().layoutNewPropertyId4.setError(error);
                getViewDataBinding().layoutNewPropertyId4.getParent().requestChildFocus
                        (getViewDataBinding().layoutNewPropertyId4, getViewDataBinding().layoutNewPropertyId4);
                break;
            case NEAR_PROPERTY_ID3_ERROR:
                getViewDataBinding().layoutNearPropertyId3.setError(error);
                getViewDataBinding().layoutNearPropertyId3.getParent().requestChildFocus
                        (getViewDataBinding().layoutNearPropertyId3, getViewDataBinding().layoutNearPropertyId3);
                break;
            case NEAR_PROPERTY_ID4_ERROR:
                getViewDataBinding().layoutNearPropertyId4.setError(error);
                getViewDataBinding().layoutNearPropertyId4.getParent().requestChildFocus
                        (getViewDataBinding().layoutNearPropertyId4, getViewDataBinding().layoutNearPropertyId4);
                break;

            case ELECTRICITY_CONSUMER_ERROR:
                getViewDataBinding().layoutElectricityConsumerNumber.setError(error);
                getViewDataBinding().layoutElectricityConsumerNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutElectricityConsumerNumber, getViewDataBinding().layoutElectricityConsumerNumber);
                break;
            case TOILET_WASTE_DISPOSAL_ERROR:
                getViewDataBinding().layoutToiletWasteDisposal.setError(error);
                getViewDataBinding().layoutToiletWasteDisposal.getParent().requestChildFocus
                        (getViewDataBinding().layoutToiletWasteDisposal, getViewDataBinding().layoutToiletWasteDisposal);
                break;
            case NEW_PROPERTY_ID_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus(getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case DRONE_ID_ERROR:
                getViewDataBinding().layoutDroneId.setError(error);
                getViewDataBinding().layoutDroneId.getParent().requestChildFocus(getViewDataBinding().layoutDroneId, getViewDataBinding().layoutDroneId);
                break;
            case ESTABLISHMENT_USAGE_ERROR:
                getViewDataBinding().layoutEstablishmentUsage.setError(error);
                getViewDataBinding().layoutEstablishmentUsage.getParent().requestChildFocus(getViewDataBinding().layoutEstablishmentUsage, getViewDataBinding().layoutEstablishmentUsage);
                break;
            case SURVEY_TYPE_ERROR:
                getViewDataBinding().layoutSurveyType.setError(error);
                getViewDataBinding().layoutSurveyType.getParent().requestChildFocus(getViewDataBinding().layoutSurveyType, getViewDataBinding().layoutSurveyType);
                break;
        }
    }

    /**
     * to validate fields and save property details
     */
    @Override
    public void saveProperty() {
        getBaseActivity().hideKeyboard();
        String uID = Objects.requireNonNull(getViewDataBinding().etUId.getText()).toString().trim();
        String landmarkName = Objects.requireNonNull(getViewDataBinding().etLandmarkName.getText()).toString().trim();
        String landmarkCategory = Objects.requireNonNull(getViewDataBinding().etLandmarkCategory.getText()).toString().trim();
        String landmarkSubCategory = Objects.requireNonNull(getViewDataBinding().etLandmarkSubCategory.getText()).toString().trim();
        String newPropertyRemarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        String ownershipEducation = Objects.requireNonNull(getViewDataBinding().etOwnershipEducational.getText()).toString().trim();
        String droneId = Objects.requireNonNull(getViewDataBinding().etDroneId.getText()).toString().trim();
        String newPropertyId1 = Objects.requireNonNull(getViewDataBinding().etNewPropertyId1.getText()).toString().trim();
        String newPropertyId2 = Objects.requireNonNull(getViewDataBinding().etNewPropertyId2.getText()).toString().trim();
        String newPropertyId3 = Objects.requireNonNull(getViewDataBinding().etNewPropertyId3.getText()).toString().trim();
        String newPropertyId4 = Objects.requireNonNull(getViewDataBinding().etNewPropertyId4.getText()).toString().trim();
        String oldPropertyId1 = Objects.requireNonNull(getViewDataBinding().etOldPropertyId1.getText()).toString().trim();
        String oldPropertyId2 = Objects.requireNonNull(getViewDataBinding().etOldPropertyId2.getText()).toString().trim();
        String oldPropertyId3 = Objects.requireNonNull(getViewDataBinding().etOldPropertyId3.getText()).toString().trim();
        String nearPropertyId1 = Objects.requireNonNull(getViewDataBinding().etNearPropertyId1.getText()).toString().trim();
        String nearPropertyId2 = Objects.requireNonNull(getViewDataBinding().etNearPropertyId2.getText()).toString().trim();
        String nearPropertyId3 = Objects.requireNonNull(getViewDataBinding().etNearPropertyId3.getText()).toString().trim();
        String nearPropertyId4 = Objects.requireNonNull(getViewDataBinding().etNearPropertyId4.getText()).toString().trim();

        String establishmentUsage = Objects.requireNonNull(getViewDataBinding().etEstablishmentUsage.getText()).toString().trim();
        String surveyType = Objects.requireNonNull(getViewDataBinding().etSurveyType.getText()).toString().trim();

        String landmark = "";
        if (getViewDataBinding().rgLandMark.getCheckedRadioButtonId() != -1) {
            landmark = ((RadioButton) getViewDataBinding().rgLandMark.findViewById(getViewDataBinding().rgLandMark.getCheckedRadioButtonId())).getText().toString();
        }

        String buildingUsage = "";
        if (getViewDataBinding().rgBuildingUsage.getCheckedRadioButtonId() != -1) {
            buildingUsage = ((RadioButton) getViewDataBinding().rgBuildingUsage.findViewById(getViewDataBinding().rgBuildingUsage.getCheckedRadioButtonId())).getText().toString();
        }

        String mainBuilding = "";
        if (getViewDataBinding().rgMainBuilding.getCheckedRadioButtonId() != -1) {
            mainBuilding = ((RadioButton) getViewDataBinding().rgMainBuilding.findViewById(getViewDataBinding().rgMainBuilding.getCheckedRadioButtonId())).getText().toString();
        }

        String doorStatus = doorStatusAdapter.getSelectedContent();
        String buildingUnder = buildingUnderAdapter.getSelectedContent();
        String buildingStatus = buildingStatusAdapter.getSelectedContent();
        String buildingType = buildingTypeAdapter.getSelectedContent();
        String buildingSubType = buildingSubTypeAdapter.getSelectedContent();


        String electricity = "";
        if (getViewDataBinding().rgElectricity.getCheckedRadioButtonId() != -1) {
            electricity = ((RadioButton) getViewDataBinding().rgElectricity.findViewById(getViewDataBinding().rgElectricity.getCheckedRadioButtonId())).getText().toString();
        }
        String electricityConsumerNumber = Objects.requireNonNull(getViewDataBinding().etElectricityConsumerNumber.getText()).toString().trim();

        if (electricity.equalsIgnoreCase(getString(R.string.cmn_nr))) {
            electricityConsumerNumber = getString(R.string.cmn_nr);
        }

        String latrine = "";
        if (getViewDataBinding().rgLatrine.getCheckedRadioButtonId() != -1) {
            latrine = ((RadioButton) getViewDataBinding().rgLatrine.findViewById(getViewDataBinding().rgLatrine.getCheckedRadioButtonId())).getText().toString();
        }
        String bathroom = "";
        if (getViewDataBinding().rgBathroom.getCheckedRadioButtonId() != -1) {
            bathroom = ((RadioButton) getViewDataBinding().rgBathroom.findViewById(getViewDataBinding().rgBathroom.getCheckedRadioButtonId())).getText().toString();
        }
        String toiletWasteDisposal = Objects.requireNonNull(getViewDataBinding().etToiletWasteDisposal.getText()).toString().trim();
        if (latrine.equalsIgnoreCase(getString(R.string.cmn_nr))) {
            toiletWasteDisposal = getString(R.string.cmn_nr);
        }

        String airConditioner = "";
        if (getViewDataBinding().rgAirConditioner.getCheckedRadioButtonId() != -1) {
            airConditioner = ((RadioButton) getViewDataBinding().rgAirConditioner.findViewById(getViewDataBinding().rgAirConditioner.getCheckedRadioButtonId())).getText().toString();
        }

        if (viewModel.validateFields(nearPropertyId3, nearPropertyId4, droneId, oldPropertyId1, oldPropertyId2, oldPropertyId3, newPropertyId3, newPropertyId4, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, buildingUsage, mainBuilding, ownershipEducation, electricity, electricityConsumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType)) {
            boolean noElectrictyWarning = false, noBathroomWarning = false, noLatrineWarning = false, isElectricityVsACWarning = false, isPropertyIdWarning = false;
            if (!buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF) && !buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING) && !buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED) && !buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)) {
                if (electricity.equalsIgnoreCase(getString(R.string.cmn_no))) {
                    noElectrictyWarning = true;
                }
                if (buildingType.equalsIgnoreCase(AppConstants.BUILDING_TYPE_RESIDENTIAL) && !buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING)) {
                    if (electricity.equalsIgnoreCase(getString(R.string.cmn_no))) {
                        noElectrictyWarning = true;
                    }
                    if (latrine.equalsIgnoreCase(getString(R.string.cmn_no))) {
                        noLatrineWarning = true;
                    }
                    if (bathroom.equalsIgnoreCase(getString(R.string.cmn_no))) {
                        noBathroomWarning = true;
                    }
                }

            }
            if (!viewModel.doorStatusPermanentDoorClosedGateLockedDoorClosed.get() && !viewModel.isPropertyStatusOngoingWithoutRoof.get() && electricity.equalsIgnoreCase(getString(R.string.cmn_no)) && !airConditioner.equalsIgnoreCase(getString(R.string.cmn_no)) && !buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING)) {
                if (!airConditioner.equalsIgnoreCase(getString(R.string.cmn_nr)))
                    isElectricityVsACWarning = true;
            }
            if (viewModel.isPointStatusBuilding() && !viewModel.isBuildStatusOnGoing.get() && !viewModel.isPropertyStatusOngoingWithoutRoof.get() && oldPropertyId1.equalsIgnoreCase(newPropertyId2) && oldPropertyId2.equalsIgnoreCase(newPropertyId3) && oldPropertyId3.equalsIgnoreCase(newPropertyId4)) {
                isPropertyIdWarning = true;
            }
            if (noElectrictyWarning || noLatrineWarning || noBathroomWarning || isElectricityVsACWarning || isPropertyIdWarning) {
                //condition in which electricity not available but AC is present
                //need warning
                String finalLandmark = landmark;
                String finalBuildingUsage = buildingUsage;
                String finalMainBuilding = mainBuilding;
                String finalElectricity = electricity;
                String finalElectricityConsumerNumber = electricityConsumerNumber;
                String finalBathroom = bathroom;
                String finalLatrine = latrine;
                String finalToiletWasteDisposal = toiletWasteDisposal;
                String finalAirConditioner = airConditioner;
                showDialog(getString(R.string.cmn_warning), viewModel.getWarningMessage(noElectrictyWarning, noBathroomWarning, noLatrineWarning, isElectricityVsACWarning, isPropertyIdWarning),
                        getString(R.string.cmn_continue), (dialog, which) -> {
                            viewModel.insertPropertyDetails(nearPropertyId1, nearPropertyId2, nearPropertyId3, nearPropertyId4, droneId, oldPropertyId1, oldPropertyId2, oldPropertyId3, newPropertyId1, newPropertyId2, newPropertyId3, newPropertyId4, uID, newPropertyRemarks, finalLandmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, finalBuildingUsage, finalMainBuilding, ownershipEducation, finalElectricity, finalElectricityConsumerNumber, finalBathroom, finalLatrine, finalToiletWasteDisposal, finalAirConditioner, surveyType);
                        }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());

            } else {
                viewModel.insertPropertyDetails(nearPropertyId1, nearPropertyId2, nearPropertyId3, nearPropertyId4, droneId, oldPropertyId1, oldPropertyId2, oldPropertyId3, newPropertyId1, newPropertyId2, newPropertyId3, newPropertyId4, uID, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, buildingUsage, mainBuilding, ownershipEducation, electricity, electricityConsumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType);
            }
        }
    }


    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutLandmarkName.setErrorEnabled(false);
        getViewDataBinding().layoutLandmarkCategory.setErrorEnabled(false);
        getViewDataBinding().layoutLandmarkSubCategory.setErrorEnabled(false);
        getViewDataBinding().layoutOwnershipEducational.setErrorEnabled(false);
        getViewDataBinding().layoutRemarks.setErrorEnabled(false);
        getViewDataBinding().layoutElectricityConsumerNumber.setErrorEnabled(false);
        getViewDataBinding().layoutToiletWasteDisposal.setErrorEnabled(false);
        getViewDataBinding().layoutDroneId.setErrorEnabled(false);
        getViewDataBinding().layoutOldropertyId1.setErrorEnabled(false);
        getViewDataBinding().layoutOldPropertyId2.setErrorEnabled(false);
        getViewDataBinding().layoutOldPropertyId3.setErrorEnabled(false);
        getViewDataBinding().layoutNewPropertyId3.setErrorEnabled(false);
        getViewDataBinding().layoutNewPropertyId4.setErrorEnabled(false);
        getViewDataBinding().layoutNearPropertyId3.setErrorEnabled(false);
        getViewDataBinding().layoutNearPropertyId4.setErrorEnabled(false);
        getViewDataBinding().layoutEstablishmentUsage.setErrorEnabled(false);
    }

    @Override
    public void setCachedData(Property property) {
        if (viewModel.isPointStatusBuilding()) {
            buildingStatusAdapter.setSelectedContent(property.buildingStatus);
            if (!viewModel.isBuildStatusDemolishedUnusable.get()) {
                doorStatusAdapter.setSelectedContent(property.doorStatus);
                buildingUnderAdapter.setSelectedContent(property.buildingUnder);
                buildingTypeAdapter.setSelectedContent(property.buildingType);
                selectedSubType = property.buildingSubType;
                getViewDataBinding().rgBuildingUsage.check(property.buildingUsage.equalsIgnoreCase(getString(R.string.building_usage_owned)) ? R.id.rbrgBuildingOwned : property.buildingUsage.equalsIgnoreCase(getString(R.string.building_usage_rented)) ? R.id.rbrgBuildingRented : -1);
                if (viewModel.isBuildingTypeEducational.get()) {
                    getViewDataBinding().rgMainBuilding.check(property.mainBuilding.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbrgMainBuildingYes : property.mainBuilding.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbrgMainBuildingNo : -1);
                }
                getViewDataBinding().rgElectricity.check(property.electricity.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbElectricityYes : property.electricity.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbElectricityNo : property.electricity.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbElectricityNr : -1);
                getViewDataBinding().rgBathroom.check(property.bathroom.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbBathroomYes : property.bathroom.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbBathroomNo : property.bathroom.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbBathroomNr : -1);
                getViewDataBinding().rgLatrine.check(property.latrine.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbLatrineYes : property.latrine.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbLatrineNo : property.latrine.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbLatrineNr : -1);
                if (!viewModel.doorStatusPermanentDoorClosedGateLockedDoorClosed.get()) {
                    getViewDataBinding().rgAirConditioner.check(property.airConditioner.equalsIgnoreCase(getString(R.string.building_centralised_ac)) ? R.id.rbAirConditionerCentralised : property.airConditioner.equalsIgnoreCase(getString(R.string.building_split_ac)) ? R.id.rbAirConditionerSplit : property.airConditioner.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbAirConditionerNo : property.airConditioner.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbACNr : -1);
                }
                getViewDataBinding().rgLandMark.check(property.landmark.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbLandMarkYes : property.landmark.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbLandMarkNo : -1);
            }
        }
    }

    @Override
    public void showNearPopUp() {
        PopupMenu popupMenu = new PopupMenu(getBaseActivity(), getViewDataBinding().btnNearNewProperty);
        if (viewModel.isBuildStatusDemolishedUnusableOnGoing.get()) {
            popupMenu.inflate(R.menu.near_menu);
        } else if (viewModel.isPropertyStatusOngoingWithoutRoof.get()) {
            popupMenu.inflate(R.menu.near_menu_withour_nr);
        } else {
            popupMenu.inflate(R.menu.near_menu_without_na);
        }
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.NR:
                    //NEW PROP NR NEAR ->NO need of check button, Clear new prop and old prop value, enable old prop id field
                    viewModel.newPropertyID1.setValue(AppConstants.NR_NEAR);
                    viewModel.isCheckVisible.set(false);
                    viewModel.updateOldPropIdUIDOnNewPropIdChange();
                    enableOldPropertyId();
                    clearArFieldsValues();
                    break;
                case R.id.NA:
                    //NEW PROP NA NEAR ->NO need of check button, Clear new prop and old prop value, enable old prop id field
                    viewModel.newPropertyID1.setValue(AppConstants.NA_NEAR);
                    viewModel.isCheckVisible.set(false);
                    viewModel.updateOldPropIdUIDOnNewPropIdChange();
                    enableOldPropertyId();
                    clearArFieldsValues();
                    break;
                case R.id.CLEAR:
                    //NEW PROP CLEAR ->check button needed, Clear new prop and old prop value, disable old prop id field
                    viewModel.newPropertyID1.setValue(CLEAR);
                    viewModel.isCheckVisible.set(true);
                    viewModel.updateOldPropIdUIDOnNewPropIdChange();
                    disableOldPropertyId();
                    clearArFieldsValues();
                    break;
            }
            popupMenu.dismiss();
            return false;
        });
        popupMenu.show();
    }

    //This is to clear all AR data stored
    private void clearArFieldsValues() {
        viewModel.ar_owner_address.setValue("");
        viewModel.ar_zone.setValue("");
        viewModel.ar_ac.setValue("");
        viewModel.ar_floor_area.setValue("");
        viewModel.ar_building_usage.setValue("");
        viewModel.ar_road_type.setValue("");
        viewModel.ar_road_name.setValue("");
        viewModel.ar_building_age.setValue("");
        viewModel.ar_roof_details.setValue("");
        viewModel.ar_floor_details.setValue("");
        viewModel.ar_modification.setValue("");
        viewModel.ar_occupier_details.setValue("");
        viewModel.ar_tax_total.setValue("");
    }

    @Override
    public void showNaNrMenu() {
        PopupMenu popupMenu = new PopupMenu(getBaseActivity(), getViewDataBinding().nrOldPropertyIdValue);
        popupMenu.inflate(R.menu.na_nr_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.NR:
                    viewModel.setOldPropIdValue(NR_UPPERCASE);
                    break;
                case R.id.NA:
                    viewModel.setOldPropIdValue(NA_UPPERCASE);
                    break;
            }
            popupMenu.dismiss();
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void showNearPopUpForNearProperty() {
        PopupMenu popupMenu = new PopupMenu(getBaseActivity(), getViewDataBinding().btnNearNewProperty);
        popupMenu.inflate(R.menu.near_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.NR:
                    viewModel.nearPropertyID1.setValue(AppConstants.NR_NEAR);
                    break;
                case R.id.NA:
                    viewModel.nearPropertyID1.setValue(AppConstants.NA_NEAR);
                    break;
                case R.id.CLEAR:
                    viewModel.nearPropertyID1.setValue(CLEAR);
                    break;
            }
            popupMenu.dismiss();
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void updateLatrineBathroomACNROnDoorStatusChange() {
        if (getViewDataBinding().rgLatrine.getCheckedRadioButtonId() != -1 && !(viewModel.doorStatusPermanentDoorClosedGateLockedDoorClosed.get() || viewModel.doorStatusNC.get())) {
            String latrine = ((RadioButton) getViewDataBinding().rgLatrine.findViewById(getViewDataBinding().rgLatrine.getCheckedRadioButtonId())).getText().toString();
            if (latrine.equalsIgnoreCase(getString(R.string.cmn_nr))) {
                getViewDataBinding().rgLatrine.clearCheck();
                getViewDataBinding().etToiletWasteDisposal.setText("");
            }
        }
        if (getViewDataBinding().rgBathroom.getCheckedRadioButtonId() != -1 && !(viewModel.doorStatusPermanentDoorClosedGateLockedDoorClosed.get() || viewModel.doorStatusNC.get())) {
            String bathroom = ((RadioButton) getViewDataBinding().rgBathroom.findViewById(getViewDataBinding().rgBathroom.getCheckedRadioButtonId())).getText().toString();
            if (bathroom.equalsIgnoreCase(getString(R.string.cmn_nr))) {
                getViewDataBinding().rgBathroom.clearCheck();
            }
        }
        if (getViewDataBinding().rgAirConditioner.getCheckedRadioButtonId() != -1 && !viewModel.doorStatusNC.get()) {
            String ac = ((RadioButton) getViewDataBinding().rgAirConditioner.findViewById(getViewDataBinding().rgAirConditioner.getCheckedRadioButtonId())).getText().toString();
            if (ac.equalsIgnoreCase(getString(R.string.cmn_nr))) {
                getViewDataBinding().rgAirConditioner.clearCheck();
            }
        }

    }

    @Override
    public void updateSubTypeRelatedBauilding() {
        getViewDataBinding().rgBuildingUsage.clearCheck();
        getViewDataBinding().rgLatrine.clearCheck();
        getViewDataBinding().rgLandMark.clearCheck();
        getViewDataBinding().rgBathroom.clearCheck();
        getViewDataBinding().rgAirConditioner.clearCheck();
        getViewDataBinding().etToiletWasteDisposal.setText("");
        getViewDataBinding().etLandmarkCategory.setText("");
        getViewDataBinding().etLandmarkSubCategory.setText("");
        getViewDataBinding().etUId.setText("");

    }

    @Override
    public void showARDetailsNewPropertyId(ArrayList<ExcelDataItem> dataItems, String propId) {
        selectedUid = "";
        selectedOldPropWardNo = "";
        selectedOldPropPropNo = "";
        selectedOldPropSubNo = "";
        selectedNewPropWardNo = "";
        selectedNewPropPropNo = "";
        selectedNewPropSubNo = "";
        ownerStaus = "";
        selectedZone="";
        selectedAC="";
        selectedFloorArea="";
        selectedBuildingUsage="";
        selectedRoadType="";
        selectedRoadName="";
        selectedBuildingAge="";
        selectedRoofDetails="";
        selectedFloorDetails="";
        selectedModification="";
        selectedOccupierDetails="";
        selectedArTaxTotal="";

        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity());
        ViewGroup viewGroup = getBaseActivity().findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.dialog_property_id_verification, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        TextView textviewTitle = (TextView) dialogView.findViewById(R.id.textviewTitle);
        Button btnVerification1 = (Button) dialogView.findViewById(R.id.btnVerification1);
        Button btnVerification2 = (Button) dialogView.findViewById(R.id.btnVerification2);
        Button btnVerification3 = (Button) dialogView.findViewById(R.id.btnVerification3);
        Button btnVerification4 = (Button) dialogView.findViewById(R.id.btnVerification4);
        Button btnVerification5 = (Button) dialogView.findViewById(R.id.btnVerification5);
        Button btnVerification6 = (Button) dialogView.findViewById(R.id.btnVerification6);
        Button btnVerification7 = (Button) dialogView.findViewById(R.id.btnVerification7);

        RadioGroup rgOwnerDeatils = (RadioGroup) dialogView.findViewById(R.id.rgOwnerDetails);
        RadioButton rbOwnerStatus1 = (RadioButton) dialogView.findViewById(R.id.rdOwnerDetailsTxt1);
        RadioButton rbOwnerStatus2 = (RadioButton) dialogView.findViewById(R.id.rdOwnerDetailsTxt2);
        RadioButton rbOwnerStatus3 = (RadioButton) dialogView.findViewById(R.id.rdOwnerDetailsTxt3);
        IPMSSpinner etOwnerDetails = (IPMSSpinner) dialogView.findViewById(R.id.etOwnerDetails);
        textviewTitle.setText(getString(R.string.property_new_property_id) + " : " + propId);
        btnVerification1.setText(getBaseActivity().getString(R.string.property_id_old_id_same_btn_text));
        btnVerification2.setText(getBaseActivity().getString(R.string.property_id_old_id_wrong_in_field_btn_text));
        btnVerification3.setText(getBaseActivity().getString(R.string.property_id_old_id_nr_btn_text));
        btnVerification4.setText(getBaseActivity().getString(R.string.property_id_old_id_not_in_ar_new_prop_btn_text));
        btnVerification5.setText(getBaseActivity().getString(R.string.property_id_old_id_not_in_ar_bt_field_btn_text));
        btnVerification6.setText(getBaseActivity().getString(R.string.property_id_old_id_in_ar_not_in_field_btn_text));
        btnVerification7.setText(getBaseActivity().getString(R.string.property_id_decline_btn_text));


        ArrayList<CommonItem> listOwnerDetails = new ArrayList<CommonItem>();
        for (ExcelDataItem item : dataItems) {
            CommonItem data = new CommonItem();
            String owners = "", oldpropertyyId = "";
            if (!(item.getOldWard().length() == 0 || item.getOldWard().equalsIgnoreCase(NR_UPPERCASE) || item.getOldWard().equalsIgnoreCase(NA_UPPERCASE))) {
                oldpropertyyId = item.getOldWard();
            }
            if (!(item.getOldPropertyNo().length() == 0 || item.getOldPropertyNo().equalsIgnoreCase(NR_UPPERCASE) || item.getOldPropertyNo().equalsIgnoreCase(NA_UPPERCASE))) {
                if (oldpropertyyId.length() == 0) {
                    oldpropertyyId = item.getOldPropertyNo();
                } else {
                    oldpropertyyId = oldpropertyyId + "/" + item.getOldPropertyNo();
                }

            }
            if (!(item.getOldSubNo().length() == 0 || item.getOldSubNo().equalsIgnoreCase(NR_UPPERCASE) || item.getOldSubNo().equalsIgnoreCase(NA_UPPERCASE))) {
                if (oldpropertyyId.length() == 0) {
                    oldpropertyyId = item.getOldSubNo();
                } else {
                    oldpropertyyId = oldpropertyyId + "/" + item.getOldSubNo();
                }

            }
            if (oldpropertyyId.length() == 0) {
                oldpropertyyId = getBaseActivity().getString(R.string.cmn_no_value);
            }
            owners = item.getOwnerAddressEng() + "\n" + getBaseActivity().getString(R.string.property_old_property_id) + " : " + oldpropertyyId;
            data.setContent(owners);
            listOwnerDetails.add(data);
        }

        if (listOwnerDetails.size() > 1) {
            etOwnerDetails.setAdapter(new CommonDropDownAdapter(getBaseActivity(), R.layout.dialog_property_id_verification, listOwnerDetails));

            etOwnerDetails.setOnItemClickListener((parent, view1, position, id) -> {
                selectedUid = dataItems.get(position).getUid();
                selectedZone=dataItems.get(position).getArZone();
                selectedAC=dataItems.get(position).getArAC();
                selectedFloorArea=dataItems.get(position).getArFloorArea();
                selectedBuildingUsage=dataItems.get(position).getArBuildingUsage();
                selectedRoadType=dataItems.get(position).getArRoadTypea();
                selectedRoadName=dataItems.get(position).getRoadName();
                selectedBuildingAge=dataItems.get(position).getArBuildingAge();
                selectedRoofDetails=dataItems.get(position).getArRoofDetail();
                selectedFloorDetails=dataItems.get(position).getArFloorDetails();
                selectedModification=dataItems.get(position).getArModification();
                selectedOccupierDetails=dataItems.get(position).getArOccupierDetails();
                selectedArTaxTotal=dataItems.get(position).getArTaxtTotal();

                selectedOldPropWardNo = dataItems.get(position).getOldWard();
                selectedOldPropPropNo = dataItems.get(position).getOldPropertyNo();
                selectedOldPropSubNo = dataItems.get(position).getOldSubNo();
                selectedAddress = dataItems.get(position).getOwnerAddressEng();
                if (viewModel.isBuildStatusOnGoing.get() || viewModel.isPropertyStatusOngoingWithoutRoof.get()) {
                    //Ongoing case no old prop id
                    btnVerification4.setVisibility(View.VISIBLE);
                    btnVerification6.setVisibility(View.VISIBLE);
                } else {
                    if (dataItems.get(position).getOldWard().length() == 0 && dataItems.get(position).getOldPropertyNo().length() == 0 && dataItems.get(position).getOldSubNo().length() == 0) {
                        btnVerification5.setVisibility(View.VISIBLE);
                        btnVerification4.setVisibility(View.VISIBLE);
                        btnVerification1.setVisibility(View.GONE);
                        btnVerification2.setVisibility(View.GONE);
                        btnVerification3.setVisibility(View.GONE);
                    } else {
                        btnVerification5.setVisibility(View.GONE);
                        btnVerification4.setVisibility(View.GONE);
                        btnVerification1.setVisibility(View.VISIBLE);
                        btnVerification2.setVisibility(View.VISIBLE);
                        btnVerification3.setVisibility(View.VISIBLE);
                    }
                }

            });
        } else {
            selectedUid = dataItems.get(0).getUid();
            selectedZone=dataItems.get(0).getArZone();
            selectedAC=dataItems.get(0).getArAC();
            selectedFloorArea=dataItems.get(0).getArFloorArea();
            selectedBuildingUsage=dataItems.get(0).getArBuildingUsage();
            selectedRoadType=dataItems.get(0).getArRoadTypea();
            selectedRoadName=dataItems.get(0).getRoadName();
            selectedBuildingAge=dataItems.get(0).getArBuildingAge();
            selectedRoofDetails=dataItems.get(0).getArRoofDetail();
            selectedFloorDetails=dataItems.get(0).getArFloorDetails();
            selectedModification=dataItems.get(0).getArModification();
            selectedOccupierDetails=dataItems.get(0).getArOccupierDetails();
            selectedArTaxTotal=dataItems.get(0).getArTaxtTotal();

            etOwnerDetails.setText(listOwnerDetails.get(0).getContent());
            selectedOldPropWardNo = dataItems.get(0).getOldWard();
            selectedOldPropPropNo = dataItems.get(0).getOldPropertyNo();
            selectedOldPropSubNo = dataItems.get(0).getOldSubNo();
            selectedAddress = dataItems.get(0).getOwnerAddressEng();
            if (viewModel.isBuildStatusOnGoing.get() || viewModel.isPropertyStatusOngoingWithoutRoof.get()) {
                //Ongoing case no old prop id
                btnVerification4.setVisibility(View.VISIBLE);
                btnVerification6.setVisibility(View.VISIBLE);
            } else {
                if (dataItems.get(0).getOldWard().length() == 0 && dataItems.get(0).getOldPropertyNo().length() == 0 && dataItems.get(0).getOldSubNo().length() == 0) {
                    btnVerification5.setVisibility(View.VISIBLE);
                    btnVerification4.setVisibility(View.VISIBLE);
                    btnVerification1.setVisibility(View.GONE);
                    btnVerification2.setVisibility(View.GONE);
                    btnVerification3.setVisibility(View.GONE);
                } else {
                    btnVerification5.setVisibility(View.GONE);
                    btnVerification4.setVisibility(View.GONE);
                    btnVerification1.setVisibility(View.VISIBLE);
                    btnVerification2.setVisibility(View.VISIBLE);
                    btnVerification3.setVisibility(View.VISIBLE);
                }
            }
        }


        btnVerification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 CONDITION1 : Old Property ID in AR and field are same
                 VISIBILITY: IF OLD PROPID from AR IS PRESENT
                 PROCESS   : Auto set old property id,UID,
                 MASSAGE   : OWNER_STATUS + Old Property ID in AR and field are same
                 Field     :newproid_ar_remarks
                 */
                if (viewModel.validateOwnerSelection(selectedAddress)) {

                    if (rgOwnerDeatils.getCheckedRadioButtonId() != -1) {
                        ownerStaus = ((RadioButton) dialogView.findViewById(rgOwnerDeatils.getCheckedRadioButtonId())).getText().toString();
                        alertDialog.dismiss();
                        disableOldPropertyId();
                        setOldPropertyIdUid(NOT_NEEDED, btnVerification1.getText().toString());
                    } else {
                        showToast(getString(R.string.property_owner_status_selection));
                    }

                }

            }
        });

        btnVerification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * CONDITION2 :Old Property ID wrong in field
                 * VISIBILITY: IF OLD PROPID from AR IS PRESENT
                 * PROCESS   : Auto set old property id,UID and popup a text box for enter olpd property id and add to massage
                 * MASSAGE   :OWNER_STATUS + SAME AS CONDIOTION + (old pro id enterd in text box)
                 * Field     :newproid_ar_remarks"
                 */
                if (rgOwnerDeatils.getCheckedRadioButtonId() != -1) {
                    ownerStaus = ((RadioButton) rgOwnerDeatils.findViewById(rgOwnerDeatils.getCheckedRadioButtonId())).getText().toString();
                    AlertDialog.Builder builderOldProp = new AlertDialog.Builder(getBaseActivity());
                    ViewGroup viewGroup = getBaseActivity().findViewById(android.R.id.content);
                    View dialogViewOldProp = LayoutInflater.from(getBaseActivity()).inflate(R.layout.dialog_old_prop, viewGroup, false);
                    builderOldProp.setView(dialogViewOldProp);
                    AlertDialog alertDialogOldProp = builderOldProp.create();
                    alertDialogOldProp.show();
                    alertDialogOldProp.setCancelable(false);
                    alertDialog.dismiss();
                    Button btnSubmit = (Button) dialogViewOldProp.findViewById(R.id.buttonOldPropSubmit);
                    EditText etOldPropertyId1 = (EditText) dialogViewOldProp.findViewById(R.id.etOldPropertyId1);
                    EditText etOldPropertyId2 = (EditText) dialogViewOldProp.findViewById(R.id.etOldPropertyId2);
                    EditText etOldPropertyId3 = (EditText) dialogViewOldProp.findViewById(R.id.etOldPropertyId3);
                    ImageView btnNaOldProprty3 = (ImageView) dialogViewOldProp.findViewById(R.id.btnNaOldProprty3);
                    ImageView nrOldPropertyIdValue = (ImageView) dialogViewOldProp.findViewById(R.id.nrOldPropertyIdValue);
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (viewModel.validateOldPropertyForDialog(etOldPropertyId1.getText().toString(), etOldPropertyId2.getText().toString(), etOldPropertyId3.getText().toString())) {
                                setOldPropertyIdUid(NOT_NEEDED, btnVerification2.getText().toString() + "(" + getString(R.string.property_old_property_id_from_field) + ":" + viewModel.getOldPrpertyId(etOldPropertyId1.getText().toString(), etOldPropertyId2.getText().toString(), etOldPropertyId3.getText().toString()));
                                disableOldPropertyId();
                                alertDialogOldProp.dismiss();
                            }
                        }
                    });
                    btnNaOldProprty3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etOldPropertyId3.setText(NA_UPPERCASE);
                        }
                    });
                    nrOldPropertyIdValue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etOldPropertyId3.setText(NR_UPPERCASE);
                            etOldPropertyId2.setText(NR_UPPERCASE);
                            etOldPropertyId1.setText(NR_UPPERCASE);
                        }
                    });

//
//
                } else {
                    showToast(getString(R.string.property_owner_status_selection));
                }

            }

        });
        btnVerification3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * CONDITION3 : Old Property ID Not Received from field
                 * VISIBILITY: IF OLD PROPID from AR IS PRESENT
                 * PROCESS   : Auto set old property id,UID
                 * MASSAGE   :OWNER_STATUS + SAME AS CONDIOTION
                 * Field             :newproid_ar_remarks"
                 */
                if (rgOwnerDeatils.getCheckedRadioButtonId() != -1) {
                    ownerStaus = ((RadioButton) rgOwnerDeatils.findViewById(rgOwnerDeatils.getCheckedRadioButtonId())).getText().toString();
                    setOldPropertyIdUid(NOT_NEEDED, btnVerification3.getText().toString());
                    disableOldPropertyId();
                    alertDialog.dismiss();
                } else {
                    showToast(getString(R.string.property_owner_status_selection));
                }

            }
        });
        btnVerification4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *CONDITION4 : Old Property ID not in AR and field becuase of it is a new property
                 * VISIBILITY: IF OLD PROPID from AR IS NOT PRESENT
                 * PROCESS   : Auto set old property id as NA,UID
                 * MASSAGE   :If ONGOING/ONGOING WITHOUT ROOF ->BUILDING STATUS + OWNER_STATUS + SAME AS CONDIOTION
                 *              ELSE ->OWNER_STATUS + SAME AS CONDIOTION
                 * Field             :newproid_arremarks"
                 */
                if (rgOwnerDeatils.getCheckedRadioButtonId() != -1) {
                    ownerStaus = ((RadioButton) rgOwnerDeatils.findViewById(rgOwnerDeatils.getCheckedRadioButtonId())).getText().toString();
                    setOldPropertyIdUid(NA_UPPERCASE, btnVerification4.getText().toString());
                    disableOldPropertyId();
                    alertDialog.dismiss();
                } else {
                    showToast(getString(R.string.property_owner_status_selection));
                }

            }
        });
        btnVerification5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "NEW CONDITION5 : Old Property ID not in AR but in field
                 * VISIBILITY: IF OLD PROPID from AR IS NOT PRESENT
                 * PROCESS   : Auto set UID, Enable old pro id
                 * MASSAGE   :OWNER_STATUS + SAME AS CONDIOTION
                 * Field     :newproid_ar_remarks"
                 */
                if (rgOwnerDeatils.getCheckedRadioButtonId() != -1) {
                    ownerStaus = ((RadioButton) rgOwnerDeatils.findViewById(rgOwnerDeatils.getCheckedRadioButtonId())).getText().toString();
                    setOldPropertyIdUid(CLEAR, btnVerification5.getText().toString());
                    enableOldPropertyId();
                    alertDialog.dismiss();
                } else {
                    showToast(getString(R.string.property_owner_status_selection));
                }


            }
        });
        btnVerification6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *CONDITION6 : Old Property ID not in AR and field becuase of it is a new property
                 * VISIBILITY: IF OLD PROPID from AR IS NOT PRESENT
                 * PROCESS   : Auto set old property id as NA,UID
                 * MASSAGE   :If ONGOING/ONGOING WITHOUT ROOF ->BUILDING STATUS + OWNER_STATUS + SAME AS CONDIOTION
                 *              ELSE ->OWNER_STATUS + SAME AS CONDIOTION
                 * Field             :newproid_arremarks"
                 */
                if (rgOwnerDeatils.getCheckedRadioButtonId() != -1) {
                    ownerStaus = ((RadioButton) rgOwnerDeatils.findViewById(rgOwnerDeatils.getCheckedRadioButtonId())).getText().toString();
                    setOldPropertyIdUid(NA_UPPERCASE, btnVerification6.getText().toString() + getString(R.string.property_old_property_id_from_ar) + viewModel.getOldPrpertyId(selectedOldPropWardNo, selectedOldPropPropNo, selectedOldPropSubNo));
                    alertDialog.dismiss();
                    showInfoDialog(getString(R.string.property_old_property_id_from_ar_warning));
                } else {
                    showToast(getString(R.string.property_owner_status_selection));
                }

            }
        });
        btnVerification7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }


    private void enableOldPropertyId() {
        getViewDataBinding().etOldPropertyId1.setEnabled(true);
        getViewDataBinding().etOldPropertyId2.setEnabled(true);
        getViewDataBinding().etOldPropertyId3.setEnabled(true);
        getViewDataBinding().layoutOldropertyId1.setAlpha(1);
        getViewDataBinding().layoutOldPropertyId2.setAlpha(1);
        getViewDataBinding().layoutOldPropertyId3.setAlpha(1);
        viewModel.isOldPropIdEnabled.set(true);
    }

    private void disableOldPropertyId() {
        getViewDataBinding().etOldPropertyId1.setEnabled(false);
        getViewDataBinding().etOldPropertyId2.setEnabled(false);
        getViewDataBinding().etOldPropertyId3.setEnabled(false);
        getViewDataBinding().layoutOldropertyId1.setAlpha(.5f);
        getViewDataBinding().layoutOldPropertyId2.setAlpha(.5f);
        getViewDataBinding().layoutOldPropertyId3.setAlpha(.5f);
        viewModel.isOldPropIdEnabled.set(false);
    }

    public void setOldPropertyIdUid(String oldPropCommonValue, String message) {
        if (oldPropCommonValue.equalsIgnoreCase(NOT_NEEDED)) {
            if (selectedOldPropWardNo.length() == 0) {
                viewModel.oldPropertyID1.setValue(NA_UPPERCASE);
            } else {
                viewModel.oldPropertyID1.setValue(selectedOldPropWardNo);
            }
            if (selectedOldPropPropNo.length() == 0) {
                viewModel.oldPropertyID2.setValue(NA_UPPERCASE);
            } else {
                viewModel.oldPropertyID2.setValue(selectedOldPropPropNo);
            }
            if (selectedOldPropSubNo.length() == 0) {
                viewModel.oldPropertyID3.setValue(NA_UPPERCASE);
            } else {
                viewModel.oldPropertyID3.setValue(selectedOldPropSubNo);
            }
        } else {

            viewModel.oldPropertyID1.setValue(oldPropCommonValue);
            viewModel.oldPropertyID2.setValue(oldPropCommonValue);
            viewModel.oldPropertyID3.setValue(oldPropCommonValue);
        }
        if (!viewModel.isSubTypeRelatedBuilding.get())
            viewModel.uID.setValue(selectedUid);

        viewModel.ar_zone.setValue(selectedZone);
        viewModel.ar_ac.setValue(selectedAC);
        viewModel.ar_floor_area.setValue(selectedFloorArea);
        viewModel.ar_building_usage.setValue(selectedBuildingUsage);
        viewModel.ar_road_type.setValue(selectedRoadType);
        viewModel.ar_road_name.setValue(selectedRoadName);
        viewModel.ar_building_age.setValue(selectedBuildingAge);
        viewModel.ar_roof_details.setValue(selectedRoofDetails);
        viewModel.ar_floor_details.setValue(selectedFloorDetails);
        viewModel.ar_modification.setValue(selectedModification);
        viewModel.ar_occupier_details.setValue(selectedOccupierDetails);
        viewModel.ar_tax_total.setValue(selectedArTaxTotal);
        viewModel.isARBuildingUsageVisible.set(true);
        viewModel.isARACVisible.set(true);
        viewModel.ar_building_usage_hint.setValue(getString(R.string.building_usage_ar_hint)+selectedBuildingUsage);
        viewModel.ar_ac_hint.setValue(getString(R.string.building_ac_ar_hint)+selectedAC);



        String buildingStatus = "";
        if (viewModel.isBuildStatusOnGoing.get()) {
            buildingStatus = AppConstants.BUILDING_STATUS_ONGOING + " / ";
        } else if (viewModel.isPropertyStatusOngoingWithoutRoof.get()) {
            buildingStatus = AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF + " / ";
        }
        viewModel.newPropARRemark.setValue(buildingStatus + ownerStaus + " / " + message);
        if (selectedAddress.length() == 0) {
            viewModel.ar_owner_address.setValue(ownerStaus);
        } else {
            viewModel.ar_owner_address.setValue(selectedAddress);
        }

    }

    @Override
    public void showNotFoundPropertyId(String message, String propId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity());
        ViewGroup viewGroup = getBaseActivity().findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.dialog_property_id_not_found, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button btnCondition1 = (Button) dialogView.findViewById(R.id.btnCondition1);
        Button btnCondition2 = (Button) dialogView.findViewById(R.id.btnCondition2);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        TextView txtTitle = (TextView) dialogView.findViewById(R.id.textviewTitle);
        TextView txtMsg = (TextView) dialogView.findViewById(R.id.textviewMessgae);
        txtTitle.setText(getString(R.string.property_new_property_id) + " : " + propId);
        btnCondition1.setText(R.string.property_not_ar_condition1);
        btnCondition2.setText(R.string.property_not_ar_condition2);
        btnCancel.setText(R.string.property_not_ar_dismiss);
        txtMsg.setText(message);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnCondition1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.newPropARRemark.setValue(getString(R.string.property_not_ar_condition1));
                enableOldPropertyId();
                alertDialog.dismiss();
            }
        });
        btnCondition2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.newPropARRemark.setValue(getString(R.string.property_not_ar_condition2));
                enableOldPropertyId();
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void showARDetailsOldPropertyId(ArrayList<ExcelDataItem> dataItems) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity());
        ViewGroup viewGroup = getBaseActivity().findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.dialog_property_id_verification, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button btnVerification1 = (Button) dialogView.findViewById(R.id.btnVerification1);
        Button btnVerification2 = (Button) dialogView.findViewById(R.id.btnVerification2);
        Button btnVerification3 = (Button) dialogView.findViewById(R.id.btnVerification3);
        Button btnVerification4 = (Button) dialogView.findViewById(R.id.btnVerification4);
        Button btnVerification5 = (Button) dialogView.findViewById(R.id.btnVerification5);
        IPMSSpinner etOwnerDetails = (IPMSSpinner) dialogView.findViewById(R.id.etOwnerDetails);
        btnVerification1.setText(getBaseActivity().getString(R.string.property_id_confirm_btn_text));
        btnVerification2.setText(getBaseActivity().getString(R.string.property_id_decline_btn_text));
        btnVerification3.setText(getBaseActivity().getString(R.string.property_id_new_id_change_owner_same_btn_text));
        btnVerification4.setText(getBaseActivity().getString(R.string.property_id_new_id_same_owner_change_btn_text));
        btnVerification5.setVisibility(View.GONE);

        ArrayList<CommonItem> listOwnerDetails = new ArrayList<CommonItem>();
        for (ExcelDataItem item : dataItems) {
            CommonItem data = new CommonItem();
            String owners = "", NewPropertyyId = "";
            if (!(item.getNewWard().length() == 0 || item.getNewWard().equalsIgnoreCase(NR_UPPERCASE) || item.getNewWard().equalsIgnoreCase(NA_UPPERCASE))) {
                NewPropertyyId = item.getNewWard();
            }
            if (!(item.getNewPropertyNo().length() == 0 || item.getNewPropertyNo().equalsIgnoreCase(NR_UPPERCASE) || item.getNewPropertyNo().equalsIgnoreCase(NA_UPPERCASE))) {
                if (NewPropertyyId.length() == 0) {
                    NewPropertyyId = item.getNewPropertyNo();
                } else {
                    NewPropertyyId = NewPropertyyId + "/" + item.getNewPropertyNo();
                }

            }
            if (!(item.getNewSubNo().length() == 0 || item.getNewSubNo().equalsIgnoreCase(NR_UPPERCASE) || item.getNewSubNo().equalsIgnoreCase(NA_UPPERCASE))) {
                if (NewPropertyyId.length() == 0) {
                    NewPropertyyId = item.getNewSubNo();
                } else {
                    NewPropertyyId = NewPropertyyId + "/" + item.getNewSubNo();
                }

            }
            if (NewPropertyyId.length() == 0) {
                NewPropertyyId = getBaseActivity().getString(R.string.cmn_no_value);
                ;
            }
            owners = item.getOwnerAddressEng() + "\n" + getBaseActivity().getString(R.string.property_new_property_id) + " : " + NewPropertyyId;
            data.setContent(owners);
            listOwnerDetails.add(data);
        }

        if (listOwnerDetails.size() > 1) {
            etOwnerDetails.setAdapter(new CommonDropDownAdapter(getBaseActivity(), R.layout.dialog_property_id_verification, listOwnerDetails));

            etOwnerDetails.setOnItemClickListener((parent, view1, position, id) -> {
                selectedUid = dataItems.get(position).getUid();
                selectedNewPropWardNo = dataItems.get(position).getNewWard();
                selectedNewPropPropNo = dataItems.get(position).getNewPropertyNo();
                selectedNewPropSubNo = dataItems.get(position).getNewSubNo();
            });
        } else {
            selectedUid = dataItems.get(0).getUid();
            etOwnerDetails.setText(listOwnerDetails.get(0).getContent());
            selectedNewPropWardNo = dataItems.get(0).getNewWard();
            selectedNewPropPropNo = dataItems.get(0).getNewPropertyNo();
            selectedNewPropSubNo = dataItems.get(0).getNewSubNo();
        }


//        if (owners.length() != 0)
//            showInfoDialog("Owner : "+owners);
        btnVerification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.validateOwnerSelection(selectedAddress)) {
                    alertDialog.dismiss();
                    viewModel.uID.setValue(selectedUid);
                    viewModel.newPropertyID1.setValue("");
                    viewModel.newPropertyID2.setValue(selectedNewPropWardNo);
                    viewModel.newPropertyID3.setValue(selectedNewPropPropNo);
                    viewModel.newPropertyID4.setValue(selectedNewPropSubNo);
                }

            }
        });
        btnVerification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnVerification3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Old prop id different
                if (viewModel.validateOwnerSelection(selectedAddress)) {
                    viewModel.newPropertyID1.setValue("");
                    viewModel.newPropertyID2.setValue(selectedNewPropWardNo);
                    viewModel.newPropertyID3.setValue(selectedNewPropPropNo);
                    viewModel.newPropertyID4.setValue(selectedNewPropSubNo);
                    viewModel.uID.setValue(selectedUid);
                    alertDialog.dismiss();
                }

            }
        });
        btnVerification4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //old proprty id same
                //owner details different
                if (viewModel.validateOwnerSelection(selectedAddress)) {
                    showToast("will update on the comment");
                    viewModel.uID.setValue(selectedUid);
                    alertDialog.dismiss();
                }
            }
        });

    }

    public void setPropertyUsageButtonStatus(boolean status) {
        if (!viewModel.isBuildStatusDemolishedUnusable.get()) {
            if (status) {
                getViewDataBinding().rgBuildingUsage.check(R.id.rbrgBuildingOwned);
                getViewDataBinding().rbrgBuildingRented.setEnabled(false);
                getViewDataBinding().rbrgBuildingOwned.setEnabled(false);
                getViewDataBinding().rgBuildingUsage.setAlpha(.5f);

            } else {
                getViewDataBinding().rgBuildingUsage.clearCheck();
                getViewDataBinding().rbrgBuildingRented.setEnabled(true);
                getViewDataBinding().rbrgBuildingOwned.setEnabled(true);
                getViewDataBinding().rgBuildingUsage.setAlpha(1);
            }
        }
    }


    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }

    @Override
    public void checkNewPropertyId() {
        String newPropertyId2 = Objects.requireNonNull(getViewDataBinding().etNewPropertyId2.getText()).toString().trim();
        String newPropertyId3 = Objects.requireNonNull(getViewDataBinding().etNewPropertyId3.getText()).toString().trim();
        String newPropertyId4 = Objects.requireNonNull(getViewDataBinding().etNewPropertyId4.getText()).toString().trim();
        if (viewModel.validateNewPropertyForCheck(newPropertyId3, newPropertyId4)) {
            File xlsFile = new File(viewModel.getARFileLoc());
            viewModel.getExcelData(newPropertyId2, newPropertyId3, newPropertyId4, NEW_PROP_ID_VERIFICATION);
        }

    }

    @Override
    public void checkOldPropertyId() {
        String oldPropertyId1 = Objects.requireNonNull(getViewDataBinding().etOldPropertyId1.getText()).toString().trim();
        String oldPropertyId2 = Objects.requireNonNull(getViewDataBinding().etOldPropertyId2.getText()).toString().trim();
        String oldPropertyId3 = Objects.requireNonNull(getViewDataBinding().etOldPropertyId3.getText()).toString().trim();
        if (viewModel.validateOldPropertyForCheck(oldPropertyId1, oldPropertyId2, oldPropertyId3)) {
            viewModel.getExcelData(oldPropertyId1, oldPropertyId2, oldPropertyId3, OLD_PROP_ID_VERIFICATION);
        }
    }

    @Override
    public void showWarning() {
        showDialog(getString(R.string.cmn_warning), getString(R.string.property_duplication_with_server), "", null, getString(android.R.string.ok), (dialog, which) -> dialog.cancel());

    }

    @Override
    public void preventDoubleClick() {
        CommonUtils.preventTwoClick((getViewDataBinding().textviewNewPropertyCheck));
        CommonUtils.preventTwoClick((getViewDataBinding().textviewOldPropertyCheck));
    }
}