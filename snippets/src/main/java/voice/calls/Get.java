package voice.calls;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.voice.api.v1.CallsService;
import com.sinch.sdk.domains.voice.models.v1.calls.response.CallInformation;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class Get {

  private static final Logger LOGGER = Logger.getLogger(Get.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    String callId = "A_CALL_ID";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    CallsService callsService = client.voice().v1().calls();

    LOGGER.info(String.format("Get information for call with ID '%s'", callId));

    CallInformation response = callsService.get(callId);

    LOGGER.info("Response: " + response);
  }
}
