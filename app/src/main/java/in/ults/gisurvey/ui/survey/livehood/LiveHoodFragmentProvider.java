package in.ults.gisurvey.ui.survey.livehood;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LiveHoodFragmentProvider {

    @ContributesAndroidInjector
    abstract LiveHoodFragment provideFragmentFactory();
}
