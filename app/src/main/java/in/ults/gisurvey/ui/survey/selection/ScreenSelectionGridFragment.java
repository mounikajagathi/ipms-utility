package in.ults.gisurvey.ui.survey.selection;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.databinding.FragmentScreenSelectionGridBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridAdapter;


public class ScreenSelectionGridFragment extends BaseFragment<FragmentScreenSelectionGridBinding, ScreenSelectionGridViewModel> implements ScreenSelectionGridNavigator {

    public static final String TAG = ScreenSelectionGridFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    ScreenSelectionGridAdapter gridAdapter;

    private ScreenSelectionGridViewModel viewModel;


    public static ScreenSelectionGridFragment newInstance() {
        Bundle args = new Bundle();
        ScreenSelectionGridFragment fragment = new ScreenSelectionGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_screen_selection_grid;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public ScreenSelectionGridViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ScreenSelectionGridViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_selection);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().rvSelectionGrid.setLayoutManager(gridLayoutManager);
        getViewDataBinding().rvSelectionGrid.setAdapter(gridAdapter);
        gridAdapter.setViewItemClickListener(position -> viewModel.onScreenSelection(gridAdapter.getDataList().get(position).getContent()));


    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getCurrentSurveyProperty();
    }

    /**
     * to validate fields and save tax details
     */
    @Override
    public void saveTaxDetails() {
        getBaseActivity().hideKeyboard();
    }

    @Override
    public void navigateToOwnerPage() {
        getBaseActivity().showOwnerFragment(true);
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

    /**
     * set validation error message
     *
     * @param code
     * @param error
     */
    @Override
    public void validationError(int code, String error) {
        switch (code) {

        }
    }


    @Override
    public void setCachedData(Property property) {

    }

    @Override
    public void navigateToCompleteSurveyPage() {
        getBaseActivity().showCompleteSurvey(true);
    }



    @Override
        public void navigateToImageDetails () {
            getBaseActivity().showImageFragment(true);
        }


        @Override
        public void navigateToEstablishmentPage () {
            getBaseActivity().showEstablishmentFragment(true);
        }

        @Override
        public void navigateToMemberPage () {
            getBaseActivity().showMemberFragment(true);
        }

        @Override
        public void navigateToLiveHoodPage () {
            getBaseActivity().showLiveHoodFragment(true);
        }

        @Override
        public void navigateToBuildingPage () {
            getBaseActivity().showBuildingFragment(true);
        }

        @Override
        public void navigateToMorePage () {
            getBaseActivity().showMoreFragment(true);
        }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }
    }