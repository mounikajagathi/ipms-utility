package in.ults.gisurvey.ui.main.surveyor;

import dagger.Module;
import dagger.Provides;

@Module
public class SurveyorFragmentModule {

    @Provides
    public SurveyorListAdapter providesSurveyorAdapter(){
        return new SurveyorListAdapter();
    }

}
