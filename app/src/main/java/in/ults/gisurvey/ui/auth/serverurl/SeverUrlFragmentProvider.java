package in.ults.gisurvey.ui.auth.serverurl;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SeverUrlFragmentProvider {

    @ContributesAndroidInjector
    abstract ServerUrlFragment provideFragmentFactory();
}
