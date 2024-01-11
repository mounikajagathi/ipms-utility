package in.ults.gisurvey.ui.main.utility.taxistand;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TaxiStandFragmentProvider {

    @ContributesAndroidInjector(modules = TaxiStandFragmentModule.class)
    abstract TaxiStandFragment provideFragmentFactory();
}
