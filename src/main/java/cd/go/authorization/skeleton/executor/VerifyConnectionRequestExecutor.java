package cd.go.authorization.skeleton.executor;

import cd.go.authorization.skeleton.model.Configuration;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.validation.ValidationResult;

public class VerifyConnectionRequestExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;

    public VerifyConnectionRequestExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() {
        Configuration configuration = Configuration.fromJSON(request.requestBody());
        ValidationResult result = validate(configuration);
        return DefaultGoPluginApiResponse.success(GSON.toJson(result.getErrors()));
    }

    public ValidationResult validate(Configuration configuration) {
        throw new RuntimeException("Implement me");
    }
}
