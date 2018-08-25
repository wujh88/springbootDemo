package com.sinotech.settle.config;



/**
 * @Description：rebbit mq配置
 */
//@Configuration
public class RabbitConfiguration {
//    public static final String EXCHANGE = "spring-boot-exchange";
//    public static final String ROUTINGKEY = "spring-boot-routingKey";
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.rabbitmq")
//    public ConnectionFactory connectionFactory(){
//        return new CachingConnectionFactory();
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }
//    /**
//      * 针对消费者配置
//        FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
//        HeadersExchange ：通过添加属性key-value匹配
//        DirectExchange:按照routingkey分发到指定队列
//        TopicExchange:多关键字匹配
//     */
//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Queue queue() {
//        return new Queue("settle-activi-task", true); //队列持久
//    }
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("exchange");
//    }
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange("fanoutExchange");
//    }
//
//    /**
//     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
//     * @param queueMessage
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//    }
//
//    /**
//     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
//     * @param queueMessage
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//    }
//
//    @Bean
//    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(AMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(BMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(CMessage).to(fanoutExchange);
//    }
//    @Bean
//    public SimpleMessageListenerContainer messageContainer() {
//      SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
//      container.setQueues(queue());
//      container.setExposeListenerChannel(true);
//      container.setMaxConcurrentConsumers(1);
//      container.setConcurrentConsumers(1);
//      container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//      container.setMessageListener(new ChannelAwareMessageListener() {
//
//          @Override
//          public void onMessage(Message message, Channel channel) throws Exception {
//              byte[] body = message.getBody();
//              System.out.println("receive msg : " + new String(body));
//              channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
//          }
//      });
//      return container;
//  }

}
