package in.ults.gisurvey.ui.serverSurvey.list;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServerSurveyFragmentProvider {

    @ContributesAndroidInjector(modules = ServerSurveyFragmentModule.class)
    abstract ServerSurveyFragment provideServerSurveyFragmentFactory();
}
