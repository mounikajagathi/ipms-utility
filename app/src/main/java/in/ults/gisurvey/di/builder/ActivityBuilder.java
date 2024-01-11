package in.ults.gisurvey.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.ults.gisurvey.ui.about.AboutFragmentProvider;
import in.ults.gisurvey.ui.auth.AuthActivity;
import in.ults.gisurvey.ui.auth.forgotpassword.ForgotPasswordFragmentProvider;
import in.ults.gisurvey.ui.auth.login.LoginFragmentProvider;
import in.ults.gisurvey.ui.auth.serverurl.SeverUrlFragmentProvider;
import in.ults.gisurvey.ui.details.DetailsActivity;
import in.ults.gisurvey.ui.main.MainActivity;
import in.ults.gisurvey.ui.main.completed.CompletedSurveyFragmentProvider;
import in.ults.gisurvey.ui.main.home.HomeFragmentProvider;
import in.ults.gisurvey.ui.main.partialsurvey.PartialSurveyFragmentProvider;
import in.ults.gisurvey.ui.main.profile.ProfileFragmentProvider;
import in.ults.gisurvey.ui.main.settings.SettingsFragmentProvider;
import in.ults.gisurvey.ui.main.surveyor.SurveyoDetailsrFragmentProvider;
import in.ults.gisurvey.ui.main.surveyor.SurveyorFragmentProvider;
import in.ults.gisurvey.ui.main.syncgrid.SyncGridFragmentProvider;
import in.ults.gisurvey.ui.main.synclist.SyncListFragmentProvider;
import in.ults.gisurvey.ui.main.utility.UtilityActivity;
import in.ults.gisurvey.ui.main.utility.bridge.BridgeFragmentProvider;
import in.ults.gisurvey.ui.main.utility.busstop.BusStopFragmentProvider;
import in.ults.gisurvey.ui.main.utility.canalline.CanalLineFragmentProvider;
import in.ults.gisurvey.ui.main.utility.culvert.CulvertFragmentProvider;
import in.ults.gisurvey.ui.main.utility.divider.DividerFragmentProvider;
import in.ults.gisurvey.ui.main.utility.drainage.DrainageFragmentProvider;
import in.ults.gisurvey.ui.main.utility.garbage.GarbageFragmentProvider;
import in.ults.gisurvey.ui.main.utility.highmastlight.HighMastLightFragmentProvider;
import in.ults.gisurvey.ui.main.utility.home.UtilityHomeFragmentProvider;
import in.ults.gisurvey.ui.main.utility.junction.JunctionFragmentProvider;
import in.ults.gisurvey.ui.main.utility.lowmastlight.LowMastLightFragmentProvider;
import in.ults.gisurvey.ui.main.utility.map.MapFragmentProvider;
import in.ults.gisurvey.ui.main.utility.park.ParkFragmentProvider;
import in.ults.gisurvey.ui.main.utility.parking.ParkingFragmentProvider;
import in.ults.gisurvey.ui.main.utility.playground.PlaygroundFragmentProvider;
import in.ults.gisurvey.ui.main.utility.pond.PondFragmentProvider;
import in.ults.gisurvey.ui.main.utility.sidewalk.SideWalkFragmentProvider;
import in.ults.gisurvey.ui.main.utility.signboard.SignboardFragmentProvider;
import in.ults.gisurvey.ui.main.utility.stadium.StadiumFragmentProvider;
import in.ults.gisurvey.ui.main.utility.streetlight.StreetLightFragmentProvider;
import in.ults.gisurvey.ui.main.utility.streettap.StreetTapFragmentProvider;
import in.ults.gisurvey.ui.main.utility.tank.TankFragmentProvider;
import in.ults.gisurvey.ui.main.utility.taxistand.TaxiStandFragmentProvider;
import in.ults.gisurvey.ui.main.utility.transformer.TransformerFragmentProvider;
import in.ults.gisurvey.ui.main.utility.well.WellFragmentProvider;
import in.ults.gisurvey.ui.main.ward.WardSelectionFragmentProvider;
import in.ults.gisurvey.ui.report.ReportFragmentProvider;
import in.ults.gisurvey.ui.serverSurvey.detials.ServerSurveyDetailsFragmentProvider;
import in.ults.gisurvey.ui.serverSurvey.list.ServerSurveyFragmentProvider;
import in.ults.gisurvey.ui.splash.SplashActivity;
import in.ults.gisurvey.ui.survey.SurveyActivity;
import in.ults.gisurvey.ui.survey.arcgis.ArcGisActivity;
import in.ults.gisurvey.ui.survey.basement.BasementFragmentProvider;
import in.ults.gisurvey.ui.survey.building.BuildingFragmentProvider;
import in.ults.gisurvey.ui.survey.completesurvey.CompleteSurveyFragmentProvider;
import in.ults.gisurvey.ui.survey.establishment.EstablishmentFragmentProvider;
import in.ults.gisurvey.ui.survey.floorcount.FloorCountFragmentProvider;
import in.ults.gisurvey.ui.survey.groundfloorselection.GroundFloorSelectionFragmentProvider;
import in.ults.gisurvey.ui.survey.image.ImagesFragmentProvider;
import in.ults.gisurvey.ui.survey.livehood.LiveHoodFragmentProvider;
import in.ults.gisurvey.ui.survey.location.LocationFragmentProvider;
import in.ults.gisurvey.ui.survey.member.MemberFragmentProvider;
import in.ults.gisurvey.ui.survey.more.MoreFragmentProvider;
import in.ults.gisurvey.ui.survey.owner.OwnerFragmentProvider;
import in.ults.gisurvey.ui.survey.pointstatus.PointStatusFragmentProvider;
import in.ults.gisurvey.ui.survey.property.PropertyFragmentProvider;
import in.ults.gisurvey.ui.survey.road.RoadFragmentProvider;
import in.ults.gisurvey.ui.survey.selection.SelectionFragmentProvider;
import in.ults.gisurvey.ui.survey.startsurvey.StartSurveyFragmentProvider;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridFragmentProvider;
import in.ults.gisurvey.ui.survey.tax.TaxFragmentProvider;
import in.ults.gisurvey.ui.survey.tenant.TenantFragmentProvider;
import in.ults.gisurvey.ui.surveyPoint.SurveyPointFragmentProvider;

@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = {
            LoginFragmentProvider.class,
            ForgotPasswordFragmentProvider.class,
            SeverUrlFragmentProvider.class})
    abstract AuthActivity bindAuthActivity();

    @ContributesAndroidInjector(modules = {
            HomeFragmentProvider.class,
            AboutFragmentProvider.class,
            SurveyPointFragmentProvider.class,
            SettingsFragmentProvider.class,
            SyncGridFragmentProvider.class,
            SyncListFragmentProvider.class,
            CompletedSurveyFragmentProvider.class,
            PartialSurveyFragmentProvider.class,
            ProfileFragmentProvider.class,
            SurveyorFragmentProvider.class,
            SurveyoDetailsrFragmentProvider.class,
            WardSelectionFragmentProvider.class,
            ReportFragmentProvider.class,
            ServerSurveyFragmentProvider.class,
            ServerSurveyDetailsFragmentProvider.class,})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
            UtilityHomeFragmentProvider.class,
            BridgeFragmentProvider.class,
            BusStopFragmentProvider.class,
            CanalLineFragmentProvider.class,
            CulvertFragmentProvider.class,
            DividerFragmentProvider.class,
            DrainageFragmentProvider.class,
            GarbageFragmentProvider.class,
            HighMastLightFragmentProvider.class,
            JunctionFragmentProvider.class,
            LowMastLightFragmentProvider.class,
            MapFragmentProvider.class,
            ParkFragmentProvider.class,
            ParkingFragmentProvider.class,
            PlaygroundFragmentProvider.class,
            PondFragmentProvider.class,
            in.ults.gisurvey.ui.main.utility.road.RoadFragmentProvider.class,
            SideWalkFragmentProvider.class,
            SignboardFragmentProvider.class,
            StadiumFragmentProvider.class,
            StreetLightFragmentProvider.class,
            StreetTapFragmentProvider.class,
            TankFragmentProvider.class,
            TaxiStandFragmentProvider.class,
            TransformerFragmentProvider.class,
            WellFragmentProvider.class})
    abstract UtilityActivity bindUtilityActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract DetailsActivity bindDetailsActivity();

    @ContributesAndroidInjector
    abstract ArcGisActivity bindArcGisActivity();

    @ContributesAndroidInjector(modules = {
            StartSurveyFragmentProvider.class,
            BuildingFragmentProvider.class,
            LiveHoodFragmentProvider.class,
            MoreFragmentProvider.class,
            MemberFragmentProvider.class,
            PropertyFragmentProvider.class,
            SurveyGridFragmentProvider.class,
            ImagesFragmentProvider.class,
            SelectionFragmentProvider.class,
            TaxFragmentProvider.class,
            TenantFragmentProvider.class,
            OwnerFragmentProvider.class,
            LocationFragmentProvider.class,
            RoadFragmentProvider.class,
            EstablishmentFragmentProvider.class,
            PointStatusFragmentProvider.class,
            BasementFragmentProvider.class,
            GroundFloorSelectionFragmentProvider.class,
            FloorCountFragmentProvider.class,
            CompleteSurveyFragmentProvider.class})
    abstract SurveyActivity bindSurveyActivity();


}
