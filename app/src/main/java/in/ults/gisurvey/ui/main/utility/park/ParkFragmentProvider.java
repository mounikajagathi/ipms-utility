package in.ults.gisurvey.ui.main.utility.park;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ParkFragmentProvider {

    @ContributesAndroidInjector(modules = ParkFragmentModule.class)
    abstract ParkFragment provideFragmentFactory();
}
