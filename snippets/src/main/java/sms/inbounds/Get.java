/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package sms.inbounds;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.sms.api.v1.InboundsService;
import com.sinch.sdk.domains.sms.models.v1.inbounds.InboundMessage;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class Get {

  private static final Logger LOGGER = Logger.getLogger(Get.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("MY_PROJECT_ID");
    String keyId = Settings.getKeyId().orElse("MY_KEY_ID");
    String keySecret = Settings.getKeySecret().orElse("MY_KEY_SECRET");

    String inboundId = "AN_INBOUND_ID";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    InboundsService inboundsService = client.sms().v1().inbounds();

    LOGGER.info(String.format("Get inbound message with ID '%s'", inboundId));

    InboundMessage response = inboundsService.get(inboundId);

    LOGGER.info("Response: " + response);
  }
}
