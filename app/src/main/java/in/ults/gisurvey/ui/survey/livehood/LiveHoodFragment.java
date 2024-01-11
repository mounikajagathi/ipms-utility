package in.ults.gisurvey.ui.survey.livehood;


import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.databinding.FragmentLiveHoodBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewMultiSelectAdapter;

import static in.ults.gisurvey.utils.AppConstants.NO;
import static in.ults.gisurvey.utils.AppConstants.NR;
import static in.ults.gisurvey.utils.AppConstants.RATIONCARD_PINK;
import static in.ults.gisurvey.utils.AppConstants.RATIONCARD_WHITE;
import static in.ults.gisurvey.utils.AppConstants.RATIONCARD_YELLOW;
import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class LiveHoodFragment extends BaseFragment<FragmentLiveHoodBinding, LiveHoodViewModel> implements LiveHoodNavigator {

    public static final String TAG = LiveHoodFragment.class.getSimpleName();

    static final int RELIGION_ERROR = 1;
    static final int RELIGION_CAST_ERROR = 2;
    static final int RATION_CARD_ERROR = 3;
    static final int RATION_CARD_NUMBER_ERROR = 4;
    static final int WATER_CONNECTION_TYPE_ERROR = 5;
    static final int WATER_SUPPLY_DURATION_ERROR = 6;
    static final int NO_CATTLE_ERROR = 7;
    static final int NO_POULTRY_ERROR = 8;
    static final int COMMON_ERROR = 9;
    static final int MEM_COUNT_ERROR = 10;
    static final int SWIMMING_POOL_AREA_ERROR = 11;

    private LiveHoodViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    CommonDropDownAdapter castAdapter;

    @Inject
    CommonDropDownAdapter religionAdapter;

    @Inject
    CommonDropDownAdapter rationCardAdapter;


    @Inject
    CommonDropDownAdapter waterDurationAdapter;

    @Inject
    FlexboxLayoutManager layoutManagerWaterSourceType;

    @Inject
    FlexRecyclerViewMultiSelectAdapter waterSourceTypeAdapter;

    @Inject
    FlexboxLayoutManager otherFacilitiesLayoutManager;

    @Inject
    FlexRecyclerViewMultiSelectAdapter otherFacilitiesAdapter;

    @Inject
    FlexboxLayoutManager petsLayoutManager;

    @Inject
    FlexRecyclerViewMultiSelectAdapter petsAdapter;

    @Inject
    FlexboxLayoutManager layoutManagerPerennialMonth;

    @Inject
    FlexRecyclerViewMultiSelectAdapter perennialMonthAdapter;

    @Inject
    FlexboxLayoutManager layoutManagerWellDetails;

    @Inject
    FlexRecyclerViewMultiSelectAdapter wellDetailsAdapter;

    @Inject
    FlexboxLayoutManager layoutManagerwaterConnectionType;

    @Inject
    FlexRecyclerViewMultiSelectAdapter waterConnectionTypeAdapter;

    @Inject
    FlexboxLayoutManager layoutManagerOtherSource;

    @Inject
    FlexRecyclerViewMultiSelectAdapter otherSourceAdapter;

    @Inject
    FlexboxLayoutManager plasticWasteManagementTypeLayoutManager;

    @Inject
    FlexRecyclerViewMultiSelectAdapter plasticWasteManagementTypeAdapter;

    @Inject
    FlexboxLayoutManager liquidWasteManagementTypeLayoutManager;

    @Inject
    FlexRecyclerViewMultiSelectAdapter liquidWasteManagementTypeAdapter;

    @Inject
    FlexboxLayoutManager organicWasteManagementTypeLayoutManager;

    @Inject
    FlexRecyclerViewMultiSelectAdapter organicWasteManagementTypeAdapter;

    public static LiveHoodFragment newInstance() {
        Bundle args = new Bundle();
        LiveHoodFragment fragment = new LiveHoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_hood;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public LiveHoodViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(LiveHoodViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_live_hood);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if (!viewModel.isSurveyOpenEditMode()) {
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            //disable all multisection data change
            perennialMonthAdapter.updateSurveyOpenEditMode(false);
            wellDetailsAdapter.updateSurveyOpenEditMode(false);
            waterConnectionTypeAdapter.updateSurveyOpenEditMode(false);
            otherSourceAdapter.updateSurveyOpenEditMode(false);
            waterSourceTypeAdapter.updateSurveyOpenEditMode(false);
            petsAdapter.updateSurveyOpenEditMode(false);
            otherFacilitiesAdapter.updateSurveyOpenEditMode(false);
            plasticWasteManagementTypeAdapter.updateSurveyOpenEditMode(false);
            liquidWasteManagementTypeAdapter.updateSurveyOpenEditMode(false);
            organicWasteManagementTypeAdapter.updateSurveyOpenEditMode(false);
        }
        getViewDataBinding().rvWellWaterSourceType.setAdapter(wellDetailsAdapter);
        getViewDataBinding().rvWaterConnection.setAdapter(waterConnectionTypeAdapter);
        getViewDataBinding().rvOtherWaterSource.setAdapter(otherSourceAdapter);
        getViewDataBinding().rvWellPerennialMonth.setAdapter(perennialMonthAdapter);
        getViewDataBinding().rvWellWaterSourceType.setLayoutManager(layoutManagerWellDetails);
        getViewDataBinding().rvWaterConnection.setLayoutManager(layoutManagerwaterConnectionType);
        getViewDataBinding().rvOtherWaterSource.setLayoutManager(layoutManagerOtherSource);
        getViewDataBinding().rvWellPerennialMonth.setLayoutManager(layoutManagerPerennialMonth);
        getViewDataBinding().rvOtherFacilities.setAdapter(otherFacilitiesAdapter);
        getViewDataBinding().rvOtherFacilities.setLayoutManager(otherFacilitiesLayoutManager);
        getViewDataBinding().rvPets.setAdapter(petsAdapter);
        getViewDataBinding().rvPets.setLayoutManager(petsLayoutManager);
        getViewDataBinding().rvPlasticWasteManagement.setAdapter(plasticWasteManagementTypeAdapter);
        getViewDataBinding().rvPlasticWasteManagement.setLayoutManager(plasticWasteManagementTypeLayoutManager);
        getViewDataBinding().rvLiquidWasteManagement.setAdapter(liquidWasteManagementTypeAdapter);
        getViewDataBinding().rvLiquidWasteManagement.setLayoutManager(liquidWasteManagementTypeLayoutManager);
        getViewDataBinding().rvOrganicWasteManagement.setAdapter(organicWasteManagementTypeAdapter);
        getViewDataBinding().rvOrganicWasteManagement.setLayoutManager(organicWasteManagementTypeLayoutManager);

        waterSourceTypeAdapter.setRecyclerViewItemClickListener(position -> {
            if (waterSourceTypeAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                waterSourceTypeAdapter.setSelectedData(noData);
            }
        });
        wellDetailsAdapter.setRecyclerViewItemClickListener(position -> {
            if (wellDetailsAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                wellDetailsAdapter.setSelectedData(noData);
                viewModel.setWellWaterAvailable(false);
                getViewDataBinding().rgWellPerennialDetails.clearCheck();

            } else {
                viewModel.setWellWaterAvailable(true);
            }
        });
        waterConnectionTypeAdapter.setRecyclerViewItemClickListener(position -> {
            if (waterConnectionTypeAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                waterConnectionTypeAdapter.setSelectedData(noData);
                viewModel.setWaterConnectionAvailable(false);
            } else {
                viewModel.setWaterConnectionAvailable(true);
            }
        });
        otherSourceAdapter.setRecyclerViewItemClickListener(position -> {
            if (otherSourceAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                otherSourceAdapter.setSelectedData(noData);
                viewModel.setOtherWaterSourceAvailable(false);
            } else {
                viewModel.setOtherWaterSourceAvailable(true);
            }
        });
        otherFacilitiesAdapter.setRecyclerViewItemClickListener(position -> {
            if (otherFacilitiesAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                otherFacilitiesAdapter.setSelectedData(noData);
            }
        });
        petsAdapter.setRecyclerViewItemClickListener(position -> {
            if (petsAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                petsAdapter.setSelectedData(noData);
            }
        });
        plasticWasteManagementTypeAdapter.setRecyclerViewItemClickListener(position -> {
            if (plasticWasteManagementTypeAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                plasticWasteManagementTypeAdapter.setSelectedData(noData);
            }
        });
        liquidWasteManagementTypeAdapter.setRecyclerViewItemClickListener(position -> {
            if (liquidWasteManagementTypeAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                liquidWasteManagementTypeAdapter.setSelectedData(noData);
            }
        });
        organicWasteManagementTypeAdapter.setRecyclerViewItemClickListener(position -> {
            if (organicWasteManagementTypeAdapter.getSelectedData().contains(AppConstants.NO)) {
                ArrayList<String> noData = new ArrayList<>();
                noData.add(AppConstants.NO);
                organicWasteManagementTypeAdapter.setSelectedData(noData);
            }
        });
        getViewDataBinding().etCast.setAdapter(castAdapter);
        getViewDataBinding().etReligion.setAdapter(religionAdapter);
        getViewDataBinding().etRationCard.setAdapter(rationCardAdapter);
        getViewDataBinding().etWaterSupplyDuration.setAdapter(waterDurationAdapter);
        getViewDataBinding().rgWellPerennialDetails.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            if (checkedId == R.id.rbWellSeasonal) {
                viewModel.setIsWellSeasonal(getString(R.string.more_well_seasonal));
            }else if (checkedId == R.id.rbWellPolluted) {
                viewModel.setIsWellSeasonal(getString(R.string.more_well_polluted));
            }else{
                viewModel.setIsWellSeasonal(getString(R.string.more_well_perennial));
            }
        });
        getViewDataBinding().etRationCard.setOnItemClickListener((parent, view, position, id) -> {
            String rationCard = Objects.requireNonNull(rationCardAdapter.getItem(position)).getContent();
            viewModel.setNoRationCard(rationCard.equalsIgnoreCase(AppConstants.LIVEHOOD_NO_RATION_CARD)
                    || rationCard.equalsIgnoreCase(AppConstants.NR_UPPERCASE));
        });
        getViewDataBinding().rgCattles.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbCattlesYes) {
                viewModel.setCattleAvailable(true);
            } else {
                viewModel.setCattleAvailable(false);
            }
        }));
        getViewDataBinding().rgPaultry.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbPaultryYes) {
                viewModel.setPoultryAvailable(true);
            } else {
                viewModel.setPoultryAvailable(false);
            }
        }));

        getViewDataBinding().rgSwimmingPool.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbSwimmingPoolYes) {
                viewModel.setSwimmingPoolAvailable(true);
            } else {
                viewModel.setSwimmingPoolAvailable(false);
            }
        }));

        viewModel.loadData();
        viewModel.getCurrentSurveyProperty();
    }

    /**
     * to validate fields and save live hood details
     */
    @Override
    public void saveLiveHoodDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String religion = Objects.requireNonNull(getViewDataBinding().etReligion.getText()).toString().trim();
        String rationCard = Objects.requireNonNull(getViewDataBinding().etRationCard.getText()).toString().trim();
        String rationCardNumber = Objects.requireNonNull(getViewDataBinding().etRationCardNumber.getText()).toString().trim();
        String religionCast = Objects.requireNonNull(getViewDataBinding().etCast.getText()).toString().trim();
        String noOfCattles = Objects.requireNonNull(getViewDataBinding().etCattles.getText()).toString().trim();
        String noOfPoultry = Objects.requireNonNull(getViewDataBinding().etPaultry.getText()).toString().trim();
        String waterSupplyDuration = Objects.requireNonNull(getViewDataBinding().etWaterSupplyDuration.getText()).toString().trim();
        String memCount = Objects.requireNonNull(getViewDataBinding().etMemCount.getText()).toString().trim();
        String swimmingPoolArea = Objects.requireNonNull(getViewDataBinding().etSwimmingPool.getText()).toString().trim();

        ArrayList<String> wellDetails = wellDetailsAdapter.getSelectedData();
        ArrayList<String> waterConnection = waterConnectionTypeAdapter.getSelectedData();
        ArrayList<String> otherSource = otherSourceAdapter.getSelectedData();
        ArrayList<String> perennialMonths = perennialMonthAdapter.getSelectedData();


        String bankAccount = "", petDog = "", cattles = "", paultry = "", gasConnection = "", rainWaterHarvest = "", lackDrinkingWater = "", solarPanel = "", wellPerennial = "", swimmingPool = "";
        if (getViewDataBinding().rgWellPerennialDetails.getCheckedRadioButtonId() != -1) {
            wellPerennial = ((RadioButton) getViewDataBinding().rgWellPerennialDetails.findViewById(getViewDataBinding().rgWellPerennialDetails.getCheckedRadioButtonId())).getText().toString();
        }

        if (getViewDataBinding().rgGasConnection.getCheckedRadioButtonId() != -1) {
            gasConnection = ((RadioButton) getViewDataBinding().rgGasConnection.findViewById(getViewDataBinding().rgGasConnection.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgRainWaterHarvest.getCheckedRadioButtonId() != -1) {
            rainWaterHarvest = ((RadioButton) getViewDataBinding().rgRainWaterHarvest.findViewById(getViewDataBinding().rgRainWaterHarvest.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgLackDrinkingWater.getCheckedRadioButtonId() != -1) {
            lackDrinkingWater = ((RadioButton) getViewDataBinding().rgLackDrinkingWater.findViewById(getViewDataBinding().rgLackDrinkingWater.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgSolarPanel.getCheckedRadioButtonId() != -1) {
            solarPanel = ((RadioButton) getViewDataBinding().rgSolarPanel.findViewById(getViewDataBinding().rgSolarPanel.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgBankAccount.getCheckedRadioButtonId() != -1) {
            bankAccount = ((RadioButton) getViewDataBinding().rgBankAccount.findViewById(getViewDataBinding().rgBankAccount.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgCattles.getCheckedRadioButtonId() != -1) {
            cattles = ((RadioButton) getViewDataBinding().rgCattles.findViewById(getViewDataBinding().rgCattles.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgPaultry.getCheckedRadioButtonId() != -1) {
            paultry = ((RadioButton) getViewDataBinding().rgPaultry.findViewById(getViewDataBinding().rgPaultry.getCheckedRadioButtonId())).getText().toString();
        }
        if (getViewDataBinding().rgSwimmingPool.getCheckedRadioButtonId() != -1) {
            swimmingPool = ((RadioButton) getViewDataBinding().rgSwimmingPool.findViewById(getViewDataBinding().rgSwimmingPool.getCheckedRadioButtonId())).getText().toString();
        }

        ArrayList<String> plasticWasteManagementType = plasticWasteManagementTypeAdapter.getSelectedData();
        ArrayList<String> liquidWasteManagementType = liquidWasteManagementTypeAdapter.getSelectedData();
        ArrayList<String> organicWasteManagementType = organicWasteManagementTypeAdapter.getSelectedData();
        ArrayList<String> otherFacility = otherFacilitiesAdapter.getSelectedData();
        ArrayList<String> pets = petsAdapter.getSelectedData();
        if(!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(religion, religionCast, rationCard, rationCardNumber, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, plasticWasteManagementType, liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pets, cattles, noOfCattles, paultry, noOfPoultry, wellDetails, waterConnection, otherSource, wellPerennial, perennialMonths, memCount, swimmingPool, swimmingPoolArea)) {
                if (rationCard.equalsIgnoreCase(getString(R.string.cmn_nr))) {
                    rationCardNumber = getString(R.string.cmn_nr);
                }
                if (!viewModel.isWaterConnectionAvailable.get() && !viewModel.buildingStatusOnGoingWithoutRoof.get() && viewModel.establishmentUsageHouseFlatVilla.get() && (!viewModel.isWellSelected.get() || wellPerennial.equalsIgnoreCase(getString(R.string.more_well_seasonal))) && lackDrinkingWater.equalsIgnoreCase(getString(R.string.cmn_no))) {
                    String finalRationCardNumber = rationCardNumber;
                    String finalGasConnection = gasConnection;
                    String finalRainWaterHarvest = rainWaterHarvest;
                    String finalLackDrinkingWater = lackDrinkingWater;
                    String finalSolarPanel = solarPanel;
                    String finalBankAccount = bankAccount;
                    String finalCattles = cattles;
                    String finalPaultry = paultry;
                    String finalWellPerennial = wellPerennial;
                    String finalSwimmingPool2 = swimmingPool;
                    showDialog(getString(R.string.cmn_warning), getString(R.string.msg_live_hood_lack_of_water_warning),
                            getString(R.string.cmn_continue), (dialog, which) -> {
                                viewModel.saveLiveHoodDetails(religion, religionCast, rationCard, finalRationCardNumber, waterSupplyDuration, finalGasConnection, finalRainWaterHarvest, finalLackDrinkingWater, finalSolarPanel, plasticWasteManagementType, liquidWasteManagementType, organicWasteManagementType, otherFacility, finalBankAccount, pets, finalCattles, noOfCattles, finalPaultry, noOfPoultry, wellDetails, waterConnection, otherSource, finalWellPerennial, perennialMonths, memCount, finalSwimmingPool2, swimmingPoolArea,true);
                            }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());


                } else {

                    if (rationCard.equalsIgnoreCase(RATIONCARD_WHITE)) {
                        if (viewModel.latrine.getValue().equalsIgnoreCase(NO) || viewModel.bathroom.getValue().equalsIgnoreCase(NO) || viewModel.electricity.getValue().equalsIgnoreCase(NO)) {
                            String finalRationCardNumber1 = rationCardNumber;
                            String finalGasConnection1 = gasConnection;
                            String finalRainWaterHarvest1 = rainWaterHarvest;
                            String finalLackDrinkingWater1 = lackDrinkingWater;
                            String finalSolarPanel1 = solarPanel;
                            String finalBankAccount1 = bankAccount;
                            String finalCattles1 = cattles;
                            String finalPaultry1 = paultry;
                            String finalWellPerennial1 = wellPerennial;
                            String finalSwimmingPool = swimmingPool;
                            showDialog(getString(R.string.cmn_warning), getString(R.string.msg_livehood_card_white_warning),
                                    getString(R.string.cmn_continue), (dialog, which) -> {
                                        viewModel.saveLiveHoodDetails(religion, religionCast, rationCard, finalRationCardNumber1, waterSupplyDuration, finalGasConnection1, finalRainWaterHarvest1, finalLackDrinkingWater1, finalSolarPanel1, plasticWasteManagementType,liquidWasteManagementType, organicWasteManagementType, otherFacility, finalBankAccount1, pets, finalCattles1, noOfCattles, finalPaultry1, noOfPoultry, wellDetails, waterConnection, otherSource, finalWellPerennial1, perennialMonths, memCount, finalSwimmingPool, swimmingPoolArea, true);
                                    }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());
                        }else{
                            viewModel.saveLiveHoodDetails(religion, religionCast, rationCard, rationCardNumber, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, plasticWasteManagementType,liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pets, cattles, noOfCattles, paultry, noOfPoultry, wellDetails, waterConnection, otherSource, wellPerennial, perennialMonths, memCount, swimmingPool, swimmingPoolArea,true);

                        }
                    }else if (rationCard.equalsIgnoreCase(RATIONCARD_PINK) || rationCard.equalsIgnoreCase(RATIONCARD_YELLOW)) {
                        if((viewModel.ac.getValue().equalsIgnoreCase(NO)||viewModel.ac.getValue().equalsIgnoreCase(NR))){
                            viewModel.saveLiveHoodDetails(religion, religionCast, rationCard, rationCardNumber, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, plasticWasteManagementType,liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pets, cattles, noOfCattles, paultry, noOfPoultry, wellDetails, waterConnection, otherSource, wellPerennial, perennialMonths, memCount, swimmingPool, swimmingPoolArea,true);
                        }else{
                            String finalRationCardNumber1 = rationCardNumber;
                            String finalGasConnection1 = gasConnection;
                            String finalRainWaterHarvest1 = rainWaterHarvest;
                            String finalLackDrinkingWater1 = lackDrinkingWater;
                            String finalSolarPanel1 = solarPanel;
                            String finalBankAccount1 = bankAccount;
                            String finalCattles1 = cattles;
                            String finalPaultry1 = paultry;
                            String finalWellPerennial1 = wellPerennial;
                            String finalSwimmingPool1 = swimmingPool;
                            showDialog(getString(R.string.cmn_warning), getString(R.string.msg_livehood_card_yellow_pink_warning),
                                    getString(R.string.cmn_continue), (dialog, which) -> {
                                        viewModel.saveLiveHoodDetails(religion, religionCast, rationCard, finalRationCardNumber1, waterSupplyDuration, finalGasConnection1, finalRainWaterHarvest1, finalLackDrinkingWater1, finalSolarPanel1, plasticWasteManagementType,liquidWasteManagementType, organicWasteManagementType, otherFacility, finalBankAccount1, pets, finalCattles1, noOfCattles, finalPaultry1, noOfPoultry, wellDetails, waterConnection, otherSource, finalWellPerennial1, perennialMonths, memCount, finalSwimmingPool1, swimmingPoolArea, true);
                                    }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());
                        }
                    } else {
                        viewModel.saveLiveHoodDetails(religion, religionCast, rationCard, rationCardNumber, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, plasticWasteManagementType,liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pets, cattles, noOfCattles, paultry, noOfPoultry, wellDetails, waterConnection, otherSource, wellPerennial, perennialMonths, memCount, swimmingPool, swimmingPoolArea,true);
                    }

                }
            }
        }else {
            //Partial Saving
            //No need od validation
            viewModel.saveLiveHoodDetails(religion, religionCast, rationCard, rationCardNumber, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, plasticWasteManagementType,liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pets, cattles, noOfCattles, paultry, noOfPoultry, wellDetails, waterConnection, otherSource, wellPerennial, perennialMonths, memCount, swimmingPool, swimmingPoolArea, false);
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
            case RELIGION_ERROR:
                getViewDataBinding().layoutReligion.setError(error);
                getViewDataBinding().layoutReligion.getParent().requestChildFocus
                        (getViewDataBinding().layoutReligion, getViewDataBinding().layoutReligion);
                break;
            case RELIGION_CAST_ERROR:
                getViewDataBinding().layoutCast.setError(error);
                getViewDataBinding().layoutCast.getParent().requestChildFocus
                        (getViewDataBinding().layoutCast, getViewDataBinding().layoutCast);
                break;
            case RATION_CARD_ERROR:
                getViewDataBinding().layoutRationCard.setError(error);
                getViewDataBinding().layoutRationCard.getParent().requestChildFocus
                        (getViewDataBinding().layoutRationCard, getViewDataBinding().layoutRationCard);
                break;
            case RATION_CARD_NUMBER_ERROR:
                getViewDataBinding().layoutRationCardNumber.setError(error);
                getViewDataBinding().layoutRationCardNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutRationCardNumber, getViewDataBinding().layoutRationCardNumber);
                break;

            case WATER_SUPPLY_DURATION_ERROR:
                getViewDataBinding().layoutWaterSupplyDuration.setError(error);
                getViewDataBinding().layoutWaterSupplyDuration.getParent().requestChildFocus
                        (getViewDataBinding().layoutWaterSupplyDuration, getViewDataBinding().layoutWaterSupplyDuration);
                break;
            case NO_CATTLE_ERROR:
                getViewDataBinding().layoutCattles.setError(error);
                getViewDataBinding().layoutCattles.getParent().requestChildFocus
                        (getViewDataBinding().layoutCattles, getViewDataBinding().layoutCattles);
                break;
            case NO_POULTRY_ERROR:
                getViewDataBinding().layoutPaultry.setError(error);
                getViewDataBinding().layoutPaultry.getParent().requestChildFocus
                        (getViewDataBinding().layoutPaultry, getViewDataBinding().layoutPaultry);
                break;
            case MEM_COUNT_ERROR:
                getViewDataBinding().layoutMemCount.setError(error);
                getViewDataBinding().layoutMemCount.getParent().requestChildFocus
                        (getViewDataBinding().layoutMemCount, getViewDataBinding().layoutMemCount);
                break;
            case SWIMMING_POOL_AREA_ERROR:
                getViewDataBinding().layoutSwimmingPool.setError(error);
                getViewDataBinding().layoutSwimmingPool.getParent().requestChildFocus
                        (getViewDataBinding().layoutSwimmingPool, getViewDataBinding().layoutSwimmingPool);
                break;
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutReligion.setErrorEnabled(false);
        getViewDataBinding().layoutCast.setErrorEnabled(false);
        getViewDataBinding().layoutRationCard.setErrorEnabled(false);
        getViewDataBinding().layoutRationCardNumber.setErrorEnabled(false);
        getViewDataBinding().layoutWaterSupplyDuration.setErrorEnabled(false);
        getViewDataBinding().layoutPaultry.setErrorEnabled(false);
        getViewDataBinding().layoutCattles.setErrorEnabled(false);
        getViewDataBinding().layoutMemCount.setErrorEnabled(false);
    }

    @Override
    public void setCachedData(Property property) {

        if (viewModel.establishmentUsageHouseFlatVilla.get()) {
            getViewDataBinding().rgGasConnection.check(property.gasConnection.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbGasConnectionYes : property.gasConnection.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbGasConnectionNo : -1);
            waterSourceTypeAdapter.setSelectedData(property.waterSourceType);
            petsAdapter.setSelectedData(property.pets);
            getViewDataBinding().rgBankAccount.check(property.bankAccount.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbBankAccountYes : property.bankAccount.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbBankAccountNo : -1);
            getViewDataBinding().rgPaultry.check(property.paultry.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbPaultryYes : property.paultry.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbPaultryNo : -1);
            getViewDataBinding().rgCattles.check(property.cattles.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbCattlesYes : property.cattles.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbCattlesNo : -1);
            getViewDataBinding().rgSwimmingPool.check(property.swimmingPool.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbSwimmingPoolYes : property.swimmingPool.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbSwimmingPoolNo : -1);
        }

        if (viewModel.buildingTypeResidential.get()) {
            getViewDataBinding().rgLackDrinkingWater.check(property.lackDrinkingWater.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbLackDrinkingWaterYes : property.lackDrinkingWater.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbLackDrinkingWaterNo : -1);
        }

        getViewDataBinding().rgSolarPanel.check(property.solarPanel.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbSolarPanelYes : property.solarPanel.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbSolarPanelNo : property.solarPanel.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbSolarPanelNr : -1);
        otherFacilitiesAdapter.setSelectedData(property.anyOtherFacility);
        getViewDataBinding().rgRainWaterHarvest.check(property.rainWaterHarvest.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbRainWaterHarvestYes : property.rainWaterHarvest.equalsIgnoreCase(getString(R.string.cmn_no)) ? R.id.rbRainWaterHarvestNo : property.rainWaterHarvest.equalsIgnoreCase(getString(R.string.cmn_nr)) ? R.id.rbRainWaterHarvestNr : -1);
        plasticWasteManagementTypeAdapter.setSelectedData(property.plasticWasteManagementType);
        liquidWasteManagementTypeAdapter.setSelectedData(property.liquidWasteManagementType);
        organicWasteManagementTypeAdapter.setSelectedData(property.organicWasteManagementType);
        wellDetailsAdapter.setSelectedData(property.wellDetails);
        waterConnectionTypeAdapter.setSelectedData(property.waterConnection);
        otherSourceAdapter.setSelectedData(property.otherSource);
        if (viewModel.isWellSelected.get() &&  (viewModel.isWellSeasonal.getValue().equalsIgnoreCase(getString(R.string.more_well_polluted))||viewModel.isWellSeasonal.getValue().equalsIgnoreCase(getString(R.string.more_well_seasonal)))) {
            perennialMonthAdapter.setSelectedData(property.wellPerennialMonth);
        }
        if (viewModel.isWellSelected.get()) {
            getViewDataBinding().rgWellPerennialDetails.check(property.wellPerennial.equalsIgnoreCase(getString(R.string.more_well_seasonal)) ? R.id.rbWellSeasonal : property.wellPerennial.equalsIgnoreCase(getString(R.string.more_well_perennial)) ? R.id.rbWellPerennial :property.wellPerennial.equalsIgnoreCase(getString(R.string.more_well_polluted)) ? R.id.rbWellPolluted : -1);
        }
    }

    /**
     * to navigate to completed survey fragment
     */
    @Override
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
    }

    @Override
    public void setLackOfDrinkingWater(boolean status) {
        if (viewModel.isSurveyOpenEditMode()) {
            //Lack of drinking water only visible in buildingSubTypeHouseFlatVilla
            //Only needed for edit mode
            if (viewModel.establishmentUsageHouseFlatVilla.get()) {
                if (status) {
                    getViewDataBinding().rgLackDrinkingWater.check(R.id.rbLackDrinkingWaterYes);
                    getViewDataBinding().rbLackDrinkingWaterNo.setEnabled(false);
                    getViewDataBinding().rbLackDrinkingWaterYes.setEnabled(false);
                    getViewDataBinding().rgLackDrinkingWater.setAlpha(.5f);

                } else {
                    getViewDataBinding().rgLackDrinkingWater.clearCheck();
                    getViewDataBinding().rbLackDrinkingWaterNo.setEnabled(true);
                    getViewDataBinding().rbLackDrinkingWaterYes.setEnabled(true);
                    getViewDataBinding().rgLackDrinkingWater.setAlpha(1);
                }
            }
        }


    }

    /**
     * to navigate to building fragment
     */
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

}