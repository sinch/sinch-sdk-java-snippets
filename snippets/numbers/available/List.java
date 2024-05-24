package numbers;

import com.sinch.sdk.domains.numbers.*;
import com.sinch.sdk.domains.numbers.models.NumberType;
import com.sinch.sdk.domains.numbers.models.requests.AvailableNumberListAllRequestParameters;
import java.util.logging.Logger;

public class List {

    private static final Logger LOGGER = Logger.getLogger(List.class.getName());
    
    public void list(NumbersService numbersService) {

        var service = numbersService.available();

        var regionCode = "US";
        var type = NumberType.LOCAL;

        var parameters =
                AvailableNumberListAllRequestParameters.builder()
                        .setRegionCode(regionCode)
                        .setType(type)
                        .build();

        var response = service.list(parameters);

        response.iterator().forEachRemaining(f -> LOGGER.info(f.toString()));
    }
}
