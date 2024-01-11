package in.ults.gisurvey.utils.adapters;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.items.CommonItem;

public class FlexRecycleViewModel {
    public final ObservableField<Integer> id = new ObservableField<>();

    public final ObservableField<String> content = new ObservableField<>();

    FlexRecycleViewModel(CommonItem item) {
        this.id.set(item.getId());
        this.content.set(item.getContent());
    }
}
