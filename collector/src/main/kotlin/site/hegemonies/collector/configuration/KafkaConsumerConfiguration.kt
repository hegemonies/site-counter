package site.hegemonies.collector.configuration

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import site.hegemonies.collector.constant.KafkaConstants
import java.io.Serializable

@Configuration
class KafkaConsumerConfiguration(
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    val bootstrapAddress: String
) {

    private val kafkaConsumerProps: Map<String, Serializable> =
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            ConsumerConfig.GROUP_ID_CONFIG to KafkaConstants.COUNTER_TOPIC_NAME,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to true,
        )

    @Bean
    @Primary
    fun kafkaConsumerFactory(): ConsumerFactory<String, String> {
        return DefaultKafkaConsumerFactory(kafkaConsumerProps)
    }
}
