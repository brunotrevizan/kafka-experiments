package com.kafka.experiments.infrastructure.kafka.config;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory,
            KafkaTemplate<String, String> kafkaTemplate) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        // Configure Dead Letter Queue
        DeadLetterPublishingRecoverer recoverer = createDeadLetterPublishingRecoverer(kafkaTemplate);

        // Configure DefaultErrorHandler with retries and DLQ
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                recoverer,
                new FixedBackOff(1000, 3) // 1-second delay, 3 retries
        );

        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    public DeadLetterPublishingRecoverer createDeadLetterPublishingRecoverer(KafkaTemplate<String, String> kafkaTemplate) {
        return new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                (rec, exception) -> {
                    logger.info("Mensagem movida para a DLQ. Key: {}, Value: {}, Exception: {}",
                            rec.key(), rec.value(), exception.getMessage());
                    return new TopicPartition("dlq-topic", rec.partition());
                }
        );
    }

}