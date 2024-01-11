package in.ults.gisurvey.ui.survey.member;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.IPMSSpinner;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class MemberDiseaseAdapter extends RecyclerView.Adapter<MemberDiseaseAdapter.MyViewHolder> {

    private ArrayList<String> dataList;
    private BaseActivity activity;
    private boolean isValidation;
    private final Set<String> selectedSet;
    private boolean isSurveyOpenEdit=true;


    class MyViewHolder extends RecyclerView.ViewHolder {
        IPMSSpinner memberDisease;
        TextInputLayout layoutMemberDisease;

        MyViewHolder(View view) {
            super(view);
            layoutMemberDisease = view.findViewById(R.id.layoutDisease);
            memberDisease = view.findViewById(R.id.etDisease);
            memberDisease.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_disease_view, IPMSApp.getAppInstance().getLocMainItem().getDiseases()));
            memberDisease.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        String roofTypeData = memberDisease.getText().toString();
                        selectedSet.remove(dataList.get(getAdapterPosition()));
                        if (selectedSet.contains(roofTypeData)) {
                            if (dataList != null) {
                                dataList.set(getAdapterPosition(), "");
                            }
                            memberDisease.setText("");
                            activity.showToast(R.string.error_member_disease_already_selected);
                        } else {
                            if (dataList != null) {
                                dataList.set(getAdapterPosition(), memberDisease.getText().toString());
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


    MemberDiseaseAdapter(BaseActivity activity) {
        this.activity = activity;
        this.dataList = new ArrayList<>();
        this.selectedSet = new HashSet<>();
        dataList.add("");
        this.isValidation = false;
    }

    void addNewItem() {
        if (dataList != null) {
            dataList.add("");
            refresh(false);
        }
    }

    void removeItem() {
        if (dataList != null && dataList.size() > 1) {
            dataList.remove(dataList.size() - 1);
            refresh(false);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_disease_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String data = getDataList().get(position);
        holder.layoutMemberDisease.setHint(activity.getString(R.string.member_disease) + " "+(position+1));
        holder.memberDisease.setText(data);
        if (!isSurveyOpenEdit) {
            holder.layoutMemberDisease.setEnabled(false);
        }
        if (isValidation) {
            if (Objects.requireNonNull(holder.memberDisease.getText()).toString().length() == 0) {
                holder.layoutMemberDisease.setError(activity.getString(R.string.error_member_disease));
            } else {
                holder.layoutMemberDisease.setErrorEnabled(false);
            }
        } else {
            holder.layoutMemberDisease.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    ArrayList<String> getDataList() {
        return dataList;
    }

    void setDataList(ArrayList<String> dataList) {
        if (dataList != null && dataList.size() == 0) {
            dataList.add("");
        }
        this.dataList = dataList;
        refresh(false);

    }

    void setValidation(boolean validation) {
        refresh(validation);
    }

    private void refresh(boolean isValidation) {
        this.isValidation = isValidation;
        this.selectedSet.clear();
        notifyDataSetChanged();
    }
    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }
}