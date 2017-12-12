package cd.go.authorization.guest;

import cd.go.authorization.guest.model.AuthConfig;
import cd.go.authorization.guest.model.AuthenticationResponse;
import cd.go.authorization.guest.model.Configuration;
import cd.go.authorization.guest.model.User;

import java.util.List;

public class Authenticator {
    public AuthenticationResponse authenticate(List<AuthConfig> authConfigs) {
        AuthConfig authConfig = authConfigs.get(0);
        Configuration configuration = authConfig.getConfiguration();
        User user = new User(configuration.getUsername(), configuration.getDisplayName());
        return new AuthenticationResponse(user, authConfig);
    }

}