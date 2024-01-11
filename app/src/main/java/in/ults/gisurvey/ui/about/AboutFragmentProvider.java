package in.ults.gisurvey.ui.about;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AboutFragmentProvider {

    @ContributesAndroidInjector
    abstract AboutFragment provideAboutFragmentFactory();
}
