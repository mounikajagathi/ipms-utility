package in.ults.gisurvey.ui.survey.building;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildingFragmentProvider {

    @ContributesAndroidInjector(modules = BuildingFragmentModule.class)
    abstract BuildingFragment provideFragmentFactory();
}
