package in.ults.gisurvey.ui.survey.surveygrid;

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
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemDeletionListener;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemEditListener;

public class SurveyGridAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<GridItem> dataList;
    private Activity activity;
    private RecyclerViewItemClickListener viewItemClickListener;
    private RecyclerViewItemDeletionListener viewItemDeletionListener;
    private RecyclerViewItemEditListener viewItemEditListener;
    private Set<String> completedSurvey;
    private String surveyId;
    private boolean isSurveyOpenEdit=true;
    private boolean syncStatus=false;



    SurveyGridAdapter(Activity activity) {
        this.dataList = new ArrayList<>();
        this.activity = activity;
        this.completedSurvey = new HashSet<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPropertyLayoutBinding layoutBinding = ItemPropertyLayoutBinding
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

    public void addItems(int count) {
        for (int i = 0; i < count; i++) {
            dataList.add(new GridItem());
        }
        notifyDataSetChanged();
    }

    public void clearItems() {
        dataList.clear();
    }

    public class CommonGridViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final ItemPropertyLayoutBinding binding;
        private SurveyGridAdapterViewModel model;

        CommonGridViewHolder(ItemPropertyLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final GridItem item = dataList.get(position);
            String propertyId = surveyId + "P" + (position + 1);
            item.setId(propertyId);
            item.setContent(activity.getResources().getString(R.string.property_property) + " " + (position + 1));
            item.setCompletedStatus(completedSurvey.contains(item.getId()));
            item.setEditVisible(syncStatus);
            item.setSurveyOpenEdit(isSurveyOpenEdit);
            model = new SurveyGridAdapterViewModel(item);
            binding.setViewModel(model);
            binding.executePendingBindings();
            binding.imgCopyPropertyId.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("property_id", binding.txtPropertyID.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(activity, "Property ID Copied Successfully", Toast.LENGTH_SHORT).show();
            });
            binding.imgDeleteProperty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isSurveyOpenEdit)
                        viewItemDeletionListener.onItemDeletion(getAdapterPosition());
                }
            });
            binding.imgEditProperty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        viewItemEditListener.onItemEdit(getAdapterPosition());
                }
            });
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
    void setViewItemDeleetionListener(RecyclerViewItemDeletionListener viewItemDeletionListener) {
        this.viewItemDeletionListener = viewItemDeletionListener;
    }
     void setViewItemEditListener(RecyclerViewItemEditListener viewItemEditListener) {
        this.viewItemEditListener = viewItemEditListener;
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
