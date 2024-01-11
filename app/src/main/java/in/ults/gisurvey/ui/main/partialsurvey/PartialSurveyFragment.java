package in.ults.gisurvey.ui.main.partialsurvey;


import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.databinding.FragmentPartialSurveyBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.SurveyActivity;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemLongClickListener;


public class PartialSurveyFragment extends BaseFragment<FragmentPartialSurveyBinding, PartialSurveyViewModel> implements PartialSurveyNavigator {
    public static final String TAG = PartialSurveyFragment.class.getSimpleName();

    private PartialSurveyViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    PartialSurveyListAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    public static PartialSurveyFragment newInstance() {
        Bundle args = new Bundle();
        PartialSurveyFragment fragment = new PartialSurveyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_partial_survey;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public PartialSurveyViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(PartialSurveyViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_survey_list);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().rvPartialSurvey.setLayoutManager(layoutManager);
        getViewDataBinding().rvPartialSurvey.setAdapter(adapter);
        adapter.setViewItemClickListener(position -> viewModel.saveSurveyId(adapter.getItem(position).getSurveyID()));
        adapter.setViewItemLongClickListener((v, position) -> {
            PopupMenu popup = new PopupMenu(getBaseActivity(), v);
            popup.inflate(R.menu.delete_menu);
            popup.setOnMenuItemClickListener(item1 -> {
                if (item1.getItemId() == R.id.delete) {
                    viewModel.deleteSurvey(position);
                }
                popup.dismiss();
                return false;
            });
            popup.show();
        });
    }

    @Override
    public void setSurveyData(List<Survey> data) {
        viewModel.setPartialSurveyList(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getPendingSurveyList();
    }

    @Override
    public void navigateToNewSurvey() {
        startActivity(SurveyActivity.newIntent(getBaseActivity(), false));
    }

    @Override
    public void navigateToPartialSurvey() {
        startActivity(SurveyActivity.newIntent(getBaseActivity(), true));
    }
}