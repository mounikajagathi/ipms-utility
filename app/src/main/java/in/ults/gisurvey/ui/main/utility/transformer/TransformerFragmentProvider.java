package in.ults.gisurvey.ui.main.utility.transformer;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TransformerFragmentProvider {

    @ContributesAndroidInjector(modules = TransformerFragmentModule.class)
    abstract TransformerFragment provideFragmentFactory();
}
