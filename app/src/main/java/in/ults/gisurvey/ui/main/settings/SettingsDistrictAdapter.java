package in.ults.gisurvey.ui.main.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ults.gisurvey.utils.IPMSEditText;

import java.util.ArrayList;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.api.District;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class SettingsDistrictAdapter extends RecyclerView.Adapter<SettingsDistrictAdapter.MyViewHolder> {

    private ArrayList<District> dataList;
    private BaseActivity activity;
    private RecyclerViewItemClickListener listener;

    SettingsDistrictAdapter(BaseActivity activity) {
        this.activity = activity;
        this.dataList = new ArrayList<>();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        MyViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txtBSRVItem);
            view.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }


    @NonNull
    @Override
    public SettingsDistrictAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bottom_sheet_recycler_view, parent, false);
        return new SettingsDistrictAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsDistrictAdapter.MyViewHolder holder, int position) {
        District item = dataList.get(position);
        holder.txtName.setText(item.getDistrictName());
    }


    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }


    public void setDataList(ArrayList<District> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public ArrayList<District> getDataList() {
        return dataList;
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.listener = listener;
    }


}