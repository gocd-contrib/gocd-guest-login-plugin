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

    String SETTINGS_SERVER_URL_KEY = "go_server_url";
    String SETTINGS_USERNAME_KEY = "guest_username";
    String SETTINGS_USER_DISPLAY_NAME_KEY = "guest_user_display_name";
    String SETTINGS_USER_EMAIL_KEY = "guest_user_email_address";


}
