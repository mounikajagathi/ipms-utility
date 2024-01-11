package in.ults.gisurvey.ui.survey.completesurvey;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CompleteSurveyFragmentProvider {

    @ContributesAndroidInjector(modules = CompleteSurveyFragmentModule.class)
    abstract CompleteSurveyFragment provideFragmentFactory();
}
