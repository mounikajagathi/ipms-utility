package in.ults.gisurvey.ui.main.syncgrid;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentSyncGridBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridAdapter;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;


public class SyncGridFragment extends BaseFragment<FragmentSyncGridBinding, SyncGridViewModel> implements SyncGridNavigator {

    public static final String TAG = SyncGridFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private SyncGridViewModel viewModel;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    SyncGridAdapter gridAdapter;

    public static SyncGridFragment newInstance() {
        Bundle args = new Bundle();
        SyncGridFragment fragment = new SyncGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_sync_grid;
    }


    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }


    @Override
    public SyncGridViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SyncGridViewModel.class);
        return viewModel;
    }


    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_sync_grid);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        getViewDataBinding().rvSyncGrid.setLayoutManager(gridLayoutManager);
        getViewDataBinding().rvSyncGrid.setAdapter(gridAdapter);
        gridAdapter.setViewItemClickListener(position -> getBaseActivity().showSyncListFragment(true, position));
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchUnSyncedData();
    }

}