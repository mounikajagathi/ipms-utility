package in.ults.gisurvey.ui.main.utility.streettap;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StreetTapFragmentProvider {

    @ContributesAndroidInjector(modules = StreetTapFragmentModule.class)
    abstract StreetTapFragment provideFragmentFactory();
}
