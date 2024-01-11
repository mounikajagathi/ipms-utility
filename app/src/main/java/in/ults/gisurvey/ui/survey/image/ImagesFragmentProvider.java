package in.ults.gisurvey.ui.survey.image;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ImagesFragmentProvider {

    @ContributesAndroidInjector
    abstract ImagesFragment provideFragmentFactory();
}
