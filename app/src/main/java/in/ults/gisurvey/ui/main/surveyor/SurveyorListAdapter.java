package in.ults.gisurvey.ui.main.surveyor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.api.Subordinates;
import in.ults.gisurvey.databinding.ItemSurveyorListBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;

public class SurveyorListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Subordinates> dataList;

    public SurveyorListAdapter() {
        this.dataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSurveyorListBinding itemViewBinding = ItemSurveyorListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonFlexViewHolder(itemViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public Subordinates getItem(int pos) {
        return dataList.get(pos);
    }

    public void clearItems() {
        dataList.clear();
    }

    public void addItems(List<Subordinates> repoList) {
        dataList.addAll(repoList);
        notifyDataSetChanged();
    }

    public class CommonFlexViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemSurveyorListBinding mBinding;
        private SurveyorListViewModel itemViewModel;

        public CommonFlexViewHolder(ItemSurveyorListBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        @Override
        public void onBind(int position) {
            final Subordinates item = dataList.get(position);
            itemViewModel = new SurveyorListViewModel(item);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
