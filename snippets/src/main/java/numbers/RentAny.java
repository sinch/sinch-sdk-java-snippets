package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.v1.Capability;
import com.sinch.sdk.domains.numbers.models.v1.NumberType;
import com.sinch.sdk.domains.numbers.models.v1.request.AvailableNumberRentAnyRequest;
import com.sinch.sdk.models.Configuration;
import java.util.Collections;
import java.util.logging.Logger;
import utils.Settings;

public class RentAny {

  private static final Logger LOGGER = Logger.getLogger(RentAny.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("my_project_id");
    String keyId = Settings.getKeyId().orElse("my_key_id");
    String keySecret = Settings.getKeySecret().orElse("my_key_secret");

    // Available regions can be retrieved by using list() function from regions service, see
    // the numbers/regions/List snippet or
    // https://developers.sinch.com/docs/numbers/api-reference/numbers/tag/Available-Regions/
    String regionCode = "my_region_code";

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
                .build());

    LOGGER.info(String.format("Rented number: %s", response));
  }
}
