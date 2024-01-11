package in.ults.gisurvey.ui.main.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.items.District;
import in.ults.gisurvey.ui.base.BaseActivity;

public class SettingsSpinnerAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<District> districts;

    SettingsSpinnerAdapter(BaseActivity baseActivity, ArrayList<District> list) {
        districts = list;
        inflater = LayoutInflater.from(baseActivity);
    }

    @Override
    public int getCount() {
        return districts != null ? districts.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return districts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_settings_spinner, null);
            viewHolder = new ViewHolder();
            viewHolder.txt = convertView.findViewById(R.id.txtSpinner);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txt.setText(districts.get(position).getDistrictName());
        return convertView;
    }


    private class ViewHolder {
        private TextView txt;
    }
}