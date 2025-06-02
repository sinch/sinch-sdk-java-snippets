package numbers.regions;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.numbers.api.v1.AvailableRegionService;
import com.sinch.sdk.domains.numbers.models.v1.NumberType;
import com.sinch.sdk.domains.numbers.models.v1.regions.available.request.AvailableRegionListRequest;
import com.sinch.sdk.domains.numbers.models.v1.regions.available.response.AvailableRegionListResponse;
import com.sinch.sdk.models.Configuration;
import java.util.Arrays;
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

    AvailableRegionService service = client.numbers().v1().regions();

    LOGGER.info("List");

    AvailableRegionListResponse response =
        service.list(
            AvailableRegionListRequest.builder()
                .setTypes(Arrays.asList(NumberType.MOBILE, NumberType.LOCAL))
                .build());

    LOGGER.info("Available regions:");

    response.iterator().forEachRemaining(item -> LOGGER.info(String.format("- %s", item)));
  }
}
