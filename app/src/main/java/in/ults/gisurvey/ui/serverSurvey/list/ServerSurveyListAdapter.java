package in.ults.gisurvey.ui.serverSurvey.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.api.SurveyPoints;
import in.ults.gisurvey.databinding.ItemServerSurveryListBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

public class ServerSurveyListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<SurveyPoints> dataList;

    public RecyclerViewItemClickListener viewItemClickListener;


    public ServerSurveyListAdapter() {
        this.dataList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public SurveyPoints getItem(int pos) {
        return dataList.get(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemServerSurveryListBinding itemViewBinding = ItemServerSurveryListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonFlexViewHolder(itemViewBinding);
    }

    public void addItems(List<SurveyPoints> repoList) {
        dataList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        dataList.clear();
    }

    public class CommonFlexViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final ItemServerSurveryListBinding mBinding;
        private ServerSurveyListViewModel itemViewModel;

        CommonFlexViewHolder(ItemServerSurveryListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final SurveyPoints item = dataList.get(position);
            itemViewModel = new ServerSurveyListViewModel(item);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (viewItemClickListener != null) {
                viewItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }


    public RecyclerViewItemClickListener getViewItemClickListener() {
        return viewItemClickListener;
    }

    public void setViewItemClickListener(RecyclerViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }
}