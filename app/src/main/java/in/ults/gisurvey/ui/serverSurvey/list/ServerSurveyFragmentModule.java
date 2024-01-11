package in.ults.gisurvey.ui.serverSurvey.list;


import dagger.Module;
import dagger.Provides;

@Module
public class ServerSurveyFragmentModule {

    @Provides
    public ServerSurveyListAdapter providesServerListAdapter() {
        return new ServerSurveyListAdapter();
    }
}
