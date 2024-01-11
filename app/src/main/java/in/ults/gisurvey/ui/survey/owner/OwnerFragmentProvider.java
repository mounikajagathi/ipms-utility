package in.ults.gisurvey.ui.survey.owner;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class OwnerFragmentProvider {

    @ContributesAndroidInjector(modules = OwnerFragmentModule.class)
    abstract OwnerFragment provideFragmentFactory();
}
