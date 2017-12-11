package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.Authenticator;
import cd.go.authorization.guest.model.AuthConfig;
import cd.go.authorization.guest.model.AuthenticationResponse;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse.SUCCESS_RESPONSE_CODE;

public class UserAuthenticationExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;
    private final Authenticator authenticator;

    public UserAuthenticationExecutor(GoPluginApiRequest request, Authenticator authenticator) {
        this.request = request;
        this.authenticator = authenticator;
    }

    @Override
    public GoPluginApiResponse execute() throws Exception {
        final List<AuthConfig> authConfigs = AuthConfig.fromJSONList(request.requestBody());

        AuthenticationResponse authenticationResponse = authenticator.authenticate(authConfigs);

        Map<String, Object> userMap = new HashMap<>();
        if (authenticationResponse != null) {
            userMap.put("user", authenticationResponse.getUser());
            userMap.put("roles", new ArrayList<>());
        }

        DefaultGoPluginApiResponse response = new DefaultGoPluginApiResponse(SUCCESS_RESPONSE_CODE, GSON.toJson(userMap));
        return response;
    }
}
