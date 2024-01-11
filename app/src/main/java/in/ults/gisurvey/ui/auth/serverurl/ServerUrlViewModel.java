package in.ults.gisurvey.ui.auth.serverurl;


import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.LoginRequest;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class ServerUrlViewModel extends BaseViewModel<ServerUrlNavigator> {
    public final MutableLiveData<String> url = new MutableLiveData<>();
    public final MutableLiveData<String> port = new MutableLiveData<>();
    public ServerUrlViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * validation of email
     * show validation error if validation fails
     * @param serverAddress
     * @return
     */
    protected boolean validateFields(String serverAddress,String port) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(serverAddress)) {
            getNavigator().validationError(ServerUrlFragment.ADDRESS_ERROR, getBaseActivity().getString(R.string.enter_server_url));
            return false;
        } else if (TextUtils.isEmpty(port)) {
            getNavigator().validationError(ServerUrlFragment.PORT_ERROR, getBaseActivity().getString(R.string.enter_port_no));
            return false;
        } else {
            return true;
        }
    }

    /**
     * Setting url from shared preference
     */
    public void setData(){
        if(getDataManager().getUrl()!=null){
             url.setValue(getDataManager().getUrl());
        }
        if(getDataManager().getPort()!=null) {
            port.setValue(getDataManager().getPort());
        }
    }

    /**
     *verifiy entered URL is correct or not
     * befor that set server URL on shared pref
     * @param address
     */
    public void verifyAddress(String address,String portNo) {
//
        Log.i("API_CHECK_COND","-"+getBaseUrlInFormat(address,portNo));
        if (CommonUtils.isNetworkConnected(getBaseActivity())) {
            getNavigator().showProgressDialog();
            getCompositeDisposable().add(getDataManager()
                    .doApiURLCheck(getBaseUrlInFormat(address,portNo))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(() -> getNavigator().hideProgressDialog())
                    .doOnError(error->{
                        Log.i("API_CHECK_COND","-"+error.getMessage());
                        if(error.getMessage().contains("UnknownHostException")){
                            getBaseActivity().showToast(getBaseActivity().getString(R.string.wrong_address));
                        }else if(error.getMessage().contains("SocketTimeoutException")){
                            getBaseActivity().showToast(getBaseActivity().getString(R.string.socket_issue));
                        } else{
                            getBaseActivity().showToast(getBaseActivity().getString(R.string.server_issue));
                        }


                    })
                    .doOnNext(urlCheckResponse -> {
                        if(urlCheckResponse.getStatus().equalsIgnoreCase("true")){
                            getDataManager().setServerUrl(getBaseUrlInFormat(address,portNo));
                            getDataManager().setUrl(address);
                            getDataManager().setPort(portNo);
                            getBaseActivity().showLoginFragment(false);
                        }
                    }).subscribe());
        } else {
            getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
        }
    }


    public void onResetPasswordClick() {
        getNavigator().verify();
    }
}
