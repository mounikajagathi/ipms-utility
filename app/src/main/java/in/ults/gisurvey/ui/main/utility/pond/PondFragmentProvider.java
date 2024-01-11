package in.ults.gisurvey.ui.main.utility.pond;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PondFragmentProvider {

    @ContributesAndroidInjector(modules = PondFragmentModule.class)
    abstract PondFragment provideFragmentFactory();
}
