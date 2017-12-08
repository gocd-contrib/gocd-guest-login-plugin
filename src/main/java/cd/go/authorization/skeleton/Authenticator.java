package cd.go.authorization.skeleton;

import cd.go.authorization.skeleton.model.AuthConfig;
import cd.go.authorization.skeleton.model.AuthenticationResponse;
import cd.go.authorization.skeleton.model.Credentials;

import java.util.List;

public class Authenticator {
    public AuthenticationResponse authenticate(Credentials credentials, List<AuthConfig> authConfigs) {
        throw new RuntimeException("Implement me!");
    }

}