package in.ults.gisurvey.ui.survey.building;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import in.ults.gisurvey.utils.IPMSEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.IPMSSpinner;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class BuildingRoofTypeAdapter extends RecyclerView.Adapter<BuildingRoofTypeAdapter.MyViewHolder> {

    private ArrayList<BuildingDetailsRoofItem> dataList;
    private Set<String> selectedSet;
    private BaseActivity activity;
    private boolean validation;
    private boolean isSurveyOpenEdit=true;


    BuildingRoofTypeAdapter(BaseActivity activity) {
        this.activity = activity;
        this.dataList = new ArrayList<>();
        this.selectedSet = new HashSet<>();
        this.validation = false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        in.ults.gisurvey.utils.IPMSEditText roofPercent;
        IPMSSpinner roofType;

        TextInputLayout layoutRoofPercent;
        TextInputLayout layoutRoofType;

        MyViewHolder(View view) {
            super(view);
            if (!isSurveyOpenEdit) {
                disableChildView((ViewGroup) view);
            }
            roofPercent = view.findViewById(R.id.etRoofPercent);
            roofType = view.findViewById(R.id.etRoofType);
            layoutRoofPercent = view.findViewById(R.id.layoutRoofPercent);
            layoutRoofType = view.findViewById(R.id.layoutRoofType);

            ArrayList<CommonItem> roofTypeData = IPMSApp.getAppInstance().getLocMainItem().getRoofType();
            roofType.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_roof_type_view, roofTypeData));

            roofPercent.setOnFocusChangeListener((v, hasFocus) -> roofPercent.post(() -> {
                if(hasFocus) {
                    new Handler().postDelayed(() -> roofPercent.requestFocus(),300);
                }
            }));


            roofPercent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        int percent = Integer.parseInt(Objects.requireNonNull(roofPercent.getText()).toString());
                        if (percent == 0 || percent > 100) {
                            roofPercent.setText("");
                        }
                    }
                    if (dataList != null) {
                        dataList.get(getAdapterPosition()).setRoofPercent(Objects.requireNonNull(roofPercent.getText()).toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            roofType.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        String roofTypeData = roofType.getText().toString();
                        selectedSet.remove(dataList.get(getAdapterPosition()).getRoofType());
                        if (selectedSet.contains(roofTypeData)) {
                            if (dataList != null) {
                                dataList.get(getAdapterPosition()).setRoofType("");
                            }
                            roofType.setText("");
                            activity.showToast(R.string.error_roof_type_already_selected);
                        } else {
                            if (dataList != null) {
                                dataList.get(getAdapterPosition()).setRoofType(roofTypeData);
                            }
                            selectedSet.add(roofTypeData);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_roof_type_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BuildingDetailsRoofItem item = getDataList().get(position);
        holder.roofType.setText(item.getRoofType());
        holder.roofPercent.setText(item.getRoofPercent());
        if (validation) {
            if (item.getRoofType().length() == 0) {
                holder.layoutRoofType.setError(activity.getString(R.string.error_roof_type));
            } else {
                holder.layoutRoofType.setErrorEnabled(false);
            }
            if (item.getRoofPercent().length() == 0) {
                holder.layoutRoofPercent.setError(activity.getString(R.string.error_roof_percent));
            } else {
                holder.layoutRoofPercent.setErrorEnabled(false);
            }
        } else {
            holder.layoutRoofPercent.setErrorEnabled(false);
            holder.layoutRoofType.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    ArrayList<BuildingDetailsRoofItem> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<BuildingDetailsRoofItem> dataList) {
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

    void addNewItem(int typeCount,String roofPercent) {
        if (dataList != null) {
            dataList.add(new BuildingDetailsRoofItem());
            if(typeCount==0)
            {
                dataList.get(0).setRoofPercent(roofPercent);
            } else
            {
                if(dataList.get(0).getRoofPercent().equalsIgnoreCase(roofPercent)){
                    dataList.get(0).setRoofPercent("");
                }
            }
            refresh(false);
        }
    }
    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }


    void removeItem() {
        if (dataList != null && dataList.size() > 0) {
            dataList.remove(dataList.size() - 1);
            refresh(false);
        }
    }
}