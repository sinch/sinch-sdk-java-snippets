package conversation;

import com.sinch.sdk.domains.conversation.api.v1.*;
import com.sinch.sdk.domains.conversation.models.v1.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.request.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.response.SendMessageResponse;
import com.sinch.sdk.domains.conversation.models.v1.messages.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.types.text.*;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.*;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(ConversationService conversationService) {

    MessagesService messagesService = conversationService.messages();

    String appId = "YOUR_app_id";
    String from = "YOUR_sms_sender";
    String to = "RECIPIENT_number";
    ChannelRecipientIdentity recipient = 
        ChannelRecipientIdentity.builder()
            .setChannel(ConversationChannel.SMS)
            .setIdentity(to)
            .build();
    Collection<ChannelRecipientIdentity> recipients = new HashSet<ChannelRecipientIdentity>();
    recipients.add(recipient);
    String body = "This is a test Conversation message using the Sinch Java SDK.";
    String smsSender = "SMS_SENDER";

    LOGGER.info("Sending SMS Text using Conversation API");
    SendMessageResponse value =
        messagesService.sendMessage(
            SendMessageRequest.builder()
                .setAppId(appId)
                .setRecipient(
                    ChannelRecipientIdentities.builder()
                        .setRecipientIdentities(recipients)
                        .build())
                .setMessage(
                    AppMessage.builder()
                        .setBody(
                            TextMessage.builder()
                                .setText(body)
                                .build())
                        .build())
                .setChannelProperties(
                    Collections.singletonMap(smsSender, from))
                .build());

    LOGGER.info("Response: " + value);
  }
}