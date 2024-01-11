package in.ults.gisurvey.ui.survey.floorcount;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FloorCountFragmentProvider {

    @ContributesAndroidInjector
    abstract FloorCountFragment provideFragmentFactory();
}
