/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package numbers.regions;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.AvailableRegionsService;
import com.sinch.sdk.domains.numbers.models.v1.regions.available.response.AvailableRegionListResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class List {

  private static final Logger LOGGER = Logger.getLogger(List.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("my_project_id");
    String keyId = Settings.getKeyId().orElse("my_key_id");
    String keySecret = Settings.getKeySecret().orElse("my_key_secret");

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    AvailableRegionsService service = client.numbers().v1().regions();

    LOGGER.info("List");

    AvailableRegionListResponse response = service.list();

    LOGGER.info("Available regions:");

    response.iterator().forEachRemaining(item -> LOGGER.info(String.format("- %s", item)));
  }
}
