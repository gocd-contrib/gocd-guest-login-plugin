package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.model.AuthConfig;
import cd.go.authorization.guest.utils.Util;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.List;

public class GetAuthorizationServerRedirectUrlExecutor {
    private GoPluginApiRequest request;

    public GetAuthorizationServerRedirectUrlExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    public GoPluginApiResponse execute() {
        final List<AuthConfig> authConfigs = AuthConfig.fromJSONList(request.requestBody());
        String serverUrl = authConfigs.get(0).getConfiguration().getServerUrl();

        return DefaultGoPluginApiResponse.success(String.format("{\"authorization_server_url\": \"%s/plugin/%s/authenticate\"}", serverUrl, Util.pluginId()));
    }
}
