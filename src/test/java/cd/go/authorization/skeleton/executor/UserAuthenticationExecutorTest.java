package cd.go.authorization.skeleton.executor;

import cd.go.authorization.skeleton.Authenticator;
import cd.go.authorization.skeleton.Authorizer;
import org.junit.Test;

public class UserAuthenticationExecutorTest {
    @Test
    public void shouldAuthenticate() throws Exception {
        new UserAuthenticationExecutor(null, new Authenticator(), new Authorizer()).execute();
    }
}