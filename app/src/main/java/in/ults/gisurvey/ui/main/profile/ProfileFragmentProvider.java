package in.ults.gisurvey.ui.main.profile;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileFragmentProvider {

    @ContributesAndroidInjector(modules = ProfileFragmentModule.class)
    abstract ProfileFragment provideFragmentFactory();
}
