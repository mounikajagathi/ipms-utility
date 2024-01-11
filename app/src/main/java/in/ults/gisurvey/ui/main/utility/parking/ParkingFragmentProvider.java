package in.ults.gisurvey.ui.main.utility.parking;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ParkingFragmentProvider {

    @ContributesAndroidInjector(modules = ParkingFragmentModule.class)
    abstract ParkingFragment provideFragmentFactory();
}
