package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.v1.Capability;
import com.sinch.sdk.domains.numbers.models.v1.NumberType;
import com.sinch.sdk.domains.numbers.models.v1.SmsConfiguration;
import com.sinch.sdk.domains.numbers.models.v1.request.AvailableNumberRentAnyRequest;
import com.sinch.sdk.models.Configuration;
import java.util.Collections;
import java.util.logging.Logger;

public class RentAny {

  private static final Logger LOGGER = Logger.getLogger(RentAny.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    String servicePlanId = "YOUR_service_plan_id";
    String regionCode = "YOUR_region_code";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    NumbersService service = client.numbers().v1();

    Capability capability = Capability.SMS;
    NumberType numberType = NumberType.LOCAL;

    ActiveNumber response =
        service.rentAny(
            AvailableNumberRentAnyRequest.builder()
                .setCapabilities(Collections.singletonList(capability))
                .setType(numberType)
                .setRegionCode(regionCode)
                .setSmsConfiguration(
                    SmsConfiguration.builder().setServicePlanId(servicePlanId).build())
                .build());

    LOGGER.info(String.format("Rented number: %s", response));
  }
}
