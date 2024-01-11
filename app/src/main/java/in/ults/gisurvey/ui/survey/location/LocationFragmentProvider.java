package in.ults.gisurvey.ui.survey.location;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LocationFragmentProvider {

    @ContributesAndroidInjector
    abstract LocationFragment provideFragmentFactory();
}
