package numbers.callback;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.CallbackConfigurationService;
import com.sinch.sdk.domains.numbers.models.v1.callbacks.request.CallbackConfigurationUpdateRequest;
import com.sinch.sdk.domains.numbers.models.v1.callbacks.response.CallbackConfigurationResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;

public class Update {

  private static final Logger LOGGER = Logger.getLogger(Update.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    String hmac = "HMAC value";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    CallbackConfigurationService service = client.numbers().v1().callback();

    LOGGER.info("Update callback HMAC value");

    CallbackConfigurationUpdateRequest parameters =
        CallbackConfigurationUpdateRequest.builder().setHmacSecret(hmac).build();

    CallbackConfigurationResponse value = service.update(parameters);

    LOGGER.info("Response :" + value);
  }
}
