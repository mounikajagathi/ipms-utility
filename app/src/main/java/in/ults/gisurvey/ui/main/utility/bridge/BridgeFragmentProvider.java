package in.ults.gisurvey.ui.main.utility.bridge;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BridgeFragmentProvider {

    @ContributesAndroidInjector(modules = BridgeFragmentModule.class)
    abstract BridgeFragment provideFragmentFactory();
}
