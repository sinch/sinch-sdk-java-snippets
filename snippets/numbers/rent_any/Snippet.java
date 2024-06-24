package numbers;

import com.sinch.sdk.domains.numbers.AvailableNumberService;
import com.sinch.sdk.domains.numbers.NumbersService;
import com.sinch.sdk.domains.numbers.models.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.Capability;
import com.sinch.sdk.domains.numbers.models.NumberType;
import com.sinch.sdk.domains.numbers.models.requests.AvailableNumberRentAnyRequestParameters;
import com.sinch.sdk.domains.numbers.models.requests.RentSMSConfigurationRequestParameters;
import java.util.Collections;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(NumbersService numbersService) {

    AvailableNumberService availableNumbersService = numbersService.available();

    String servicePlanId = "YOUR_service_plan_id";
    String regionCode = "YOUR_region_code";

    Capability capability = Capability.SMS;
    NumberType numberType = NumberType.LOCAL;

    ActiveNumber response =
        availableNumbersService.rentAny(
            AvailableNumberRentAnyRequestParameters.builder()
                .setCapabilities(Collections.singletonList(capability))
                .setType(numberType)
                .setRegionCode(regionCode)
                .setSmsConfiguration(
                    RentSMSConfigurationRequestParameters.builder()
                        .setServicePlanId(servicePlanId)
                        .build())
                .build());

    LOGGER.info(String.format("Rented number: %s", response));
  }
}
