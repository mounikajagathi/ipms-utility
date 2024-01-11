package in.ults.gisurvey.ui.survey.member;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.databinding.FragmentMemberBinding;
import in.ults.gisurvey.ui.base.BaseFragment;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class MemberFragment extends BaseFragment<FragmentMemberBinding, MemberViewModel> implements MemberNavigator {

    public static final String TAG = MemberFragment.class.getSimpleName();

    private MemberViewModel viewModel;

    @Inject
    public MemberDetailsAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    ViewModelProviderFactory factory;

    static final int NO_OF_MEMBER_LIST_ERROR = 1;


    public static MemberFragment newInstance() {
        Bundle args = new Bundle();
        MemberFragment fragment = new MemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_member;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public MemberViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MemberViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_member);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            adapter.updateSurveyOpenEditMode(false);
        }
        getViewDataBinding().rvMember.setLayoutManager(layoutManager);
        getViewDataBinding().rvMember.setAdapter(adapter);
        viewModel.getCurrentSurveyProperty();
    }

    /**
     * set validation error message
     */
    @Override
    public void validationError(int code, String error) {
        if (code == NO_OF_MEMBER_LIST_ERROR) {
            adapter.setValidation(true);
        }
    }

    @Override
    public void clearValidationErrors() {
        adapter.setValidation(false);
    }

    @Override
    public void setCachedData(Property property) {
        adapter.setDataList(property.memberDetails);
    }

    @Override
    public void showNoMemberDialog() {
        showDialog(null, getString(R.string.msg_no_member),
                getString(android.R.string.yes), (dialog, which) -> {
                    viewModel.navigateToNextPage();
                }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());

    }

    /**
     * to validate fields and save member details
     */
    @Override
    public void saveMemberDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        if (adapter != null && adapter.getDataList() != null) {
            String noOfMembers = Objects.requireNonNull(getViewDataBinding().etNoOfMembers.getText()).toString().trim();
            if(!isPartial) {
                //Data submission
                //So validation is necessary


                if (viewModel.checkMembers(noOfMembers) && viewModel.validateFields(adapter.getDataList())) {
                    viewModel.saveMemberDetails(noOfMembers, adapter.getDataList(),true);
                }
            }else{
                //Partial Saving
                //No need od validation
                viewModel.saveMemberDetails(noOfMembers, adapter.getDataList(),false);
            }


        }
    }

    @Override
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
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

    /**
     * create a new member
     */
    @Override
    public void addMembers() {
        getBaseActivity().hideKeyboard();
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfMembers.getText()).toString());
        if (value > -1) {
            getViewDataBinding().etNoOfMembers.setText(String.valueOf(value + 1));
            viewModel.setNoOfMembers(value + 1);
            adapter.addNewItem();
        }
    }

    /**
     * here,remove existing member
     */
    @Override
    public void removeMembers() {
        getBaseActivity().hideKeyboard();
        int value = Integer.parseInt(Objects.requireNonNull(getViewDataBinding().etNoOfMembers.getText()).toString());
        if (value > 0) {
            viewModel.setNoOfMembers(value - 1);
            adapter.removeItem();
        }
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }
    @Override
    public void showWarningDialog(String message) {
        showDialog(getString(R.string.cmn_warning), message,
                getString(R.string.cmn_continue), (dialog, which) -> {
                    String noOfMembers = Objects.requireNonNull(getViewDataBinding().etNoOfMembers.getText()).toString().trim();
                    viewModel.saveMemberDetails(noOfMembers, adapter.getDataList(),true);
                }, getString(R.string.cmn_change), (dialog, which) -> dialog.cancel());

    }

    @Override
    public void showErrorDialog(String message) {
        showInfoDialog(message);
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