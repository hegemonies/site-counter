package site.hegemonies.collector.configuration

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import site.hegemonies.collector.constant.KafkaConstants

@Configuration
class KafkaTopicConfiguration {

    @Bean
    fun siteCounterTopic(): NewTopic =
        NewTopic(KafkaConstants.COUNTER_TOPIC_NAME, 1, 1)
}
