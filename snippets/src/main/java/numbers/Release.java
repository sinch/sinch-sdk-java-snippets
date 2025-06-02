package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.ActiveNumber;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;

public class Release {

  private static final Logger LOGGER = Logger.getLogger(Release.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    String phoneNumber = "YOUR_phone_number";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    NumbersService service = client.numbers().v1();

    LOGGER.info("Release for: " + phoneNumber);

    ActiveNumber value = service.release(phoneNumber);

    LOGGER.info("Response: " + value);
  }
}
