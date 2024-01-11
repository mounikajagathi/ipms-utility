package in.ults.gisurvey.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.ActivityMainBinding;
import in.ults.gisurvey.databinding.NavHeaderMainBinding;
import in.ults.gisurvey.ui.about.AboutFragment;
import in.ults.gisurvey.ui.auth.AuthActivity;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.ui.main.completed.CompletedSurveyFragment;
import in.ults.gisurvey.ui.main.home.HomeFragment;
import in.ults.gisurvey.ui.main.partialsurvey.PartialSurveyFragment;
import in.ults.gisurvey.ui.main.profile.ProfileFragment;
import in.ults.gisurvey.ui.main.settings.SettingsFragment;
import in.ults.gisurvey.ui.main.surveyor.SurveyorDetailsFragment;
import in.ults.gisurvey.ui.main.surveyor.SurveyorFragment;
import in.ults.gisurvey.ui.main.syncgrid.SyncGridFragment;
import in.ults.gisurvey.ui.main.synclist.SyncListFragment;
import in.ults.gisurvey.ui.main.ward.WardSelectionFragment;
import in.ults.gisurvey.ui.report.ReportFragment;
import in.ults.gisurvey.ui.serverSurvey.detials.ServerSurveyDetailsFragment;
import in.ults.gisurvey.ui.serverSurvey.list.ServerSurveyFragment;
import in.ults.gisurvey.ui.survey.property.PropertyFragment;
import in.ults.gisurvey.ui.surveyPoint.SurveyPointFragment;

import static in.ults.gisurvey.utils.AppConstants.COMPLETED_LIST_TYPE_SYNC;
import static in.ults.gisurvey.utils.AppConstants.COMPLETED_LIST_TYPE_UNSYNC;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private MainViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(this, this);
    }

    @Override
    public MainViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        return viewModel;
    }


    @Override
    public void onBackPressed() {
        if (getViewDataBinding().drawerView.isDrawerOpen(GravityCompat.START)) {
            getViewDataBinding().drawerView.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }

    }

    /**
     * to set tool bar title
     *
     * @param toolbarTitle
     */
    @Override
    public void setToolbarTitle(String toolbarTitle) {
        if (getViewDataBinding().toolbar != null) {
            getViewDataBinding().toolbar.setTitle(toolbarTitle != null ? toolbarTitle : "");
        }
    }

    @Override
    public void openAuthActivity() {
        startActivity(AuthActivity.newIntent(this));
        finish();
    }

    @Override
    public void openReportFragment(boolean isVisible) {
        if(isVisible){
            showReportFragment(true);
        }
        else{
            showToast("sync your data");
        }
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        configureToolbar();
        setupNavMenu();
        viewModel.updateContent();
        showHomeFragment(false);
    }

    /**
     * to set tool bar
     */
    protected void configureToolbar() {
        setSupportActionBar(getViewDataBinding().toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, getViewDataBinding().drawerView, getViewDataBinding().toolbar, R.string.open_drawer, R.string.close_drawer);
        getViewDataBinding().drawerView.addDrawerListener(toggle);
        getViewDataBinding().drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.syncState();

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
                toggle.setDrawerIndicatorEnabled(true);
                toggle.setToolbarNavigationClickListener(toggle.getToolbarNavigationClickListener());
                getViewDataBinding().drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            } else {
                getViewDataBinding().drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                toggle.setDrawerIndicatorEnabled(false);
                toggle.setToolbarNavigationClickListener(v -> {
                        onBackPressed();
                });
            }
        });
    }

    /**
     * to set navigation menu
     */
    private void setupNavMenu() {
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, getViewDataBinding().navigationView, false);
        getViewDataBinding().navigationView.addHeaderView(navHeaderMainBinding.getRoot());
        navHeaderMainBinding.setViewModel(viewModel);

        getViewDataBinding().navigationView.setNavigationItemSelectedListener(
                item -> {
                    getViewDataBinding().drawerView.closeDrawer(GravityCompat.START);
                    switch (item.getItemId()) {
                        case R.id.navItemAbout:
                            showAboutFragment(true);
                            return true;
                        case R.id.navItemSettings:
                            showSettingsFragment(true);
                            return true;
                        case R.id.navItemCompletedSurvey:
                            viewModel.setCompleteListType(COMPLETED_LIST_TYPE_UNSYNC);
                            showCompletedFragment(true);
                            return true;
                        case R.id.navItemSyncededSurvey:
                            viewModel.setCompleteListType(COMPLETED_LIST_TYPE_SYNC);
                            showCompletedFragment(true);
                            return true;
                        case R.id.navItemFetchSurveyPoints:
                            if(viewModel.getWardNumber()!=null)
                                viewModel.fetchSurveyPoints(viewModel.getSelectedWardsInString());
                            else
                                showToast(getString(R.string.msg_select_ward_no_aert));
                            return true;
                        case R.id.navItemSurveyPoints:
                            showSurveyPointsFragment(true);
                            return true;
                        case R.id.navItemServerSurveyDetails:
                            if(viewModel.getSurveyPoints()!=null){
                                showServerSurveyFragment(true);
                            }else{
                                showToast(getString(R.string.no_data));
                            }
                            return true;
                        case R.id.navItemReport:
                            viewModel.checkReportView();
                            return true;
                        case R.id.navItemLogout:
                            onLogoutUser();
                            return true;
                        default:
                            return false;
                    }
                });
    }

    @Override
    protected void lockMenuDrawer() {
        super.lockMenuDrawer();
        if (getViewDataBinding().drawerView != null) {
            getViewDataBinding().drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    protected void unLockMenuDrawer() {
        super.unLockMenuDrawer();
        if (getViewDataBinding().drawerView != null) {
            getViewDataBinding().drawerView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    /**
     * to load home fragment
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showHomeFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, HomeFragment.newInstance(), HomeFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load partial survey fragment
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showPartialSurvey(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, PartialSurveyFragment.newInstance(), PartialSurveyFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load about fragment
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showAboutFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, AboutFragment.newInstance(), AboutFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load profile fragment
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showProfileFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ProfileFragment.newInstance(), PropertyFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load settings fragment
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showSettingsFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, SettingsFragment.newInstance(), SettingsFragment.TAG, true);
    }

    /**
     *
     */
    @Override
    public void onLogoutUser() {
        viewModel.logout();
    }

    /**
     * to load surveyor list
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showSurveyorList(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, SurveyorFragment.newInstance(), SurveyorFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load ward selection screen for getting survey points
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showWardSelectionFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, WardSelectionFragment.newInstance(), WardSelectionFragment.TAG, isAddedToBackStack);
    }
    /**
     * to load surveyor list
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showSurveyorDetailsFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, SurveyorDetailsFragment.newInstance(), SurveyorDetailsFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load server survey fragment
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showServerSurveyFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ServerSurveyFragment.newInstance(), ServerSurveyFragment.TAG, isAddedToBackStack);
    }


    /**
     * to load server survey details
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showServerSurveyDetailsFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ServerSurveyDetailsFragment.newInstance(), ServerSurveyDetailsFragment.TAG, isAddedToBackStack);
    }
    /**
     * to load survey points on map
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showSurveyPointsFragment(boolean isAddedToBackStack) {
        if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            showFragment(R.id.fragmentContainer, SurveyPointFragment.newInstance(), SurveyPointFragment.TAG, isAddedToBackStack);
        } else {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissionsSafely(permissions, 786);
        }

//        showFragment(R.id.fragmentContainer, SurveyPointFragment.newInstance(), SurveyPointFragment.TAG, isAddedToBackStack);
    }
    /**
     * to load completed list
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showCompletedFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, CompletedSurveyFragment.newInstance(), CompletedSurveyFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load sync list
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showSyncListFragment(boolean isAddedToBackStack, int selectedCount) {
        showFragment(R.id.fragmentContainer, SyncListFragment.newInstance(selectedCount), SyncListFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load sync grid
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showSyncGridFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, SyncGridFragment.newInstance(), SyncGridFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load report
     *
     * @param isAddedToBackStack
     */
    @Override
    public void showReportFragment(boolean isAddedToBackStack){
        showFragment(R.id.fragmentContainer, ReportFragment.newInstance(), ReportFragment.TAG, isAddedToBackStack);
    }
}
