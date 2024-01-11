package in.ults.gisurvey.ui.survey.selection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SelectionFragmentProvider {
    @ContributesAndroidInjector(modules = ScreenSelectionGridFragmentModule.class)
    abstract ScreenSelectionGridFragment provideFragmentFactory();
}
