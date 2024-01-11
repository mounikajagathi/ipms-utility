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

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.items.CommonItem;

public class PostOfficeListAdapter extends ArrayAdapter<CommonItem> {
    Context context;
    private List<CommonItem> list;
    private List<CommonItem> filteredList = new ArrayList<>();

    public PostOfficeListAdapter(@NonNull Context context, int resource, List<CommonItem> list) {
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

    @NonNull
    @Override
    public Filter getFilter() {
        return new OwnerFilter(this, list);
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
        CommonItem commonItem = filteredList.get(position);
        if (commonItem != null) {
            TextView lblName = view.findViewById(R.id.txtSpinner);
            if (lblName != null)
                lblName.setText(commonItem.getContent());
        }
        return view;
    }

    private class OwnerFilter extends Filter {

        PostOfficeListAdapter ownerListAdapter;
        List<CommonItem> originalList;
        List<CommonItem> filteredList;

        public OwnerFilter(PostOfficeListAdapter ownerListAdapter, List<CommonItem> originalList) {
            super();
            this.ownerListAdapter = ownerListAdapter;
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
                for (final CommonItem item : originalList) {
                    if (item.getContent().toLowerCase().contains(filterPattern)) {
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
                ownerListAdapter.filteredList.clear();
                ownerListAdapter.filteredList.addAll((List) results.values);
                ownerListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((CommonItem) resultValue).getContent();
        }
    }

    @Nullable
    @Override
    public CommonItem getItem(int position) {
        return filteredList.get(position);
    }
}

