package in.ults.gisurvey.ui.main.syncgrid;

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
import in.ults.gisurvey.data.model.items.GridItem;
import in.ults.gisurvey.databinding.ItemPropertyLayoutBinding;
import in.ults.gisurvey.databinding.ItemSyncGridBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridAdapterViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

public class SyncGridAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Activity activity;
    private RecyclerViewItemClickListener viewItemClickListener;
    private int gridCount;
    private int totalSurvey;


    SyncGridAdapter(Activity activity) {
        this.gridCount = 0;
        this.totalSurvey = 0;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSyncGridBinding layoutBinding = ItemSyncGridBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommonGridViewHolder(layoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return gridCount;
    }


    public void setTotalSurveyCount(int count){
        int surveyCount = count / AppConstants.SYNC_COUNT;
        int surveyReminder = count % AppConstants.SYNC_COUNT;
        if (surveyReminder == 0) {
            gridCount = surveyCount;
        } else {
            gridCount = surveyCount + 1;
        }
        this.totalSurvey = count;
        notifyDataSetChanged();
    }

    public void clearItems() {
        gridCount = 0;
        totalSurvey = 0;
    }

    public class CommonGridViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemSyncGridBinding binding;
        private SyncGridAdapterViewModel model;

        CommonGridViewHolder(ItemSyncGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            model = new SyncGridAdapterViewModel(position, totalSurvey);
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

    void setViewItemClickListener(RecyclerViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }
}
