package me.kapsin.jpa.thread;

import me.kapsin.jpa.channel.Channel;
import me.kapsin.jpa.channel.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ThreadRepositoryTest {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private ChannelRepository channelRepository;

//    @Test
//    void insertSelectThreadTest() {
//        //given
//        var newChannel = Channel.builder().name("new-channel").build();
//        var newThread = Thread.builder().message("new-message").build();
//        var newThread2 = Thread.builder().message("new-message2").build();
//        newThread.setChannel(newChannel);
//        newThread2.setChannel(newChannel);
//        //when
//        var saveChannel = channelRepository.insertChannel(newChannel);
//        var saveThread = threadRepository.insertThread(newThread);
//        var saveThread2 = threadRepository.insertThread(newThread2);
//
//        //then
//        var foundChannel = channelRepository.selectChannel(saveChannel.getId());
//        assert foundChannel.getThreads().containsAll(Set.of(saveThread,saveThread2));
//    }

    @Test
    void deleteThreadbyOrphanRemovalTest() {
        //given
        var newChannel = Channel.builder().name("new-channel").build();
        var newThread = Thread.builder().message("new-message").build();
        var newThread2 = Thread.builder().message("new-message2").build();
        newThread.setChannel(newChannel);
        newThread2.setChannel(newChannel);
        //when
        var saveChannel = channelRepository.insertChannel(newChannel);
        var saveThread = threadRepository.insertThread(newThread);
        var saveThread2 = threadRepository.insertThread(newThread2);

        //then
        var foundChannel = channelRepository.selectChannel(saveChannel.getId());
        foundChannel.getThreads().remove(saveThread);
//        var updateChannel = channelRepository.insertChannel(foundChannel);
//
//        assert foundChannel.getThreads().containsAll(Set.of(saveThread,saveThread2));
    }
}