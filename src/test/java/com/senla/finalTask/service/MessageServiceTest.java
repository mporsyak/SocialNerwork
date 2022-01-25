package com.senla.finalTask.service;

import com.senla.finalTask.Util.WsSender;
import com.senla.finalTask.dto.EventType;
import com.senla.finalTask.dto.ObjectType;
import com.senla.finalTask.model.Message;
import com.senla.finalTask.model.Views;
import com.senla.finalTask.repository.MessageRepository;
import com.senla.finalTask.repository.UserSubscriptionRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
    private BiConsumer<EventType, Message> wsSender;

    @Test
    public void create() throws IOException {
        Message message = new Message();
        message.setId(1L);
        message.setText("Test message https://www.youtube.com/watch?v=IF_ldbgFbzU&t=710s");
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        UserSubscriptionRepository userSubscriptionRepository = Mockito.mock(UserSubscriptionRepository.class);
        WsSender wsSender = Mockito.mock(WsSender.class);
        when(wsSender.getSender(ObjectType.MESSAGE, Views.FullMessage.class)).thenReturn((x1, x2) -> {
                }
        );
        MessageService messageService = new MessageService(messageRepository, userSubscriptionRepository, wsSender);
        when(messageRepository.save(message)).thenReturn(message);
        Message res = messageService.create(message);
        assertTrue(res.getCreationDate().isBefore(LocalDateTime.now().plusSeconds(1L)));
        assertTrue(res.getCreationDate().isAfter(LocalDateTime.now().minusSeconds(1L)));
        assertEquals("https://www.youtube.com/watch?v=IF_ldbgFbzU&t=710s", res.getLink());
    }
}