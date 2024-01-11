package in.ults.gisurvey.ui.main.synclist;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentSyncListBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.main.completed.CompletedSurveyListAdapter;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.ViewUtils;


public class SyncListFragment extends BaseFragment<FragmentSyncListBinding, SyncListViewModel> implements SyncListNavigator {

    public static final String TAG = SyncListFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private SyncListViewModel viewModel;

    @Inject
    SyncListAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    ProgressDialog syncDialog;

    View dialogView;

    public static final String KEY_SELECTED_COUNT = "selected_count";

    public static SyncListFragment newInstance(int selectedCount) {
        Bundle args = new Bundle();
        args.putInt(KEY_SELECTED_COUNT, selectedCount);
        SyncListFragment fragment = new SyncListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sync_list;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public SyncListViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SyncListViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_sync_list);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().rvSyncList.setLayoutManager(layoutManager);
        getViewDataBinding().rvSyncList.setAdapter(adapter);
        if (getArguments() != null) {
            int count = getArguments().getInt(KEY_SELECTED_COUNT);
            int initialCount = count * AppConstants.SYNC_COUNT;
            viewModel.fetchSyncListData(initialCount);
        }
    }

    @Override
    public void showSyncDialog() {
        syncDialog = new ProgressDialog(getBaseActivity());
        try {
            dialogView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.sync_dialog, null);
            syncDialog.setCanceledOnTouchOutside(false);
            syncDialog.setCancelable(false);
            syncDialog.show();
            syncDialog.setContentView(dialogView);
            Objects.requireNonNull(syncDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            syncDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            int currentRotation = 0;
            RotateAnimation anim = new RotateAnimation(currentRotation, (360 * 4),
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            currentRotation = (currentRotation + 45) % 360;
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(4000);
            anim.setRepeatCount(Animation.INFINITE);
            anim.setRepeatMode(Animation.RESTART);
            anim.setFillEnabled(true);
            anim.setFillAfter(true);
            dialogView.findViewById(R.id.img_dialog_sync).startAnimation(anim);
            dialogView.findViewById(R.id.img_sync_close).setOnClickListener(v -> viewModel.stopSync());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSyncStatusLabels(String label) {
        if (dialogView != null) {
            ((TextView) dialogView.findViewById(R.id.txt_sync_status)).setText(label);
        }
    }

    @Override
    public void dismissSyncDialog() {
        if (syncDialog != null) {
            syncDialog.dismiss();
        }
    }

    @Override
    public void updateUploadPercent(long bytesUploaded, long totalBytes) {
        if (dialogView != null) {
            ((TextView) dialogView.findViewById(R.id.txt_image_upload_count)).setText(String.format(Locale.ENGLISH,"%d %% / 100 Completed", (bytesUploaded / totalBytes) * 100));
        }
    }

    @Override
    public void showUploadCount() {
        if (dialogView != null) {
            dialogView.findViewById(R.id.txt_image_upload_count).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void preventDoubleClick() {
        CommonUtils.preventTwoClick((getViewDataBinding().fab));
    }

    @Override
    public void refreshPage() {
        if (syncDialog != null) {
            syncDialog.dismiss();
        }
        getBaseActivity().onBackPressed();
    }
}