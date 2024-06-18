package numbers;

import com.sinch.sdk.domains.numbers.AvailableNumberService;
import com.sinch.sdk.domains.numbers.NumbersService;
import com.sinch.sdk.domains.numbers.models.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.requests.AvailableNumberRentRequestParameters;
import com.sinch.sdk.domains.numbers.models.requests.RentSMSConfigurationRequestParameters;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(NumbersService numbersService) {

    AvailableNumberService availableNumbersService = numbersService.available();

    String servicePlanId = "YOUR_service_plan_id";
    String phoneNumber = "YOUR_phone_number";

    ActiveNumber response =
        availableNumbersService.rent(
            phoneNumber,
            AvailableNumberRentRequestParameters.builder()
                .setSmsConfiguration(
                    RentSMSConfigurationRequestParameters.builder()
                        .setServicePlanId(servicePlanId)
                        .build())
                .build());

    LOGGER.info(String.format("Rented number: %s", response));
  }
}
