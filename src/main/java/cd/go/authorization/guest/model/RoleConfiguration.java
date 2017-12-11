package cd.go.authorization.guest.model;

import cd.go.authorization.guest.annotation.MetadataHelper;
import cd.go.authorization.guest.annotation.ProfileField;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class RoleConfiguration {

    @Expose
    @SerializedName("ExampleField")
    @ProfileField(key = "ExampleField", required = true, secure = false)
    private String exampleField;

    public String getExampleField() {
        return exampleField;
    }

    public static List<Map<String, String>> validate(Map<String, String> properties) {
        final List<Map<String, String>> validationResult = MetadataHelper.validate(RoleConfiguration.class, properties);
        return validationResult;
    }
}
