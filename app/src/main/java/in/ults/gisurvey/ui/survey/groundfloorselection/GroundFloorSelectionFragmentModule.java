package in.ults.gisurvey.ui.survey.groundfloorselection;

import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.survey.SurveyActivity;

@Module
public class GroundFloorSelectionFragmentModule {

    @Provides
    GroundFloorSelectionAdapter provideGroundFloorAdapter(SurveyActivity activity){
        return new GroundFloorSelectionAdapter(activity,0);
    }
}
