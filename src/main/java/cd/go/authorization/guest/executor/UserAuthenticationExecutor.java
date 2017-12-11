package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.Authenticator;
import cd.go.authorization.guest.Authorizer;
import cd.go.authorization.guest.model.AuthConfig;
import cd.go.authorization.guest.model.AuthenticationResponse;
import cd.go.authorization.guest.model.Credentials;
import cd.go.authorization.guest.model.RoleConfig;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse.SUCCESS_RESPONSE_CODE;

public class UserAuthenticationExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;
    private final Authenticator authenticator;
    private final Authorizer authorizer;

    public UserAuthenticationExecutor(GoPluginApiRequest request, Authenticator authenticator, Authorizer authorizer) {
        this.request = request;
        this.authenticator = authenticator;
        this.authorizer = authorizer;
    }

    @Override
    public GoPluginApiResponse execute() throws Exception {
        Credentials credentials = Credentials.fromJSON(request.requestBody());
        final List<AuthConfig> authConfigs = AuthConfig.fromJSONList(request.requestBody());
        final List<RoleConfig> roleConfigs = RoleConfig.fromJSONList(request.requestBody());

        AuthenticationResponse authenticationResponse = authenticator.authenticate(credentials, authConfigs);

        Map<String, Object> userMap = new HashMap<>();
        if (authenticationResponse != null) {
            userMap.put("user", authenticationResponse.getUser());
            userMap.put("roles", authorizer.authorize(authenticationResponse.getUser(), authenticationResponse.getConfigUsedForAuthentication(), roleConfigs));
        }

        DefaultGoPluginApiResponse response = new DefaultGoPluginApiResponse(SUCCESS_RESPONSE_CODE, GSON.toJson(userMap));
        return response;
    }
}
