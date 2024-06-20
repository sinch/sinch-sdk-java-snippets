package numbers;

import com.sinch.sdk.domains.numbers.ActiveNumberService;
import com.sinch.sdk.domains.numbers.AvailableNumberService;
import com.sinch.sdk.domains.numbers.NumbersService;
import com.sinch.sdk.domains.numbers.models.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.requests.ActiveNumberUpdateRequestParameters;
import com.sinch.sdk.domains.numbers.models.requests.ActiveNumberUpdateVoiceConfigurationRequestParameters;
import com.sinch.sdk.domains.numbers.models.requests.AvailableNumberRentRequestParameters;
import com.sinch.sdk.domains.numbers.models.requests.RentSMSConfigurationRequestParameters;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(NumbersService numbersService) {

    AvailableNumberService availableNumbersService = numbersService.available();

    // Available numbers list can be retrieved by using list() function from available service:
    // See https://developers.sinch.com/quickstart-getting-started-poc/docs/numbers/getting-started/java-sdk/searchavailable
    String phoneNumber = "available_phone_number_to_be_rented";
    String servicePlanId = "YOUR_service_plan_id";
    String appId = "YOUR_app_id";

    String displayName = "Updated from Sinch Java SDK";

    RentSMSConfigurationRequestParameters smsConfiguration =
        RentSMSConfigurationRequestParameters.builder().setServicePlanId(servicePlanId).build();

    AvailableNumberRentRequestParameters rentRequest =
        AvailableNumberRentRequestParameters.builder()
            .setSmsConfiguration(smsConfiguration)
            .build();

    // rent with configuration settings
    ActiveNumber response = availableNumbersService.rent(phoneNumber, rentRequest);

    LOGGER.info(String.format("Rented number: %s", response));

    // Update rented number to edit configuration:
    // - adding voice configuration
    // - setting a display name
    ActiveNumberService activeNumbersService = numbersService.active();

    ActiveNumberUpdateVoiceConfigurationRequestParameters voiceConfiguration =
        ActiveNumberUpdateVoiceConfigurationRequestParameters.builder().setAppId(appId).build();

    ActiveNumberUpdateRequestParameters updateRequest =
        ActiveNumberUpdateRequestParameters.builder()
            .setDisplayName(displayName)
            .setVoiceConfiguration(voiceConfiguration)
            .build();

    response = activeNumbersService.update(phoneNumber, updateRequest);

    LOGGER.info(String.format("Updated number: %s", response));
  }
}
