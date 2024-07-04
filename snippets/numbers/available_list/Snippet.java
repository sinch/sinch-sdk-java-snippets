package numbers;

import com.sinch.sdk.domains.numbers.api.v1.AvailableNumberService;
import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.NumberType;
import com.sinch.sdk.domains.numbers.models.v1.available.request.AvailableNumberListRequest;
import com.sinch.sdk.domains.numbers.models.v1.available.response.AvailableNumberListResponse;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(NumbersService numbersService) {

    AvailableNumberService availableNumbersService = numbersService.available();

    String regionCode = "US";
    NumberType type = NumberType.LOCAL;

    AvailableNumberListRequest parameters =
        AvailableNumberListRequest.builder()
            .setRegionCode(regionCode)
            .setType(type)
            .build();

    AvailableNumberListResponse response = availableNumbersService.list(parameters);

    response
        .iterator()
        .forEachRemaining(
            number -> LOGGER.info(String.format("Available number details: %s", number)));
  }
}
