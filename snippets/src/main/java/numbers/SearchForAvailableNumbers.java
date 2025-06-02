package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.NumberType;
import com.sinch.sdk.domains.numbers.models.v1.request.AvailableNumberListRequest;
import com.sinch.sdk.domains.numbers.models.v1.response.AvailableNumberListResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;

public class SearchForAvailableNumbers {

  private static final Logger LOGGER = Logger.getLogger(SearchForAvailableNumbers.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    NumbersService service = client.numbers().v1();

    String regionCode = "US";
    NumberType type = NumberType.LOCAL;

    AvailableNumberListRequest parameters =
        AvailableNumberListRequest.builder().setRegionCode(regionCode).setType(type).build();

    LOGGER.info("Looking for available numbers");

    AvailableNumberListResponse response = service.searchForAvailableNumbers(parameters);

    response
        .iterator()
        .forEachRemaining(
            number -> LOGGER.info(String.format("Available number details: %s", number)));
  }
}
