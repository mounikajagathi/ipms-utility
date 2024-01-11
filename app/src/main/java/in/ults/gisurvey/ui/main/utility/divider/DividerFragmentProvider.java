package in.ults.gisurvey.ui.main.utility.divider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DividerFragmentProvider {

    @ContributesAndroidInjector(modules = DividerFragmentModule.class)
    abstract DividerFragment provideFragmentFactory();
}
