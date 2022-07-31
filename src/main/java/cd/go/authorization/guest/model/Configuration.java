/*
 * Copyright 2022 Thoughtworks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        if (serverUrl.endsWith("/")) {
            int endIndex = serverUrl.length() - 1;
            return serverUrl.substring(0, endIndex);
        }
        return serverUrl;
    }
}
