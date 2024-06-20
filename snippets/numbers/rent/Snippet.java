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

    // Available numbers list can be retrieved by using list() function from available service, see:
    // https://developers.sinch.com/quickstart-getting-started-poc/docs/numbers/getting-started/java-sdk/searchavailable
    String phoneNumber = "available_phone_number_to_be_rented";
    String servicePlanId = "YOUR_service_plan_id";

    RentSMSConfigurationRequestParameters smsConfiguration =
        RentSMSConfigurationRequestParameters.builder().setServicePlanId(servicePlanId).build();

    AvailableNumberRentRequestParameters rentRequest =
        AvailableNumberRentRequestParameters.builder()
            .setSmsConfiguration(smsConfiguration)
            .build();

    ActiveNumber response = availableNumbersService.rent(phoneNumber, rentRequest);

    LOGGER.info(String.format("Rented number: %s", response));
  }
}
