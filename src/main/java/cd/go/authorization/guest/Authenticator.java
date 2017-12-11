package cd.go.authorization.guest;

import cd.go.authorization.guest.model.*;

import java.util.List;

public class Authenticator {
    public AuthenticationResponse authenticate(Credentials credentials, List<AuthConfig> authConfigs) {
        AuthConfig authConfig = authConfigs.get(0);
        Configuration configuration = authConfig.getConfiguration();
        User user = new User(configuration.getUsername(), configuration.getDisplayName(), configuration.getEmailAddress());
        return new AuthenticationResponse(user, authConfig);
    }

}