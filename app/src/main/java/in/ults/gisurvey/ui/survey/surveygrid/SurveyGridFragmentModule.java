package in.ults.gisurvey.ui.survey.surveygrid;


import androidx.recyclerview.widget.GridLayoutManager;

import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ui.survey.SurveyActivity;

@Module
public class SurveyGridFragmentModule {

    @Provides
    GridLayoutManager gridLayoutManager(SurveyActivity surveyActivity) {
        return new GridLayoutManager(surveyActivity, surveyActivity.getResources().getInteger(R.integer.survey_grid_count));
    }

    @Provides
    SurveyGridAdapter gridAdapter(SurveyActivity activity) {
        return new SurveyGridAdapter(activity);
    }
}
