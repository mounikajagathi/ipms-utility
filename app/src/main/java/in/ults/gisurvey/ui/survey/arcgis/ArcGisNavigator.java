package in.ults.gisurvey.ui.survey.arcgis;

import com.esri.arcgisruntime.geometry.PointCollection;

import java.util.List;

import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface ArcGisNavigator extends BaseNavigator {

    void navigateToNextPage();

    void isPointPlotted();

    void zoomToCurrentLocation(double latitude, double longitude);

    void setMarkedLocation(double latitude, double longitude);

    void plotAllPropertyPoints(List<SurveyWithProperty> surveyWithProperties);

    void onLocationFetchError();

    void showProgress();

    void hideProgress();

    void plotLiveLocation(PointCollection stateCapitalsPST);

    void showColourDetailspopup();
}
