package in.ults.gisurvey.ui.main.utility.playground;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaygroundFragmentProvider {

    @ContributesAndroidInjector(modules = PlaygroundFragmentModule.class)
    abstract PlaygroundFragment provideFragmentFactory();
}
