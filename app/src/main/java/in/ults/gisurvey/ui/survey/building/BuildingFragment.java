package in.ults.gisurvey.ui.survey.building;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.databinding.FragmentBuildingBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewMultiSelectAdapter;

import static in.ults.gisurvey.utils.AppConstants.ROOF_TYPE_WARNING_YEAR;
import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class BuildingFragment extends BaseFragment<FragmentBuildingBinding, BuildingViewModel> implements BuildingNavigator {

    public static final String TAG = BuildingFragment.class.getSimpleName();
    static final int BUILDING_NAME_ERROR = 1;
    static final int YEAR_OF_CONSTRUCTION_ERROR = 2;
    static final int NUMBER_OF_FLOOR_ERROR = 3;
    static final int NUMBER_OF_FLOOR_AREA_ERROR = 4;
    static final int ROOF_TOTAL_ERROR = 5;
    static final int NUMBER_OF_ROOF_TYPE_ERROR = 6;
    static final int ROOF_TYPE_ERROR = 7;
    static final int COMMON_ERROR = 8;
    static final int NUMBER_OF_ROOM_ERROR = 9;
    static final int CAR_PORCH_AREA_ERROR = 10;
    static final int COMMON_STAIRS_AREA_ERROR = 11;
    static final int COLONY_NAME_ERROR = 12;
    static final int PATHWAY_AREA_ERROR = 13;
    static final int STRUCTURAL_YEAR_ERROR = 14;
    static final int ROOF_CHANGE_YEAR_ERROR = 15;
    static final int OTHER_BUILDING_DETAILS_ERROR = 16;
    static final int SURVEY_NUMBER_ERROR = 17;
    private int floorCount = 0;
    static final int STAIRS_AREA_ERROR = 18;
    static final int AREA_REMARKS_ERROR = 19;

    private BuildingViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    FlexRecyclerViewMultiSelectAdapter floorTypeAdapter;

    @Inject
    FlexboxLayoutManager floorTypeLayoutManager;

    @Inject
    FlexRecyclerViewMultiSelectAdapter wallTypeAdapter;

    @Inject
    FlexboxLayoutManager wallTypeLayoutManager;

    @Inject
    BuildingRoofTypeAdapter rootTypeAdapter;

    @Inject
    BuildingFloorAreaAdapter floorAreaAdapter;

    @Inject
    LinearLayoutManager floorLayoutManager;

    @Inject
    LinearLayoutManager roofTypeLayoutManager;

    private int currentYear;

    public static BuildingFragment newInstance() {
        Bundle args = new Bundle();
        BuildingFragment fragment = new BuildingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_building;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public BuildingViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(BuildingViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_building);
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
            case BUILDING_NAME_ERROR:
                getViewDataBinding().layoutBuildingName.setError(error);
                getViewDataBinding().layoutBuildingName.getParent().requestChildFocus
                        (getViewDataBinding().layoutBuildingName, getViewDataBinding().layoutBuildingName);
                break;
            case SURVEY_NUMBER_ERROR:
                getViewDataBinding().layoutSurveyNumber.setError(error);
                getViewDataBinding().layoutSurveyNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutSurveyNumber, getViewDataBinding().layoutSurveyNumber);
                break;
            case YEAR_OF_CONSTRUCTION_ERROR:
                getViewDataBinding().layoutYearOfConstruction.setError(error);
                getViewDataBinding().layoutYearOfConstruction.getParent().requestChildFocus
                        (getViewDataBinding().layoutYearOfConstruction, getViewDataBinding().layoutYearOfConstruction);
                break;
            case NUMBER_OF_ROOM_ERROR:
                getViewDataBinding().layoutNoOfRooms.setError(error);
                getViewDataBinding().layoutNoOfRooms.getParent().requestChildFocus
                        (getViewDataBinding().layoutNoOfRooms, getViewDataBinding().layoutNoOfRooms);
                break;
            case NUMBER_OF_FLOOR_ERROR:
                getViewDataBinding().layoutNoOfFloor.setError(error);
                getViewDataBinding().layoutNoOfFloor.getParent().requestChildFocus
                        (getViewDataBinding().layoutNoOfFloor, getViewDataBinding().layoutNoOfFloor);
                break;
            case ROOF_TOTAL_ERROR:
                getViewDataBinding().layoutRoofTotalPercent.setError(error);
                getViewDataBinding().layoutRoofTotalPercent.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoofTotalPercent, getViewDataBinding().layoutRoofTotalPercent);
                break;
            case NUMBER_OF_ROOF_TYPE_ERROR:
                getViewDataBinding().layoutNoOfRoof.setError(error);
                getViewDataBinding().layoutNoOfRoof.getParent().requestChildFocus
                        (getViewDataBinding().layoutNoOfRoof, getViewDataBinding().layoutNoOfRoof);
                break;
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
            case NUMBER_OF_FLOOR_AREA_ERROR:
                floorAreaAdapter.setValidation(true);
                break;
            case CAR_PORCH_AREA_ERROR:
                getViewDataBinding().layoutPorchArea.setError(error);
                getViewDataBinding().layoutPorchArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutPorchArea, getViewDataBinding().layoutPorchArea);
                break;
            case COLONY_NAME_ERROR:
                getViewDataBinding().layoutColonyName.setError(error);
                getViewDataBinding().layoutColonyName.getParent().requestChildFocus
                        (getViewDataBinding().layoutColonyName, getViewDataBinding().layoutColonyName);
                break;
            case PATHWAY_AREA_ERROR:
                getViewDataBinding().layoutPathWayArea.setError(error);
                getViewDataBinding().layoutPathWayArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutPathWayArea, getViewDataBinding().layoutPathWayArea);
                break;
            case STRUCTURAL_YEAR_ERROR:
                getViewDataBinding().layoutAnyStructuralChangeYear.setError(error);
                getViewDataBinding().layoutAnyStructuralChangeYear.getParent().requestChildFocus
                        (getViewDataBinding().layoutAnyStructuralChangeYear, getViewDataBinding().layoutAnyStructuralChangeYear);
                break;
            case ROOF_CHANGE_YEAR_ERROR:
                getViewDataBinding().layoutAnyRoofTypeChangeYear.setError(error);
                getViewDataBinding().layoutAnyRoofTypeChangeYear.getParent().requestChildFocus
                        (getViewDataBinding().layoutAnyRoofTypeChangeYear, getViewDataBinding().layoutAnyRoofTypeChangeYear);
                break;
            case OTHER_BUILDING_DETAILS_ERROR:
                getViewDataBinding().layoutOtherBuildingDetails.setError(error);
                getViewDataBinding().layoutOtherBuildingDetails.getParent().requestChildFocus
                        (getViewDataBinding().layoutOtherBuildingDetails, getViewDataBinding().layoutOtherBuildingDetails);
                break;
            case COMMON_STAIRS_AREA_ERROR:
                getViewDataBinding().layoutCommonStairArea.setError(error);
                getViewDataBinding().layoutCommonStairArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutCommonStairArea, getViewDataBinding().layoutCommonStairArea);
                break;
            case STAIRS_AREA_ERROR:
                getViewDataBinding().layoutStairArea.setError(error);
                getViewDataBinding().layoutStairArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutStairArea, getViewDataBinding().layoutStairArea);
                break;
            case ROOF_TYPE_ERROR:
                rootTypeAdapter.setValidation(true);
                break;
        }
    }

    /**
     * to validate fields and save building details
     */
    @Override
    public void saveBuildingDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String buildingName = Objects.requireNonNull(getViewDataBinding().etBuildingName.getText()).toString().trim();
        String colonyName = Objects.requireNonNull(getViewDataBinding().etColonyName.getText()).toString().trim();
        String surveyNumber = Objects.requireNonNull(getViewDataBinding().etSurveyNumber.getText()).toString().trim();
        String yearsOfConstruction = Objects.requireNonNull(getViewDataBinding().etYearOfConstruction.getText()).toString().trim();
        String totalYears = Objects.requireNonNull(getViewDataBinding().etHowManyYears.getText()).toString().trim();
        String noOfFloors = Objects.requireNonNull(getViewDataBinding().etNoOfFloor.getText()).toString().trim();
        String noOfRooms = Objects.requireNonNull(getViewDataBinding().etNoOfRooms.getText()).toString().trim();
        String pathwayArea = Objects.requireNonNull(getViewDataBinding().etPathWayArea.getText()).toString().trim();
        ArrayList<BuildingDetailsFloorAreaItem> floorArea = floorAreaAdapter.getDataList();
        String areaRemarks = Objects.requireNonNull(getViewDataBinding().etAreaRemarks.getText()).toString().trim();
        if(Integer.parseInt(noOfFloors) <= 0){
            areaRemarks = getString(R.string.cmn_na);
        }

        String carPorch = "", structureType = "", commonStair = "", otherBuilding = "",stair = "", higherFloorSqft = "", isAnyStructuralChange = "", isRoofTypeChange = "";
        if (getViewDataBinding().rgCarPorch.getCheckedRadioButtonId() != -1) {
            carPorch = ((RadioButton) getViewDataBinding().rgCarPorch.findViewById(getViewDataBinding().rgCarPorch.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgStructureType.getCheckedRadioButtonId() != -1) {
            structureType = ((RadioButton) getViewDataBinding().rgStructureType.findViewById(getViewDataBinding().rgStructureType.getCheckedRadioButtonId())).getText().toString();
        }
        String carPorchArea = Objects.requireNonNull(getViewDataBinding().etPorchArea.getText()).toString().trim();
        if (carPorch.equalsIgnoreCase(getString(R.string.cmn_nr))) {
            carPorchArea = getString(R.string.cmn_nr);
        }
        if (getViewDataBinding().rgCommonStair.getCheckedRadioButtonId() != -1) {
            commonStair = ((RadioButton) getViewDataBinding().rgCommonStair.findViewById(getViewDataBinding().rgCommonStair.getCheckedRadioButtonId())).getText().toString();
        }
//        if (getViewDataBinding().rgStair.getCheckedRadioButtonId() != -1) {
//            stair = ((RadioButton) getViewDataBinding().rgStair.findViewById(getViewDataBinding().rgStair.getCheckedRadioButtonId())).getText().toString();
//        }
        stair = getString(R.string.cmn_na);
        String commonStairArea = Objects.requireNonNull(getViewDataBinding().etCommonStairArea.getText()).toString().trim();
        String stairArea = Objects.requireNonNull(getViewDataBinding().etStairArea.getText()).toString().trim();
        if (commonStair.equalsIgnoreCase(getString(R.string.cmn_nr))) {
            commonStairArea = getString(R.string.cmn_nr);
        }
        if (stair.equalsIgnoreCase(getString(R.string.cmn_na))) {
            stairArea = getString(R.string.cmn_na);
        }
        if (getViewDataBinding().rgOtherBuilding.getCheckedRadioButtonId() != -1) {
            otherBuilding = ((RadioButton) getViewDataBinding().rgOtherBuilding.findViewById(getViewDataBinding().rgOtherBuilding.getCheckedRadioButtonId())).getText().toString();
        }
        String otherBuildingDetails = Objects.requireNonNull(getViewDataBinding().etOtherBuildingDetails.getText()).toString().trim();

        if (getViewDataBinding().rgAnyStructuralChange.getCheckedRadioButtonId() != -1) {
            isAnyStructuralChange = ((RadioButton) getViewDataBinding().rgAnyStructuralChange.findViewById(getViewDataBinding().rgAnyStructuralChange.getCheckedRadioButtonId())).getText().toString();
        }
        String structuralChangeYear = Objects.requireNonNull(getViewDataBinding().etAnyStructuralChangeYear.getText()).toString().trim();


        if (getViewDataBinding().rgAnyRoofTypeChange.getCheckedRadioButtonId() != -1) {
            isRoofTypeChange = ((RadioButton) getViewDataBinding().rgAnyRoofTypeChange.findViewById(getViewDataBinding().rgAnyRoofTypeChange.getCheckedRadioButtonId())).getText().toString();
        }
        String roofTypeChangeYear = Objects.requireNonNull(getViewDataBinding().etAnyRoofTypeChangeYear.getText()).toString().trim();
        if (getViewDataBinding().rgHigherFloorType.getVisibility() == View.VISIBLE) {
            if (getViewDataBinding().rgHigherFloorType.getCheckedRadioButtonId() != -1) {
                higherFloorSqft = ((RadioButton) getViewDataBinding().rgHigherFloorType.findViewById(getViewDataBinding().rgHigherFloorType.getCheckedRadioButtonId())).getText().toString();
            }
        } else {
            higherFloorSqft = getString(R.string.cmn_no);
        }
        ArrayList<String> floorType = floorTypeAdapter.getSelectedData();
        ArrayList<String> wallType = wallTypeAdapter.getSelectedData();
        String noOfRoofType = Objects.requireNonNull(getViewDataBinding().etNoOfRoof.getText()).toString().trim();
        String roofTotal = Objects.requireNonNull(getViewDataBinding().etRoofTotalPercent.getText()).toString().trim();
        ArrayList<BuildingDetailsRoofItem> roofDetails = rootTypeAdapter.getDataList();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            boolean isValidationLivehoodOk = true;
            boolean isValidationEstablismentOk = true;
            if (viewModel.isEstablishmentVisible.get()) {
                if (!viewModel.isEstablishmentValidationok.get()) {
                    isValidationEstablismentOk = false;
                }
            }
            if (viewModel.isLivehoodVisible.get()) {
                if (!viewModel.isLivehoodValidationOk.get()) {
                    isValidationLivehoodOk = false;
                }
            }
            if (!isValidationEstablismentOk && !isValidationLivehoodOk) {
                showToast(getString(R.string.building_livehood_establishment_warning));
            } else if (!isValidationEstablismentOk) {
                showToast(getString(R.string.building_establishment_screen_warning));
            } else if (!isValidationLivehoodOk) {
                showToast(getString(R.string.building_livehood_screen_warning));
            } else {
                if (viewModel.validateFields(buildingName, surveyNumber, yearsOfConstruction, noOfRooms, noOfFloors, floorArea, structureType, carPorch, carPorchArea, commonStair, commonStairArea,stair,stairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, isAnyStructuralChange, structuralChangeYear, isRoofTypeChange, roofTypeChangeYear, otherBuildingDetails, areaRemarks)) {
                    String finalStructureType = structureType;
                    String finalCarPorch = carPorch;
                    String finalCarPorchArea = carPorchArea;
                    String finalCommonStair = commonStair;
                    String finalCommonStairArea = commonStairArea;
                    String finalOtherBuilding = otherBuilding;
                    String finalHigherFloorSqft = higherFloorSqft;
                    String finalIsAnyStructuralChange = isAnyStructuralChange;
                    String finalIsRoofTypeChange = isRoofTypeChange;
                    String finalStair = stair;
                    String finalStairArea = stairArea;
                    String finalAreaRemarks = areaRemarks;
                    if ((viewModel.rationCard.getValue().equalsIgnoreCase(AppConstants.RATIONCARD_PINK) || viewModel.rationCard.getValue().equalsIgnoreCase(AppConstants.RATIONCARD_YELLOW)) && higherFloorSqft.equalsIgnoreCase(getString(R.string.cmn_yes))) {
                        showDialog(getString(R.string.cmn_warning), getString(R.string.building_ration_floortype_waring),
                                getString(R.string.cmn_continue), (dialog, which) -> {
                                    viewModel.saveBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, finalStructureType, finalCarPorch, finalCarPorchArea, finalCommonStair, finalCommonStairArea, finalOtherBuilding, finalHigherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, finalIsAnyStructuralChange, structuralChangeYear, finalIsRoofTypeChange, roofTypeChangeYear, otherBuildingDetails,finalStair, finalStairArea, finalAreaRemarks, true);
                                }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());

                    } else {
                        try {
                            if (yearsOfConstruction != null && yearsOfConstruction.length() != 0 && Integer.valueOf(yearsOfConstruction) >= ROOF_TYPE_WARNING_YEAR) {
                                BuildingDetailsRoofItem mainRoofItem = null;
                                for (BuildingDetailsRoofItem roofItem : roofDetails) {
                                    if (mainRoofItem == null || Integer.parseInt(mainRoofItem.getRoofPercent()) < Integer.parseInt(roofItem.getRoofPercent())) {
                                        mainRoofItem = roofItem;
                                    }
                                }
                                if (mainRoofItem.getRoofType().equalsIgnoreCase(AppConstants.ROOF_TYPE_CLAY_TILE)) {
                                    String finalStair1 = stair;
                                    String finalStairArea1 = stairArea;
                                    String finalAreaRemarks1 = areaRemarks;
                                    showDialog(getString(R.string.cmn_warning), getString(R.string.building_clay_tile_waring),
                                            getString(R.string.cmn_continue), (dialog, which) -> {
                                                viewModel.saveBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, finalStructureType, finalCarPorch, finalCarPorchArea, finalCommonStair, finalCommonStairArea, finalOtherBuilding, finalHigherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, finalIsAnyStructuralChange, structuralChangeYear, finalIsRoofTypeChange, roofTypeChangeYear, otherBuildingDetails, finalStair1, finalStairArea1, finalAreaRemarks1, true);
                                            }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());

                                } else {
                                    viewModel.saveBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, structureType, carPorch, carPorchArea, commonStair, commonStairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, isAnyStructuralChange, structuralChangeYear, isRoofTypeChange, roofTypeChangeYear, otherBuildingDetails,stair, stairArea, areaRemarks, true);
                                }
                            } else {
                                viewModel.saveBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, structureType, carPorch, carPorchArea, commonStair, commonStairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, isAnyStructuralChange, structuralChangeYear, isRoofTypeChange, roofTypeChangeYear, otherBuildingDetails,stair, stairArea, areaRemarks, true);
                            }

                        } catch (Exception e) {
                            viewModel.saveBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, structureType, carPorch, carPorchArea, commonStair, commonStairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, isAnyStructuralChange, structuralChangeYear, isRoofTypeChange, roofTypeChangeYear, otherBuildingDetails,stair, stairArea, areaRemarks, true);
                        }


                    }

                }
            }

        } else {
            //Partial Saving
            //No need od validation
            viewModel.saveBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, structureType, carPorch, carPorchArea, commonStair, commonStairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, isAnyStructuralChange, structuralChangeYear, isRoofTypeChange, roofTypeChangeYear, otherBuildingDetails,stair,stairArea, areaRemarks,false);

        }


    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutBuildingName.setErrorEnabled(false);
        getViewDataBinding().layoutSurveyNumber.setErrorEnabled(false);
        getViewDataBinding().layoutYearOfConstruction.setErrorEnabled(false);
        getViewDataBinding().layoutNoOfRooms.setErrorEnabled(false);
        getViewDataBinding().layoutNoOfFloor.setErrorEnabled(false);
        getViewDataBinding().layoutRoofTotalPercent.setErrorEnabled(false);
        getViewDataBinding().layoutNoOfRoof.setErrorEnabled(false);
        getViewDataBinding().layoutPorchArea.setErrorEnabled(false);
        getViewDataBinding().layoutCommonStairArea.setErrorEnabled(false);
        getViewDataBinding().layoutColonyName.setErrorEnabled(false);
        getViewDataBinding().layoutPathWayArea.setErrorEnabled(false);
        getViewDataBinding().layoutAnyStructuralChangeYear.setErrorEnabled(false);
        getViewDataBinding().layoutAnyRoofTypeChangeYear.setErrorEnabled(false);
        getViewDataBinding().layoutOtherBuildingDetails.setErrorEnabled(false);
        getViewDataBinding().layoutCommonStairArea.setErrorEnabled(false);
        getViewDataBinding().layoutStairArea.setErrorEnabled(false);
        getViewDataBinding().layoutAreaRemarks.setErrorEnabled(false);
        floorAreaAdapter.setValidation(false);
        rootTypeAdapter.setValidation(false);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if (!viewModel.isSurveyOpenEditMode()) {
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            floorTypeAdapter.updateSurveyOpenEditMode(false);
            wallTypeAdapter.updateSurveyOpenEditMode(false);
            rootTypeAdapter.updateSurveyOpenEditMode(false);
            floorAreaAdapter.updateSurveyOpenEditMode(false);
        }
        currentYear = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
        getViewDataBinding().rvFloorType.setLayoutManager(floorTypeLayoutManager);
        getViewDataBinding().rvFloorType.setAdapter(floorTypeAdapter);
        getViewDataBinding().rvWallType.setLayoutManager(wallTypeLayoutManager);
        getViewDataBinding().rvWallType.setAdapter(wallTypeAdapter);
        getViewDataBinding().rvRoofType.setLayoutManager(roofTypeLayoutManager);
        getViewDataBinding().rvRoofType.setAdapter(rootTypeAdapter);
        getViewDataBinding().rvFloorArea.setLayoutManager(floorLayoutManager);
        getViewDataBinding().rvFloorArea.setAdapter(floorAreaAdapter);
        viewModel.loadData();


        getViewDataBinding().etYearOfConstruction.addTextChangedListener(twYFC);

        getViewDataBinding().etHowManyYears.addTextChangedListener(twHMY);

        getViewDataBinding().etAnyStructuralChangeYear.addTextChangedListener(structureChangeYearListener);
        getViewDataBinding().etAnyRoofTypeChangeYear.addTextChangedListener(roofChangeYearListener);

        floorTypeAdapter.setRecyclerViewItemClickListener(position -> {
            if (floorTypeAdapter.getSelectedData().contains(AppConstants.NR)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NR);
                floorTypeAdapter.setSelectedData(noData);
            }
//            getViewDataBinding().rgHigherFloorType.clearCheck();
            viewModel.setHigherFloorTypeVisibility(floorAreaAdapter.getDataList(), floorTypeAdapter.getSelectedData());
        });
        floorAreaAdapter.setRecyclerViewTypingListener(new BuildingFloorAreaListener() {
            @Override
            public void onItemClick() {
//                getViewDataBinding().rgHigherFloorType.clearCheck();
                viewModel.setHigherFloorTypeVisibility(floorAreaAdapter.getDataList(), floorTypeAdapter.getSelectedData());
            }
        });
        getViewDataBinding().rgCarPorch.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbCarPorchYes) {
                viewModel.isCarPorchAvailable(true);
            } else {
                viewModel.isCarPorchAvailable(false);
            }
        });
        getViewDataBinding().rgCommonStair.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbCommonStairYes) {
                viewModel.isCommonStairAvailable(true);
            } else {
                viewModel.isCommonStairAvailable(false);
            }
        }));

        getViewDataBinding().rgStair.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbStairYes) {
                viewModel.isStairAvailable(true);
            } else {
                viewModel.isStairAvailable(false);
            }
        }));
        getViewDataBinding().rgAnyStructuralChange.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbAnyStructuralChangeYes) {
                viewModel.isStructureChangeAvailable(true);
            } else {
                viewModel.isStructureChangeAvailable(false);
            }
        }));

        getViewDataBinding().rgAnyRoofTypeChange.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbAnyRoofTypeChangeYes) {
                viewModel.isRoofChangeAvailable(true);
            } else {
                viewModel.isRoofChangeAvailable(false);
            }
        }));

        getViewDataBinding().rgOtherBuilding.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbOtherBuildingYes) {
                viewModel.isOtherBuildingAvailable(true);
            } else {
                viewModel.isOtherBuildingAvailable(false);
            }
        }));

        getViewDataBinding().etRoofTotalPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    int percent = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etRoofTotalPercent.getText()).toString().trim());
                    if (percent == 0 || percent > 100) {
                        getViewDataBinding().etRoofTotalPercent.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        getViewDataBinding().etCommonStairArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    String percent = Objects.requireNonNull(getViewDataBinding().etCommonStairArea.getText()).toString().trim();
                    if (percent.equalsIgnoreCase("0")) {
                        getViewDataBinding().etCommonStairArea.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        getViewDataBinding().etPorchArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    String percent = Objects.requireNonNull(getViewDataBinding().etPorchArea.getText()).toString().trim();
                    if (percent.equalsIgnoreCase("0")) {
                        getViewDataBinding().etPorchArea.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        getViewDataBinding().etPathWayArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    String percent = Objects.requireNonNull(getViewDataBinding().etPathWayArea.getText()).toString().trim();
                    if (percent.equalsIgnoreCase("0")) {
                        getViewDataBinding().etPathWayArea.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        getViewDataBinding().etNoOfFloor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String noOfFloors = Objects.requireNonNull(getViewDataBinding().etNoOfFloor.getText()).toString().trim();
                int noOfFloor = Integer.parseInt(noOfFloors);
                if(noOfFloor >0){
                    viewModel.isAreaRemarksVisible.set(true);
                }
                else{
                    viewModel.isAreaRemarksVisible.set(false);
                    viewModel.areaRemarks.setValue("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        viewModel.getCurrentSurvey();
        viewModel.getCurrentSurveyProperty();
    }


    private final TextWatcher structureChangeYearListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 4) {
                int yearData = Integer.parseInt(String.valueOf(s));
                if (yearData > currentYear) {
                    getViewDataBinding().etAnyStructuralChangeYear.removeTextChangedListener(structureChangeYearListener);
                    getViewDataBinding().etAnyStructuralChangeYear.setText("");
                    getViewDataBinding().etAnyStructuralChangeYear.addTextChangedListener(structureChangeYearListener);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private TextWatcher roofChangeYearListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 4) {
                int yearData = Integer.parseInt(String.valueOf(s));
                if (yearData > currentYear) {
                    getViewDataBinding().etAnyRoofTypeChangeYear.removeTextChangedListener(roofChangeYearListener);
                    getViewDataBinding().etAnyRoofTypeChangeYear.setText("");
                    getViewDataBinding().etAnyRoofTypeChangeYear.addTextChangedListener(roofChangeYearListener);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private TextWatcher twYFC = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            getViewDataBinding().etHowManyYears.removeTextChangedListener(twHMY);
            if (s.length() == 4) {
                int yearData = Integer.parseInt(String.valueOf(s));
                if (yearData > currentYear) {
                    getViewDataBinding().etYearOfConstruction.removeTextChangedListener(twYFC);
                    getViewDataBinding().etYearOfConstruction.setText("");
                    getViewDataBinding().etYearOfConstruction.addTextChangedListener(twYFC);
                } else {
                    int data = currentYear - yearData;
                    getViewDataBinding().etHowManyYears.setText(String.valueOf(data));
                }
            } else {
                getViewDataBinding().etHowManyYears.setText("");
            }
            getViewDataBinding().etHowManyYears.addTextChangedListener(twHMY);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    private TextWatcher twHMY = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            getViewDataBinding().etYearOfConstruction.removeTextChangedListener(twYFC);
            try {
                if (s.length() > 0) {
                    int totalYear = Integer.parseInt(String.valueOf(s));
                    int data = currentYear - totalYear;
                    if (data > 0) {
                        getViewDataBinding().etYearOfConstruction.setText(String.valueOf(data));
                    } else {
                        getViewDataBinding().etHowManyYears.setText("");
                        getViewDataBinding().etYearOfConstruction.setText("");
                    }
                } else {
                    getViewDataBinding().etYearOfConstruction.setText("");
                }
            } catch (Exception e) {

            }

            getViewDataBinding().etYearOfConstruction.addTextChangedListener(twYFC);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    @Override
    public void setCachedData(Property property) {

        getViewDataBinding().rgCarPorch.check(property.carPorch.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbCarPorchYes : property.carPorch.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbCarPorchNo : property.carPorch.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbCarPorchNr : -1);
        getViewDataBinding().rgCommonStair.check(property.commonStair.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbCommonStairYes : property.commonStair.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbCommonStairNo : property.commonStair.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbCommonStairNr : -1);
        getViewDataBinding().rgStair.check(property.stair.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbStairYes : property.stair.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbStairNo : property.stair.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbStairNr : -1);

        if (property.structureType.length() != 0) {
            getViewDataBinding().rgStructureType.check(property.structureType.equalsIgnoreCase(getString(R.string.building_structure_type_normal)) ? R.id.rbStructureTypeNormal : property.structureType.equalsIgnoreCase(getString(R.string.building_structure_type_flat)) ? R.id.rbStructureTypeFlat : -1);
        }

        if (!viewModel.getDoorStatusPDCDCGL().get()) {
            getViewDataBinding().rgOtherBuilding.check(property.otherBuilding.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbOtherBuildingYes : property.otherBuilding.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbOtherBuildingNo : -1);
            getViewDataBinding().rgHigherFloorType.check(property.higherFloorSqft.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbHigherFloorTypeYes : property.higherFloorSqft.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbHigherFloorTypeNo : -1);
            getViewDataBinding().rgAnyStructuralChange.check(property.anyStructuralChange.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbAnyStructuralChangeYes : property.anyStructuralChange.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbAnyStructuralChangeNo : -1);
            getViewDataBinding().rgAnyRoofTypeChange.check(property.roofTypeChange.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbAnyRoofTypeChangeYes : property.roofTypeChange.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbAnyRoofTypeChangeNo : -1);
            floorTypeAdapter.setSelectedData(property.floorType);
            wallTypeAdapter.setSelectedData(property.wallType);

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
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
    }

    /**
     * to set floor count
     *
     * @param count
     * @param groundFloor
     */
    @Override
    public void setFloorCount(int count, int groundFloor) {
        floorCount = count;
        floorAreaAdapter.setFloorCount(count - 1, groundFloor);

    }

    /**
     * set structre type radio button according to property count
     */
    @Override
    public void setStructureType(Survey survey) {
        //if property count greater than 1 then structure type will be flat
        if (survey.getPropertyCount() > 1) {
            getViewDataBinding().rgStructureType.check(R.id.rbStructureTypeFlat);
            getViewDataBinding().rbStructureTypeFlat.setEnabled(false);
            getViewDataBinding().rbStructureTypeNormal.setEnabled(false);
            getViewDataBinding().rbStructureTypeFlat.setAlpha(.5f);
            getViewDataBinding().rbStructureTypeNormal.setAlpha(.5f);

        } else {
            getViewDataBinding().rgStructureType.check(R.id.rbStructureTypeNormal);
        }
    }

    /**
     *
     */
    @Override
    public void addFloorArea() {
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfFloor.getText()).toString().trim());
        if (value > -1 && value < floorCount) {
            getViewDataBinding().etNoOfFloor.setText(String.valueOf(value + 1));
            if (!viewModel.doorStatusNC.get())
                floorAreaAdapter.addNewItem();
        }
    }

    @Override
    public void removeFloorArea() {
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfFloor.getText()).toString());
        if (value > 0) {
            getViewDataBinding().etNoOfFloor.setText(String.valueOf(value - 1));
            if (!viewModel.doorStatusNC.get())
                floorAreaAdapter.removeItem();
        }
    }

    @Override
    public void addRoofType() {
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfRoof.getText()).toString());
        if (value > -1 && value < 4) {
            getViewDataBinding().etNoOfRoof.setText(String.valueOf(value + 1));
            rootTypeAdapter.addNewItem(value, getViewDataBinding().etRoofTotalPercent.getText().toString());
        }
    }

    @Override
    public void removeRoofType() {
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfRoof.getText()).toString());
        if (value > 0) {
            getViewDataBinding().etNoOfRoof.setText(String.valueOf(value - 1));
            rootTypeAdapter.removeItem();
        }
    }


    @Override
    public void showPathWayNANRPopUP() {
        PopupMenu popup = new PopupMenu(getBaseActivity(), getViewDataBinding().nrPathWayArea);
        popup.inflate(R.menu.na_nr_menu);
        popup.setOnMenuItemClickListener(item1 -> {
            switch (item1.getItemId()) {
                case R.id.NR:
                    viewModel.pathwayArea.setValue(AppConstants.NR_UPPERCASE);
                    break;
                case R.id.NA:
                    viewModel.pathwayArea.setValue(AppConstants.NA_UPPERCASE);
                    break;
            }
            popup.dismiss();
            return false;
        });
        popup.show();
    }

    @Override
    public void showBuildNameNANRPopUp() {
        PopupMenu popupMenu = new PopupMenu(getBaseActivity(), getViewDataBinding().nrBuildingName);
        popupMenu.inflate(R.menu.na_nr_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.NR:
                    viewModel.buildingName.setValue(AppConstants.NR_UPPERCASE);
                    break;
                case R.id.NA:
                    viewModel.buildingName.setValue(AppConstants.NA_UPPERCASE);
                    break;
            }
            popupMenu.dismiss();
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void showColonyNameNANRPopUp() {
        PopupMenu popupMenu = new PopupMenu(getBaseActivity(), getViewDataBinding().nrColonyName);
        popupMenu.inflate(R.menu.na_nr_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.NR:
                    viewModel.colonyName.setValue(AppConstants.NR_UPPERCASE);
                    break;
                case R.id.NA:
                    viewModel.colonyName.setValue(AppConstants.NA_UPPERCASE);
                    break;
            }
            popupMenu.dismiss();
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }

    @Override
    public void disableBuildingName() {
        getViewDataBinding().etBuildingName.setEnabled(false);
        getViewDataBinding().nrBuildingName.setEnabled(false);
        getViewDataBinding().layoutBuildingName.setAlpha(.5f);
    }
    @Override
    public void showWarning(String messgae) {
        showInfoDialog(messgae);
    }


}