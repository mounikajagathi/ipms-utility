package in.ults.gisurvey.ui.auth.login;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginFragmentProvider {

    @ContributesAndroidInjector
    abstract LoginFragment provideFragmentFactory();
}
