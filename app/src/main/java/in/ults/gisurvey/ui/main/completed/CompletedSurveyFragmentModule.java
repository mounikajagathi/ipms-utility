package in.ults.gisurvey.ui.main.completed;


import dagger.Module;
import dagger.Provides;

@Module
public class CompletedSurveyFragmentModule {

    @Provides
    public CompletedSurveyListAdapter providesCompletedListAdapter() {
        return new CompletedSurveyListAdapter();
    }
}
