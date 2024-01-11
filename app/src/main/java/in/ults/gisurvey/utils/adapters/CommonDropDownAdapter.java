package in.ults.gisurvey.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.items.CommonItem;

public class CommonDropDownAdapter extends ArrayAdapter<CommonItem> {
    Context context;
    private List<CommonItem> list;

    public CommonDropDownAdapter(@NonNull Context context, int resource, List<CommonItem> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
    }

    public void addItems(List<CommonItem> repoList) {
        list.addAll(repoList);
    }

    public void clearItems() {
        list.clear();
    }


    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner, parent, false);
        }
        CommonItem commonItem = list.get(position);
        if (commonItem != null) {
            TextView lblName = view.findViewById(R.id.txtSpinner);
            if (lblName != null)
                lblName.setText(commonItem.getContent());
        }
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((CommonItem) resultValue).getContent();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return new FilterResults();
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }
}

