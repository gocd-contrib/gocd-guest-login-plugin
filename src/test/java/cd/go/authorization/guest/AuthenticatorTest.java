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

package cd.go.authorization.guest;

import cd.go.authorization.guest.model.AuthConfig;
import cd.go.authorization.guest.model.AuthenticationResponse;
import cd.go.authorization.guest.model.Configuration;
import cd.go.authorization.guest.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticatorTest {

    @Test
    public void shouldAuthenticate() throws Exception {
        String serverUrl = "server-url";
        String username = "Bob";
        String displayName = "Some Cool Bob!";

        ArrayList<AuthConfig> authConfigs = new ArrayList<>();
        AuthConfig authConfig = new AuthConfig();

        authConfig.setId("auth-config-1");
        authConfig.setConfiguration(new Configuration(serverUrl, username, displayName));

        authConfigs.add(authConfig);

        AuthenticationResponse expected = new AuthenticationResponse(new User(username, displayName), authConfig);
        AuthenticationResponse response = new Authenticator().authenticate(authConfigs);

        assertThat(response, is(expected));
    }
}