/*
 * Copyright 2017 ThoughtWorks, Inc.
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

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Collections;
import java.util.HashMap;

import static cd.go.authorization.guest.Constants.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthConfigValidateRequestExecutorTest {

    private GoPluginApiRequest request;

    @Before
    public void setup() throws Exception {
        request = mock(GoPluginApiRequest.class);
    }

    @Test
    public void shouldBarfWhenUnknownKeysArePassed() throws Exception {
        HashMap<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put("foo", "bar");
        keyValuePairs.put(SETTINGS_SERVER_URL_KEY, "server-url");
        keyValuePairs.put(SETTINGS_USERNAME_KEY, "username");
        keyValuePairs.put(SETTINGS_USER_DISPLAY_NAME_KEY, "display-name");

        when(request.requestBody()).thenReturn(new Gson().toJson(keyValuePairs));

        GoPluginApiResponse response = new AuthConfigValidateRequestExecutor(request).execute();
        String json = response.responseBody();

        String expectedJSON = "[\n" +
                "  {\n" +
                "    \"key\": \"foo\",\n" +
                "    \"message\": \"Is an unknown property\"\n" +
                "  }\n" +
                "]";

        JSONAssert.assertEquals(expectedJSON, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void shouldValidateMandatoryKeys() throws Exception {
        when(request.requestBody()).thenReturn(new Gson().toJson(Collections.emptyMap()));

        HashMap<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put(SETTINGS_USERNAME_KEY, "username");
        keyValuePairs.put(SETTINGS_USER_DISPLAY_NAME_KEY, "display-name");

        when(request.requestBody()).thenReturn(new Gson().toJson(keyValuePairs));

        GoPluginApiResponse response = new AuthConfigValidateRequestExecutor(request).execute();
        String json = response.responseBody();

        String expectedJSON = String.format("[{\"message\": \"%s must not be blank.\",\"key\": \"%s\"}]", SETTINGS_SERVER_URL_KEY, SETTINGS_SERVER_URL_KEY);
        JSONAssert.assertEquals(expectedJSON, json, JSONCompareMode.NON_EXTENSIBLE);
    }
}