package in.ults.gisurvey.ui.survey.tenant;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TenantFragmentProvider {

    @ContributesAndroidInjector
    abstract TenantFragment provideFragmentFactory();
}
