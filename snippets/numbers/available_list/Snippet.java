package numbers;

import com.sinch.sdk.domains.numbers.*;
import com.sinch.sdk.domains.numbers.models.*;
import com.sinch.sdk.domains.numbers.models.requests.*;
import com.sinch.sdk.domains.numbers.models.responses.AvailableNumberListResponse;
import java.util.logging.Logger;

public class Snippet {

    private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

    static void execute(NumbersService numbersService) {

        AvailableNumberService availableNumbersService = numbersService.available();

        String regionCode = "US";
        NumberType type = NumberType.LOCAL;

        AvailableNumberListAllRequestParameters parameters =
                AvailableNumberListAllRequestParameters.builder()
                        .setRegionCode(regionCode)
                        .setType(type)
                        .build();

        AvailableNumberListResponse response = availableNumbersService.list(parameters);

        response.iterator()
                .forEachRemaining(
                        number ->
                                LOGGER.info(String.format("Available number details: %s", number)));
    }
}
