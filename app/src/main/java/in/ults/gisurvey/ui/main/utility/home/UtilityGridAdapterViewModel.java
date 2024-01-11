package in.ults.gisurvey.ui.main.utility.home;

import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.items.UtilityGridItem;

public class UtilityGridAdapterViewModel {

    public final ObservableField<Integer> id = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Integer> img = new ObservableField<>();

    UtilityGridAdapterViewModel(UtilityGridItem item) {
        id.set(item.getId());
        name.set(item.getName());
        img.set(item.getImg());
    }
}
