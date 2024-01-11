package in.ults.gisurvey.ui.main.settings;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.api.District;
import in.ults.gisurvey.data.model.api.LocalBody;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.databinding.FragmentSettingsBinding;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.FileUtils;
import in.ults.gisurvey.utils.PathUtils;


public class SettingsFragment extends BaseFragment<FragmentSettingsBinding, SettingsViewModel> implements SettingsNavigator, BaseActivity.FilePicker {

    public static final String TAG = SettingsFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private SettingsViewModel viewModel;

    @Inject
    SettingsDistrictAdapter districtAdapter;

    @Inject
    SettingsLocalBodyAdapter localBodyAdapter;

    @Inject
    SettingsWardNumberAdapter wardNumberAdapter;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public SettingsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SettingsViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_settings);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        viewModel.updateContent();
        getViewDataBinding().switchLiveLocation.setChecked(viewModel.liveLocationUpdate.getValue().equalsIgnoreCase(AppConstants.LIVE_LOC_ON));
        getViewDataBinding().switchLiveLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewModel.saveLiveLocationStatusInPref(AppConstants.LIVE_LOC_ON);
                    viewModel.liveLocationUpdate.setValue(AppConstants.LIVE_LOC_ON);
                } else {
                    viewModel.saveLiveLocationStatusInPref(AppConstants.LIVE_LOC_OFF);
                    viewModel.liveLocationUpdate.setValue(AppConstants.LIVE_LOC_OFF);
                }

            }
        });
    }

    @Override
    public void navigateToSurveyorList() {

        getBaseActivity().showSurveyorDetailsFragment(true);
    }

    @Override
    public void navigateToSurveyPointsWardSelection() {
        getBaseActivity().showWardSelectionFragment(true);
    }

    @Override
    public void showDistrictBottomSheet(ArrayList<District> data) {
        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(getBaseActivity());
        View parentView = getLayoutInflater().inflate(R.layout.bottom_sheet_recycler_view, null);
        bottomSheerDialog.setContentView(parentView);
        RecyclerView recyclerView = parentView.findViewById(R.id.rvBottomSheet);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setAdapter(districtAdapter);
        districtAdapter.setDataList(data);
        districtAdapter.setOnItemClickListener(position -> {
            viewModel.setDistrict(data.get(position).getDistrictName());
            bottomSheerDialog.dismiss();
        });
        bottomSheerDialog.show();
    }

    @Override
    public void showLocalBodyBottomSheet(ArrayList<LocalBody> data) {
        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(getBaseActivity());
        View parentView = getLayoutInflater().inflate(R.layout.bottom_sheet_recycler_view, null);
        bottomSheerDialog.setContentView(parentView);
        RecyclerView recyclerView = parentView.findViewById(R.id.rvBottomSheet);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setAdapter(localBodyAdapter);
        localBodyAdapter.setDataList(data);
        localBodyAdapter.setOnItemClickListener(position -> {
            viewModel.setLocalBody(data.get(position).getLocalBodyName());
            viewModel.setLocalBodyCode(data.get(position).getLocalBodyCode());
            bottomSheerDialog.dismiss();
        });
        bottomSheerDialog.show();

    }

    @Override
    public void showWardNumberBottomSheet(ArrayList<CommonItem> data) {
        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(getBaseActivity());
        View parentView = getLayoutInflater().inflate(R.layout.bottom_sheet_recycler_view, null);
        bottomSheerDialog.setContentView(parentView);
        RecyclerView recyclerView = parentView.findViewById(R.id.rvBottomSheet);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setAdapter(wardNumberAdapter);
        wardNumberAdapter.setDataList(data);
        wardNumberAdapter.setOnItemClickListener(position -> {
            if (CommonUtils.isNetworkConnected(getBaseActivity())) {
                viewModel.setWardNumber(data.get(position).getContent());
                viewModel.setWardName(data.get(position).getSubContent());
                viewModel.getDataManager().setSelectedSurveyPointWards(new ArrayList<String>());
                bottomSheerDialog.dismiss();
                viewModel.fetchSurveyPoints(data.get(position).getContent());
            }else{
                showToast(getBaseActivity().getString(R.string.settings_no_internet_alert_ward_selection));
            }
        });
        bottomSheerDialog.show();
    }

    /**
     * to sync collected data to server
     */
    @Override
    public void syncData() {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            viewModel.fetchUnSyncedData();
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    @Override
    public void selectTPKData() {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getBaseActivity().chooseFile(this, AppConstants.REQUEST_CODE_PICK_A_FILE);
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    @Override
    public void selectBackupData() {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getBaseActivity().chooseFile(this, AppConstants.REQUEST_CODE_PICK_BACK_UP_FILE);
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    @Override
    public void selectARData() {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getBaseActivity().chooseFile(this, AppConstants.REQUEST_CODE_PICK_AR_FILE);
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    @Override
    public void selectInfoVideoData() {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getBaseActivity().chooseFile(this, AppConstants.REQUEST_CODE_PICK_INFO_VIDEO_FILE);
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    @Override
    public void showMultipleWardSelectionAlert() {
            showDialog(getBaseActivity().getString(R.string.cmn_warning), getBaseActivity().getString(R.string.ward_selection_alert), getBaseActivity().getString(R.string.cmn_yes), (dialog, which) -> {
                navigateToSurveyPointsWardSelection();
            }, getBaseActivity().getString(R.string.cmn_no), (dialog, which) -> dialog.cancel());
    }

    @Override
    public void backupSurvey() {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            viewModel.backupData();
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == AppConstants.REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.fetchUnSyncedData();
            } else {
                Toast.makeText(getBaseActivity(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void navigateToSync() {
        getBaseActivity().showSyncGridFragment(true);
    }

    @Override
    public void onFilePicked(Uri fileUrl, int reqTypeCode) {
        if (reqTypeCode == AppConstants.REQUEST_CODE_PICK_A_FILE) {
            String path = PathUtils.getPath(getBaseActivity(), fileUrl);
            boolean isValidTpkFile = FileUtils.isValidTpkFile(path);
            if (isValidTpkFile) {
                viewModel.saveTpkLocation(path);
            } else {
                getBaseActivity().showToast(getString(R.string.settings_tpk_file_error));
            }
        } else if (reqTypeCode == AppConstants.REQUEST_CODE_PICK_BACK_UP_FILE) {
            String path = PathUtils.getPath(getBaseActivity(), fileUrl);
            boolean isValidTxtFile = FileUtils.isValidTxtFile(path);
            if (isValidTxtFile) {
                //here
                showpopupDialog(path);
//                viewModel.saveTxtLocation(path);
            } else {
                getBaseActivity().showToast(getString(R.string.settings_txt_file_error));
            }
        } else if (reqTypeCode == AppConstants.REQUEST_CODE_PICK_AR_FILE) {
            String path = PathUtils.getPath(getBaseActivity(), fileUrl);
            boolean isValidTxtFile = FileUtils.isValidXLSFile(path);
            if (isValidTxtFile) {
                viewModel.saveARLocation(path);
            } else {
                getBaseActivity().showToast(getString(R.string.settings_txt_file_error));
            }
        } else if (reqTypeCode == AppConstants.REQUEST_CODE_PICK_INFO_VIDEO_FILE) {
            String path = PathUtils.getPath(getBaseActivity(), fileUrl);
            boolean isValidInfoVideoFile = FileUtils.isValidInfoVideoFile(path);
            if (isValidInfoVideoFile) {
                String fileName = path.substring(path.lastIndexOf("/") + 1);
                int lastPos = path.length() - fileName.length();
                String folder = path.substring(0, lastPos);
                viewModel.saveInfoVideoLocation(folder);
            } else {
                getBaseActivity().showToast(getString(R.string.settings_info_video_file_error));
            }
        }
    }

    private void showpopupDialog(String txtLoc) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity());
        builder.setTitle("Restore...")
                .setCancelable(true)
                .setNegativeButton("Normal data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.saveTxtLocation(txtLoc, false);
                    }
                })
                .setPositiveButton("Encrypted data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        //Do here whatever.....
                        viewModel.saveTxtLocation(txtLoc, true);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


}