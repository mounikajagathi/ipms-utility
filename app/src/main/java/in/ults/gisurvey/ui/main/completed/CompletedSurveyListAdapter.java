package in.ults.gisurvey.ui.main.completed;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.databinding.ItemCompletedSurveryListBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

public class CompletedSurveyListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<SurveyWithProperty> dataList;

    public RecyclerViewItemClickListener viewItemClickListener;


    public CompletedSurveyListAdapter() {
        this.dataList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public SurveyWithProperty getItem(int pos) {
        return dataList.get(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompletedSurveryListBinding itemViewBinding = ItemCompletedSurveryListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonFlexViewHolder(itemViewBinding);
    }

    public void addItems(List<SurveyWithProperty> repoList) {
        dataList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        dataList.clear();
    }

    public class CommonFlexViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final ItemCompletedSurveryListBinding mBinding;
        private CompletedSurveyListViewModel itemViewModel;

        CommonFlexViewHolder(ItemCompletedSurveryListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final SurveyWithProperty item = dataList.get(position);
            itemViewModel = new CompletedSurveyListViewModel(item);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
            mBinding.getRoot().setOnClickListener(this);
            if(item.getPointStatus().equalsIgnoreCase(AppConstants.POINT_STATUS_UNWANTED)){
                mBinding.txtPointStatus.setTextColor(Color.RED);
            }else if(item.getPointStatus().equalsIgnoreCase(AppConstants.POINT_STATUS_LANDMARK)){
                mBinding.txtPointStatus.setTextColor(Color.MAGENTA);
            }else {
                mBinding.txtPointStatus.setTextColor(Color.BLUE);
            }
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