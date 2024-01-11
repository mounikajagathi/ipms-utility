package in.ults.gisurvey.ui.survey.basement;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.IPMSEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.db.BasementAreaItem;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseActivity;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class BasementAreaAdapter extends RecyclerView.Adapter<BasementAreaAdapter.MyViewHolder> {

    private ArrayList<BasementAreaItem> dataList;
    private BaseActivity activity;
    private boolean validation;
    private final ArrayList<CommonItem> floorNumberData;
    private boolean isSurveyOpenEdit=true;


    BasementAreaAdapter(BaseActivity activity) {
        this.activity = activity;
        this.dataList = new ArrayList<>();
        this.floorNumberData = new ArrayList<>();
        this.validation = false;
    }

    public void updateSurveyOpenEditMode(boolean status) {
        isSurveyOpenEdit=status;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        in.ults.gisurvey.utils.IPMSEditText basementArea;
        in.ults.gisurvey.utils.IPMSEditText basementNumber;
        TextInputLayout layoutBasementArea;
        TextInputLayout layoutBasementNumber;
        AppCompatImageView nrBasementArea;
        MyViewHolder(View view) {
            super(view);
            basementNumber = view.findViewById(R.id.etBasementNumber);
            basementArea = view.findViewById(R.id.etBasementArea);
            layoutBasementNumber = view.findViewById(R.id.layoutBasementNumber);
            layoutBasementArea = view.findViewById(R.id.layoutBasementArea);
            nrBasementArea = view.findViewById(R.id.nrBasementArea);
            if (!isSurveyOpenEdit) {
                disableChildView((ViewGroup) view);
            }
            basementArea.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (dataList != null) {
                        dataList.get(getAdapterPosition()).setArea(Objects.requireNonNull(basementArea.getText()).toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            nrBasementArea.setOnClickListener(v -> basementArea.setText(AppConstants.NR_UPPERCASE));

           /* basementNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        String roofTypeData = basementNumber.getText().toString();
                        selectedSet.remove(dataList.get(getAdapterPosition()).getBasementNumber());
                        if (selectedSet.contains(roofTypeData)) {
                            if (dataList != null) {
                                dataList.get(getAdapterPosition()).setBasementNumber("");
                            }
                            basementNumber.setText("");
                            activity.showToast(R.string.error_floor_number_already_selected);
                        } else {
                            if (dataList != null) {
                                dataList.get(getAdapterPosition()).setBasementNumber(roofTypeData);
                            }
                            selectedSet.add(roofTypeData);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });*/

//           view.findViewById(R.id.nrBasementArea).setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   basementArea.setText(AppConstants.NR_UPPERCASE);
//               }
//           });

        }
    }

    @NonNull
    @Override
    public BasementAreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_basement_area_view, parent, false);

        return new BasementAreaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BasementAreaAdapter.MyViewHolder holder, int position) {
        BasementAreaItem item = getDataList().get(position);
        holder.basementArea.setText(item.getArea());
        holder.basementNumber.setText(item.getBasementNumber());
        if (validation) {
            if (item.getBasementNumber().length() == 0) {
                holder.layoutBasementNumber.setError(activity.getString(R.string.error_basement_number));
            } else {
                holder.layoutBasementNumber.setErrorEnabled(false);
            }

            if (item.getArea().length() == 0 || (!item.getArea().equalsIgnoreCase(AppConstants.NR_UPPERCASE) && Double.parseDouble(item.getArea()) == 0)) {
                holder.layoutBasementArea.setError(activity.getString(R.string.error_basement_area));
            } else {
                holder.layoutBasementArea.setErrorEnabled(false);
            }
        } else {
            holder.layoutBasementNumber.setErrorEnabled(false);
            holder.layoutBasementArea.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    ArrayList<BasementAreaItem> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<BasementAreaItem> dataList) {
        this.dataList = dataList;
        refresh(false);
    }


    void setValidation(boolean validation) {
        refresh(validation);
    }

    private void refresh(boolean isValidation) {
        this.validation = isValidation;
        notifyDataSetChanged();
    }


    public void setBasementCount(int count) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        for (int i = 0; i < count; i++) {
            BasementAreaItem item = new BasementAreaItem();
            item.setBasementNumber(activity.getString(R.string.basement_no) + (i + 1));
            item.setArea("");
            dataList.add(item);
        }
        refresh(false);

    }

}