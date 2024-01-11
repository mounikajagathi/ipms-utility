package in.ults.gisurvey.ui.survey.member;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MemberFragmentProvider {

    @ContributesAndroidInjector(modules = MemberFragmentModule.class)
    abstract MemberFragment provideFragmentFactory();
}
