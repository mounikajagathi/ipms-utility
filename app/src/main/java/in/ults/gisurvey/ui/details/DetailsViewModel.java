package in.ults.gisurvey.ui.details;

import androidx.databinding.ObservableField;

import com.google.gson.Gson;

import java.util.List;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.functions.Consumer;

public class DetailsViewModel extends BaseViewModel<DetailsNavigator> {

    public final  ObservableField<String> content = new ObservableField<>();

    public DetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void setContent(String content){
        this.content.set(content);
    }

    void syncData() {
      /*  getCompositeDisposable().add(getDataManager()
                .loadSurvey()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(surveys -> {
                    *//*System.out.println("@@@@ Data Survey---->"+new Gson().toJson(surveys));
                    String data = new Gson().toJson(surveys);
                    if(data!=null) {
                        setContent(data);
                    }*//*
                })
                .subscribe());*/

        getCompositeDisposable().add(getDataManager()
                .loadAllCompletedSurveyWithProperty()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(new Consumer<List<SurveyWithProperty>>() {
                    @Override
                    public void accept(List<SurveyWithProperty> surveyWithProperties) throws Exception {
                        System.out.println("@@@@ Data Property---->"+new Gson().toJson(surveyWithProperties));
                        String data = new Gson().toJson(surveyWithProperties);
                        if(data!=null) {
                            setContent(data);
                        }
                    }
                })
                .subscribe());

    }


}
