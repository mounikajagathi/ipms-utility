package in.ults.gisurvey.ui.survey.owner;


import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.ui.survey.SurveyActivity;
import in.ults.gisurvey.ui.survey.member.MemberDetailsAdapter;

@Module
public class OwnerFragmentModule {

    @Provides
    OwnerListAdapter provideAdapter(SurveyActivity activity){
        return new OwnerListAdapter(activity, 0, new ArrayList<>());
    }
}
