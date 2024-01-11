package in.ults.gisurvey.ui.main.partialsurvey;


import dagger.Module;
import dagger.Provides;

@Module
public class PartialSurveyFragmentModule {

    @Provides
    public PartialSurveyListAdapter providesPartialListAdapter(){
        return new PartialSurveyListAdapter();
    }

}
