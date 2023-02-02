package me.kapsin.jpa.userchannel;

import me.kapsin.jpa.channel.Channel;
import me.kapsin.jpa.channel.ChannelRepository;
import me.kapsin.jpa.user.User;
import me.kapsin.jpa.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserChannelRepositoryTest {
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void userJoinChannelWithCascadeTest() {
        // given
        var newChannel = Channel.builder().name("new-group").build();
        var newUSer = User.builder().username("new_user").password("new_password").build();
        newChannel.joinUser(newUSer);

        // when
        var savedChannel = channelRepository.insertChannel(newChannel);
        var savedUser = userRepository.insertUser(newUSer);

        // then
        var foundChannel = channelRepository.selectChannel(savedChannel.getId());
        assert foundChannel.getUserChannels().stream()
                .map(UserChannel::getChannel)
                .map(Channel::getName)
                .anyMatch(name -> name.equals(newChannel.getName()));
    }
}