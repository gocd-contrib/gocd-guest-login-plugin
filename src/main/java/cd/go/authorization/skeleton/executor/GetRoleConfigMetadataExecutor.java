package cd.go.authorization.skeleton.executor;

import cd.go.authorization.skeleton.annotation.ProfileMetadata;
import cd.go.authorization.skeleton.annotation.MetadataHelper;
import cd.go.authorization.skeleton.model.RoleConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.List;

public class GetRoleConfigMetadataExecutor implements RequestExecutor {

    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public GoPluginApiResponse execute() throws Exception {
        final List<ProfileMetadata> metadata = MetadataHelper.getMetadata(RoleConfiguration.class);
        return new DefaultGoPluginApiResponse(200, GSON.toJson(metadata));
    }

}
