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
import com.sinch.sdk.domains.numbers.models.v1.NumberType;
import com.sinch.sdk.domains.numbers.models.v1.request.AvailableNumbersListQueryParameters;
import com.sinch.sdk.domains.numbers.models.v1.response.AvailableNumberListResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class SearchForAvailableNumbers {

  private static final Logger LOGGER = Logger.getLogger(SearchForAvailableNumbers.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("my_project_id");
    String keyId = Settings.getKeyId().orElse("my_key_id");
    String keySecret = Settings.getKeySecret().orElse("my_key_secret");

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

    AvailableNumbersListQueryParameters parameters =
        AvailableNumbersListQueryParameters.builder()
            .setRegionCode(regionCode)
            .setType(type)
            .build();

    LOGGER.info("Looking for available numbers");

    AvailableNumberListResponse response = service.searchForAvailableNumbers(parameters);

    response
        .iterator()
        .forEachRemaining(
            number -> LOGGER.info(String.format("Available number details: %s", number)));
  }
}
