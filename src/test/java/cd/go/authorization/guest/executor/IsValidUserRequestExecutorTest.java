/*
 * Copyright 2019 ThoughtWorks, Inc.
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
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static cd.go.authorization.guest.Constants.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class IsValidUserRequestExecutorTest {
    @Mock
    GoPluginApiRequest request;
    private String loginUsername;

    @Before
    public void setUp() throws Exception {
        loginUsername = "username";
        initMocks(this);
    }

    @Test
    public void shouldReturnTrueWhenUserIsValid() throws Exception {
        HashMap<String, String> configuration = new HashMap<>();
        configuration.put(SETTINGS_SERVER_URL_KEY, "server-url");
        configuration.put(SETTINGS_USERNAME_KEY, loginUsername);
        configuration.put(SETTINGS_USER_DISPLAY_NAME_KEY, "display-name");

        HashMap<String, Object> authConfig = new HashMap<>();
        authConfig.put("id", "dummy_id");
        authConfig.put("configuration", configuration);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("auth_config", authConfig);
        requestBody.put("username", loginUsername);

        when(request.requestBody()).thenReturn(new Gson().toJson(requestBody));

        GoPluginApiResponse actualResponse = new IsValidUserRequestExecutor(request, new Authenticator()).execute();
        DefaultGoPluginApiResponse expectedResponse = new DefaultGoPluginApiResponse(200);

        assertThat(actualResponse.responseCode(), is(expectedResponse.responseCode()));
    }

    @Test
    public void shouldReturnTrueWhenUserIsValidForCaseInsensitiveUsernameMatch() throws Exception {
        HashMap<String, String> configuration = new HashMap<>();
        configuration.put(SETTINGS_SERVER_URL_KEY, "server-url");
        configuration.put(SETTINGS_USERNAME_KEY, loginUsername);
        configuration.put(SETTINGS_USER_DISPLAY_NAME_KEY, "display-name");

        HashMap<String, Object> authConfig = new HashMap<>();
        authConfig.put("id", "dummy_id");
        authConfig.put("configuration", configuration);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("auth_config", authConfig);
        requestBody.put("username", "UsErNaMe");

        when(request.requestBody()).thenReturn(new Gson().toJson(requestBody));

        GoPluginApiResponse actualResponse = new IsValidUserRequestExecutor(request, new Authenticator()).execute();
        DefaultGoPluginApiResponse expectedResponse = new DefaultGoPluginApiResponse(200);

        assertThat(actualResponse.responseCode(), is(expectedResponse.responseCode()));
    }

    @Test
    public void shouldReturnFalseWhenUserIsInvalid() throws Exception {
        HashMap<String, String> configuration = new HashMap<>();
        configuration.put(SETTINGS_SERVER_URL_KEY, "server-url");
        configuration.put(SETTINGS_USERNAME_KEY, loginUsername);
        configuration.put(SETTINGS_USER_DISPLAY_NAME_KEY, "display-name");

        HashMap<String, Object> authConfig = new HashMap<>();
        authConfig.put("id", "dummy_id");
        authConfig.put("configuration", configuration);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("auth_config", authConfig);
        requestBody.put("username", "invalid-username");

        when(request.requestBody()).thenReturn(new Gson().toJson(requestBody));

        GoPluginApiResponse actualResponse = new IsValidUserRequestExecutor(request, new Authenticator()).execute();
        DefaultGoPluginApiResponse expectedResponse = new DefaultGoPluginApiResponse(404);

        assertThat(actualResponse.responseCode(), is(expectedResponse.responseCode()));
    }
}
