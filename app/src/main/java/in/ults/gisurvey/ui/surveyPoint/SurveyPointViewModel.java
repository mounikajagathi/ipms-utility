package in.ults.gisurvey.ui.surveyPoint;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;

import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.List;
import java.util.concurrent.TimeUnit;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;
import pl.charmas.android.reactivelocation2.ReactiveLocationProviderConfiguration;

/**
 * Created by anuag on 26-06-2020
 **/
public class SurveyPointViewModel extends BaseViewModel<SurveyPointNavigator> {
    private final MutableLiveData<List<Survey>> completedSurveyList = new MutableLiveData<>();
    private ReactiveLocationProvider locationProvider;
    private static final int GPS_ENABLE_REQUEST_CODE = 974;
    private Disposable currentLocationDisposable;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;


    private FusedLocationProviderClient fusedLocationClient;
    public SurveyPointViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    protected void onCompletedSurvey(List<Survey> completedSurvey) {
        setCompletedSurveyList(completedSurvey);
        for (Survey s : completedSurvey) {

        }
    }

    public void setCompletedSurveyList(List<Survey> completedSurveyList) {
        this.completedSurveyList.setValue(completedSurveyList);
    }

    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        if (property.latitude == 0 && property.longitude == 0) {
            fetchPropertyListData();
//            checkLocation();
        } else {
            getNavigator().setMarkedLocation(property.latitude, property.longitude);
        }
    }
    void fetchPropertyListData() {
        getCompositeDisposable().add(getDataManager()
                .loadAllSurveyWithProperty()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(surveyWithProperties -> {
                        getNavigator().plotAllPropertyPoints(surveyWithProperties);
                }).subscribe());
    }
    public  void setLocationUpdates(){
        if(getLiveLocationStatus()!=null&&getLiveLocationStatus().equalsIgnoreCase(AppConstants.LIVE_LOC_ON)){
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(getBaseActivity());

        }


    }
    public void stopLocationUpdates(){
        if(getLiveLocationStatus()!=null&&getLiveLocationStatus().equalsIgnoreCase(AppConstants.LIVE_LOC_ON)) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }
    public void startLocationUpdates() {
        if(getLiveLocationStatus()!=null&&getLiveLocationStatus().equalsIgnoreCase(AppConstants.LIVE_LOC_ON)) {

            showEnableGPSPopup();
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    PointCollection stateCapitalsPST = new PointCollection(SpatialReferences.getWgs84());
                    for (Location location : locationResult.getLocations()) {
//                    // Update UI with location data
//                    // ......
                        if (location.getLongitude() != 0 && location.getLatitude() != 0) {
                            stateCapitalsPST.add(location.getLongitude(), location.getLatitude());

                        }
                    }
                    if (stateCapitalsPST != null) {
                        getNavigator().plotLiveLocation(stateCapitalsPST);
                    }
                }
            };
            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    Looper.getMainLooper());
        }
    }
    private void showEnableGPSPopup() {

        locationProvider = new ReactiveLocationProvider(getBaseActivity().getApplicationContext(),
                ReactiveLocationProviderConfiguration
                        .builder()
                        .setRetryOnConnectionSuspended(true)
                        .build());

        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(5000);


        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true)
                .build();


        Observable<Location> currentLocationObservable = locationProvider.
                checkLocationSettings(locationSettingsRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .delaySubscription(3, TimeUnit.SECONDS)
                .doOnNext(locationSettingsResult -> {
                    Status status = locationSettingsResult.getStatus();
                    if (status.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                        try {
                            status.startResolutionForResult(getBaseActivity(), GPS_ENABLE_REQUEST_CODE);
                        } catch (IntentSender.SendIntentException th) {
                            th.printStackTrace();

                        }
                    }
                })
                .flatMap(locationSettingsResult -> {
                    Status status = locationSettingsResult.getStatus();
                    if (status.getStatusCode() != LocationSettingsStatusCodes.SUCCESS) {
                        return null;
                    }
                    if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return null;
                    }
                    return locationProvider.getUpdatedLocation(locationRequest);
                });
        currentLocationDisposable = currentLocationObservable.subscribe();
        getCompositeDisposable().add(currentLocationDisposable);

    }
    private void checkLocation() {

        locationProvider = new ReactiveLocationProvider(getBaseActivity().getApplicationContext(),
                ReactiveLocationProviderConfiguration
                        .builder()
                        .setRetryOnConnectionSuspended(true)
                        .build());

        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(5000);


        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true)
                .build();


        Observable<Location> currentLocationObservable = locationProvider.
                checkLocationSettings(locationSettingsRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .delaySubscription(3, TimeUnit.SECONDS)
                .doOnSubscribe(disposable -> getNavigator().showProgress())
                .doFinally(() -> getNavigator().hideProgress())
                .doOnError(throwable -> {
                    getNavigator().onLocationFetchError();
                    finishLocationCheck();
                })
                .doOnNext(locationSettingsResult -> {
                    Status status = locationSettingsResult.getStatus();
                    if (status.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                        try {
                            status.startResolutionForResult(getBaseActivity(), GPS_ENABLE_REQUEST_CODE);
                        } catch (IntentSender.SendIntentException th) {
                            th.printStackTrace();
                            getNavigator().onLocationFetchError();
                            finishLocationCheck();
                        }
                    }
                })
                .flatMap(locationSettingsResult -> {
                    Status status = locationSettingsResult.getStatus();
                    if (status.getStatusCode() != LocationSettingsStatusCodes.SUCCESS) {
                        return null;
                    }
                    if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return null;
                    }
                    return locationProvider.getUpdatedLocation(locationRequest);
                })
                .doOnNext(location -> {
                    getNavigator().zoomToCurrentLocation(location.getLatitude(), location.getLongitude());
                    finishLocationCheck();
                });
        currentLocationDisposable = currentLocationObservable.subscribe();
        getCompositeDisposable().add(currentLocationDisposable);

    }
    private void finishLocationCheck() {
        if (currentLocationDisposable != null && !currentLocationDisposable.isDisposed()) {
            currentLocationDisposable.dispose();
        }
    }
    public void currentLocationOnClick() {
        checkLocation();
    }
    public void previousLocationOnClick() {
        previousLocation();
    }
    private void previousLocation() {
        getCompositeDisposable().add(getDataManager()
                .getPreviousProperty( getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(property -> {
                    if (property != null) {
                        getNavigator().zoomToCurrentLocation(property.latitude, property.longitude);

                    }else {
                        getBaseActivity().showToast(getBaseActivity().getString(R.string.error_no_prevois_location));
                    }
                })
                .doOnError(throwable -> {
                    getBaseActivity().showToast(getBaseActivity().getString(R.string.error_no_prevois_location));
                })
                .subscribe());

    }
    public void colourDetailsOnClick() {
        getNavigator().showColourDetailspopup();
    }
}
