package in.ults.gisurvey.ui.survey.road;


import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.survey.SurveyActivity;
import in.ults.gisurvey.ui.survey.owner.OwnerListAdapter;

@Module
public class RoadFragmentModule {

    @Provides
    RoadListAdapter provideAdapter(SurveyActivity activity){
        return new RoadListAdapter(activity, 0, new ArrayList<>());
    }
}
