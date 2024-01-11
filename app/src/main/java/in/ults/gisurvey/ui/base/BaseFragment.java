package in.ults.gisurvey.ui.base;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import dagger.android.support.AndroidSupportInjection;
import in.ults.gisurvey.R;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements BaseNavigator {

    private BaseActivity baseActivity;
    private View rootView;
    private T mViewDataBinding;
    private V mViewModel;
    public static String TAG = BaseFragment.class.getSimpleName();

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

    public View getRootView() {
        return rootView;
    }

    protected BaseActivity getBaseActivity() {
        return baseActivity;
    }

    /**
     * @return view data binding instance
     */
    protected T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.baseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);
        initNavigator();
    }

    @Override
    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            rootView = mViewDataBinding.getRoot();
            setUp(savedInstanceState);
        }
        return rootView;
    }

    protected void setUp(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        getBaseActivity().setBaseFragment(this);
        getBaseActivity().setToolbarTitle(getToolbarTitle());
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();

    }

    protected String getToolbarTitle() {
        return null;
    }


    public void hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard();
        }
    }

    public void onFragmentBackPressed() {
        getBaseActivity().myBackPress();
    }


    public boolean isNetworkConnected() {
        return baseActivity != null && baseActivity.isNetworkConnected();
    }

    public void openActivityOnTokenExpire() {
        if (baseActivity != null) {
            baseActivity.openActivityOnTokenExpire();
        }
    }

    @Override
    public void showToast(String message) {
        if (baseActivity != null) {
            baseActivity.showToast(message);
        }
    }

    /**
     * to call toast method in BaseActivity
     */
    @Override
    public void showToast(int message) {
        if (baseActivity != null) {
            baseActivity.showToast(message);
        }
    }

    /**
     * to call dialog method in BaseActivity
     */
    @Override
    public void showDialog(String title, String message, String positiveButtonLabel, DialogInterface.OnClickListener positiveButton, String negativeButtonLabel, DialogInterface.OnClickListener negativeButton) {
        if (baseActivity != null) {
            baseActivity.showDialog(title, message, positiveButtonLabel, positiveButton, negativeButtonLabel, negativeButton);
        }
    }

    /**
     * to show info dialog in base activity
     */
    @Override
    public void showInfoDialog(String info) {
        if (baseActivity != null) {
            baseActivity.showInfoDialog(info);
        }
    }

    /**
     * to show info dialog with video in base activity
     */
    @Override
    public void showInfoDialogWithVideo(String info,String videoName) {
        if (baseActivity != null) {
            baseActivity.showInfoDialogWithVideo(info,videoName);
        }
    }

    /**
     * to call show progress dialog method in BaseActivity
     */
    @Override
    public void showProgressDialog() {
        if (baseActivity != null) {
            baseActivity.showProgressDialog();
        }
    }

    /**
     * to call hide progress dialog method in BaseActivity
     */
    @Override
    public void hideProgressDialog() {
        if (baseActivity != null) {
            baseActivity.hideProgressDialog();
        }
    }

    @Override
    public void onApiFailure() {
        if (baseActivity != null) {
            baseActivity.onApiFailure();
        }
    }

    @Override
    public void onLogoutUser() {
        if (baseActivity != null) {
            baseActivity.onLogoutUser();
        }
    }

    protected void goBackFromSurvey() {
        showDialog(null, getString(R.string.msg_go_back_survey), getString(R.string.cmn_go_back), (dialog, which) -> {
            getBaseActivity().myBackPress();
        }, getString(R.string.cmn_exit_survey), (dialog, which) -> {
            getBaseActivity().finish();
        });
    }
}
