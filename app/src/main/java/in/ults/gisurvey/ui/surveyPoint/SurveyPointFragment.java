package in.ults.gisurvey.ui.surveyPoint;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.esri.arcgisruntime.geometry.Multipoint;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.databinding.FragmentPointPlotBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.AppConstants;

/**
 * Created by anuag on 26-06-2020
 **/
public class SurveyPointFragment extends BaseFragment<FragmentPointPlotBinding, SurveyPointViewModel> implements SurveyPointNavigator {
    public static final String TAG = SurveyPointFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private SurveyPointViewModel viewModel;
    private MapView mMapView;
    private GraphicsOverlay mGraphicsOverlay;
    private double currentLatitude = 0;
    private double currentLongitude = 0;
    private Graphic currentLocationGraphic;
    private boolean isPointPlotted = false;
    private double latitude = 0;
    private double longitude = 0;
    Graphic pointGraphicLivePoint;

    public static SurveyPointFragment newInstance() {
        Bundle args = new Bundle();
        SurveyPointFragment fragment = new SurveyPointFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_point_plot;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_survey_point);
    }


    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        mMapView = getViewDataBinding().mapView;
        viewModel.setLocationUpdates();
        setupOfflineMap();
    }

    @Override
    public SurveyPointViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SurveyPointViewModel.class);
        return viewModel;
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
//            mMapView.setOnTouchListener(new ArcGisActivity.MapTouchListener(this, mMapView));
            viewModel.fetchPropertyListData();
        }else if(tpkFileLoc==null){
            showToast("Need to choose tpk file");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (requestingLocationUpdates) {
        viewModel.startLocationUpdates();
//        }
    }



    @Override
    public void onPause() {
        super.onPause();
        viewModel.stopLocationUpdates();
    }
    @Override
    public void plotAllPropertyPoints(List<SurveyWithProperty> surveyWithProperties) {
        if (surveyWithProperties != null && surveyWithProperties.size() > 0) {
            for (SurveyWithProperty properties : surveyWithProperties) {
                if (properties.latitude != 0 && properties.longitude != 0 && mMapView != null) {
                    Point point = new Point(properties.longitude, properties.latitude, SpatialReferences.getWgs84());
                    SimpleMarkerSymbol pointSymbol = getBaseActivity().getMarkSymbolForPointDCGLNC(false,properties.pointStatus,properties.buildingStatus,properties.doorStatus);
                    if(pointSymbol!=null){
                        Graphic pointGraphic = new Graphic(point, pointSymbol);
                        mGraphicsOverlay.getGraphics().add(pointGraphic);
                    }
                }
            }
        } else {
            showToast("No completed survey");
        }
        plotServerPoints();

    }

    @Override
    public void zoomToCurrentLocation(double latitude, double longitude) {
        if (latitude != 0 && longitude != 0 && mMapView != null) {
            currentLatitude = latitude;
            currentLongitude = longitude;
            mMapView.setViewpoint(new Viewpoint(new Point(longitude, latitude, SpatialReferences.getWgs84()), 100));
            BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.img_marker);
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
            currentLocationGraphic = new Graphic(pinStarBluePoint, pinStarBlueSymbol);
            mGraphicsOverlay.getGraphics().add(currentLocationGraphic);

        }
    }

    @Override
    public void onLocationFetchError() {
        showToast(R.string.error_fetch_location);
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
    public void setMarkedLocation(double latitude, double longitude) {
        if (latitude != 0 && longitude != 0 && mMapView != null) {
            isPointPlotted = true;
            this.latitude = latitude;
            this.longitude = longitude;
            mMapView.setViewpoint(new Viewpoint(new Point(longitude, latitude, SpatialReferences.getWgs84()), 100));
        }
        viewModel.fetchPropertyListData();
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
                String pointStatus="";
                if(!survryPointsResponse.getSurveyPointsList().get(i).getBldgstatus().equalsIgnoreCase(AppConstants.NA_UPPERCASE)){
                    pointStatus=AppConstants.POINT_STATUS_BUILDING;
                }
                Point point = new Point(longVal, latVal, SpatialReferences.getWgs84());
                SimpleMarkerSymbol pointSymbol = getBaseActivity().getMarkSymbolForPointDCGLNC(true,pointStatus, survryPointsResponse.getSurveyPointsList().get(i).bldgstatus,survryPointsResponse.getSurveyPointsList().get(i).doorstatus);
                if (pointSymbol != null) {
                    Graphic pointGraphic = new Graphic(point, pointSymbol);
                    mGraphicsOverlay.getGraphics().add(pointGraphic);
                }
            }

        }

    }
    @Override
    public void plotLiveLocation(PointCollection stateCapitalsPST) {
        Multipoint multipoint = new Multipoint(stateCapitalsPST, SpatialReferences.getWgs84());
        if (pointGraphicLivePoint != null)
            mGraphicsOverlay.getGraphics().remove(pointGraphicLivePoint);
        BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(getBaseActivity(), R.drawable.live_location_icon);
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
        getBaseActivity().showMapColourCodePopup();
    }
}
