package in.ults.gisurvey.ui.main.syncgrid;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SyncGridFragmentProvider {

    @ContributesAndroidInjector(modules = SyncGridFragmentModule.class)
    abstract SyncGridFragment provideFragmentFactory();
}
