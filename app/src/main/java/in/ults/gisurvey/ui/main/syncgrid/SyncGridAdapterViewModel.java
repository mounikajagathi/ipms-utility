package in.ults.gisurvey.ui.main.syncgrid;
import androidx.databinding.ObservableField;
import in.ults.gisurvey.utils.AppConstants;

public class SyncGridAdapterViewModel {

    public final ObservableField<String> syncCount = new ObservableField<>();

    SyncGridAdapterViewModel(int count,int totalItems) {
        int initialCount = count * AppConstants.SYNC_COUNT;
        int finalCount = initialCount + AppConstants.SYNC_COUNT;
        syncCount.set("( "+ ( initialCount + 1 ) + " - "+ ( finalCount > totalItems ? totalItems : finalCount ) + " )");
    }
}
