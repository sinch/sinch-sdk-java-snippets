package numbers;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.NumberType;
import com.sinch.sdk.domains.numbers.models.v1.request.ActiveNumberListRequest;
import com.sinch.sdk.domains.numbers.models.v1.response.ActiveNumberListResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;

public class List {

  private static final Logger LOGGER = Logger.getLogger(List.class.getName());

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

    LOGGER.info("Listing active numbers");

    ActiveNumberListResponse response =
        service.list(
            ActiveNumberListRequest.builder()
                .setRegionCode("US")
                .setType(NumberType.LOCAL)
                .build());

    LOGGER.info("Response");

    response
        .iterator()
        .forEachRemaining(f -> LOGGER.info(String.format("%s: %s", f.getPhoneNumber(), f)));
  }
}
