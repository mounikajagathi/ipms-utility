package in.ults.gisurvey.ui.main.utility.home;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.items.UtilityGridItem;
import in.ults.gisurvey.databinding.FragmentUtilityHomeBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;


public class UtilityHomeFragment extends BaseFragment<FragmentUtilityHomeBinding, UtilityHomeViewModel> implements UtilityHomeNavigator {

    public static final String TAG = UtilityHomeFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    UtilityGridAdapter gridAdapter;

    private UtilityHomeViewModel viewModel;

    public static UtilityHomeFragment newInstance() {
        Bundle args = new Bundle();
        UtilityHomeFragment fragment = new UtilityHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_home;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public UtilityHomeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(UtilityHomeViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {

    }

    @Override
    public void validationError(int code, String error) {

    }

    @Override
    public void clearValidationErrors() {

    }

    @Override
    public void setCachedData() {

    }

    @Override
    public void disablePartialSave() {

    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_utility);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        String[] utilityArray = getResources().getStringArray(R.array.utility);
//        int[] utilityImage = getResources().getIntArray(R.array.utility_images);

        TypedArray tArray = getResources().obtainTypedArray(R.array.utility_images);
        ArrayList<UtilityGridItem> utilityGridItems = new ArrayList<>();
        for (int i = 0; i < utilityArray.length; i++) {
            utilityGridItems.add(new UtilityGridItem((i + 1), utilityArray[i], tArray.getResourceId(i, 0)));
        }
        gridAdapter.addItems(utilityGridItems);
        getViewDataBinding().rvUtilityGrid.setLayoutManager(gridLayoutManager);
        getViewDataBinding().rvUtilityGrid.addItemDecoration(new GridSpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.standard_spacing_medium), true));
        getViewDataBinding().rvUtilityGrid.setAdapter(gridAdapter);
        gridAdapter.setViewItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        getBaseActivity().showBridgeFragment(true);
                        break;
                    case 1:
                        getBaseActivity().showBusStopFragment(true);
                        break;
                    case 2:
                        getBaseActivity().showCanalLineFragment(true);
                        break;
                    case 3:
                        getBaseActivity().showCulvertFragment(true);
                        break;
                    case 4:
                        getBaseActivity().showDividerFragment(true);
                        break;
                    case 5:
                        getBaseActivity().showDrainageFragment(true);
                        break;
                    case 6:
                        getBaseActivity().showGarbagePointFragment(true);
                        break;
                    case 7:
                        getBaseActivity().showHighMastLightFragment(true);
                        break;
                    case 8:
                        getBaseActivity().showLowMastLightFragment(true);
                        break;
                    case 9:
                        getBaseActivity().showParkFragment(true);
                        break;
                    case 10:
                        getBaseActivity().showParkingAreaFragment(true);
                        break;
                    case 11:
                        getBaseActivity().showPlaygroundFragment(true);
                        break;
                    case 12:
                        getBaseActivity().showPondFragment(true);
                        break;
                    case 13:
                        getBaseActivity().showRoadsFragment(true);
                        break;
                    case 14:
                        getBaseActivity().showRoadJunctionFragment(true);
                        break;
                    case 15:
                        getBaseActivity().showRoadSideWalkFragment(true);
                        break;
                    case 16:
                        getBaseActivity().showRoadSignboardFragment(true);
                        break;
                    case 17:
                        getBaseActivity().showStadiumFragment(true);
                        break;
                    case 18:
                        getBaseActivity().showStreetLightFragment(true);
                        break;
                    case 19:
                        getBaseActivity().showStreetTapFragment(true);
                        break;
                    case 20:
                        getBaseActivity().showTankFragment(true);
                        break;
                    case 21:
                        getBaseActivity().showTaxiStandFragment(true);
                        break;
                    case 22:
                        getBaseActivity().showTransformerFragment(true);
                        break;
                    case 23:
                        getBaseActivity().showWellFragment(true);
                        break;
                    default:
                        break;

                }
            }
        });
        tArray.recycle();
    }

}