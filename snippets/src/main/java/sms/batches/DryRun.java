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
import com.sinch.sdk.domains.sms.models.v1.batches.response.DryRunResponse;
import com.sinch.sdk.models.Configuration;
import com.sinch.sdk.models.SMSRegion;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import utils.Settings;

public class DryRun {

  private static final Logger LOGGER = Logger.getLogger(DryRun.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("MY_PROJECT_ID");
    String keyId = Settings.getKeyId().orElse("MY_KEY_ID");
    String keySecret = Settings.getKeySecret().orElse("MY_KEY_SECRET");
    String smsRegion = Settings.getSMSRegion().orElse("MY_SMS_REGION");

    String phoneNumber = Settings.getPhoneNumber().orElse("MY_SINCH_PHONE_NUMBER");

    List<String> recipients = Arrays.asList("A_RECIPIENT_PHONE_NUMBER");
    String body = "A body text here";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .setSmsRegion(SMSRegion.from(smsRegion))
            .build();

    SinchClient client = new SinchClient(configuration);

    BatchesService batchesService = client.sms().v1().batches();

    LOGGER.info("DryRun Request");

    TextRequest request =
        TextRequest.builder().setFrom(phoneNumber).setTo(recipients).setBody(body).build();

    DryRunResponse response = batchesService.dryRun(request);

    LOGGER.info("Response: " + response);
  }
}
