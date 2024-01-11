package in.ults.gisurvey.ui.main.utility.stadium;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StadiumFragmentProvider {

    @ContributesAndroidInjector(modules = StadiumFragmentModule.class)
    abstract StadiumFragment provideFragmentFactory();
}
