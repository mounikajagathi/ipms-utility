package in.ults.gisurvey.ui.main.syncgrid;


import androidx.recyclerview.widget.GridLayoutManager;

import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ui.main.MainActivity;

@Module
public class SyncGridFragmentModule {


    @Provides
    GridLayoutManager gridLayoutManager(MainActivity activity) {
        return new GridLayoutManager(activity, activity.getResources().getInteger(R.integer.survey_grid_count));
    }

    @Provides
    SyncGridAdapter gridAdapter(MainActivity activity) {
        return new SyncGridAdapter(activity);
    }

}
