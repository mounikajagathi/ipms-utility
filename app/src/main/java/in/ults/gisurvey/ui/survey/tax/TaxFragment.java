package in.ults.gisurvey.ui.survey.tax;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.databinding.FragmentTaxBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;

import static in.ults.gisurvey.utils.AppConstants.CLEAR;
import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class TaxFragment extends BaseFragment<FragmentTaxBinding, TaxViewModel> implements TaxNavigator {

    public static final String TAG = TaxFragment.class.getSimpleName();
    static final int TAX_NUMBER_ERROR = 1;
    static final int TAX_AMOUNT_ERROR = 2;
    static final int TAX_DATE_ERROR = 3;
    static final int TAX_ANNUL_TAX_AMOUNT_ERROR = 4;
    static final int COMMON_ERROR = 5;

    @Inject
    ViewModelProviderFactory factory;
    private TaxViewModel viewModel;


    public static TaxFragment newInstance() {
        Bundle args = new Bundle();
        TaxFragment fragment = new TaxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tax;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public TaxViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(TaxViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_tax);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        final int currentYear = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
        getViewDataBinding().etTaxYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    int yearData = Integer.parseInt(String.valueOf(s));
                    if (yearData > currentYear) {
                        viewModel.taxYear.setValue("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        viewModel.getCurrentSurveyProperty();

    }

    /**
     *
     */
    @Override
    public void onChooseYear() {
        Calendar newCalendar = Calendar.getInstance();
        String dateFormat = "dd MMMM yyyy";
        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        DatePickerDialog datePicker = new DatePickerDialog(getBaseActivity(), (view, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            viewModel.setTaxDate(sdfDate.format(newDate.getTime()));
            if (monthOfYear > 2) {
                viewModel.setTaxYear(year + " - " + (year + 1));
            } else {
                viewModel.setTaxYear((year - 1) + " - " + year);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());
        datePicker.show();
    }

    @Override
    public void showNANRMenu(View view) {
        switch (view.getId()) {
            case R.id.nrTaxNumber:
                PopupMenu popupMenuTaxNo = new PopupMenu(getBaseActivity(), getViewDataBinding().nrTaxNumber);
                popupMenuTaxNo.inflate(R.menu.na_nr_menu);
                popupMenuTaxNo.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.NR:
                            viewModel.taxNumber.setValue(AppConstants.NR_UPPERCASE);
                            break;
                        case R.id.NA:
                            viewModel.taxNumber.setValue(AppConstants.NA_UPPERCASE);
                            break;
                    }
                    popupMenuTaxNo.dismiss();
                    return false;
                });
                popupMenuTaxNo.show();
                break;
            case R.id.nrTaxAmount:
                PopupMenu popupMenutaxAmt = new PopupMenu(getBaseActivity(), getViewDataBinding().nrTaxAmount);
                popupMenutaxAmt.inflate(R.menu.na_nr_menu);
                popupMenutaxAmt.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.NR:
                            viewModel.taxAmount.setValue(AppConstants.NR_UPPERCASE);
                            break;
                        case R.id.NA:
                            viewModel.taxAmount.setValue(AppConstants.NA_UPPERCASE);
                            break;
                    }
                    popupMenutaxAmt.dismiss();
                    return false;
                });
                popupMenutaxAmt.show();
                break;
            case R.id.nrTaxDate:
                PopupMenu popupMenutaxDate = new PopupMenu(getBaseActivity(), getViewDataBinding().nrTaxDate);
                popupMenutaxDate.inflate(R.menu.na_nr_menu);
                popupMenutaxDate.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.NR:
                            viewModel.taxDate.setValue(AppConstants.NR_UPPERCASE);
                            viewModel.taxYear.setValue(AppConstants.NR_UPPERCASE);
                            break;
                        case R.id.NA:
                            viewModel.taxDate.setValue(AppConstants.NA_UPPERCASE);
                            viewModel.taxYear.setValue(AppConstants.NA_UPPERCASE);
                            break;
                    }
                    popupMenutaxDate.dismiss();
                    return false;
                });
                popupMenutaxDate.show();
                break;
            case R.id.nrTaxYear:
                PopupMenu popupMenutaxYear = new PopupMenu(getBaseActivity(), getViewDataBinding().nrTaxYear);
                popupMenutaxYear.inflate(R.menu.na_nr_menu);
                popupMenutaxYear.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.NR:
                            viewModel.taxDate.setValue(AppConstants.NR_UPPERCASE);
                            viewModel.taxYear.setValue(AppConstants.NR_UPPERCASE);
                            break;
                        case R.id.NA:
                            viewModel.taxDate.setValue(AppConstants.NA_UPPERCASE);
                            viewModel.taxYear.setValue(AppConstants.NA_UPPERCASE);
                            break;
                    }
                    popupMenutaxYear.dismiss();
                    return false;
                });
                popupMenutaxYear.show();
                break;
            case R.id.nrAnnualTax:
                PopupMenu popupMenuAnualtax = new PopupMenu(getBaseActivity(), getViewDataBinding().nrAnnualTax);
                popupMenuAnualtax.inflate(R.menu.na_nr_menu);
                popupMenuAnualtax.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.NR:
                            viewModel.taxAnnualAmount.setValue(AppConstants.NR_UPPERCASE);
                            break;
                        case R.id.NA:
                            viewModel.taxAnnualAmount.setValue(AppConstants.NA_UPPERCASE);
                            break;
                    }
                    popupMenuAnualtax.dismiss();
                    return false;
                });
                popupMenuAnualtax.show();
                break;
        }

    }

    /**
     * to validate fields and save tax details
     */
    @Override
    public void saveTaxDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String taxNumber = Objects.requireNonNull(getViewDataBinding().etTaxNumber.getText()).toString().trim();
        String taxAmount = Objects.requireNonNull(getViewDataBinding().etTaxAmount.getText()).toString().trim();
        String taxDate = Objects.requireNonNull(getViewDataBinding().etTaxDate.getText()).toString().trim();
        String taxYear = Objects.requireNonNull(getViewDataBinding().etTaxYear.getText()).toString().trim();
        String taxAnnualAmount = Objects.requireNonNull(getViewDataBinding().etTaxAnnual.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(taxNumber, taxAmount, taxDate, taxAnnualAmount)) {
                viewModel.saveTaxDetails(taxNumber, taxAmount, taxYear, taxDate, taxAnnualAmount,true);
            }

        }else {
            //Partial Saving
            //No need od validation
            viewModel.saveTaxDetails(taxNumber, taxAmount, taxYear, taxDate, taxAnnualAmount,false);
        }

    }

    /**
     * set validation error message
     *
     * @param code
     * @param error
     */
    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case TAX_NUMBER_ERROR:
                getViewDataBinding().layoutTaxNumber.setError(error);
                getViewDataBinding().layoutTaxNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxNumber, getViewDataBinding().layoutTaxNumber);
                break;
            case TAX_AMOUNT_ERROR:
                getViewDataBinding().layoutTaxAmount.setError(error);
                getViewDataBinding().layoutTaxAmount.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxAmount, getViewDataBinding().layoutTaxAmount);
                break;
            case TAX_DATE_ERROR:
                getViewDataBinding().layoutTaxDate.setError(error);
                getViewDataBinding().layoutTaxDate.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxDate, getViewDataBinding().layoutTaxDate);
                break;
            case TAX_ANNUL_TAX_AMOUNT_ERROR:
                getViewDataBinding().layoutAnnualTax.setError(error);
                getViewDataBinding().layoutAnnualTax.getParent().requestChildFocus
                        (getViewDataBinding().layoutAnnualTax, getViewDataBinding().layoutAnnualTax);
                break;
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
        }
    }
    @Override
    public void showNANROption() {
        PopupMenu popupMenu = new PopupMenu(getBaseActivity(), getViewDataBinding().nrTaxNumber);

        popupMenu.inflate(R.menu.na_nr_clear_menu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.NR:
                    //enable all other for typing
                    //Receipt number value will be NR
                    viewModel.taxNumber.setValue(AppConstants.NR_UPPERCASE);
                    enableAllFields();
                    break;
                case R.id.NA:
                    //disable all other for typing set all value as NA
                    //Receipt number value will be NR
                    viewModel.taxNumber.setValue(AppConstants.NA_UPPERCASE);
                    viewModel.taxAnnualAmount.setValue(AppConstants.NA_UPPERCASE);
                    viewModel.taxYear.setValue(AppConstants.NA_UPPERCASE);
                    viewModel.taxDate.setValue(AppConstants.NA_UPPERCASE);
                    viewModel.taxAmount.setValue(AppConstants.NA_UPPERCASE);
                    disableAllField();
                    break;
                case R.id.CLEAR:
//                    Clear and enable all fields and
                    viewModel.taxNumber.setValue("");
                    viewModel.taxAnnualAmount.setValue("");
                    viewModel.taxYear.setValue("");
                    viewModel.taxDate.setValue("");
                    viewModel.taxAmount.setValue("");
                    enableAllFields();
                    break;
            }
            popupMenu.dismiss();
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void setCachedData(Property property) {
        if(property.taxNumber.equalsIgnoreCase(AppConstants.NA_UPPERCASE)){
            disableAllField();
        }
    }

    private void enableAllFields() {
        getViewDataBinding().etTaxNumber.setEnabled(true);
        getViewDataBinding().etTaxAmount.setEnabled(true);
        getViewDataBinding().etTaxAnnual.setEnabled(true);
        getViewDataBinding().etTaxDate.setEnabled(true);
        getViewDataBinding().etTaxYear.setEnabled(true);
        getViewDataBinding().nrAnnualTax.setEnabled(true);
        getViewDataBinding().nrTaxAmount.setEnabled(true);
        getViewDataBinding().nrTaxDate.setEnabled(true);
        getViewDataBinding().nrTaxYear.setEnabled(true);
        getViewDataBinding().layoutTaxNumber.setAlpha(1);
        getViewDataBinding().layoutAnnualTax.setAlpha(1);
        getViewDataBinding().layoutTaxAmount.setAlpha(1);
        getViewDataBinding().layoutTaxDate.setAlpha(1);
        getViewDataBinding().layoutTaxYear.setAlpha(1);

    }

    private void disableAllField() {
        getViewDataBinding().etTaxNumber.setEnabled(false);
        getViewDataBinding().etTaxAmount.setEnabled(false);
        getViewDataBinding().etTaxAnnual.setEnabled(false);
        getViewDataBinding().etTaxDate.setEnabled(false);
        getViewDataBinding().etTaxYear.setEnabled(false);
        getViewDataBinding().nrAnnualTax.setEnabled(false);
        getViewDataBinding().nrTaxAmount.setEnabled(false);
        getViewDataBinding().nrTaxDate.setEnabled(false);
        getViewDataBinding().nrTaxYear.setEnabled(false);
        getViewDataBinding().layoutTaxNumber.setAlpha(.5f);
        getViewDataBinding().layoutAnnualTax.setAlpha(.5f);
        getViewDataBinding().layoutTaxAmount.setAlpha(.5f);
        getViewDataBinding().layoutTaxDate.setAlpha(.5f);
        getViewDataBinding().layoutTaxYear.setAlpha(.5f);

    }
        @Override
        public void clearValidationErrors () {
            getViewDataBinding().layoutTaxNumber.setErrorEnabled(false);
            getViewDataBinding().layoutTaxAmount.setErrorEnabled(false);
            getViewDataBinding().layoutTaxDate.setErrorEnabled(false);
            getViewDataBinding().layoutAnnualTax.setErrorEnabled(false);
        }


        @Override
        public void navigateToImageDetails () {
            getBaseActivity().showImageFragment(true);
        }


        @Override
        public void navigateToEstablishmentPage () {
            getBaseActivity().showEstablishmentFragment(true);
        }

        @Override
        public void navigateToMemberPage () {
            getBaseActivity().showMemberFragment(true);
        }

        @Override
        public void navigateToLiveHoodPage () {
            getBaseActivity().showLiveHoodFragment(true);
        }

        @Override
        public void navigateToBuildingPage () {
            getBaseActivity().showBuildingFragment(true);
        }

        @Override
        public void navigateToMorePage () {
            getBaseActivity().showMoreFragment(true);
        }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
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