package in.ults.gisurvey.ui.survey.selection;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
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
import in.ults.gisurvey.databinding.ItemScreenSelectionGridLayoutBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridAdapterViewModel;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemDeletionListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemEditListener;

public class ScreenSelectionGridAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<GridItem> dataList;
    private Activity activity;
    private RecyclerViewItemClickListener viewItemClickListener;
    private Set<String> completedSurvey;
    private String surveyId;
    private boolean isSurveyOpenEdit=true;
    private boolean syncStatus=false;



    ScreenSelectionGridAdapter(Activity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;
        this.completedSurvey = new HashSet<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScreenSelectionGridLayoutBinding layoutBinding = ItemScreenSelectionGridLayoutBinding
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

    public void addItems(List<GridItem> repoList) {
        dataList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        dataList.clear();
    }

    public class CommonGridViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemScreenSelectionGridLayoutBinding binding;
        private ScreenSelectionGridAdapterViewModel model;

        CommonGridViewHolder(ItemScreenSelectionGridLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final GridItem item = dataList.get(position);
            model = new ScreenSelectionGridAdapterViewModel(item);
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

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }


    List<GridItem> getDataList() {
        return dataList;
    }

    void setViewItemClickListener(RecyclerViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }

    public void setCompletedSurvey(Set<String> completedSurvey) {
        if (completedSurvey != null) {
            this.completedSurvey = completedSurvey;
        } else {
            this.completedSurvey = new HashSet<>();
        }
        notifyDataSetChanged();
    }

    /**
     * This is to limit deletion click if it opened for view
     * @param status
     */
    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }

    public void setSurveySyncStatus(boolean status) {
        this.syncStatus = status;
    }
}
