package in.ults.gisurvey.ui.survey.establishment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class EstablishmentFragmentProvider {

    @ContributesAndroidInjector
    abstract EstablishmentFragment provideFragmentFactory();
}
