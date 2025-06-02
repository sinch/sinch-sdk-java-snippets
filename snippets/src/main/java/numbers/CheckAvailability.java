package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.response.AvailableNumber;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;

public class CheckAvailability {

  private static final Logger LOGGER = Logger.getLogger(CheckAvailability.class.getName());

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

    LOGGER.info("CheckAvailability for: " + phoneNumber);

    AvailableNumber value = service.checkAvailability(phoneNumber);

    LOGGER.info("Response: " + value);
  }
}
