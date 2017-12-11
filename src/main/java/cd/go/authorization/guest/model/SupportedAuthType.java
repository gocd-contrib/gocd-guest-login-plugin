package cd.go.authorization.guest.model;

import com.google.gson.annotations.SerializedName;

public enum SupportedAuthType {
    @SerializedName("password")
    Password,
    @SerializedName("web")
    Web
}
