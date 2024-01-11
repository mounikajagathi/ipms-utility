package in.ults.gisurvey.ui.main.utility.home;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UtilityHomeFragmentProvider {

    @ContributesAndroidInjector(modules = UtilityHomeFragmentModule.class)
    abstract UtilityHomeFragment provideFragmentFactory();
}
