package com.ruoyi.common.ai.config;

import com.ruoyi.common.ai.service.AgentChatService;
import com.ruoyi.common.ai.service.RagService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.document.DefaultContentFormatter;
import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.transformer.ContentFormatTransformer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AI模块自动配置类
 *
 * @author ruoyi
 */
@AutoConfiguration
@Configuration
public class AiAutoConfiguration {

    @Bean
    public AgentChatService agentChatService(ChatModel chatModel) {
        return new AgentChatService(chatModel);
    }

    @Bean
    public DocumentTransformer documentTransformer() {
        return new ContentFormatTransformer(DefaultContentFormatter.defaultConfig());
    }

    @Bean
    public RagService ragService(DocumentTransformer documentTransformer, ChromaVectorStore chromaVectorStore) {
        return new RagService(documentTransformer, chromaVectorStore);
    }
}