//package com.kama.notes.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.admin.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Properties;
//
//@Slf4j
//@Configuration
//public class KafkaConfig {
//
//    @Value("${kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Value("${kafka.topic.email-task}")
//    private String emailTaskTopic;
//
//    @Value("${kafka.topic.message-task}")
//    private String messageTaskTopic;
//
//    @PostConstruct
//    public void createTopics() {
//        try (AdminClient adminClient = createAdminClient()) {
//            DescribeTopicsResult describeResult = adminClient.describeTopics(Collections.singletonList(emailTaskTopic));
//            Map<String, TopicDescription> topicDescriptionMap = describeResult.all().get();
//
//            if (!topicExists(adminClient, emailTaskTopic)) {
//                NewTopic topic = new NewTopic(emailTaskTopic, 1, (short) 1);
//                adminClient.createTopics(Collections.singleton(topic));
//                log.info("✅ Kafka 主题 [{}] 已成功创建", emailTaskTopic);
//            } else {
//                TopicDescription description = topicDescriptionMap.get(emailTaskTopic);
//                int currentPartitionCount = description.partitions().size();
//
//                if (currentPartitionCount < 5) {
//                    // 增加分区数到 5
//                    NewPartitions newPartitions = NewPartitions.increaseTo(5);
//                    adminClient.createPartitions(Collections.singletonMap(emailTaskTopic, newPartitions)).all().get();
//                    log.info("🔧 Kafka 主题 [{}] 分区数从 {} 增加到 5", emailTaskTopic, currentPartitionCount);
//                } else {
//                    log.info("ℹ️ Kafka 主题 [{}] 已存在，分区数为 {}，无需修改", emailTaskTopic, currentPartitionCount);
//                }
//            }
//
//        } catch (Exception e) {
//            log.error("❌ Kafka 主题创建失败: {}", e.getMessage(), e);
//        }
//    }
//
//    private AdminClient createAdminClient() {
//        Properties props = new Properties();
//        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        return AdminClient.create(props);
//    }
//
//    private boolean topicExists(AdminClient adminClient, String topicName) throws Exception {
//        ListTopicsResult topics = adminClient.listTopics();
//        return topics.names().get().contains(topicName);
//    }
//}
