package in.ults.gisurvey.ui.main.utility.drainage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DrainageFragmentProvider {

    @ContributesAndroidInjector(modules = DrainageFragmentModule.class)
    abstract DrainageFragment provideFragmentFactory();
}
