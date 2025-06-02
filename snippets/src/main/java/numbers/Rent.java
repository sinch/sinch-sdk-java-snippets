package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.v1.SmsConfiguration;
import com.sinch.sdk.domains.numbers.models.v1.request.AvailableNumberRentRequest;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;

public class Rent {

  private static final Logger LOGGER = Logger.getLogger(Rent.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    // Available numbers list can be retrieved by using list() function from available service, see:
    // https://developers.sinch.com/docs/numbers/getting-started/java-sdk/searchavailable/
    String phoneNumber = "available_phone_number_to_be_rented";
    String servicePlanId = "YOUR_service_plan_id";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    NumbersService service = client.numbers().v1();

    SmsConfiguration smsConfiguration =
        SmsConfiguration.builder().setServicePlanId(servicePlanId).build();

    AvailableNumberRentRequest rentRequest =
        AvailableNumberRentRequest.builder().setSmsConfiguration(smsConfiguration).build();

    ActiveNumber response = service.rent(phoneNumber, rentRequest);

    LOGGER.info(String.format("Rented number: %s", response));
  }
}
