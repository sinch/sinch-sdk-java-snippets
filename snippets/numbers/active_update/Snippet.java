package numbers;

import com.sinch.sdk.domains.numbers.api.v1.NumbersService;
import com.sinch.sdk.domains.numbers.models.v1.ActiveNumber;
import com.sinch.sdk.domains.numbers.models.v1.VoiceConfiguration;
import com.sinch.sdk.domains.numbers.models.v1.VoiceConfigurationRTC;
import com.sinch.sdk.domains.numbers.models.v1.request.ActiveNumberUpdateRequest;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(NumbersService numbersService) {

    String phoneNumber = "YOUR_phone_number_to_be_updated";
    String appId = "YOUR_app_id";
    String displayName = "Updated from Sinch Java SDK";

    VoiceConfiguration voiceConfiguration = VoiceConfigurationRTC.builder().setAppId(appId).build();

    ActiveNumberUpdateRequest updateRequest =
        ActiveNumberUpdateRequest.builder()
            .setDisplayName(displayName)
            .setVoiceConfiguration(voiceConfiguration)
            .build();

    ActiveNumber response = numbersService.update(phoneNumber, updateRequest);

    LOGGER.info(String.format("Updated number: %s", response));
  }
}
