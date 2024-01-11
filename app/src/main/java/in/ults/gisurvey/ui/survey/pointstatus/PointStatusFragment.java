package in.ults.gisurvey.ui.survey.pointstatus;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentPointStatusBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class PointStatusFragment extends BaseFragment<FragmentPointStatusBinding, PointStatusViewModel> implements PointStatusNavigator {

    public static final String TAG = PointStatusFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private PointStatusViewModel viewModel;
    private String selectedPointStatus = "";

    public static PointStatusFragment newInstance() {
        Bundle args = new Bundle();
        PointStatusFragment fragment = new PointStatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_point_status;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public PointStatusViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(PointStatusViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_point_status);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        viewModel.getCurrentSurvey();
    }


    @Override
    public void savePointStatus() {
        if (getViewDataBinding().rgBuildingCheckList.getCheckedRadioButtonId() != -1) {
            String pointStatus = ((RadioButton) getViewDataBinding().rgBuildingCheckList.findViewById(getViewDataBinding().rgBuildingCheckList.getCheckedRadioButtonId())).getText().toString();
            viewModel.savePointStatus(pointStatus);
        } else {
            getBaseActivity().showToast(R.string.select_point_status);
        }
    }

    /**
     * set the current point status and check mark the selected point status
     *
     * @param pointStatus
     */
    @Override
    public void setPointStatus(String pointStatus) {
        setSelectedPointStatus(pointStatus);
        getViewDataBinding().rgBuildingCheckList.clearCheck();
        switch (pointStatus) {
            case AppConstants.POINT_STATUS_UNWANTED:
                getViewDataBinding().rgBuildingCheckList.check(R.id.radioBtnUnwanted);
                break;
            case AppConstants.POINT_STATUS_BUILDING:
                getViewDataBinding().rgBuildingCheckList.check(R.id.radioBtnBuilding);
                break;
            case AppConstants.POINT_STATUS_LANDMARK:
                getViewDataBinding().rgBuildingCheckList.check(R.id.radioBtnLandmark);
                break;
            default:
                break;
        }
        if(!viewModel.isSurveyOpenEditMode()) {
            //For setting edit option on title bar
            //Only need edit option in case of Survey open in view mode(Ie from completed survey)
            //Not sync done
            //UNWANTED OR LANDMARK
            if(selectedPointStatus.equalsIgnoreCase(AppConstants.POINT_STATUS_UNWANTED)||selectedPointStatus.equalsIgnoreCase(AppConstants.POINT_STATUS_LANDMARK))
                viewModel.getunsyncDataCount();
        }

    }

    @Override
    public void setSelectedPointStatus(String pointStatus) {
        selectedPointStatus = pointStatus;
    }

    @Override
    public void navigateToFloorDetailsPage() {
        getBaseActivity().showFloorCountFragment(true);
    }

    @Override
    public void navigateToLocationDetailsPage() {
        getBaseActivity().showLocationFragment(true);
    }

    /**
     * update the current point status and clear previous survey details
     */
    @Override
    public void radioButtonClicked() {
        if (getViewDataBinding().rgBuildingCheckList.getCheckedRadioButtonId() != -1) {
            String pointStatus = ((RadioButton) getViewDataBinding().rgBuildingCheckList.findViewById(getViewDataBinding().rgBuildingCheckList.getCheckedRadioButtonId())).getText().toString();
            if (selectedPointStatus != null && selectedPointStatus.length() != 0 && !pointStatus.equals(selectedPointStatus)) {
                setPointStatus(selectedPointStatus);
                showDialog(null, getString(R.string.msg_change_point_status), getString(android.R.string.yes), (dialog, which) -> {
                    setPointStatus(pointStatus);
                    viewModel.clearSurveyDetails(pointStatus);
                    selectedPointStatus = "";
                }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
            }
        }
    }

    @Override
    public void onEdtSuccess() {
        showToast(getString(R.string.msg_edit_success));
        getBaseActivity().finish();
    }

    @Override
    public void setEditOnTitleBar() {
        //Only for UNWANTED and LANDMARK
         setHasOptionsMenu(true);
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_menu, menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                showDialog(null, getString(R.string.msg_edit_survey_alert),
                        getString(android.R.string.yes), (dialog, which) -> {
                            viewModel.updateSurveyCompletedStatus();
//
                        }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());

                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}