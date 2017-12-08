package cd.go.authorization.skeleton;

import cd.go.authorization.skeleton.executor.*;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.GoPlugin;
import com.thoughtworks.go.plugin.api.GoPluginIdentifier;
import com.thoughtworks.go.plugin.api.annotation.Extension;
import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import static cd.go.authorization.skeleton.Constants.PLUGIN_IDENTIFIER;

@Extension
public class GoCDGuestLoginPlugin implements GoPlugin {
    public static final Logger LOG = Logger.getLoggerFor(GoCDGuestLoginPlugin.class);

    private GoApplicationAccessor accessor;

    @Override
    public void initializeGoApplicationAccessor(GoApplicationAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest request) throws UnhandledRequestTypeException {
        try {
            switch (RequestFromServer.fromString(request.requestName())) {
                case REQUEST_GET_PLUGIN_ICON:
                    return new GetPluginIconExecutor().execute();

                case REQUEST_GET_CAPABILITIES:
                    return new GetCapabilitiesExecutor().execute();

                case REQUEST_GET_AUTH_CONFIG_METADATA:
                    return new GetAuthConfigMetadataExecutor().execute();
                case REQUEST_AUTH_CONFIG_VIEW:
                    return new GetAuthConfigViewExecutor().execute();
                case REQUEST_VALIDATE_AUTH_CONFIG:
                    return new AuthConfigValidateRequestExecutor(request).execute();
                case REQUEST_VERIFY_CONNECTION:
                    return new VerifyConnectionRequestExecutor(request).execute();
                case REQUEST_GET_ROLE_CONFIG_METADATA:
                    return new GetRoleConfigMetadataExecutor().execute();
                case REQUEST_ROLE_CONFIG_VIEW:
                    return new GetRoleConfigViewExecutor().execute();
                case REQUEST_VALIDATE_ROLE_CONFIG:
                    return new RoleConfigValidateRequestExecutor(request).execute();
                case REQUEST_AUTHENTICATE_USER:
                    return new UserAuthenticationExecutor(request, new Authenticator(), new Authorizer()).execute();
                case REQUEST_SEARCH_USERS:
                    return new SearchUserExecutor(request).execute();
                default:
                    throw new UnhandledRequestTypeException(request.requestName());
            }
        } catch (NoSuchRequestHandler e) {
            LOG.warn(e.getMessage());
            return null;
        } catch (Exception e) {
            LOG.error("Error while executing request " + request.requestName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public GoPluginIdentifier pluginIdentifier() {
        return PLUGIN_IDENTIFIER;
    }
}
