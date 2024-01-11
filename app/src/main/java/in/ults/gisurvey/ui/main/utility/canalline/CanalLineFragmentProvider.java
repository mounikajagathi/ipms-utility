package in.ults.gisurvey.ui.main.utility.canalline;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CanalLineFragmentProvider {

    @ContributesAndroidInjector(modules = CanalLineFragmentModule.class)
    abstract CanalLineFragment provideFragmentFactory();
}
