package in.ults.gisurvey.ui.main.utility.lowmastlight;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LowMastLightFragmentProvider {

    @ContributesAndroidInjector(modules = LowMastLightFragmentModule.class)
    abstract LowMastLightFragment provideFragmentFactory();
}
