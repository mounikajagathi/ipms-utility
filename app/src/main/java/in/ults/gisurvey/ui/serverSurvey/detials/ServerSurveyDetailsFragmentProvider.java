package in.ults.gisurvey.ui.serverSurvey.detials;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.ults.gisurvey.ui.main.surveyor.SurveyorFragment;

@Module
public abstract class ServerSurveyDetailsFragmentProvider {

    @ContributesAndroidInjector
    abstract ServerSurveyDetailsFragment provideFragmentFactory();
}
