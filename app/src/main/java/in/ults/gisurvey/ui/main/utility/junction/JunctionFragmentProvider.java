package in.ults.gisurvey.ui.main.utility.junction;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class JunctionFragmentProvider {

    @ContributesAndroidInjector(modules = JunctionFragmentModule.class)
    abstract JunctionFragment provideFragmentFactory();
}
