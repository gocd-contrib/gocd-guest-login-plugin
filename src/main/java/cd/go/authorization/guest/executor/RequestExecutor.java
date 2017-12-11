package cd.go.authorization.guest.executor;

import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public interface RequestExecutor {

    GoPluginApiResponse execute() throws Exception;
}
