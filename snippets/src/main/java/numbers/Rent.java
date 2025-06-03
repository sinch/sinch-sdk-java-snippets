package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.v1.SmsConfiguration;
import com.sinch.sdk.domains.numbers.models.v1.request.AvailableNumberRentRequest;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class Rent {

  private static final Logger LOGGER = Logger.getLogger(Rent.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("my_project_id");
    String keyId = Settings.getKeyId().orElse("my_key_id");
    String keySecret = Settings.getKeySecret().orElse("my_key_secret");

    String servicePlanId = Settings.getServicePlanId().orElse("my_service_plan_id");

    // Available numbers list can be retrieved by using list() function from available service, see
    // the SearchAvailableSnippet or
    // https://developers.sinch.com/docs/numbers/getting-started/java-sdk/searchavailable
    String phoneNumber = "available_phone_number_to_be_rented";

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
