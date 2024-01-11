package in.ults.gisurvey.ui.auth.forgotpassword;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ForgotPasswordFragmentProvider {

    @ContributesAndroidInjector
    abstract ForgotPasswordFragment provideFragmentFactory();
}
