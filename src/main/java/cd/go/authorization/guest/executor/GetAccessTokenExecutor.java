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

package cd.go.authorization.guest.executor;

import com.google.gson.JsonObject;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import static cd.go.authorization.guest.GuestLoginPlugin.TEMPORARY_ACCESS_TOKEN;

public class GetAccessTokenExecutor {
    public static final String ACCESS_TOKEN_FOR_VIEW_USER = "access_token_for_view_user";
    private GoPluginApiRequest request;

    public GetAccessTokenExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    public GoPluginApiResponse execute() {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ACCESS_TOKEN_FOR_VIEW_USER, TEMPORARY_ACCESS_TOKEN);
        return DefaultGoPluginApiResponse.success(jsonObject.toString());
    }
}
