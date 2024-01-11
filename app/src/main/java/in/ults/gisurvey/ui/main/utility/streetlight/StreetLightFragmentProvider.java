package in.ults.gisurvey.ui.main.utility.streetlight;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StreetLightFragmentProvider {

    @ContributesAndroidInjector(modules = StreetLightFragmentModule.class)
    abstract StreetLightFragment provideFragmentFactory();
}
