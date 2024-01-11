package in.ults.gisurvey.ui.main.home;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeFragmentProvider {
    @ContributesAndroidInjector
    abstract HomeFragment provideFragmentFactory();
}
