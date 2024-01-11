package in.ults.gisurvey.ui.report;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReportFragmentProvider {

    @ContributesAndroidInjector
    abstract ReportFragment provideReportFragmentFactory();
}
