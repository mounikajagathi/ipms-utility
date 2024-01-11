package in.ults.gisurvey.ui.main.utility.busstop;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BusStopFragmentProvider {

    @ContributesAndroidInjector(modules = BusStopFragmentModule.class)
    abstract BusStopFragment provideFragmentFactory();
}
