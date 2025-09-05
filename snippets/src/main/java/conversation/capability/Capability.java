/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package conversation.capability;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.conversation.api.v1.CapabilityService;
import com.sinch.sdk.domains.conversation.models.v1.ChannelRecipientIdentities;
import com.sinch.sdk.domains.conversation.models.v1.ChannelRecipientIdentity;
import com.sinch.sdk.domains.conversation.models.v1.ConversationChannel;
import com.sinch.sdk.domains.conversation.models.v1.Recipient;
import com.sinch.sdk.domains.conversation.models.v1.capability.request.QueryCapabilityRequest;
import com.sinch.sdk.domains.conversation.models.v1.capability.response.QueryCapabilityResponse;
import com.sinch.sdk.models.Configuration;
import com.sinch.sdk.models.ConversationRegion;
import java.util.logging.Logger;
import utils.Settings;

public class Capability {

  private static final Logger LOGGER = Logger.getLogger(Capability.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("MY_PROJECT_ID");
    String keyId = Settings.getKeyId().orElse("MY_KEY_ID");
    String keySecret = Settings.getKeySecret().orElse("MY_KEY_SECRET");
    String conversationRegion = Settings.getConversationRegion().orElse("MY_CONVERSATION_REGION");

    // The ID of the Conversation App where the recipient channel is configured
    String conversationApplicationId = "CONVERSATION_APP_ID";
    // The channel to look up the capabilities for
    ConversationChannel recipientChannel = ConversationChannel.SMS;
    // The phone number of the recipient to look up the capabilities for
    String recipientPhoneNumber = "RECIPIENT_PHONE_NUMBER";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .setConversationRegion(ConversationRegion.from(conversationRegion))
            .build();

    SinchClient client = new SinchClient(configuration);

    CapabilityService capabilityService = client.conversation().v1().capability();

    Recipient channelRecipients =
        ChannelRecipientIdentities.of(
            ChannelRecipientIdentity.builder()
                .setChannel(recipientChannel)
                .setIdentity(recipientPhoneNumber)
                .build());

    QueryCapabilityRequest request =
        QueryCapabilityRequest.builder()
            .setAppId(conversationApplicationId)
            .setRecipient(channelRecipients)
            .build();

    LOGGER.info(
        String.format("Trigger capability lookup for phone number '%s'", recipientPhoneNumber));

    QueryCapabilityResponse response = capabilityService.lookup(request);

    LOGGER.info("Response: " + response);
  }
}
