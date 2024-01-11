package in.ults.gisurvey.ui.survey.road;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.ults.gisurvey.ui.survey.owner.OwnerFragmentModule;

@Module
public abstract class RoadFragmentProvider {

    @ContributesAndroidInjector(modules = RoadFragmentModule.class)
    abstract RoadFragment provideFragmentFactory();
}
