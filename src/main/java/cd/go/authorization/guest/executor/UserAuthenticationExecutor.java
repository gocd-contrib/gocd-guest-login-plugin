/*
 * Copyright 2018 ThoughtWorks, Inc.
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

import cd.go.authorization.guest.Authenticator;
import cd.go.authorization.guest.model.AuthenticationRequest;
import cd.go.authorization.guest.model.AuthenticationResponse;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static cd.go.authorization.guest.GuestLoginPlugin.TEMPORARY_ACCESS_TOKEN;
import static cd.go.authorization.guest.executor.GetAccessTokenExecutor.ACCESS_TOKEN_FOR_VIEW_USER;

public class UserAuthenticationExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;
    private final Authenticator authenticator;

    public UserAuthenticationExecutor(GoPluginApiRequest request, Authenticator authenticator) {
        this.request = request;
        this.authenticator = authenticator;
    }

    @Override
    public GoPluginApiResponse execute() {
        final AuthenticationRequest authenticationRequest = AuthenticationRequest.fromJson(request.requestBody());

        if (authenticationRequest.getCredentials().get(ACCESS_TOKEN_FOR_VIEW_USER).equals(TEMPORARY_ACCESS_TOKEN)) {
            AuthenticationResponse authenticationResponse = authenticator.authenticate(authenticationRequest.getAuthConfigs());
            Map<String, Object> userMap = new HashMap<>();
            if (authenticationResponse != null) {
                userMap.put("user", authenticationResponse.getUser());
                userMap.put("roles", new ArrayList<>());
            }
            return DefaultGoPluginApiResponse.success(GSON.toJson(userMap));
        }

        return DefaultGoPluginApiResponse.error("Invalid access token specified.");
    }

}
