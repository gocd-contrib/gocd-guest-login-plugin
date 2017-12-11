package cd.go.authorization.guest.model;

public class AuthenticationResponse {
    private User user;
    private AuthConfig configUsedForAuthentication;

    public AuthenticationResponse(User user, AuthConfig configUsedForAuthentication) {
        this.user = user;
        this.configUsedForAuthentication = configUsedForAuthentication;
    }

    public User getUser() {
        return user;
    }

    public AuthConfig getConfigUsedForAuthentication() {
        return configUsedForAuthentication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationResponse)) return false;

        AuthenticationResponse that = (AuthenticationResponse) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return configUsedForAuthentication != null ? configUsedForAuthentication.equals(that.configUsedForAuthentication) : that.configUsedForAuthentication == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (configUsedForAuthentication != null ? configUsedForAuthentication.hashCode() : 0);
        return result;
    }
}
