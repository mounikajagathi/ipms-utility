package in.ults.gisurvey.ui.survey.more;


import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.survey.SurveyActivity;

@Module
public class MoreFragmentModule {


    @Provides
    VehicleAdapter provideVehicleAdapter(SurveyActivity activity) {
        return new VehicleAdapter(activity);
    }
}
