package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.model.Capabilities;
import cd.go.authorization.guest.model.SupportedAuthType;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import static com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse.SUCCESS_RESPONSE_CODE;

public class GetCapabilitiesExecutor {

    public GoPluginApiResponse execute() {
        Capabilities capabilities = getCapabilities();
        return new DefaultGoPluginApiResponse(SUCCESS_RESPONSE_CODE, capabilities.toJSON());
    }

    Capabilities getCapabilities() {
        return new Capabilities(SupportedAuthType.Web, false, false);
    }
}
