package in.ults.gisurvey.ui.survey.more;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.ults.gisurvey.ui.survey.building.BuildingFragmentModule;

@Module
public abstract class MoreFragmentProvider {
    @ContributesAndroidInjector(modules = MoreFragmentModule.class)
    abstract MoreFragment moreFragment();



}
