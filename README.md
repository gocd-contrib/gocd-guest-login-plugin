# Guest User Login Plugin for GoCD
GoCD Guest User Login Plugin allows the user to login as a guest to [GoCD](https://www.gocd.org) server.

## Requirements
* GoCD server version v17.5.0 or above

## Installation
Copy the file `build/libs/gocd-guest-login-plugin-VERSION.jar` to the GoCD server under `${GO_SERVER_DIR}/plugins/external` and restart the server. The `GO_SERVER_DIR` is usually `/var/lib/go-server` on Linux and `C:\Program Files\Go Server` on Windows.

### Configuration

#### Create Authorization Configuration

1. Login to GoCD Server as admin and navigate to **_Admin_** _>_ **_Security_** _>_ **_Authorization Configuration_**
2. Click on Add to create new authorization configuration:  
    1. Specify `id` for auth config
    2. Select `Guest Login Plugin` for _**Plugin Id**_ 
    3. Specify `Go Server Url`. Eg. `https://<GO_SERVER_URL>/go`
    4. Specify `Username` of the guest user.
    5. Specify `Display name` of the guest user.

    !["Guest Login Plugin Auth Config"][1]

#### Troubleshooting

### Enable Debug Logs

#### If you are on GoCD version 19.6 and above:

Edit the file `wrapper-properties.conf` on your GoCD server and add the following options. The location of the `wrapper-properties.conf` can be found in the [installation documentation](https://docs.gocd.org/current/installation/installing_go_server.html) of the GoCD server.

```properties
# We recommend that you begin with the index `100` and increment the index for each system property
wrapper.java.additional.100=-Dplugin.cd.go.contrib.authorization.guest.log.level=debug
```

If you're running with GoCD server 19.6 and above on docker using one of the supported GoCD server images, set the environment variable `GOCD_SERVER_JVM_OPTIONS`:

```shell
docker run -e "GOCD_SERVER_JVM_OPTIONS=-Dplugin.cd.go.contrib.authorization.guest.log.level=debug" ...
```

#### If you are on GoCD version 19.5 and lower:

* On Linux:

    Enabling debug level logging can help you troubleshoot an issue with this plugin. To enable debug level logs, edit the file `/etc/default/go-server` (for Linux) to add:

    ```shell
    export GO_SERVER_SYSTEM_PROPERTIES="$GO_SERVER_SYSTEM_PROPERTIES -Dplugin.cd.go.contrib.authorization.guest.log.level=debug"
    ```

    If you're running the server via `./server.sh` script:

    ```shell
    $ GO_SERVER_SYSTEM_PROPERTIES="-Dplugin.cd.go.contrib.authorization.guest.log.level=debug" ./server.sh
    ```

* On windows:

    Edit the file `config/wrapper-properties.conf` inside the GoCD Server installation directory (typically `C:\Program Files\Go Server`):

    ```
    # config/wrapper-properties.conf
    # since the last "wrapper.java.additional" index is 15, we use the next available index.
    wrapper.java.additional.16=-Dplugin.cd.go.contrib.authorization.guest.log.level=debug
    ```

## Building the code base
To build the jar, run `./gradlew clean test assemble`

## License

```plain
Copyright 2022 Thoughtworks, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[1]: images/auth-config.png     "Guest Login Plugin Auth Config"
