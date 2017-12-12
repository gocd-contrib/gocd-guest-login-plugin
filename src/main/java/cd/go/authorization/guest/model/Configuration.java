package cd.go.authorization.guest.model;

import cd.go.authorization.guest.annotation.MetadataHelper;
import cd.go.authorization.guest.annotation.ProfileField;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import static cd.go.authorization.guest.Constants.*;
import static cd.go.authorization.guest.utils.Util.GSON;

public class Configuration {
    @Expose
    @SerializedName(SETTINGS_SERVER_URL_KEY)
    @ProfileField(key = SETTINGS_SERVER_URL_KEY, required = true, secure = false)
    private String serverUrl;

    @Expose
    @SerializedName(SETTINGS_USERNAME_KEY)
    @ProfileField(key = SETTINGS_USERNAME_KEY, required = true, secure = false)
    private String username;

    @Expose
    @SerializedName(SETTINGS_USER_DISPLAY_NAME_KEY)
    @ProfileField(key = SETTINGS_USER_DISPLAY_NAME_KEY, required = true, secure = false)
    private String displayName;

    public Configuration() {
    }

    public Configuration(String serverUrl, String username, String displayName) {
        this.serverUrl = serverUrl;
        this.username = username;
        this.displayName = displayName;
    }

    public static Configuration fromJSON(String json) {
        return GSON.fromJson(json, Configuration.class);
    }

    public static List<Map<String, String>> validate(Map<String, String> properties) {
        final List<Map<String, String>> validationResult = MetadataHelper.validate(Configuration.class, properties);
        return validationResult;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public String getServerUrl() {
        return serverUrl;
    }
}
