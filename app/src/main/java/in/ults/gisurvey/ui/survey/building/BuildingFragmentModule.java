package in.ults.gisurvey.ui.survey.building;


import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.survey.SurveyActivity;

@Module
public class BuildingFragmentModule {

    @Provides
    BuildingFloorAreaAdapter provideFloorAreaAdapter(SurveyActivity activity){
        return new BuildingFloorAreaAdapter(activity);
    }

    @Provides
    BuildingRoofTypeAdapter provideRoofTypeAdapter(SurveyActivity activity){
        return new BuildingRoofTypeAdapter(activity);
    }
}
