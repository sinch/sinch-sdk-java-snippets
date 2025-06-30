package sms.batches;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.sms.api.v1.BatchesService;
import com.sinch.sdk.domains.sms.models.v1.batches.request.TextRequest;
import com.sinch.sdk.domains.sms.models.v1.batches.response.BatchResponse;
import com.sinch.sdk.models.Configuration;
import java.util.Collections;
import java.util.logging.*;
import java.util.logging.Logger;

public class Send {

  private static final Logger LOGGER = Logger.getLogger(Send.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    BatchesService service = client.sms().v1().batches();

    String from = "YOUR_sinch_phone_number";
    String recipient = "YOUR_recipient_phone_number";
    String body = "This is a test SMS message using the Sinch Java SDK.";

    LOGGER.info("Sending SMS Text");
    BatchResponse value =
        service.send(
            TextRequest.builder()
                .setTo(Collections.singletonList(recipient))
                .setBody(body)
                .setFrom(from)
                .build());

    LOGGER.info("Response: " + value);
  }
}
