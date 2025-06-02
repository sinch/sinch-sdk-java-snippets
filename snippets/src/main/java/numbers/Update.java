package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.v1.request.ActiveNumberUpdateRequest;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;

public class Update {

  private static final Logger LOGGER = Logger.getLogger(Update.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    String phoneNumber = "YOUR_phone_number";
    String displayName = "Updated from Sinch Java SDK";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    NumbersService service = client.numbers().v1();

    ActiveNumberUpdateRequest updateRequest =
        ActiveNumberUpdateRequest.builder().setDisplayName(displayName).build();

    ActiveNumber response = service.update(phoneNumber, updateRequest);

    LOGGER.info(String.format("Updated number: %s", response));
  }
}
