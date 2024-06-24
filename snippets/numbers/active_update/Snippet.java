package numbers;

import com.sinch.sdk.domains.numbers.ActiveNumberService;
import com.sinch.sdk.domains.numbers.NumbersService;
import com.sinch.sdk.domains.numbers.models.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.requests.ActiveNumberUpdateRequestParameters;
import com.sinch.sdk.domains.numbers.models.requests.ActiveNumberUpdateVoiceConfigurationRequestParameters;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(NumbersService numbersService) {

    ActiveNumberService activeNumbersService = numbersService.active();

    String phoneNumber = "YOUR_phone_number_to_be_updated";
    String appId = "YOUR_app_id";
    String displayName = "Updated from Sinch Java SDK";

    ActiveNumberUpdateVoiceConfigurationRequestParameters voiceConfiguration =
        ActiveNumberUpdateVoiceConfigurationRequestParameters.builder().setAppId(appId).build();

    ActiveNumberUpdateRequestParameters updateRequest =
        ActiveNumberUpdateRequestParameters.builder()
            .setDisplayName(displayName)
            .setVoiceConfiguration(voiceConfiguration)
            .build();

    ActiveNumber response = activeNumbersService.update(phoneNumber, updateRequest);

    LOGGER.info(String.format("Updated number: %s", response));
  }
}
