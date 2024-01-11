package in.ults.gisurvey.ui.serverSurvey.list;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentServerSurveyBinding;
import in.ults.gisurvey.ui.base.BaseFragment;


public class ServerSurveyFragment extends BaseFragment<FragmentServerSurveyBinding, ServerSurveyViewModel> implements ServerSurveyNavigator {

    public static final String TAG = ServerSurveyFragment.class.getSimpleName();
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    ServerSurveyListAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    private ServerSurveyViewModel viewModel;

    public static ServerSurveyFragment newInstance() {
        Bundle args = new Bundle();
        ServerSurveyFragment fragment = new ServerSurveyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_server_survey;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_server_survey);
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }

    @Override
    public ServerSurveyViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this,factory).get(ServerSurveyViewModel.class);
        return viewModel;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        setHasOptionsMenu(true);
        getViewDataBinding().rvCompletedSurvey.setLayoutManager(layoutManager);
        getViewDataBinding().rvCompletedSurvey.setAdapter(adapter);
        adapter.setViewItemClickListener(position -> viewModel.onItemClick(adapter.getItem(position)));
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.getServerSurveyFromPref();
    }


    @Override
    public void navigateToDetails() {
        getBaseActivity().showServerSurveyDetailsFragment(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.survey_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

//        EditText editText = (EditText) searchView.findViewById( androidx.appcompat.R.id.search_src_text);
//        editText.setTextColor(Color.WHITE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    viewModel.getServerSurveyFromPref();
                    return false;
                }
            });

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    viewModel.setSearchResult(newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    viewModel.setSearchResult(query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

}