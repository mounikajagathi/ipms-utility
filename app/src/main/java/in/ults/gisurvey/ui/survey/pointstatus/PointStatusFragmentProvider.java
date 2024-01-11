package in.ults.gisurvey.ui.survey.pointstatus;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PointStatusFragmentProvider {

    @ContributesAndroidInjector(modules = PointStatusFragmentModule.class)
    abstract PointStatusFragment provideFragmentFactory();
}
