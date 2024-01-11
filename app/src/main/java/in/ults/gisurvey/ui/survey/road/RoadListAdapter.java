package in.ults.gisurvey.ui.survey.road;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Road;

public class RoadListAdapter extends ArrayAdapter<Road> {
    private Context context;
    private List<Road> list;
    private List<Road> filteredList = new ArrayList<>();

    public RoadListAdapter(@NonNull Context context, int resource, List<Road> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
    }

    public void addItems(List<Road> repoList) {
        list.addAll(repoList);
    }

    public void clearItems() {
        list.clear();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new RoadFilter(this, list);
    }


    @Override
    public int getCount() {
        return filteredList != null ? filteredList.size() : 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner, parent, false);
        }
        Road commonItem = filteredList.get(position);
        if (commonItem != null) {
            TextView lblName = view.findViewById(R.id.txtSpinner);
            if (lblName != null)
                lblName.setText(commonItem.getNearRoad());
        }
        return view;
    }

    private static class RoadFilter extends Filter {

        RoadListAdapter roadListAdapter;
        List<Road> originalList;
        List<Road> filteredList;

        public RoadFilter(RoadListAdapter roadListAdapter, List<Road> originalList) {
            super();
            this.roadListAdapter = roadListAdapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Road item : originalList) {
                    if (item.getNearRoad().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(results.values!=null) {
                roadListAdapter.filteredList.clear();
                roadListAdapter.filteredList.addAll((List) results.values);
                roadListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Road) resultValue).getNearRoad();
        }
    }

    @Nullable
    @Override
    public Road getItem(int position) {
        return filteredList.get(position);
    }
}

