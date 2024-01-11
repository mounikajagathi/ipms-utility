package in.ults.gisurvey.ui.main.utility.signboard;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SignboardFragmentProvider {

    @ContributesAndroidInjector(modules = SignboardFragmentModule.class)
    abstract SignboardFragment provideFragmentFactory();
}
