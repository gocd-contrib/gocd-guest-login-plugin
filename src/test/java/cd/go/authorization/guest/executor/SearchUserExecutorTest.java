package cd.go.authorization.guest.executor;

import org.junit.Test;

public class SearchUserExecutorTest {

    @Test
    public void shouldSearch() throws Exception {
        new SearchUserExecutor(null).execute();
    }
}