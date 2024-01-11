package in.ults.gisurvey.ui.survey.image;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentImagesBinding;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.FileUtils;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class ImagesFragment extends BaseFragment<FragmentImagesBinding, ImagesViewModel> implements ImagesNavigator, BaseActivity.ImagePickListener {

    public static final String TAG = ImagesFragment.class.getSimpleName();

    static final int COMMON_ERROR = 1;
    static final int INFORMED_BY_ERROR = 2;
    static final int COOPERATIVE_SURVEY_ERROR = 3;
    static final int SURVEYOR_NAME_ERROR = 4;
    static final int REMARKS_ERROR = 5;

    static final int IMAGE_CAPTURE_ONE = 101;
    static final int IMAGE_CAPTURE_TWO = 102;
    static final int IMAGE_CAPTURE_THREE = 103;

    private ImagesViewModel viewModel;
    private String imagePath1 = "";
    private String imagePath2 = "";
    private String imagePath3 = "";

    @Inject
    CommonDropDownAdapter informedByAdapter;

    @Inject
    CommonDropDownAdapter cooperateSurveyAdapter;

    @Inject
    ViewModelProviderFactory factory;

    public static ImagesFragment newInstance() {
        Bundle args = new Bundle();
        ImagesFragment fragment = new ImagesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_images;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public ImagesViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ImagesViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_images);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        getViewDataBinding().etInformedBy.setAdapter(informedByAdapter);
        getViewDataBinding().etCooperateSurvey.setAdapter(cooperateSurveyAdapter);
//        setting limit for Remarks edittext
        getViewDataBinding().etRemarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(AppConstants.REMARKS_LENGTH_LIMIT) });
        getViewDataBinding().etMoreRemarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(AppConstants.REMARKS_LENGTH_LIMIT) });

        getViewModel().loadData();
        getViewModel().getCurrentSurveyProperty();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == AppConstants.REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getBaseActivity().openImagePicker(this);
            } else {
                Toast.makeText(getBaseActivity(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void saveImageDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String informedBy = Objects.requireNonNull(getViewDataBinding().etInformedBy.getText()).toString().trim();
        String cooperativeSurvey = Objects.requireNonNull(getViewDataBinding().etCooperateSurvey.getText()).toString().trim();
        String surveyorName = Objects.requireNonNull(getViewDataBinding().etSurveyorName.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        String extraRemarks= Objects.requireNonNull(getViewDataBinding().etMoreRemarks.getText()).toString().trim();
        if(!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(informedBy, cooperativeSurvey, surveyorName, remarks, imagePath1, imagePath2,imagePath3)) {
                viewModel.saveImageDetails(informedBy, cooperativeSurvey, surveyorName,viewModel.surveyorList.getValue(), remarks, imagePath1, imagePath2,imagePath3,extraRemarks,true);
            }
        }else{
            //Partial Saving
            //No need od validation
            viewModel.saveImageDetails(informedBy, cooperativeSurvey, surveyorName,viewModel.surveyorList.getValue(), remarks, imagePath1, imagePath2,imagePath3,extraRemarks,false);
        }


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

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
            case INFORMED_BY_ERROR:
                getViewDataBinding().layoutInformedBy.setError(error);
                getViewDataBinding().layoutInformedBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutInformedBy, getViewDataBinding().layoutInformedBy);
                break;
            case COOPERATIVE_SURVEY_ERROR:
                getViewDataBinding().layoutCooperateSurvey.setError(error);
                getViewDataBinding().layoutCooperateSurvey.getParent().requestChildFocus
                        (getViewDataBinding().layoutCooperateSurvey, getViewDataBinding().layoutCooperateSurvey);
                break;
            case SURVEYOR_NAME_ERROR:
                getViewDataBinding().layoutSurveyorName.setError(error);
                getViewDataBinding().layoutSurveyorName.getParent().requestChildFocus
                        (getViewDataBinding().layoutSurveyorName, getViewDataBinding().layoutSurveyorName);
                break;
            case REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutInformedBy.setErrorEnabled(false);
        getViewDataBinding().layoutCooperateSurvey.setErrorEnabled(false);
        getViewDataBinding().layoutSurveyorName.setErrorEnabled(false);
        getViewDataBinding().layoutRemarks.setErrorEnabled(false);
    }

    @Override
    public void captureImage(int reqTypeCode) {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getBaseActivity().openImagePicker(this, reqTypeCode);
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
        }
    }

    @Override
    public void onImageCaptured(String imagePath, int reqTypeCode) {
        viewModel.saveImageLocally(imagePath, reqTypeCode);
    }

    @Override
    public void onImageRemoved(int reqTypeCode) {
        if (reqTypeCode == IMAGE_CAPTURE_ONE) {
            this.imagePath1 = "";
            viewModel.setImageUrl1("");
        } else if (reqTypeCode == IMAGE_CAPTURE_TWO) {
            this.imagePath2 = "";
            viewModel.setImageUrl2("");
        }else if (reqTypeCode == IMAGE_CAPTURE_THREE) {
            this.imagePath3 = "";
            viewModel.setImageUrl3("");
        }
    }

    @Override
    public void setImagePath(String imagePath, int reqTypeCode) {
        String finalImagePath = "";
        if (imagePath != null && imagePath.length() > 0) {
            //for above Android10
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                String exactBackupPathImage =FileUtils.getBackupImagePath(imagePath);
                finalImagePath=exactBackupPathImage;

            }else{
                //for below Android10
                String exactPathImage = FileUtils.getAppFileDirectory() + "/" + imagePath;
                String exactBackupPathImage = FileUtils.getSurveyImageBackupDirectoryForBelowQ() + "/" + imagePath;
                if (new File(exactPathImage).exists()) {
                    finalImagePath = exactPathImage;
                } else if (new File(exactBackupPathImage).exists()) {
                    finalImagePath = exactBackupPathImage;
                }

            }
            if (reqTypeCode == IMAGE_CAPTURE_ONE) {
                this.imagePath1 = imagePath;
                viewModel.setImageUrl1(finalImagePath);
            } else if (reqTypeCode == IMAGE_CAPTURE_TWO) {
                this.imagePath2 = imagePath;
                viewModel.setImageUrl2(finalImagePath);
            }else if (reqTypeCode == IMAGE_CAPTURE_THREE) {
                this.imagePath3 = imagePath;
                viewModel.setImageUrl3(finalImagePath);
            }
        }
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }
}