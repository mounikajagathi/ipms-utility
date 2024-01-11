package in.ults.gisurvey.utils.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.databinding.ItemCommonFlexMultiSelectionBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

public class FlexRecyclerViewMultiSelectAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<CommonItem> dataList;
    private ArrayList<String> selectedData;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;
    private boolean isSurveyOpenEdit=true;

    public FlexRecyclerViewMultiSelectAdapter() {
        this.dataList = new ArrayList<>();
        this.selectedData = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommonFlexMultiSelectionBinding openSourceViewBinding = ItemCommonFlexMultiSelectionBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonFlexViewHolder(openSourceViewBinding);
    }

    public void addItems(List<CommonItem> repoList) {
        dataList.addAll(repoList);
    }

    public void clearItems() {
        dataList.clear();
    }

    public class CommonFlexViewHolder extends BaseViewHolder {

        private final ItemCommonFlexMultiSelectionBinding mBinding;
        private FlexRecycleViewModel itemViewModel;

        CommonFlexViewHolder(ItemCommonFlexMultiSelectionBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final CommonItem item = dataList.get(position);
            itemViewModel = new FlexRecycleViewModel(item);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
            if(!isSurveyOpenEdit){
                mBinding.tvContent.setEnabled(false);
            }
            mBinding.tvContent.setOnClickListener(v -> {
                if(recyclerViewItemClickListener!=null){
                    recyclerViewItemClickListener.onItemClick(position);
                }
            });
            mBinding.tvContent.setChecked(selectedData.contains(item.getContent()));
            mBinding.tvContent.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked && !selectedData.contains(buttonView.getText().toString())) {
                    selectedData.add(buttonView.getText().toString());
                } else if (!isChecked) {
                    selectedData.remove(buttonView.getText().toString());
                }
            });
        }
    }


    public ArrayList<String> getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(ArrayList<String> selectedData) {
        this.selectedData = selectedData;
        notifyDataSetChanged();
    }

    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }

}