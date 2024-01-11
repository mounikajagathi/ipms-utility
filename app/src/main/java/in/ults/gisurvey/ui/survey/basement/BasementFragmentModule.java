package in.ults.gisurvey.ui.survey.basement;


import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.survey.SurveyActivity;

@Module
public class BasementFragmentModule {

    @Provides
    BasementAreaAdapter provideAdapter(SurveyActivity activity) {
        return new BasementAreaAdapter(activity);
    }
}
