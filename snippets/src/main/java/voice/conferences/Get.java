package voice.conferences;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.voice.api.v1.ConferencesService;
import com.sinch.sdk.domains.voice.models.v1.conferences.response.GetConferenceInfoResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class Get {

  private static final Logger LOGGER = Logger.getLogger(Get.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    String conferenceId = "AN_EXISTING_CONFERENCE_ID";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    ConferencesService conferencesService = client.voice().v1().conferences();

    LOGGER.info(String.format("Get info for conference with ID '%s'", conferenceId));

    GetConferenceInfoResponse response = conferencesService.get(conferenceId);

    LOGGER.info("Response: " + response);
  }
}
