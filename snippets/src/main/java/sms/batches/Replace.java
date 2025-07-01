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
import com.sinch.sdk.domains.sms.models.v1.batches.request.TextRequest;
import com.sinch.sdk.domains.sms.models.v1.batches.response.BatchResponse;
import com.sinch.sdk.models.Configuration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import utils.Settings;

public class Replace {

  private static final Logger LOGGER = Logger.getLogger(Replace.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("MY_PROJECT_ID");
    String keyId = Settings.getKeyId().orElse("MY_KEY_ID");
    String keySecret = Settings.getKeySecret().orElse("MY_KEY_SECRET");

    String batchId = "A_BATCH_ID";
    List<String> recipients = Arrays.asList("A_NEW_RECIPIENT_PHONE_NUMBER");
    String body = "A message body updated";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    BatchesService service = client.sms().v1().batches();

    LOGGER.info(String.format("Replacing batch ID '%s'", batchId));

    TextRequest request = TextRequest.builder().setTo(recipients).setBody(body).build();

    BatchResponse response = service.replace(batchId, request);

    LOGGER.info("Response: " + response);
  }
}
