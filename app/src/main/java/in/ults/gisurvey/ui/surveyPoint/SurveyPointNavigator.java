package in.ults.gisurvey.ui.surveyPoint;

import com.esri.arcgisruntime.geometry.PointCollection;

import java.util.List;

import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.ui.base.BaseNavigator;

/**
 * Created by anuag on 26-06-2020
 **/
public interface SurveyPointNavigator extends BaseNavigator {
    void plotAllPropertyPoints(List<SurveyWithProperty> surveyWithProperties);
    void zoomToCurrentLocation(double latitude, double longitude);
    void setMarkedLocation(double latitude, double longitude);

    void onLocationFetchError();

    void showProgress();

    void hideProgress();
    void plotLiveLocation(PointCollection stateCapitalsPST);
    void showColourDetailspopup();
}
