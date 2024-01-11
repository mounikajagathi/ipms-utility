package in.ults.gisurvey.ui.main.utility.road;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RoadFragmentProvider {

    @ContributesAndroidInjector(modules = RoadFragmentModule.class)
    abstract RoadFragment provideFragmentFactory();
}
