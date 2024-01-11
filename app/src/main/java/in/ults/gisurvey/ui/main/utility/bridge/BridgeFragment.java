package in.ults.gisurvey.ui.main.utility.bridge;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentUtilityBridgeDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class BridgeFragment extends BaseFragment<FragmentUtilityBridgeDetailsBinding, BridgeViewModel> implements BridgeNavigator {

    public static final String TAG = BridgeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    CommonDropDownAdapter bridgeMaterialAdapter;
    @Inject
    CommonDropDownAdapter bridgeSubTypeAdapter;
    @Inject
    CommonDropDownAdapter bridgeMaintainByAdapter;
    @Inject
    CommonDropDownAdapter bridgeFootPathConstructionMaterialAdapter;


    private BridgeViewModel viewModel;

    private String imagePath1 = "";
    static final int BRIDGE_NAME_ERROR = 1;
    static final int BRIDGE_PLACE_ERROR = 2;
    static final int BRIDGE_MATERIAL_ERROR = 3;
    static final int BRIDGE_SUBTYPE_ERROR = 4;
    static final int BRIDGE_WIDTH_ERROR = 5;
    static final int BRIDGE_LENGTH_ERROR = 6;
    static final int BRIDGE_FOOTPATH_CONSTRUCTION_MAT_ERROR = 7;
    static final int BRIDGE_MAINTAIN_BY_ERROR = 8;
    static final int BRIDGE_REMARKS_ERROR = 9;
    static final int BRIDGE_COMMON_ERROR = 10;

    public static BridgeFragment newInstance() {
        Bundle args = new Bundle();
        BridgeFragment fragment = new BridgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_bridge_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public BridgeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(BridgeViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String bridgeName = Objects.requireNonNull(getViewDataBinding().etBridgeName.getText()).toString().trim();
        String bridgePlace = Objects.requireNonNull(getViewDataBinding().etBridgePlace.getText()).toString().trim();
        String bridgeMaterial = (String) getViewDataBinding().srBridgeMaterial.getTag();
        String bridgeSubtype = (String) getViewDataBinding().srBridgeSubType.getTag();
        String bridgeWidth = Objects.requireNonNull(getViewDataBinding().etBridgeWidth.getText()).toString().trim();
        String bridgeLength = Objects.requireNonNull(getViewDataBinding().etBridgeLength.getText()).toString().trim();
        String bridgeFootPathConstMat = (String) getViewDataBinding().srFootpathConstructionMaterial.getTag();
        String bridgeMaintainBy =(String) getViewDataBinding().srMaintainBy.getTag();
        String bridgeRemarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(bridgeName, bridgePlace, bridgeMaterial, bridgeSubtype, bridgeWidth, bridgeLength, bridgeFootPathConstMat, bridgeMaintainBy, bridgeRemarks,imagePath1)) {
                viewModel.saveUtilityDetails(bridgeName, bridgePlace, bridgeMaterial, bridgeSubtype, bridgeWidth, bridgeLength, bridgeFootPathConstMat, bridgeMaintainBy, bridgeRemarks,imagePath1,true);
            }

        }else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(bridgeName, bridgePlace, bridgeMaterial, bridgeSubtype, bridgeWidth, bridgeLength, bridgeFootPathConstMat, bridgeMaintainBy, bridgeRemarks,imagePath1,false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case BRIDGE_NAME_ERROR:
                getViewDataBinding().layoutBridgeName.setError(error);
                getViewDataBinding().layoutBridgeName.getParent().requestChildFocus
                        (getViewDataBinding().layoutBridgeName, getViewDataBinding().layoutBridgeName);
                break;
            case BRIDGE_PLACE_ERROR:
                getViewDataBinding().layoutBridgePlace.setError(error);
                getViewDataBinding().layoutBridgePlace.getParent().requestChildFocus
                        (getViewDataBinding().layoutBridgePlace, getViewDataBinding().layoutBridgePlace);
                break;
            case BRIDGE_MATERIAL_ERROR:
                getViewDataBinding().layoutBridgeMaterial.setError(error);
                getViewDataBinding().layoutBridgeMaterial.getParent().requestChildFocus
                        (getViewDataBinding().layoutBridgeMaterial, getViewDataBinding().layoutBridgeMaterial);
                break;
            case BRIDGE_SUBTYPE_ERROR:
                getViewDataBinding().layoutBridgeSubType.setError(error);
                getViewDataBinding().layoutBridgeSubType.getParent().requestChildFocus
                        (getViewDataBinding().layoutBridgeSubType, getViewDataBinding().layoutBridgeSubType);
                break;
            case BRIDGE_WIDTH_ERROR:
                getViewDataBinding().layoutBridgeWidth.setError(error);
                getViewDataBinding().layoutBridgeWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutBridgeWidth, getViewDataBinding().layoutBridgeWidth);
                break;
            case BRIDGE_LENGTH_ERROR:
                getViewDataBinding().layoutBridgeLength.setError(error);
                getViewDataBinding().layoutBridgeLength.getParent().requestChildFocus
                        (getViewDataBinding().layoutBridgeLength, getViewDataBinding().layoutBridgeLength);
                break;
            case BRIDGE_FOOTPATH_CONSTRUCTION_MAT_ERROR:
                getViewDataBinding().layoutFootpathConstructionMaterial.setError(error);
                getViewDataBinding().layoutFootpathConstructionMaterial.getParent().requestChildFocus
                        (getViewDataBinding().layoutFootpathConstructionMaterial, getViewDataBinding().layoutFootpathConstructionMaterial);
                break;
            case BRIDGE_MAINTAIN_BY_ERROR:
                getViewDataBinding().layoutMaintainBy.setError(error);
                getViewDataBinding().layoutMaintainBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutMaintainBy, getViewDataBinding().layoutMaintainBy);
                break;
            case BRIDGE_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case BRIDGE_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutBridgeName.setErrorEnabled(false);
        getViewDataBinding().layoutBridgePlace.setErrorEnabled(false);
        getViewDataBinding().layoutBridgeMaterial.setErrorEnabled(false);
        getViewDataBinding().layoutFootpathConstructionMaterial.setErrorEnabled(false);
        getViewDataBinding().layoutBridgeSubType.setErrorEnabled(false);
        getViewDataBinding().layoutBridgeWidth.setErrorEnabled(false);
        getViewDataBinding().layoutBridgeLength.setErrorEnabled(false);
        getViewDataBinding().layoutMaintainBy.setErrorEnabled(false);
        getViewDataBinding().layoutRemarks.setErrorEnabled(false);
    }

    @Override
    public void setCachedData() {

    }

    @Override
    public void disablePartialSave() {

    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_utility_bridge);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srBridgeMaterial.setAdapter(bridgeMaterialAdapter);
        getViewDataBinding().srBridgeSubType.setAdapter(bridgeSubTypeAdapter);
        getViewDataBinding().srMaintainBy.setAdapter(bridgeMaintainByAdapter);
        getViewDataBinding().srFootpathConstructionMaterial.setAdapter(bridgeFootPathConstructionMaterialAdapter);
        viewModel.loadData();
    }

}