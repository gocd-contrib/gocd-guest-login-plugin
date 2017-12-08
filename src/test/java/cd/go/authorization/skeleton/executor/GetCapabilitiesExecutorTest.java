package cd.go.authorization.skeleton.executor;

import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class GetCapabilitiesExecutorTest {

    @Test
    public void shouldReturnsPluginCapabilities() throws Exception {
        GoPluginApiResponse response = new GetCapabilitiesExecutor().execute();

        String expectedJSON = "{\n" +
                "    \"supported_auth_type\":\"web\",\n" +
                "    \"can_search\":false,\n" +
                "    \"can_authorize\":false\n" +
                "}";

        JSONAssert.assertEquals(expectedJSON, response.responseBody(), true);
    }
}
