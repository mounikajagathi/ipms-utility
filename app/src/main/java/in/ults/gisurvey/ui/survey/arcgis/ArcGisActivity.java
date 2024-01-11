package in.ults.gisurvey.ui.survey.arcgis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Multipoint;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.databinding.ActivityArcGisBinding;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.utils.AppConstants;

public class ArcGisActivity extends BaseActivity<ActivityArcGisBinding, ArcGisViewModel> implements ArcGisNavigator {

    @Inject
    ViewModelProviderFactory factory;

    ArcGisViewModel viewModel;

    private boolean isPointPlotted = false;

    private MapView mMapView;
    private GraphicsOverlay mGraphicsOverlay;
    private Graphic currentPointGraphic;
    private double latitude = 0;
    private double longitude = 0;
    private double currentLatitude = 0;
    private double currentLongitude = 0;

    Graphic pointGraphicLivePoint;
    private Graphic currentLocationGraphic;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_arc_gis;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(this, this);
    }

    @Override
    public ArcGisViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ArcGisViewModel.class);
        return viewModel;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        mMapView = getViewDataBinding().mapView;
        viewModel.setLocationUpdates();
        setupOfflineMap();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (requestingLocationUpdates) {
        viewModel.startLocationUpdates();
//        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        viewModel.stopLocationUpdates();
    }

    @Override
    public void navigateToNextPage() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void isPointPlotted() {
        if (latitude != 0 && longitude != 0) {
            viewModel.saveGeoLocation(latitude, longitude);
        } else {
            showToast(R.string.error_arc_gis_select_location);
        }
    }

    @Override
    public void setMarkedLocation(double latitude, double longitude) {
        if (latitude != 0 && longitude != 0 && mMapView != null) {
            isPointPlotted = true;
            this.latitude = latitude;
            this.longitude = longitude;
            mMapView.setViewpoint(new Viewpoint(new Point(longitude, latitude, SpatialReferences.getWgs84()), 100));
        }
        viewModel.fetchPropertyListData();
    }

    private void setupOfflineMap() {
        mMapView.refreshDrawableState();
        mGraphicsOverlay = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(mGraphicsOverlay);
        String tpkFileLoc = viewModel.getTpkFileLoc();
        if (mMapView != null && tpkFileLoc != null) {
            File mmpkFile = new File(tpkFileLoc);
            ArcGISTiledLayer localTiledLayer = new ArcGISTiledLayer(mmpkFile.getAbsolutePath());
            ArcGISMap map = new ArcGISMap(new Basemap(localTiledLayer));
            mMapView.setMap(map);
            mMapView.setOnTouchListener(new MapTouchListener(ArcGisActivity.this, mMapView));
            viewModel.getCurrentSurveyProperty();
        }
    }

    //define custom touch listener class, overriding the onSingleTapConfirmed method
    class MapTouchListener extends DefaultMapViewOnTouchListener {

        public MapTouchListener(Context context, MapView mapView) {
            super(context, mapView);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (isPointPlotted) {
                if (viewModel.isSurveyOpenEditMode()) {
                    showDialog("", getString(R.string.msg_change_location), getString(R.string.cmn_yes), (dialog, which) -> plotPoint(e), getString(R.string.cmn_no), (dialog, which) -> dialog.cancel());
                }else{
                    //Only view
                    showInfoDialog(getString(R.string.msg_not_change_location));
                }
            } else {
                plotPoint(e);
            }
            return true;
        }

    }

    public void plotPoint(MotionEvent e) {
        if (currentPointGraphic != null) {
            mGraphicsOverlay.getGraphics().remove(currentPointGraphic);
        }

        android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
        Point mapPoint = mMapView.screenToLocation(screenPoint);
        if (mapPoint != null) {
            Point wgs84Point = (Point) GeometryEngine.project(mapPoint, SpatialReferences.getWgs84());
            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CROSS, Color.rgb(255, 0, 0), 20.0f);
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.RED, 1.0f));
            currentPointGraphic = new Graphic(wgs84Point, pointSymbol);
            mGraphicsOverlay.getGraphics().add(currentPointGraphic);
            latitude = wgs84Point.getY();
            longitude = wgs84Point.getX();
        }

    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void zoomToCurrentLocation(double latitude, double longitude) {
        if (latitude != 0 && longitude != 0 && mMapView != null) {
            currentLatitude = latitude;
            currentLongitude = longitude;
            mMapView.setViewpoint(new Viewpoint(new Point(longitude, latitude, SpatialReferences.getWgs84()), 100));
            BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.img_marker);
            final PictureMarkerSymbol pinStarBlueSymbol = new PictureMarkerSymbol(pinStarBlueDrawable);
            //Optionally set the size, if not set the image will be auto sized based on its size in pixels,
            //its appearance would then differ across devices with different resolutions.
            pinStarBlueSymbol.setHeight(40);
            pinStarBlueSymbol.setWidth(40);
            //Optionally set the offset, to align the base of the symbol aligns with the point geometry
//            pinStarBlueSymbol.setOffsetY(11);
            //The image used for the symbol has a transparent buffer around it, so the offset is not simply height/2
            Point pinStarBluePoint = new Point(longitude, latitude, SpatialReferences.getWgs84());
            if (currentLocationGraphic != null)
                mGraphicsOverlay.getGraphics().remove(currentLocationGraphic);
            Graphic pinStarBlueGraphic = new Graphic(pinStarBluePoint, pinStarBlueSymbol);
            mGraphicsOverlay.getGraphics().add(pinStarBlueGraphic);
        }
    }

    @Override
    public void onLocationFetchError() {
        showToast(R.string.error_fetch_location);
    }

    @Override
    public void plotAllPropertyPoints(List<SurveyWithProperty> surveyWithProperties) {
        for (SurveyWithProperty properties : surveyWithProperties) {
            if (properties.latitude != 0 && properties.longitude != 0 && mMapView != null) {
                Point point = new Point(properties.longitude, properties.latitude, SpatialReferences.getWgs84());
                SimpleMarkerSymbol pointSymbol = getMarkSymbolForPointDCGLNC(false, properties.pointStatus, properties.buildingStatus, properties.doorStatus);
                if (pointSymbol != null) {
                    if (properties.latitude == latitude && properties.longitude == longitude) {
                        SimpleMarkerSymbol currentSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CROSS, Color.rgb(255, 0, 0), 20.0f);
                        currentSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.RED, 1.0f));
                        currentPointGraphic = new Graphic(point, currentSymbol);
                        mGraphicsOverlay.getGraphics().add(currentPointGraphic);
                    } else {
                        Graphic pointGraphic = new Graphic(point, pointSymbol);
                        mGraphicsOverlay.getGraphics().add(pointGraphic);
                    }
                }
            }
        }
        plotServerPoints();
    }

    /**
     * This is to plot property points which are already surveyed
     * data will getting from API call(Survey points under single ward)
     */

    private void plotServerPoints() {
        SurvryPointsResponse survryPointsResponse = viewModel.getSurveyPoints();
        if (survryPointsResponse != null && survryPointsResponse.getSurveyPointsList() != null) {
            for (int i = 0; i < survryPointsResponse.getSurveyPointsList().size(); i++) {
                Double longVal = null, latVal = null;
                longVal = Double.valueOf(survryPointsResponse.getSurveyPointsList().get(i).longitude);
                latVal = Double.valueOf(survryPointsResponse.getSurveyPointsList().get(i).latitude);
                String pointStatus = "";
                if (!survryPointsResponse.getSurveyPointsList().get(i).getBldgstatus().equalsIgnoreCase(AppConstants.NA_UPPERCASE)) {
                    pointStatus = AppConstants.POINT_STATUS_BUILDING;
                }
                Point point = new Point(longVal, latVal, SpatialReferences.getWgs84());
                SimpleMarkerSymbol pointSymbol = getMarkSymbolForPointDCGLNC(true, pointStatus, survryPointsResponse.getSurveyPointsList().get(i).bldgstatus, survryPointsResponse.getSurveyPointsList().get(i).doorstatus);
                if (pointSymbol != null) {
                    Graphic pointGraphic = new Graphic(point, pointSymbol);
                    mGraphicsOverlay.getGraphics().add(pointGraphic);
                }
            }

        }

    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    public void plotLiveLocation(PointCollection stateCapitalsPST) {
        Multipoint multipoint = new Multipoint(stateCapitalsPST, SpatialReferences.getWgs84());
        if (pointGraphicLivePoint != null)
            mGraphicsOverlay.getGraphics().remove(pointGraphicLivePoint);
        BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.live_location_icon);
        final PictureMarkerSymbol pinStarBlueSymbol = new PictureMarkerSymbol(pinStarBlueDrawable);
        //Optionally set the size, if not set the image will be auto sized based on its size in pixels,
        //its appearance would then differ across devices with different resolutions.
        pinStarBlueSymbol.setHeight(30);
        pinStarBlueSymbol.setWidth(30);

        pointGraphicLivePoint = new Graphic(multipoint, pinStarBlueSymbol);
        mGraphicsOverlay.getGraphics().add(pointGraphicLivePoint);

    }

    @Override
    public void showColourDetailspopup() {
        showMapColourCodePopup();
    }
}
