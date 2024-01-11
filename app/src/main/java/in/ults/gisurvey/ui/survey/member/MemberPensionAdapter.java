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
public class MemberPensionAdapter extends RecyclerView.Adapter<MemberPensionAdapter.MyViewHolder> {

    private ArrayList<String> dataList;
    private BaseActivity activity;
    private boolean isValidation;
    private final Set<String> selectedSet;
    private boolean isSurveyOpenEdit=true;


    class MyViewHolder extends RecyclerView.ViewHolder {
        IPMSSpinner memberPension;
        TextInputLayout layoutMemberPension;

        MyViewHolder(View view) {
            super(view);
            layoutMemberPension = view.findViewById(R.id.layoutPension);
            memberPension = view.findViewById(R.id.etPension);
            if (!isSurveyOpenEdit) {
                layoutMemberPension.setEnabled(false);
            }
            memberPension.setAdapter(new CommonDropDownAdapter(activity, R.layout.item_pension_view, IPMSApp.getAppInstance().getLocMainItem().getPensions()));
            memberPension.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        String roofTypeData = memberPension.getText().toString();
                        selectedSet.remove(dataList.get(getAdapterPosition()));
                        if (selectedSet.contains(roofTypeData)) {
                            if (dataList != null) {
                                dataList.set(getAdapterPosition(), "");
                            }
                            memberPension.setText("");
                            activity.showToast(R.string.error_member_pension_already_selected);
                        } else {
                            if (dataList != null) {
                                dataList.set(getAdapterPosition(), memberPension.getText().toString());
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


    MemberPensionAdapter(BaseActivity activity) {
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
    void addChildPensionItem() {
        if (dataList != null) {
            dataList.set(0,activity.getString(R.string.member_child_pension));
            refresh(false);
        }
    }
    void removeChildPensionItem() {
        if (dataList != null) {
            dataList.set(0,"");
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
                .inflate(R.layout.item_pension_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String data = getDataList().get(position);
        holder.layoutMemberPension.setHint(activity.getString(R.string.member_pension) + " "+(position+1));
        holder.memberPension.setText(data);
        if (isValidation) {
            if (Objects.requireNonNull(holder.memberPension.getText()).toString().length() == 0) {
                holder.layoutMemberPension.setError(activity.getString(R.string.error_member_pension));
            } else {
                holder.layoutMemberPension.setErrorEnabled(false);
            }
        } else {
            holder.layoutMemberPension.setErrorEnabled(false);
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