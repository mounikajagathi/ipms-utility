package in.ults.gisurvey.ui.main.utility.well;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class WellFragmentProvider {

    @ContributesAndroidInjector(modules = WellFragmentModule.class)
    abstract WellFragment provideFragmentFactory();
}
