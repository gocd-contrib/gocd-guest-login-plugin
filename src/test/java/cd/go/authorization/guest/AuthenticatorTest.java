package cd.go.authorization.guest;

import cd.go.authorization.guest.model.AuthConfig;
import cd.go.authorization.guest.model.AuthenticationResponse;
import cd.go.authorization.guest.model.Configuration;
import cd.go.authorization.guest.model.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticatorTest {

    @Test
    public void shouldAuthenticate() throws Exception {
        String serverUrl = "server-url";
        String username = "Bob";
        String displayName = "Some Cool Bob!";
        String emailId = "bob@bob.com";

        ArrayList<AuthConfig> authConfigs = new ArrayList<>();
        AuthConfig authConfig = new AuthConfig();

        authConfig.setId("auth-config-1");
        authConfig.setConfiguration(new Configuration(serverUrl, username, displayName, emailId));

        authConfigs.add(authConfig);

        AuthenticationResponse expected = new AuthenticationResponse(new User(username, displayName, emailId), authConfig);
        AuthenticationResponse response = new Authenticator().authenticate(authConfigs);

        assertThat(response, is(expected));
    }
}