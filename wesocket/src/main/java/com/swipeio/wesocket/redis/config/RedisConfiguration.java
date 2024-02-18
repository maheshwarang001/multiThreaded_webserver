//package com.swipeio.wesocket.redis.config;
//
//import com.swipeio.wesocket.redis.subscriber.Subscriber;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//@Configuration
//@Slf4j
//public class RedisConfiguration {
//
//    @Value("${redis.value.topic}")
//    private String topic;
//
//    private RedisMessageListenerContainer redisMessageListenerContainer;
//
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName("localhost");
//        redisStandaloneConfiguration.setPort(6379);
//
//        log.info("REDIS CONNECTION");
//
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Message>(Message.class));
//        return redisTemplate;
//    }
//
//    @Bean
//    public ChannelTopic topic(){
//        return new ChannelTopic(topic);
//    }
//
//    @Bean
//    public MessageListenerAdapter messageListenerAdapter(){
//        return new MessageListenerAdapter(new Subscriber());
//    }
//
//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer(){
//        redisMessageListenerContainer = new RedisMessageListenerContainer();
//        redisMessageListenerContainer.setConnectionFactory(jedisConnectionFactory());
//        redisMessageListenerContainer.addMessageListener(messageListenerAdapter(),topic());
//        return redisMessageListenerContainer;
//    }
//
//
//
//    public void unsubscribe() {
//        if (redisMessageListenerContainer != null) {
//            redisMessageListenerContainer.removeMessageListener(messageListenerAdapter());
//        }
//    }
//}
