package in.ults.gisurvey.ui.main.partialsurvey;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PartialSurveyFragmentProvider {

    @ContributesAndroidInjector(modules = PartialSurveyFragmentModule.class)
    abstract PartialSurveyFragment provideFragmentFactory();
}
