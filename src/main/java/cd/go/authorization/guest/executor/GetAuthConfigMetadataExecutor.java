package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.annotation.MetadataHelper;
import cd.go.authorization.guest.annotation.ProfileMetadata;
import cd.go.authorization.guest.model.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.List;

public class GetAuthConfigMetadataExecutor implements RequestExecutor {
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public GoPluginApiResponse execute() throws Exception {
        final List<ProfileMetadata> authConfigMetadata = MetadataHelper.getMetadata(Configuration.class);
        return new DefaultGoPluginApiResponse(200, GSON.toJson(authConfigMetadata));
    }
}
