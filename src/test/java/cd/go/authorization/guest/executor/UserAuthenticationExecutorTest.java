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

import cd.go.authorization.guest.Authenticator;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static cd.go.authorization.guest.Constants.*;
import static cd.go.authorization.guest.GuestLoginPlugin.TEMPORARY_ACCESS_TOKEN;
import static cd.go.authorization.guest.executor.GetAccessTokenExecutor.ACCESS_TOKEN_FOR_VIEW_USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserAuthenticationExecutorTest {
    @Mock
    GoPluginApiRequest request;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks(this);
    }

    @Test
    public void shouldAuthenticate() throws Exception {
        HashMap<String, String> configuration = new HashMap<>();
        configuration.put(SETTINGS_SERVER_URL_KEY, "server-url");
        configuration.put(SETTINGS_USERNAME_KEY, "username");
        configuration.put(SETTINGS_USER_DISPLAY_NAME_KEY, "display-name");

        HashMap<String, Object> authConfig = new HashMap<>();
        authConfig.put("id", "dummy_id");
        authConfig.put("configuration", configuration);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("auth_configs", Arrays.asList(authConfig));
        requestBody.put("credentials", Collections.singletonMap(ACCESS_TOKEN_FOR_VIEW_USER, TEMPORARY_ACCESS_TOKEN));

        when(request.requestBody()).thenReturn(new Gson().toJson(requestBody));

        GoPluginApiResponse actualResponse = new UserAuthenticationExecutor(request, new Authenticator()).execute();
        DefaultGoPluginApiResponse expectedResponse = new DefaultGoPluginApiResponse(200, "{\"roles\":[],\"user\":{\"username\":\"username\",\"display_name\":\"display-name\"}}");

        assertThat(actualResponse.responseBody(), is(expectedResponse.responseBody()));
        assertThat(actualResponse.responseCode(), is(expectedResponse.responseCode()));
    }
}
