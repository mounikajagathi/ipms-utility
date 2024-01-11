package in.ults.gisurvey.utils.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.databinding.ItemCommonFlexSingleSelectionBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

public class FlexRecyclerViewSingleSelectAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<CommonItem> dataList;


    private int selectedPosition = -1;
    private TextView previousSelectedView = null;
    private String selectedContent = "";
    private boolean isSurveyOpenEdit=true;

    private RecyclerViewItemClickListener viewItemClickListener;

    public FlexRecyclerViewSingleSelectAdapter() {
        this.dataList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    public RecyclerViewItemClickListener getViewItemClickListener() {
        return viewItemClickListener;
    }

    public void setViewItemClickListener(RecyclerViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommonFlexSingleSelectionBinding openSourceViewBinding = ItemCommonFlexSingleSelectionBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonFlexViewHolder(openSourceViewBinding);
    }

    public void addItems(List<CommonItem> repoList) {
        dataList.addAll(repoList);
    }

    public void clearItems() {
        dataList.clear();
    }

    public void resetItems() {
        selectedPosition = -1;
        previousSelectedView = null;
        selectedContent = "";
    }

    public void refreshData() {
        notifyDataSetChanged();
    }


    public class CommonFlexViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final ItemCommonFlexSingleSelectionBinding mBinding;
        private FlexRecycleViewModel itemViewModel;

        CommonFlexViewHolder(ItemCommonFlexSingleSelectionBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final CommonItem item = dataList.get(position);
            itemViewModel = new FlexRecycleViewModel(item);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
            mBinding.tvContent.setOnClickListener(this);
            if (item.getContent().equalsIgnoreCase(selectedContent)) {
                mBinding.tvContent.setEnabled(false);
                previousSelectedView = mBinding.tvContent;
                selectedPosition = getAdapterPosition();
                if (viewItemClickListener != null) {
                    viewItemClickListener.onItemClick(getAdapterPosition());
                }
            } else {
                mBinding.tvContent.setEnabled(true);
            }
        }

        @Override
        public void onClick(View view) {
            if(isSurveyOpenEdit) {
                mBinding.tvContent.setEnabled(false);
                if (previousSelectedView != null) {
                    previousSelectedView.setEnabled(true);
                }
                previousSelectedView = mBinding.tvContent;
                selectedPosition = getAdapterPosition();
                selectedContent = mBinding.tvContent.getText().toString();
                if (viewItemClickListener != null) {
                    viewItemClickListener.onItemClick(getAdapterPosition());
                }
            }
        }
    }

    public void clearSelectedItem(){
        selectedPosition = -1;
        previousSelectedView = null;
        selectedContent = "";
        notifyDataSetChanged();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public String getSelectedContent() {
        return selectedContent;
    }

    public void setSelectedContent(String selectedContent) {
        this.selectedContent = selectedContent;
        notifyDataSetChanged();
    }

    public List<CommonItem> getDataList() {
        return dataList;
    }

    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }

}