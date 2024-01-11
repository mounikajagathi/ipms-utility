package in.ults.gisurvey.ui.main.synclist;


import dagger.Module;
import dagger.Provides;

@Module
public class SyncListFragmentModule {

    @Provides
    public SyncListAdapter providesSyncListAdapter() {
        return new SyncListAdapter();
    }
}
