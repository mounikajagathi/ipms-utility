package in.ults.gisurvey.ui.main.utility.garbage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GarbageFragmentProvider {

    @ContributesAndroidInjector(modules = GarbageFragmentModule.class)
    abstract GarbageFragment provideFragmentFactory();
}
