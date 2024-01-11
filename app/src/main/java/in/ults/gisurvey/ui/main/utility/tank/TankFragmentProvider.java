package in.ults.gisurvey.ui.main.utility.tank;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TankFragmentProvider {

    @ContributesAndroidInjector(modules = TankFragmentModule.class)
    abstract TankFragment provideFragmentFactory();
}
