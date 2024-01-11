package in.ults.gisurvey.ui.main.utility.home;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.items.UtilityGridItem;
import in.ults.gisurvey.databinding.ItemPropertyLayoutBinding;
import in.ults.gisurvey.databinding.ItemUtilityGridBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemDeletionListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemEditListener;

public class UtilityGridAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<UtilityGridItem> dataList;
    private Activity activity;
    private RecyclerViewItemClickListener viewItemClickListener;



    public UtilityGridAdapter(Activity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;
    }

    void addItems(List<UtilityGridItem> dataList){
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUtilityGridBinding layoutBinding = ItemUtilityGridBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonGridViewHolder(layoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CommonGridViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemUtilityGridBinding binding;
        private UtilityGridAdapterViewModel model;

        CommonGridViewHolder(ItemUtilityGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final UtilityGridItem item = dataList.get(position);
            model = new UtilityGridAdapterViewModel(item);
            binding.setViewModel(model);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (viewItemClickListener != null) {
                viewItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    List<UtilityGridItem> getDataList() {
        return dataList;
    }

    void setViewItemClickListener(RecyclerViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }
}
