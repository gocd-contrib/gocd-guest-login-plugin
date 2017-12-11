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
    2. Select `Guest Authorization Plugin` for _**Plugin Id**_ 
    3. Specify `Go Server Url`
    4. Specify `Username` of the guest user.
    5. Specify `Display name` of the guest user.
    6. Specify `Email address` of the guest user.

    !["Guest Login Plugin Auth Config"][1]

#### Troubleshooting
Enabling debug level logging can help you troubleshoot an issue with the `Guest Authorization Plugin`. To enable debug level logs, edit the `/etc/default/go-server` (for Linux) to add
```
export GO_SERVER_SYSTEM_PROPERTIES="$GO_SERVER_SYSTEM_PROPERTIES -Dplugin.cd.go.contrib.authorization.guest.log.level=debug"
```

If you're running the server via `./server.sh` script —
```
$ GO_SERVER_SYSTEM_PROPERTIES="-Dplugin.cd.go.contrib.authorization.guest.log.level=debug" ./server.sh
```

## Building the code base
To build the jar, run `./gradlew clean test assemble`

## License

```plain
Copyright 2017 ThoughtWorks, Inc.

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
