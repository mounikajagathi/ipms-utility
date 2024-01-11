package in.ults.gisurvey.ui.main.utility.map;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MapFragmentProvider {

    @ContributesAndroidInjector(modules = MapFragmentModule.class)
    abstract MapFragment provideFragmentFactory();
}
