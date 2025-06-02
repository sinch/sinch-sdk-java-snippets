package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.EmergencyAddress;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class GetEmergencyAddress {
  private static final Logger LOGGER = Logger.getLogger(GetEmergencyAddress.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("my_project_id");
    String keyId = Settings.getKeyId().orElse("my_key_id");
    String keySecret = Settings.getKeySecret().orElse("my_key_secret");

    String phoneNumber = Settings.getPhoneNumber().orElse("my_sinch_phone_number");

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    NumbersService service = client.numbers().v1();

    LOGGER.info("Get EmergencyAddress for: " + phoneNumber);

    EmergencyAddress response = service.getEmergencyAddress(phoneNumber);

    LOGGER.info("Response: " + response);
  }
}
