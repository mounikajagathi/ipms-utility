package in.ults.gisurvey.ui.survey.basement;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BasementFragmentProvider {

    @ContributesAndroidInjector(modules = BasementFragmentModule.class)
    abstract BasementFragment provideFragmentFactory();
}
