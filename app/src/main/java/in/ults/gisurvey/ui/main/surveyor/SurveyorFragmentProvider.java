package in.ults.gisurvey.ui.main.surveyor;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SurveyorFragmentProvider {

    @ContributesAndroidInjector(modules = SurveyorFragmentModule.class)
    abstract SurveyorFragment provideFragmentFactory();
}
