package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.Authenticator;
import cd.go.authorization.guest.Authorizer;
import org.junit.Test;

public class UserAuthenticationExecutorTest {
    @Test
    public void shouldAuthenticate() throws Exception {
        new UserAuthenticationExecutor(null, new Authenticator(), new Authorizer()).execute();
    }
}