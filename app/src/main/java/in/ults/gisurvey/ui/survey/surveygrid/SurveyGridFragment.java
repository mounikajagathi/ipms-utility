package in.ults.gisurvey.ui.survey.surveygrid;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.databinding.FragmentSurveyGridBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.arcgis.ArcGisActivity;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemDeletionListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemEditListener;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class SurveyGridFragment extends BaseFragment<FragmentSurveyGridBinding, SurveyGridViewModel> implements SurveyGridNavigator {

    public static final String TAG = SurveyGridFragment.class.getSimpleName();

    private SurveyGridViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    SurveyGridAdapter gridAdapter;

    public static SurveyGridFragment newInstance() {
        Bundle args = new Bundle();
        SurveyGridFragment fragment = new SurveyGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_survey_grid;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public SurveyGridViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SurveyGridViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_survey_grid);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            gridAdapter.updateSurveyOpenEditMode(false);
        }
        viewModel.getSyncDataCount();
        getViewDataBinding().rvPropertyGrid.setLayoutManager(gridLayoutManager);
        gridAdapter.setSurveyId(viewModel.getCurrentSurveyId());
        getViewDataBinding().rvPropertyGrid.setAdapter(gridAdapter);
        gridAdapter.setViewItemClickListener(position -> viewModel.saveSelectedPropertyId(gridAdapter.getDataList().get(position).getId()));
        gridAdapter.setViewItemDeleetionListener(new RecyclerViewItemDeletionListener() {
            @Override
            public void onItemDeletion(int position) {
                showDialog(null, getString(R.string.msg_delete_property_alert),
                        getString(android.R.string.yes), (dialog, which) -> {
                            viewModel.deleteProperty(gridAdapter.getDataList().get(position).getId(), position);
//
                        }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
            }
        });
        gridAdapter.setViewItemEditListener(new RecyclerViewItemEditListener() {
            @Override
            public void onItemEdit(int position) {
                showDialog(null, getString(R.string.msg_edit_property_alert),
                        getString(android.R.string.yes), (dialog, which) -> {
                            viewModel.editProperty(gridAdapter.getDataList().get(position).getId(), position);
//
                        }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCurrentSurvey();
    }

    @Override
    public void navigateToNextPage() {
        if (getBaseActivity().hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && getBaseActivity().hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            startActivityForResult(new Intent(getBaseActivity(), ArcGisActivity.class), 54321);
        } else {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            getBaseActivity().requestPermissionsSafely(permissions, 786);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 54321) {
            if (resultCode == Activity.RESULT_OK) {
                getBaseActivity().showPropertyFragment(true);
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 786:
                try {
                    for (int i = 0; i < permissions.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            startActivityForResult(new Intent(getBaseActivity(), ArcGisActivity.class), 54321);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

    }
    @Override
    public void completeSurvey() {
        getBaseActivity().finish();
    }

    @Override
    public void completedSurveyList(Set<String> data) {
        gridAdapter.setCompletedSurvey(data);
    }

    @Override
    public void onDeletionSuccess(int floorCount, int propertyCount, int position) {
        gridAdapter.getDataList().remove(position);
        gridAdapter.notifyDataSetChanged();
        viewModel.saveFloorPropertyCount(floorCount,propertyCount);
        showToast(getString(R.string.sync_grid_dltn_done_msg));
        super.onFragmentBackPressed();
//        viewModel.getCompletedSurveyProperty();
    }

    @Override
    public void onEditSuccess() {
        showToast(getString(R.string.msg_edit_success));
        getBaseActivity().finish();
    }

    @Override
    public void onFetchSyncCount(int count) {

        if(count==0)
        {
            //no sync ie data edit is needed
            gridAdapter.setSurveySyncStatus(false);
        } else{
            //sync occured ie data edit is not needed
            gridAdapter.setSurveySyncStatus(true);
        }

    }
}