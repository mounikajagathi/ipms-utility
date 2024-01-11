package in.ults.gisurvey.ui.main.synclist;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SyncListFragmentProvider {

    @ContributesAndroidInjector(modules = SyncListFragmentModule.class)
    abstract SyncListFragment provideFragmentFactory();
}
