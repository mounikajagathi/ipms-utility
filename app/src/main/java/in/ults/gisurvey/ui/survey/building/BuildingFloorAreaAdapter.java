package in.ults.gisurvey.ui.survey.building;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ults.gisurvey.utils.IPMSEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.IPMSSpinner;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class BuildingFloorAreaAdapter extends RecyclerView.Adapter<BuildingFloorAreaAdapter.MyViewHolder> {

    private ArrayList<BuildingDetailsFloorAreaItem> dataList;
    private Set<String> selectedSet;
    private BaseActivity activity;
    private boolean validation;
    private final ArrayList<CommonItem> floorNumberData;
    private BuildingFloorAreaListener listener;
    private boolean isSurveyOpenEdit=true;


    BuildingFloorAreaAdapter(BaseActivity activity) {
        this.activity = activity;
        this.dataList = new ArrayList<>();
        this.selectedSet = new HashSet<>();
        this.floorNumberData = new ArrayList<>();
        this.validation = false;
    }

    public void setRecyclerViewTypingListener(BuildingFloorAreaListener typeListener) {
        this.listener = typeListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        in.ults.gisurvey.utils.IPMSEditText floorArea;
        IPMSSpinner floorNumber;
        TextInputLayout layoutFloorArea;
        TextInputLayout layoutFloorNumber;

        MyViewHolder(View view) {
            super(view);
            if (!isSurveyOpenEdit) {
                disableChildView((ViewGroup) view);
            }
            floorNumber = view.findViewById(R.id.etRoofFloorNumber);
            floorArea = view.findViewById(R.id.etFloorArea);
            layoutFloorNumber = view.findViewById(R.id.layoutFloorNumber);
            layoutFloorArea = view.findViewById(R.id.layoutFloorArea);
            floorArea.setOnFocusChangeListener((v, hasFocus) -> floorArea.post(() -> {
                if (hasFocus) {
                    new Handler().postDelayed(() -> floorArea.requestFocus(), 300);
                }
            }));
            floorNumber.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_floor_area_view, floorNumberData));
            floorArea.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (dataList != null) {
                        dataList.get(getAdapterPosition()).setArea(Objects.requireNonNull(floorArea.getText()).toString());
                        if (floorArea.getText().toString().length() != 0) {
                            try {
                                listener.onItemClick();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            floorNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        String roofTypeData = floorNumber.getText().toString();
                        selectedSet.remove(dataList.get(getAdapterPosition()).getFloorNumberDispaly());
                        if (selectedSet.contains(roofTypeData)) {
                            if (dataList != null) {
                                dataList.get(getAdapterPosition()).setFloorNumber("");
                                dataList.get(getAdapterPosition()).setFloorNumberDispaly("");
                            }
                            floorNumber.setText("");
                            activity.showToast(R.string.error_floor_number_already_selected);
                        } else {
                            if (dataList != null) {
                                for(CommonItem c:floorNumberData){
                                    if(c.getContent().equalsIgnoreCase(roofTypeData)) {
                                        dataList.get(getAdapterPosition()).setFloorNumber(c.getSubContent());
                                    }
                                }
                                dataList.get(getAdapterPosition()).setFloorNumberDispaly(roofTypeData);
                            }
                            selectedSet.add(roofTypeData);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            view.findViewById(R.id.nrFloorArea).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorArea.setText(AppConstants.NR_UPPERCASE);
                }
            });
        }
    }

    @NonNull
    @Override
    public BuildingFloorAreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_floor_area_view, parent, false);

        return new BuildingFloorAreaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingFloorAreaAdapter.MyViewHolder holder, int position) {
        BuildingDetailsFloorAreaItem item = getDataList().get(position);
        holder.floorArea.setText(item.getArea());
//        holder.floorNumber.setText(item.getFloorNumber());
        holder.floorNumber.setText(item.getFloorNumberDispaly());
        if (validation) {
            if (item.getFloorNumberDispaly().length() == 0) {
                holder.layoutFloorNumber.setError(activity.getString(R.string.error_floor_number));
            } else {
                holder.layoutFloorNumber.setErrorEnabled(false);
            }
            if (item.getArea().length() == 0 || (!item.getArea().equalsIgnoreCase(AppConstants.NR_UPPERCASE) && Double.parseDouble(item.getArea()) == 0)) {
                holder.layoutFloorArea.setError(activity.getString(R.string.error_floor_area));
            } else {
                holder.layoutFloorArea.setErrorEnabled(false);
            }
        } else {
            holder.layoutFloorNumber.setErrorEnabled(false);
            holder.layoutFloorArea.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    ArrayList<BuildingDetailsFloorAreaItem> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<BuildingDetailsFloorAreaItem> dataList) {
        this.dataList = dataList;
        refresh(false);
    }


    void setValidation(boolean validation) {
        refresh(validation);
    }

    private void refresh(boolean isValidation) {
        this.validation = isValidation;
        this.selectedSet.clear();
        notifyDataSetChanged();
    }

    void addNewItem() {
        if (dataList != null) {
            dataList.add(new BuildingDetailsFloorAreaItem());
            refresh(false);
        }
    }

    void removeItem() {
        if (dataList != null && dataList.size() > 0) {
            dataList.remove(dataList.size() - 1);
            refresh(false);
        }
    }

    void setFloorCount(int floorCount, int groundFloor) {
        floorNumberData.clear();
        for (int i = floorCount; i >= 0; i--) {
            CommonItem item = new CommonItem();
            int count = i;
            item.setId(i);
            String floorVal = "";
            if (groundFloor > 0) {
                if (count == groundFloor) {
                    count = 0;
                    floorVal = activity.getString(R.string.floor_number_ground_floor_txt);
                } else if (count > groundFloor) {
                    count = (count - groundFloor);
                    floorVal = activity.getString(R.string.floor_number_txt) +  count;
                } else {
                    count = 0 - (groundFloor - count);
                    floorVal = activity.getString(R.string.floor_basement_number_txt) + String.valueOf(count).replaceAll("-", "");
                }
            }else{
                if (floorCount == 0){
                    floorVal = activity.getString(R.string.floor_number_ground_floor_txt);
                }else{
                    if(i==0)
                        floorVal = activity.getString(R.string.floor_number_ground_floor_txt);
                    else
                        floorVal = activity.getString(R.string.floor_number_txt) + count;
                }

            }
             item.setContent(floorVal);
            item.setSubContent(String.valueOf(count));
            floorNumberData.add(item);
        }
        notifyDataSetChanged();
    }
    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }
//    public String floorInInteger(String floor){
//        String numval="";
//        if(floor.toLowerCase().trim().equalsIgnoreCase(activity.getString(R.string.floor_number_ground_floor_number).toLowerCase().trim())){
//            numval="0";
//        }else if(floor.toLowerCase().trim().contains(activity.getString(R.string.floor_basement_number_txt).toLowerCase().trim())){
//            numval="-" + floor.split(activity.getString(R.string.floor_basement_number_txt))[1].trim();
//        }else if(floor.toLowerCase().trim().contains(activity.getString(R.string.floor_number_txt).toLowerCase().trim())) {
//            numval=floor.split(activity.getString(R.string.floor_number_txt))[1].trim();
//        }
//        return numval;
//    }
}