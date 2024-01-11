package in.ults.gisurvey.ui.survey.surveygrid;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SurveyGridFragmentProvider {
    @ContributesAndroidInjector(modules = SurveyGridFragmentModule.class)
    abstract SurveyGridFragment provideFragmentFactory();
}
