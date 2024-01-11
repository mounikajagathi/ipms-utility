package in.ults.gisurvey.ui.main.synclist;

import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.DataSyncResponse;
import in.ults.gisurvey.data.model.api.ImageSyncResponse;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.FileUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SyncListViewModel extends BaseViewModel<SyncListNavigator> {

    private final MutableLiveData<List<SurveyWithProperty>> syncList = new MutableLiveData<>();
    private final MutableLiveData<List<SurveyWithProperty>> syncListAddedNA = new MutableLiveData<>();


    public SyncListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public MutableLiveData<List<SurveyWithProperty>> getSyncList() {
        return syncList;
    }

    private void setSyncList(List<SurveyWithProperty> syncList) {
        this.syncList.setValue(syncList);

        List<SurveyWithProperty> dataList = new ArrayList<>();
        for (SurveyWithProperty data : syncList) {
            data.addNA();
            dataList.add(data);
        }
        this.syncListAddedNA.setValue(dataList);
    }


    private Disposable syncDisposible;


    void fetchSyncListData(int offset) {
        getCompositeDisposable().add(getDataManager()
                .loadAllCompletedSurveyWithPropertyLimit(AppConstants.SYNC_COUNT, offset)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(this::setSyncList)
                .doOnError(throwable -> getBaseActivity().showToast(R.string.sync_status_sync_failed))
                .subscribe());
    }



    /**
     * Syncing process
     */
    public void syncDataOnClick() {
        DateFormat df = new SimpleDateFormat(AppConstants.SURVEY_DATE_FORMAT, Locale.ENGLISH);
        String syncCompletedDate = df.format(Calendar.getInstance().getTime());

        getNavigator().preventDoubleClick();
        if (syncListAddedNA.getValue() != null && syncListAddedNA.getValue().size() > 0) {
            List<String> selectedIds = new ArrayList<>();
            List<String> selectedImages = new ArrayList<>();
            for (SurveyWithProperty data : syncListAddedNA.getValue()) {
                selectedIds.add(data.propertyID);
                selectedImages.add(data.propertyImageOne);
                selectedImages.add(data.propertyImageTwo);
                selectedImages.add(data.propertyImageThree);
            }
            List<File> data = FileUtils.getSurveyImages(selectedImages);

            if (CommonUtils.isNetworkConnected(getBaseActivity())) {
                syncDisposible = Observable.timer(2, TimeUnit.SECONDS)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnNext(aLong -> getNavigator().updateSyncStatusLabels(getBaseActivity().getString(R.string.sync_status_syncing_data)))
                        .flatMap((Function<Long, ObservableSource<Long>>) aLong -> Observable.timer(4, TimeUnit.SECONDS))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().io())
                        .flatMap((Function<Long, ObservableSource<DataSyncResponse>>) aLong ->
                                getDataManager().doDataSyncToServer(new Gson().toJson(syncListAddedNA.getValue()),
                                        getDataManager().getAccessToken(),getDataManager().getServerUrl()))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .flatMap((Function<DataSyncResponse, ObservableSource<Boolean>>) dataSyncResponse -> {
                            if (dataSyncResponse.getStatus().equalsIgnoreCase("true")) {
                                getNavigator().updateSyncStatusLabels(getBaseActivity().getString(R.string.sync_status_successfully_synced));
                                return Observable.fromCallable(() -> true);
                            } else {
                                return Observable.empty();
                            }
                        })
                        .doOnNext(aBoolean -> {
                            getNavigator().showUploadCount();
                            getNavigator().updateSyncStatusLabels(getBaseActivity().getString(R.string.sync_status_uploading_images));
                        })
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().io())
                        .flatMap((Function<Boolean, ObservableSource<ImageSyncResponse>>) aBoolean -> getDataManager()
                                .uploadMultipleImages(data, getDataManager().getAccessToken(), (bytesUploaded, totalBytes) -> getNavigator().updateUploadPercent(bytesUploaded, totalBytes),getDataManager().getServerUrl()))
                        .flatMap((Function<ImageSyncResponse, ObservableSource<Boolean>>) imageSyncResponse -> getDataManager().updateSyncCompletedStatus(selectedIds,syncCompletedDate))
                        .doOnNext(aBoolean -> FileUtils.removeSurveyImages(selectedImages))
                        .flatMap((Function<Boolean, ObservableSource<?>>) aBoolean -> Observable.timer(4, TimeUnit.SECONDS))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnSubscribe(disposable -> getNavigator().showSyncDialog())
                        .doOnError(throwable -> {
                            getBaseActivity().showToast(getBaseActivity().getString(R.string.sync_status_sync_failed));
                        })
                        .doFinally(() -> getNavigator().refreshPage())
                        .subscribe();

                getCompositeDisposable().add(syncDisposible);
            } else {
                getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
            }
        }
    }

    void stopSync() {
        if (syncDisposible != null) {
            syncDisposible.dispose();
        }
        getNavigator().dismissSyncDialog();
    }
}