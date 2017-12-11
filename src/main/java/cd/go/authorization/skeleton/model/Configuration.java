package cd.go.authorization.skeleton.model;

import cd.go.authorization.skeleton.annotation.MetadataHelper;
import cd.go.authorization.skeleton.annotation.ProfileField;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import static cd.go.authorization.skeleton.Constants.*;
import static cd.go.authorization.skeleton.utils.Util.GSON;

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

    @Expose
    @SerializedName(SETTINGS_USER_EMAIL_KEY)
    @ProfileField(key = SETTINGS_USER_EMAIL_KEY, required = true, secure = false)
    private String emailAddress;

    public static Configuration fromJSON(String json) {
        return GSON.fromJson(json, Configuration.class);
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public static List<Map<String, String>> validate(Map<String, String> properties) {
        final List<Map<String, String>> validationResult = MetadataHelper.validate(Configuration.class, properties);
        return validationResult;
    }
}
