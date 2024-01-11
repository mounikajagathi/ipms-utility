package in.ults.gisurvey.ui.main.ward;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.ults.gisurvey.ui.main.surveyor.SurveyorDetailsFragment;

@Module
public abstract class WardSelectionFragmentProvider {

    @ContributesAndroidInjector
    abstract WardSelectionFragment provideFragmentFactory();

}
