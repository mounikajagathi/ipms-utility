package in.ults.gisurvey;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.about.AboutViewModel;
import in.ults.gisurvey.ui.auth.AuthViewModel;
import in.ults.gisurvey.ui.auth.forgotpassword.ForgotPasswordViewModel;
import in.ults.gisurvey.ui.auth.login.LoginViewModel;
import in.ults.gisurvey.ui.auth.serverurl.ServerUrlViewModel;
import in.ults.gisurvey.ui.details.DetailsViewModel;
import in.ults.gisurvey.ui.main.MainViewModel;
import in.ults.gisurvey.ui.main.completed.CompletedSurveyViewModel;
import in.ults.gisurvey.ui.main.home.HomeViewModel;
import in.ults.gisurvey.ui.main.partialsurvey.PartialSurveyViewModel;
import in.ults.gisurvey.ui.main.profile.ProfileViewModel;
import in.ults.gisurvey.ui.main.surveyor.SurveyorDetailsViewModel;
import in.ults.gisurvey.ui.main.surveyor.SurveyorViewModel;
import in.ults.gisurvey.ui.main.syncgrid.SyncGridViewModel;
import in.ults.gisurvey.ui.main.synclist.SyncListViewModel;
import in.ults.gisurvey.ui.main.utility.UtilityViewModel;
import in.ults.gisurvey.ui.main.utility.bridge.BridgeViewModel;
import in.ults.gisurvey.ui.main.utility.busstop.BusStopViewModel;
import in.ults.gisurvey.ui.main.utility.canalline.CanalLineViewModel;
import in.ults.gisurvey.ui.main.utility.culvert.CulvertViewModel;
import in.ults.gisurvey.ui.main.utility.divider.DividerViewModel;
import in.ults.gisurvey.ui.main.utility.drainage.DrainageViewModel;
import in.ults.gisurvey.ui.main.utility.garbage.GarbageViewModel;
import in.ults.gisurvey.ui.main.utility.highmastlight.HighMastLightViewModel;
import in.ults.gisurvey.ui.main.utility.home.UtilityHomeViewModel;
import in.ults.gisurvey.ui.main.utility.junction.JunctionViewModel;
import in.ults.gisurvey.ui.main.utility.lowmastlight.LowMastLightViewModel;
import in.ults.gisurvey.ui.main.utility.map.MapViewModel;
import in.ults.gisurvey.ui.main.utility.park.ParkViewModel;
import in.ults.gisurvey.ui.main.utility.parking.ParkingViewModel;
import in.ults.gisurvey.ui.main.utility.playground.PlaygroundViewModel;
import in.ults.gisurvey.ui.main.utility.pond.PondViewModel;
import in.ults.gisurvey.ui.main.utility.sidewalk.SideWalkViewModel;
import in.ults.gisurvey.ui.main.utility.signboard.SignboardViewModel;
import in.ults.gisurvey.ui.main.utility.stadium.StadiumViewModel;
import in.ults.gisurvey.ui.main.utility.streetlight.StreetLightViewModel;
import in.ults.gisurvey.ui.main.utility.streettap.StreetTapViewModel;
import in.ults.gisurvey.ui.main.utility.tank.TankViewModel;
import in.ults.gisurvey.ui.main.utility.taxistand.TaxiStandViewModel;
import in.ults.gisurvey.ui.main.utility.transformer.TransformerViewModel;
import in.ults.gisurvey.ui.main.utility.well.WellViewModel;
import in.ults.gisurvey.ui.main.ward.WardSelectionViewModel;
import in.ults.gisurvey.ui.report.ReportViewModel;
import in.ults.gisurvey.ui.serverSurvey.detials.ServerSurveyDetailsViewModel;
import in.ults.gisurvey.ui.serverSurvey.list.ServerSurveyViewModel;
import in.ults.gisurvey.ui.splash.SplashViewModel;
import in.ults.gisurvey.ui.survey.SurveyViewModel;
import in.ults.gisurvey.ui.survey.arcgis.ArcGisViewModel;
import in.ults.gisurvey.ui.survey.basement.BasementViewModel;
import in.ults.gisurvey.ui.survey.building.BuildingViewModel;
import in.ults.gisurvey.ui.survey.completesurvey.CompleteSurveyViewModel;
import in.ults.gisurvey.ui.survey.establishment.EstablishmentViewModel;
import in.ults.gisurvey.ui.survey.floorcount.FloorCountViewModel;
import in.ults.gisurvey.ui.survey.groundfloorselection.GroundFloorSelectionViewModel;
import in.ults.gisurvey.ui.survey.image.ImagesViewModel;
import in.ults.gisurvey.ui.survey.livehood.LiveHoodViewModel;
import in.ults.gisurvey.ui.survey.location.LocationViewModel;
import in.ults.gisurvey.ui.survey.member.MemberViewModel;
import in.ults.gisurvey.ui.survey.more.MoreViewModel;
import in.ults.gisurvey.ui.survey.owner.OwnerViewModel;
import in.ults.gisurvey.ui.survey.pointstatus.PointStatusViewModel;
import in.ults.gisurvey.ui.survey.property.PropertyViewModel;
import in.ults.gisurvey.ui.survey.road.RoadViewModel;
import in.ults.gisurvey.ui.survey.selection.ScreenSelectionGridViewModel;
import in.ults.gisurvey.ui.survey.startsurvey.StartSurveyViewModel;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridViewModel;
import in.ults.gisurvey.ui.survey.tax.TaxViewModel;
import in.ults.gisurvey.ui.survey.tenant.TenantViewModel;
import in.ults.gisurvey.ui.surveyPoint.SurveyPointViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AboutViewModel.class)) {
            //noinspection unchecked
            return (T) new AboutViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SurveyPointViewModel.class)) {
            //noinspection unchecked
            return (T) new SurveyPointViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AuthViewModel.class)) {
            //noinspection unchecked
            return (T) new AuthViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ForgotPasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new ForgotPasswordViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ServerUrlViewModel.class)) {
            //noinspection unchecked
            return (T) new ServerUrlViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(in.ults.gisurvey.ui.main.settings.SettingsViewModel.class)) {
            //noinspection unchecked
            return (T) new in.ults.gisurvey.ui.main.settings.SettingsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(StartSurveyViewModel.class)) {
            //noinspection unchecked
            return (T) new StartSurveyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SurveyViewModel.class)) {
            //noinspection unchecked
            return (T) new SurveyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BuildingViewModel.class)) {
            //noinspection unchecked
            return (T) new BuildingViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LiveHoodViewModel.class)) {
            //noinspection unchecked
            return (T) new LiveHoodViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MoreViewModel.class)) {
            //noinspection unchecked
            return (T) new MoreViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MemberViewModel.class)) {
            //noinspection unchecked
            return (T) new MemberViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PropertyViewModel.class)) {
            //noinspection unchecked
            return (T) new PropertyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SurveyGridViewModel.class)) {
            //noinspection unchecked
            return (T) new SurveyGridViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TaxViewModel.class)) {
            //noinspection unchecked
            return (T) new TaxViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ScreenSelectionGridViewModel.class)) {
            //noinspection unchecked
            return (T) new ScreenSelectionGridViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TenantViewModel.class)) {
            //noinspection unchecked
            return (T) new TenantViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            //noinspection unchecked
            return (T) new HomeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FloorCountViewModel.class)) {
            //noinspection unchecked
            return (T) new FloorCountViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PointStatusViewModel.class)) {
            //noinspection unchecked
            return (T) new PointStatusViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(GroundFloorSelectionViewModel.class)) {
            //noinspection unchecked
            return (T) new GroundFloorSelectionViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PartialSurveyViewModel.class)) {
            //noinspection unchecked
            return (T) new PartialSurveyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SurveyorViewModel.class)) {
            //noinspection unchecked
            return (T) new SurveyorViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LocationViewModel.class)) {
            //noinspection unchecked
            return (T) new LocationViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RoadViewModel.class)) {
            //noinspection unchecked
            return (T) new RoadViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ArcGisViewModel.class)) {
            //noinspection unchecked
            return (T) new ArcGisViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(EstablishmentViewModel.class)) {
            //noinspection unchecked
            return (T) new EstablishmentViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new ProfileViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OwnerViewModel.class)) {
            //noinspection unchecked
            return (T) new OwnerViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BasementViewModel.class)) {
            //noinspection unchecked
            return (T) new BasementViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ImagesViewModel.class)) {
            //noinspection unchecked
            return (T) new ImagesViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CompleteSurveyViewModel.class)) {
            //noinspection unchecked
            return (T) new CompleteSurveyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CompletedSurveyViewModel.class)) {
            //noinspection unchecked
            return (T) new CompletedSurveyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SyncListViewModel.class)) {
            //noinspection unchecked
            return (T) new SyncListViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SyncGridViewModel.class)) {
            //noinspection unchecked
            return (T) new SyncGridViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SurveyorDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new SurveyorDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ReportViewModel.class)) {
            //noinspection unchecked
            return (T) new ReportViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ServerSurveyViewModel.class)) {
            //noinspection unchecked
            return (T) new ServerSurveyViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ServerSurveyDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new ServerSurveyDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(WardSelectionViewModel.class)) {
            //noinspection unchecked
            return (T) new WardSelectionViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MapViewModel.class)) {
            //noinspection unchecked
            return (T) new MapViewModel(dataManager, schedulerProvider);
            //---------------------------Utility------------------------
        } else if (modelClass.isAssignableFrom(UtilityViewModel.class)) {
            //noinspection unchecked
            return (T) new UtilityViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(UtilityHomeViewModel.class)) {
            //noinspection unchecked
            return (T) new UtilityHomeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BridgeViewModel.class)) {
            //noinspection unchecked
            return (T) new BridgeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BusStopViewModel.class)) {
            //noinspection unchecked
            return (T) new BusStopViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CanalLineViewModel.class)) {
            //noinspection unchecked
            return (T) new CanalLineViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CulvertViewModel.class)) {
            //noinspection unchecked
            return (T) new CulvertViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DividerViewModel.class)) {
            //noinspection unchecked
            return (T) new DividerViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DrainageViewModel.class)) {
            //noinspection unchecked
            return (T) new DrainageViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(GarbageViewModel.class)) {
            //noinspection unchecked
            return (T) new GarbageViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(HighMastLightViewModel.class)) {
            //noinspection unchecked
            return (T) new HighMastLightViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(JunctionViewModel.class)) {
            //noinspection unchecked
            return (T) new JunctionViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LowMastLightViewModel.class)) {
            //noinspection unchecked
            return (T) new LowMastLightViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ParkViewModel.class)) {
            //noinspection unchecked
            return (T) new ParkViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ParkingViewModel.class)) {
            //noinspection unchecked
            return (T) new ParkingViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PlaygroundViewModel.class)) {
            //noinspection unchecked
            return (T) new PlaygroundViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PondViewModel.class)) {
            //noinspection unchecked
            return (T) new PondViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(in.ults.gisurvey.ui.main.utility.road.RoadViewModel.class)) {
            //noinspection unchecked
            return (T) new in.ults.gisurvey.ui.main.utility.road.RoadViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SignboardViewModel.class)) {
            //noinspection unchecked
            return (T) new SignboardViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SideWalkViewModel.class)) {
            //noinspection unchecked
            return (T) new SideWalkViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SignboardViewModel.class)) {
            //noinspection unchecked
            return (T) new SignboardViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(StadiumViewModel.class)) {
            //noinspection unchecked
            return (T) new StadiumViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(StreetLightViewModel.class)) {
            //noinspection unchecked
            return (T) new StreetLightViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(StreetTapViewModel.class)) {
            //noinspection unchecked
            return (T) new StreetTapViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TankViewModel.class)) {
            //noinspection unchecked
            return (T) new TankViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TaxiStandViewModel.class)) {
            //noinspection unchecked
            return (T) new TaxiStandViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(TransformerViewModel.class)) {
            //noinspection unchecked
            return (T) new TransformerViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(WellViewModel.class)) {
            //noinspection unchecked
            return (T) new WellViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}