package in.ults.gisurvey.ui.survey.startsurvey;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StartSurveyFragmentProvider {

    @ContributesAndroidInjector
    abstract StartSurveyFragment provideFragmentFactory();
}
