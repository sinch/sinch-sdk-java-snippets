package voice.conferences;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.voice.api.v1.ConferencesService;
import com.sinch.sdk.domains.voice.models.v1.callouts.request.CalloutRequestConference;
import com.sinch.sdk.domains.voice.models.v1.destination.DestinationPstn;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class Call {

  private static final Logger LOGGER = Logger.getLogger(Call.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");
    String phoneNumber = Settings.getPhoneNumber().orElse("MY_SINCH_PHONE_NUMBER");

    String conferenceId = "AN_EXISTING_OR_TO_BE_CREATED_CONFERENCE_ID";
    String phoneNumberToBeCalled = "PHONE_NUMBER_TO_BE_CALLED";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    ConferencesService conferencesService = client.voice().v1().conferences();

    LOGGER.info(
        String.format(
            "Joining conference '%s' for phone number '%s'", conferenceId, phoneNumberToBeCalled));

    CalloutRequestConference request =
        CalloutRequestConference.builder()
            .setDestination(DestinationPstn.from(phoneNumberToBeCalled))
            .setCli(phoneNumber)
            .setConferenceId(conferenceId)
            .build();

    String response = conferencesService.call(request);

    LOGGER.info("Response: " + response);
  }
}
