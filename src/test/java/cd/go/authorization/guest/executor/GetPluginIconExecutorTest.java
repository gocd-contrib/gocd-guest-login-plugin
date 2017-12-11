package cd.go.authorization.guest.executor;

import cd.go.authorization.guest.utils.Util;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Test;

import java.util.Base64;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GetPluginIconExecutorTest {

    @Test
    public void shouldReturnsPluginIcon() throws Exception {
        GoPluginApiResponse response = new GetPluginIconExecutor().execute();
        HashMap<String, String> hashMap = new Gson().fromJson(response.responseBody(), HashMap.class);
        assertThat(hashMap.size(), is(2));
        assertThat(hashMap.get("content_type"), is("image/png"));
        assertThat(Util.readResourceBytes("/guest.png"), is(Base64.getDecoder().decode(hashMap.get("data"))));
    }
}