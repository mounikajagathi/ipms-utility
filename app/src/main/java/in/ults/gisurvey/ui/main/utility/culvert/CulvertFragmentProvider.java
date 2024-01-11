package in.ults.gisurvey.ui.main.utility.culvert;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CulvertFragmentProvider {

    @ContributesAndroidInjector(modules = CulvertFragmentModule.class)
    abstract CulvertFragment provideFragmentFactory();
}
