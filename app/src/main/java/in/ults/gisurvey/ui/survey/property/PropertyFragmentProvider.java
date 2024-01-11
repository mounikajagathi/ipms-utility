package in.ults.gisurvey.ui.survey.property;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PropertyFragmentProvider {

    @ContributesAndroidInjector
    abstract PropertyFragment provideFragmentFactory();
}
