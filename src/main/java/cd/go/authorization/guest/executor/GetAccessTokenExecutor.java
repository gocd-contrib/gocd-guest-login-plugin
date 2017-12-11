package cd.go.authorization.guest.executor;

import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class GetAccessTokenExecutor {
    private GoPluginApiRequest request;

    public GetAccessTokenExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    public GoPluginApiResponse execute() {
        return DefaultGoPluginApiResponse.success("{}");
    }
}
