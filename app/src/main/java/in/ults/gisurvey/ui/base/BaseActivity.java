package in.ults.gisurvey.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.android.AndroidInjection;
import in.ults.gisurvey.BuildConfig;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ui.auth.AuthActivity;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.FileUtils;
import in.ults.gisurvey.utils.NetworkUtils;
import in.ults.gisurvey.utils.PathUtils;
import io.reactivex.plugins.RxJavaPlugins;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements FragmentCallback, BaseNavigator {

    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;
    private BaseFragment baseFragment;
    private Uri imageUri;
    private int reqTypeCode;
    private ImagePickListener imagePickListener;
    private FilePicker filePicker;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract @LayoutRes
    int getLayoutId();

    /**
     * Override for set navigator
     */
    public abstract void initNavigator();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
        initNavigator();
        RxJavaPlugins.setErrorHandler(e -> Log.d("RxException", ""));
        setUp(savedInstanceState);
    }


    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    /**
     * to hide keyboard
     **/

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    protected void setUp(@Nullable Bundle savedInstanceState) {

    }

    protected void lockMenuDrawer() {

    }

    protected void unLockMenuDrawer() {

    }

    /**
     * to show common progress dialog
     **/
    @Override
    public void showProgressDialog() {
        hideProgressDialog();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    /**
     * to hide common progress dialog
     **/
    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    /**
     * common method to set tool bar title
     *
     * @param toolbarTitle
     */
    public void setToolbarTitle(String toolbarTitle) {

    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
        startActivity(AuthActivity.newIntent(this));
        finish();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }


    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }

    /**
     * to load fragment
     **/
    protected void showFragment(int container, Fragment fragment, String tag, boolean isBackStack) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, tag);
            if (isBackStack) {
                ft.addToBackStack(tag);
            }
            ft.commit();
        }
    }

    /**
     * @return base fragment
     */
    public BaseFragment getBaseFragment() {
        return baseFragment;
    }

    /**
     * to check whether fragment attached or not
     */
    public boolean isBaseFragmentAttached() {
        return baseFragment != null;
    }

    /**
     * to set base fragment
     */

    public void setBaseFragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }


    @Override
    public void showLoginFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showForgotPasswordFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showServerUrlFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showHomeFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showBuildingFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showBuildingCheckFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showFloorCountFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showBasementFragment(boolean isAddedToBackStack) {

    }
    /**
     * To set fragment for listing surveyed properties that getting from server
     * @param isAddedToBackStack
     */
    @Override
    public void showServerSurveyFragment(boolean isAddedToBackStack) {

    }
    /**
     * To set fragment for details surveyed property that getting from server
     * @param isAddedToBackStack
     */
    @Override
    public void showServerSurveyDetailsFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showGroundFloorSelection(boolean isAddedToBackStack) {

    }

    @Override
    public void showLiveHoodFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showMemberFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showPropertyFragment(boolean isAddedToBackStack) {

    }


    @Override
    public void showSurveyGridFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showTaxFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showTenantFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showSettingsFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showPartialSurvey(boolean isAddedToBackStack) {

    }

    @Override
    public void showOwnerFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showLocationFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showRoadFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showEstablishmentFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showAboutFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showMapFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showProfileFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showCompletedFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showSurveyPointsFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showCompleteSurvey(boolean isAddedToBackStack) {

    }

    @Override
    public void showSurveyorList(boolean isAddedToBackStack) {

    }

    @Override
    public void showSurveyorDetailsFragment(boolean isAddedToBackStack) {

    }


    @Override
    public void showWardSelectionFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showMoreFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showReportFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showSelectionFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showBridgeFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showUtilityHomeFragment(boolean isAddedToBackStack) {

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * to show string toast message
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * to show integer toast message
     */
    @Override
    public void showToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * to show info dialog
     */
    @Override
    public void showInfoDialog(String info) {
        showDialog(null, info, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, null, null);
    }

    /**
     * to show info dialog with video
     */
    @Override
    public void showInfoDialogWithVideo(String info, String videoName) {

        showDialog(null, info, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, getString(R.string.cmn_video), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mViewModel.getInfoVideoFileLoc() == null) {
                    showToast("Select video file location  from settings");
                } else {
                    String path = mViewModel.getInfoVideoFileLoc() + videoName;
                    File file = new File(path);
                    if (file.exists()) {
                        Uri uri = Uri.parse(path);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri, "video/*");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                    } else {
                        showToast("Info video file not found in the video folder");
                    }
                }
            }
        });

    }

    /**
     * to show dialog
     */
    @Override
    public void showDialog(String title, String message, String positiveButtonLabel, DialogInterface.OnClickListener positiveButton, String negativeButtonLabel, DialogInterface.OnClickListener negativeButton) {
        AlertDialog builder = new AlertDialog.Builder(this).create();
        if (title != null) {
            builder.setTitle(title);
        }
//        TextView tv = (TextView) findViewById(R.id.tv_long);
//        tv.setMovementMethod(new ScrollingMovementMethod());

        builder.setMessage(message);
        builder.setCancelable(true);
        if (positiveButton != null) {
            builder.setButton(AlertDialog.BUTTON_POSITIVE, positiveButtonLabel, positiveButton);
        }
        if (negativeButton != null) {
            builder.setButton(AlertDialog.BUTTON_NEGATIVE, negativeButtonLabel, negativeButton);
        }

        builder.setOnShowListener(dialog -> {
            final TextView textView = builder.findViewById(android.R.id.message);
            if (textView != null) {
                textView.setMovementMethod(new ScrollingMovementMethod());
            }
        });

        builder.show();
    }

    /**
     * Override for open camera
     */
    public void openCamera(BaseActivity activity, int requestCode) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        File photo = new File(FileUtils.getCaptureImageDirectory(), "profile_img_" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date()) + ".jpg");
        imageUri = Uri.fromFile(photo);
        Uri intentUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, intentUri);
        activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.open_camera_using)), requestCode);

    }

    /**
     * to  show toast when api fails
     */
    @Override
    public void onApiFailure() {
        showToast(R.string.api_default_error);
    }

    /**
     * to open for open gallery
     */
    public void openGallery(BaseActivity activity, int requestCode) {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, FileUtils.MIME_TYPE_IMAGE);
        activity.startActivityForResult(i, requestCode);
    }


    /**
     * to open for open gallery or camera or to remove image
     **/
    public void openImagePicker(ImagePickListener imagePickListener, int reqTypeCode) {
        this.reqTypeCode = reqTypeCode;
        this.imagePickListener = imagePickListener;
        View sortingView = getLayoutInflater().inflate(R.layout.bottom_sheet_image_picker, null);
        BottomSheetDialog pickerBottomSheet = new BottomSheetDialog(this);
        pickerBottomSheet.setContentView(sortingView);
        pickerBottomSheet.setCanceledOnTouchOutside(true);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(((View) sortingView.getParent()));
        bottomSheetBehavior.setPeekHeight(1000);
        bottomSheetBehavior.setHideable(false);
        pickerBottomSheet.show();
        sortingView.findViewById(R.id.imgCamera).setOnClickListener(v -> {
            pickerBottomSheet.dismiss();
            openCamera(this, AppConstants.REQUEST_CODE_OPEN_CAMERA);
        });

        sortingView.findViewById(R.id.imgGallery).setOnClickListener(v -> {
            pickerBottomSheet.dismiss();
            openGallery(this, AppConstants.REQUEST_CODE_OPEN_GALLERY);
        });

        sortingView.findViewById(R.id.imgRemove).setOnClickListener(v -> {
            if (imagePickListener != null) {
                imagePickListener.onImageRemoved(reqTypeCode);
            }
            pickerBottomSheet.dismiss();
        });
    }

    public void chooseFile(FilePicker filePicker, int requestCode) {
        this.filePicker = filePicker;
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        Intent intent = Intent.createChooser(chooseFile, "Choose tpk file");
        startActivityForResult(intent, requestCode);
    }

    /**
     * Override for save or remove image
     */
    public interface ImagePickListener {
        void onImageCaptured(String path, int reqTypeCode);

        void onImageRemoved(int reqTypeCode);
    }

    /**
     * Override for save or remove image
     */
    public interface FilePicker {
        void onFilePicked(Uri fileUrl, int reqTypeCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.REQUEST_CODE_OPEN_CAMERA) {
                if (imageUri != null && FileUtils.isValidImageFile(imageUri.getPath())) {
                    if (imagePickListener != null) {
                        imagePickListener.onImageCaptured(imageUri.getPath(), reqTypeCode);
                    }
                }
            } else if (requestCode == AppConstants.REQUEST_CODE_OPEN_GALLERY) {
                if (data != null && data.getData() != null && FileUtils.isValidImageFile(PathUtils.getPath(this, data.getData()))) {
                    if (imagePickListener != null) {
                        imagePickListener.onImageCaptured(PathUtils.getPath(this, data.getData()), reqTypeCode);
                    }
                }
            } else if (requestCode == AppConstants.REQUEST_CODE_PICK_A_FILE) {
                if (data != null && data.getData() != null) {
                    if (filePicker != null) {
                        filePicker.onFilePicked(data.getData(), requestCode);
                    }
                }
            } else if (requestCode == AppConstants.REQUEST_CODE_PICK_BACK_UP_FILE) {
                if (data != null && data.getData() != null) {
                    if (filePicker != null) {
                        filePicker.onFilePicked(data.getData(), requestCode);
                    }
                }
            } else if (requestCode == AppConstants.REQUEST_CODE_PICK_AR_FILE) {
                if (data != null && data.getData() != null) {
                    if (filePicker != null) {
                        filePicker.onFilePicked(data.getData(), requestCode);
                    }
                }
            } else if (requestCode == AppConstants.REQUEST_CODE_PICK_INFO_VIDEO_FILE) {
                if (data != null && data.getData() != null) {
                    if (filePicker != null) {
                        filePicker.onFilePicked(data.getData(), requestCode);
                    }
                }
            }
        }
    }

    public void popBackStackAfterTag(String tag) {
        boolean isTagAvailable = false;
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            String name = getSupportFragmentManager().getBackStackEntryAt(i).getName();
            if (isTagAvailable) {
                getSupportFragmentManager().popBackStack();
            }
            if (name != null && name.equalsIgnoreCase(tag)) {
                isTagAvailable = true;
            }
        }
    }


    @Override
    public void onLogoutUser() {

    }

    @Override
    public void showImageFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showSyncListFragment(boolean isAddedToBackStack, int selectedCount) {

    }

    @Override
    public void showSyncGridFragment(boolean isAddedToBackStack) {

    }


    @Override
    public void onBackPressed() {
        if (baseFragment != null) {
            baseFragment.onFragmentBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void myBackPress() {
        super.onBackPressed();
    }

    /**
     * This is to return Marker symbol for point plotting on map
     * Border
     * -----------------------
     * Yellow=>DB Points
     * SkyBlue => Server Points
     * Inner Color
     * -----------------------
     * Red=>DC/GL/NC
     * Green=>Door opened
     * Blue=>PDC
     * Rose=> Point status other than Building and for Building with property Status Demolished and unusable
     *
     * @param isServerPoint
     * @param pointStatus
     * @param propertyStatus
     * @param doorStatus
     * @return
     */
    public SimpleMarkerSymbol getMarkSymbolForPointDCGLNC(boolean isServerPoint, String pointStatus, String propertyStatus, String doorStatus) {

        SimpleMarkerSymbol pointSymbol;

        if (pointStatus.equalsIgnoreCase(AppConstants.POINT_STATUS_BUILDING)) {
            if (propertyStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)) {
                //Property Status DEMOLISHED
                //Light Coffe Brown
                pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(120, 63, 4), 11.0f);

            } else if (propertyStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)) {
                //Property Status Unusable
                //Orange
                pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(255, 153, 0), 11.0f);

            } else {
                //Now showing color depend on door status
                if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_OPEN)) {
                    //circle
                    //green
                    pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(0, 204, 0), 11.0f);

                } else if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC)) {
                    //Blue
                    //squre
                    pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, Color.rgb(0, 0, 255), 11.0f);

                } else if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL)||doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC)) {
                    //Red
                    //squre
                    pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, Color.rgb(255, 0, 0), 11.0f);


                }  else if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_NC)) {
                    //Violet
                    //squre
                    pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, Color.rgb(153, 0, 255), 11.0f);

                } else {
                    //If any new door status added it will be included in this condition
                    //Black
                    pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(0, 0, 0), 11.0f);
                }


            }
        } else if (pointStatus.equalsIgnoreCase(AppConstants.POINT_STATUS_UNWANTED)) {
            //Point status UNWANTED
            //Light Pink
            //Trinagle
            pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.TRIANGLE, Color.rgb(255, 146, 187), 11.0f);

        } else if (pointStatus.equalsIgnoreCase(AppConstants.POINT_STATUS_LANDMARK)) {
            //Point status LANDAMRK
            //GREY
            pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(122, 122, 122), 11.0f);

        } else {
            //If any new point status added it will be included in this condition
            //Black
            pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.rgb(0, 0, 0), 11.0f);
        }


        if (isServerPoint) {
            //border color will be sky blue
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.rgb(0, 255, 255), 3.0f));
        } else {
            //border color will be sky yellow
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.rgb(255, 255, 0), 3.0f));

        }
        return pointSymbol;
    }

    public void showMapColourCodePopup() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_map_colour_code, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button btnVClose = (Button) dialogView.findViewById(R.id.buttonClose);
        btnVClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void showBusStopFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showCanalLineFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showCulvertFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showDividerFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showDrainageFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showGarbagePointFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showHighMastLightFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showLowMastLightFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showParkFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showParkingAreaFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showPlaygroundFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showPondFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showRoadsFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showRoadJunctionFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showRoadSideWalkFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showRoadSignboardFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showStadiumFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showStreetLightFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showStreetTapFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showTankFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showTaxiStandFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showTransformerFragment(boolean isAddedToBackStack) {

    }

    @Override
    public void showWellFragment(boolean isAddedToBackStack) {

    }
}

