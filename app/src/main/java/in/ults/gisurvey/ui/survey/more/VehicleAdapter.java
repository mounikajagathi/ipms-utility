package in.ults.gisurvey.ui.survey.more;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.db.VehicleDetailsItem;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.IPMSSpinner;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {

    private ArrayList<VehicleDetailsItem> dataList;
    private Set<String> selectedSet;
    private BaseActivity activity;
    private boolean validation;
    private boolean isSurveyOpenEdit=true;

    VehicleAdapter(BaseActivity activity) {
        this.activity = activity;
        this.dataList = new ArrayList<>();
        this.selectedSet = new HashSet<>();
        this.validation = false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        IPMSSpinner vehicleType;
        IPMSSpinner vehicleUsage;

        TextInputLayout layoutVehicleUsage;
        TextInputLayout layoutVehicleType;

        ConstraintLayout layoutContainer;

        MyViewHolder(View view) {
            super(view);
            vehicleType = view.findViewById(R.id.etVehicleType);
            vehicleUsage = view.findViewById(R.id.etVehicleUsage);
            layoutVehicleType = view.findViewById(R.id.layoutVehicleType);
            layoutVehicleUsage = view.findViewById(R.id.layoutVehicleUsage);
            layoutContainer=view.findViewById(R.id.layoutContainer);

            ArrayList<CommonItem> vehicleTypeData = IPMSApp.getAppInstance().getLocMainItem().getVehicleType();
            vehicleType.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_vehicle_view, vehicleTypeData));
            ArrayList<CommonItem> vehicleUsageData = IPMSApp.getAppInstance().getLocMainItem().getVehicleUsage();
            vehicleUsage.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_vehicle_view, vehicleUsageData));


            vehicleType.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (dataList != null) {
                        dataList.get(getAdapterPosition()).setVehicleType(Objects.requireNonNull(vehicleType.getText()).toString());
                    }
                    if(dataList.get(getAdapterPosition()).getVehicleType().equalsIgnoreCase(AppConstants.TWO_WHEELER)){
                        vehicleUsage.setText(AppConstants.NON_TAXI);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            vehicleUsage.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (dataList != null) {
                        dataList.get(getAdapterPosition()).setVehicleUsage(Objects.requireNonNull(vehicleUsage.getText()).toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            view.findViewById(R.id.btnVehicleTypeInfo).setOnClickListener(infoClick);
            view.findViewById(R.id.btnVehicleUsageInfo).setOnClickListener(infoClick);


        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehicle_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VehicleDetailsItem item = getDataList().get(position);
        if (!isSurveyOpenEdit) {
            disableChildView(holder.layoutContainer);
        }
        holder.vehicleType.setText(item.getVehicleType());
        holder.vehicleUsage.setText(item.getVehicleUsage());
        if (validation) {
            if (item.getVehicleType().length() == 0) {
                holder.layoutVehicleType.setError(activity.getString(R.string.error_vehicle_type_item));
            } else {
                holder.layoutVehicleType.setErrorEnabled(false);
            }
            if (item.getVehicleUsage().length() == 0) {
                holder.layoutVehicleUsage.setError(activity.getString(R.string.error_vehicle_usage_item));
            } else {
                holder.layoutVehicleUsage.setErrorEnabled(false);
            }
        } else {
            holder.layoutVehicleType.setErrorEnabled(false);
            holder.layoutVehicleUsage.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    ArrayList<VehicleDetailsItem> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<VehicleDetailsItem> dataList) {
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
            dataList.add(new VehicleDetailsItem());
            refresh(false);
        }
    }

    void removeItem() {
        if (dataList != null && dataList.size() > 0) {
            dataList.remove(dataList.size() - 1);
            refresh(false);
        }
    }

    public View.OnClickListener infoClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnVehicleTypeInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_vehicle_type), InfoVideoNames.MORE_DETAILS_VEHICLE_TYPE_INFO_VIDEO);
                    break;
                case R.id.btnVehicleUsageInfo:
                    activity.showInfoDialogWithVideo(activity.getString(R.string.info_vehicle_usage),InfoVideoNames.MORE_DETAILS_VEHICLE_USAGE_INFO_VIDEO);
                    break;
            }
        }
    };
    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }

}