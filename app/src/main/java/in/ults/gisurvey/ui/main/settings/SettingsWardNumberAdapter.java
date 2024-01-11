package in.ults.gisurvey.ui.main.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.listeners.RecyclerViewItemClickListener;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class SettingsWardNumberAdapter extends RecyclerView.Adapter<SettingsWardNumberAdapter.MyViewHolder> {

    private ArrayList<CommonItem> dataList;
    private BaseActivity activity;
    private RecyclerViewItemClickListener listener;

    SettingsWardNumberAdapter(BaseActivity activity) {
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
    public SettingsWardNumberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bottom_sheet_recycler_view, parent, false);
        return new SettingsWardNumberAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsWardNumberAdapter.MyViewHolder holder, int position) {
        CommonItem item = dataList.get(position);
        holder.txtName.setText(item.getContent());
    }


    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }


    public void setDataList(ArrayList<CommonItem> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public ArrayList<CommonItem> getDataList() {
        return dataList;
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.listener = listener;
    }


}