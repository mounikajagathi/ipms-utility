package in.ults.gisurvey.ui.survey.member;


import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.survey.SurveyActivity;

@Module
public class MemberFragmentModule {

    @Provides
    MemberDetailsAdapter provideAdapter(SurveyActivity activity){
        return new MemberDetailsAdapter(activity);
    }
}
