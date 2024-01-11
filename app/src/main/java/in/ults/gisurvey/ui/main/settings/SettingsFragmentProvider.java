package in.ults.gisurvey.ui.main.settings;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SettingsFragmentProvider {

    @ContributesAndroidInjector(modules = SettingsFragmentModule.class)
    abstract SettingsFragment provideFragmentFactory();
}
