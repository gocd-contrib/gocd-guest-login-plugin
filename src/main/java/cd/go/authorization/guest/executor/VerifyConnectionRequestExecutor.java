package cd.go.authorization.guest.executor;

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class VerifyConnectionRequestExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;

    public VerifyConnectionRequestExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() {
        return DefaultGoPluginApiResponse.success("{\n" +
                "  \"status\": \"success\"\n" +
                "}");
    }
}
