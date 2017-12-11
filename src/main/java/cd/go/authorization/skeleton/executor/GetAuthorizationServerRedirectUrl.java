package cd.go.authorization.skeleton.executor;

import cd.go.authorization.skeleton.model.AuthConfig;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.List;

import static cd.go.authorization.skeleton.Constants.PLUGIN_ID;

public class GetAuthorizationServerRedirectUrl {
    private GoPluginApiRequest request;

    public GetAuthorizationServerRedirectUrl(GoPluginApiRequest request) {
        this.request = request;
    }

    public GoPluginApiResponse execute() {
        final List<AuthConfig> authConfigs = AuthConfig.fromJSONList(request.requestBody());
        String serverUrl = authConfigs.get(0).getConfiguration().getServerUrl();

        return DefaultGoPluginApiResponse.success(String.format("{\"authorization_server_url\": \"%s/plugin/%s/authenticate\"}", serverUrl, PLUGIN_ID));
    }
}
