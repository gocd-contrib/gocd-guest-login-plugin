package cd.go.authorization.guest;

import org.junit.Test;

public class AuthorizerTest {
    @Test
    public void shouldAuthorize() throws Exception {
        new Authorizer().authorize(null, null, null);
    }
}