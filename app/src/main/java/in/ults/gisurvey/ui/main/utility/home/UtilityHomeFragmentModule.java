package in.ults.gisurvey.ui.main.utility.home;


import androidx.recyclerview.widget.GridLayoutManager;

import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ui.main.utility.UtilityActivity;

@Module
public class UtilityHomeFragmentModule {

    @Provides
    public GridLayoutManager gridLayoutManager(UtilityActivity surveyActivity) {
        return new GridLayoutManager(surveyActivity, surveyActivity.getResources().getInteger(R.integer.survey_grid_count));
    }

    @Provides
    public UtilityGridAdapter gridAdapter(UtilityActivity activity) {
        return new UtilityGridAdapter(activity);
    }
}
