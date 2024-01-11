package in.ults.gisurvey.ui.main.settings;


import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.main.MainActivity;

@Module
public class SettingsFragmentModule {

    @Provides
    SettingsDistrictAdapter provideDistrictAdapter(MainActivity activity) {
        return new SettingsDistrictAdapter(activity);
    }

    @Provides
    SettingsLocalBodyAdapter provideLocalBodyAdapter(MainActivity activity) {
        return new SettingsLocalBodyAdapter(activity);
    }
    @Provides
    SettingsWardNumberAdapter provideSettingsWardNumberAdapter(MainActivity activity) {
        return new SettingsWardNumberAdapter(activity);
    }
}
