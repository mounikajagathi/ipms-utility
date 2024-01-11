package in.ults.gisurvey.ui.main.completed;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CompletedSurveyFragmentProvider {

    @ContributesAndroidInjector(modules = CompletedSurveyFragmentModule.class)
    abstract CompletedSurveyFragment provideFragmentFactory();
}
