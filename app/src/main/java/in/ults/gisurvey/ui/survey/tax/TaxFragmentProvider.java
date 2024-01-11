package in.ults.gisurvey.ui.survey.tax;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TaxFragmentProvider {

    @ContributesAndroidInjector
    abstract TaxFragment provideFragmentFactory();
}
