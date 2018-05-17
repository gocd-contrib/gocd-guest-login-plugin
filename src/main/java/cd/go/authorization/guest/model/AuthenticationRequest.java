package cd.go.authorization.guest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import static cd.go.authorization.guest.utils.Util.GSON;

public class AuthenticationRequest {
    @Expose
    @SerializedName("credentials")
    private Map<String, Object> credentials;
    @Expose
    @SerializedName("auth_configs")
    private List<AuthConfig> authConfigs;

    public Map<String, Object> getCredentials() {
        return credentials;
    }

    public List<AuthConfig> getAuthConfigs() {
        return authConfigs;
    }

    public static AuthenticationRequest fromJson(String requestBody) {
        return GSON.fromJson(requestBody, AuthenticationRequest.class);
    }
}
