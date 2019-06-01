package ses1grp6.dbsystemandroid.network;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.json.JSONObject;

public class NetworkFragment extends Fragment {

    private MethodType method;
    private SimpleRequest request;
    private String requestMapping;
    private JSONObject jsonObject;
    private DBSystemNetwork.OnRequestComplete callback;

    public NetworkFragment() {
    }

    public void setupNetworkFragment(MethodType method, String requestMapping, JSONObject jsonObject, DBSystemNetwork.OnRequestComplete callback) {
        this.method = method;
        this.requestMapping = requestMapping;
        this.jsonObject = jsonObject;
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (jsonObject == null) throw new RuntimeException("setupNetworkFragment needs to be called first!");
        request = new SimpleRequest(method, requestMapping, jsonObject, callback);
        request.execute();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        request.clearCallbackReference();
    }
}
