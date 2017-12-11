package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class GetRoleConfigViewExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();

    @Override
    public GoPluginApiResponse execute() throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("template", Util.readResource(getRoleConfigTemplate()));
        DefaultGoPluginApiResponse defaultGoPluginApiResponse = new DefaultGoPluginApiResponse(200, GSON.toJson(jsonObject));
        return defaultGoPluginApiResponse;
    }

    private String getRoleConfigTemplate() {
        return "/role-config.template.html";
    }
}
