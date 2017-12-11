package cd.go.authorization.skeleton;

import cd.go.authorization.skeleton.model.AuthConfig;
import cd.go.authorization.skeleton.model.RoleConfig;
import cd.go.authorization.skeleton.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Authorizer {
    public Set<String> authorize(User user, AuthConfig authConfig, List<RoleConfig> roleConfigs) {
        return new HashSet<>();
    }
}
