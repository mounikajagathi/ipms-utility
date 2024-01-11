package in.ults.gisurvey.ui.survey.groundfloorselection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import in.ults.gisurvey.R;
import in.ults.gisurvey.databinding.ItemFloorLayoutBinding;
import in.ults.gisurvey.ui.base.BaseViewHolder;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

public class GroundFloorSelectionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private int floorCount;
    private int selectedPosition = -1;
    private Activity activity;
    private RecyclerViewItemClickListener clickListener;
    private boolean isSurveyOpenEdit=true;

    GroundFloorSelectionAdapter(Activity activity, int floorCount) {
        this.floorCount = floorCount;
        this.activity = activity;
    }

    public void updateFloorCount(int selectedPosition, int floorCount) {
        if (selectedPosition != -1 && floorCount > selectedPosition) {
            this.selectedPosition = selectedPosition;
        }
        this.floorCount = floorCount;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return floorCount + 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFloorLayoutBinding openSourceViewBinding = ItemFloorLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GroundFloorSelectionViewHolder(openSourceViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    int getSelectedPosition() {
        return selectedPosition;
    }

    public class GroundFloorSelectionViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final ItemFloorLayoutBinding mBinding;
        private GroundFloorSelectionItemViewModel itemViewModel;

        GroundFloorSelectionViewHolder(ItemFloorLayoutBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onBind(int position) {
            itemViewModel = new GroundFloorSelectionItemViewModel();
            int txtPos = position;
            if (selectedPosition == -1) {
                if (position == 0) {
                    itemViewModel.setFloorImageType(R.drawable.ground_floor);
                } else if (position == floorCount) {
                    itemViewModel.setFloorImageType(R.drawable.floor_top);
                } else {
                    itemViewModel.setFloorImageType(R.drawable.floors);
                }
            } else if (selectedPosition != floorCount) {
                if (position == selectedPosition) {
                    txtPos = 0;
                    itemViewModel.setFloorImageType(R.drawable.ground_floor);
                    itemViewModel.setIsSelected(true);
                } else if (position > selectedPosition) {
                    txtPos = (position - selectedPosition);
                    itemViewModel.setIsSelected(false);
                    if (position == floorCount) {
                        itemViewModel.setFloorImageType(R.drawable.floor_top);
                    } else {
                        itemViewModel.setFloorImageType(R.drawable.floors);
                    }
                } else {
                    txtPos = 0 - (selectedPosition - position);
                    itemViewModel.setIsSelected(false);
                    if (position == 0) {
                        itemViewModel.setFloorImageType(R.drawable.base_bottom);
                    } else {
                        itemViewModel.setFloorImageType(R.drawable.base_floors);
                    }
                }
            }
            if (position != floorCount) {
                if (txtPos == 0) {
                    if(selectedPosition==-1){
                        itemViewModel.setFloorNumber(String.format(Locale.ENGLISH, "%s %d", activity.getString(R.string.floor_number_txt), txtPos));
                    }else{
                        itemViewModel.setFloorNumber(activity.getString(R.string.floor_number_ground_floor_txt));
                    }

                } else if (txtPos < 0) {
                    itemViewModel.setFloorNumber(String.format(Locale.ENGLISH, "%s %s", activity.getString(R.string.floor_basement_number_txt), String.valueOf(txtPos).replaceAll("-", "")));
                } else {
                    itemViewModel.setFloorNumber(String.format(Locale.ENGLISH, "%s %d", activity.getString(R.string.floor_number_txt), txtPos));
                }
            }
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {

            if (isSurveyOpenEdit) {
                if (getAdapterPosition() < floorCount) {
                    selectedPosition = getAdapterPosition();
                    if (clickListener != null) {
                        clickListener.onItemClick(getAdapterPosition());
                    }
                    notifyDataSetChanged();
                }
            }
        }
    }

    public void setClickListener(RecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }
}
