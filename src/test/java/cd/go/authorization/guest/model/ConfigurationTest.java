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

package cd.go.authorization.guest.model;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigurationTest {

    @Test
    public void shouldReturnServerUrlAsItIsWhenThereIsNoTrailingSlash() {
        String serverUrl = "server-url";
        String username = "Bob";
        String displayName = "Some Cool Bob!";

        Configuration configuration = new Configuration(serverUrl, username, displayName);

        String expected = "server-url";
        String actual = configuration.getServerUrl();
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnServerUrlWithoutTrailingSlash() {
        String serverUrl = "server-url/";
        String username = "Bob";
        String displayName = "Some Cool Bob!";

        Configuration configuration = new Configuration(serverUrl, username, displayName);

        String expected = "server-url";
        String actual = configuration.getServerUrl();
        assertThat(actual, is(expected));
    }
}
