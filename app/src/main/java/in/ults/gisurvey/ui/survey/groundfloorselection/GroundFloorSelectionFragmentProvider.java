package in.ults.gisurvey.ui.survey.groundfloorselection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GroundFloorSelectionFragmentProvider {

    @ContributesAndroidInjector(modules = GroundFloorSelectionFragmentModule.class)
    abstract GroundFloorSelectionFragment provideFragmentFactory();
}
