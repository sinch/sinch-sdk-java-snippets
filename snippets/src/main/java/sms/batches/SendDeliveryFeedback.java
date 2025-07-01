/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package sms.batches;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.sms.api.v1.BatchesService;
import com.sinch.sdk.domains.sms.models.v1.batches.request.SendDeliveryFeedbackRequest;
import com.sinch.sdk.models.Configuration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import utils.Settings;

public class SendDeliveryFeedback {

  private static final Logger LOGGER = Logger.getLogger(SendDeliveryFeedback.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("MY_PROJECT_ID");
    String keyId = Settings.getKeyId().orElse("MY_KEY_ID");
    String keySecret = Settings.getKeySecret().orElse("MY_KEY_SECRET");

    String batchId = "A_BATCH_ID";
    List<String> recipients = Arrays.asList("A_RECIPIENT_PHONE_NUMBER");

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    BatchesService service = client.sms().v1().batches();

    LOGGER.info(String.format("Sending delivery feedback for batch ID '%s'", batchId));

    SendDeliveryFeedbackRequest request =
        SendDeliveryFeedbackRequest.builder().setRecipients(recipients).build();

    service.sendDeliveryFeedback(batchId, request);

    LOGGER.info("Done");
  }
}
