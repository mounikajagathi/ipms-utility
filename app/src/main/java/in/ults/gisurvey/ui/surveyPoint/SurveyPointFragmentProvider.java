package in.ults.gisurvey.ui.surveyPoint;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by anuag on 26-06-2020
 **/
@Module
public abstract class SurveyPointFragmentProvider {

    @ContributesAndroidInjector
    abstract SurveyPointFragment provideSurveyPointFragmentFactory();
}
