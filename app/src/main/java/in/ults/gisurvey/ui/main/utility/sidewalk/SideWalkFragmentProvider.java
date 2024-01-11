package in.ults.gisurvey.ui.main.utility.sidewalk;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SideWalkFragmentProvider {

    @ContributesAndroidInjector(modules = SideWalkFragmentModule.class)
    abstract SideWalkFragment provideFragmentFactory();
}
