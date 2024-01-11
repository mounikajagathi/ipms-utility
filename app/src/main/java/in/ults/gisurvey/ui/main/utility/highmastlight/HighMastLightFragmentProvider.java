package in.ults.gisurvey.ui.main.utility.highmastlight;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HighMastLightFragmentProvider {

    @ContributesAndroidInjector(modules = HighMastLightFragmentModule.class)
    abstract HighMastLightFragment provideFragmentFactory();
}
