package cd.go.authorization.guest;

import cd.go.authorization.guest.model.AuthConfig;
import cd.go.authorization.guest.model.RoleConfig;
import cd.go.authorization.guest.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Authorizer {
    public Set<String> authorize(User user, AuthConfig authConfig, List<RoleConfig> roleConfigs) {
        return new HashSet<>();
    }
}
