package cd.go.authorization.guest;

import com.thoughtworks.go.plugin.api.GoPluginIdentifier;

import java.util.Collections;

public interface Constants {
    // The type of this extension
    String EXTENSION_TYPE = "authorization";

    // The extension point API version that this plugin understands
    String API_VERSION = "1.0";

    // the identifier of this plugin
    GoPluginIdentifier PLUGIN_IDENTIFIER = new GoPluginIdentifier(EXTENSION_TYPE, Collections.singletonList(API_VERSION));
    public static final String PLUGIN_ID = "cd.go.guest.login";

    public static final String SETTINGS_SERVER_URL_KEY = "SETTINGS_SERVER_URL_KEY";
    public static final String SETTINGS_USERNAME_KEY = "GUEST_USER_USERNAME";
    public static final String SETTINGS_USER_DISPLAY_NAME_KEY = "GUEST_USER_DISPLAY_NAME";
    public static final String SETTINGS_USER_EMAIL_KEY = "GUEST_USER_EMAIL";


}
