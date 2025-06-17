/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.EmergencyAddress;
import com.sinch.sdk.domains.numbers.models.v1.request.EmergencyAddressRequest;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class ProvisionEmergencyAddress {

  private static final Logger LOGGER = Logger.getLogger(ProvisionEmergencyAddress.class.getName());

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

    LOGGER.info("Provisioning EmergencyAddress for: " + phoneNumber);

    EmergencyAddress emergencyAddress =
        EmergencyAddress.builder()
            .setStreetNumber("3500")
            .setStreetInfo("Lenox Rd NE")
            .setCity("Atlanta")
            .setState("GA")
            .setPostalCode("30326")
            .build();

    EmergencyAddressRequest request =
        EmergencyAddressRequest.builder()
            .setDisplayName("Emergency Address Display Name")
            .setAddress(emergencyAddress)
            .build();
    EmergencyAddress value = service.provisionEmergencyAddress(phoneNumber, request);

    LOGGER.info("Response: " + value);
  }
}
