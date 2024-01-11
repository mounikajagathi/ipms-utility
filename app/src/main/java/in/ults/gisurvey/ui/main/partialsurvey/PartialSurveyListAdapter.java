package in.ults.gisurvey.ui.main.partialsurvey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.databinding.ItemPartialSurveryListBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemLongClickListener;

public class PartialSurveyListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Survey> dataList;

    public RecyclerViewItemClickListener viewItemClickListener;
    public RecyclerViewItemLongClickListener viewItemLongClickListener;


    public PartialSurveyListAdapter() {
        this.dataList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public Survey getItem(int pos) {
        return dataList.get(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPartialSurveryListBinding itemViewBinding = ItemPartialSurveryListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonFlexViewHolder(itemViewBinding);
    }

    public void addItems(List<Survey> repoList) {
        dataList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        dataList.clear();
    }

    public class CommonFlexViewHolder extends BaseViewHolder implements View.OnClickListener,View.OnLongClickListener {

        private final ItemPartialSurveryListBinding mBinding;
        private PartialSurveyListViewModel itemViewModel;

        CommonFlexViewHolder(ItemPartialSurveryListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Survey item = dataList.get(position);
            itemViewModel = new PartialSurveyListViewModel(item);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
            mBinding.getRoot().setOnClickListener(this);
            mBinding.getRoot().setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (viewItemClickListener != null) {
                viewItemClickListener.onItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (viewItemLongClickListener != null) {
                viewItemLongClickListener.onItemLongClick(v,getAdapterPosition());
            }
            return false;
        }
    }


    public RecyclerViewItemClickListener getViewItemClickListener() {
        return viewItemClickListener;
    }

    public void setViewItemClickListener(RecyclerViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }

    public void setViewItemLongClickListener(RecyclerViewItemLongClickListener viewItemLongClickListener) {
        this.viewItemLongClickListener = viewItemLongClickListener;
    }
}