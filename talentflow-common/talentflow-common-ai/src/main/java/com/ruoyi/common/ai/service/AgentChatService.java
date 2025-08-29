package com.ruoyi.common.ai.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class AgentChatService {

    private static final String DEFAULT_PROMPT = "你好，介绍下你自己吧。请用中文回答。";
    private final ChatModel ollamaChatModel;

    public AgentChatService(ChatModel chatModel) {
        this.ollamaChatModel = chatModel;
    }

    public String simpleChat() {
        return ollamaChatModel.call(new Prompt(DEFAULT_PROMPT)).getResult().getOutput().getText();
    }

    /**
     * Stream 流式调用。可以使大模型的输出信息实现打字机效果。
     */
    public Flux<String> streamChat(HttpServletResponse response) {
        // 避免返回乱码
        response.setCharacterEncoding("UTF-8");
        Flux<ChatResponse> stream = ollamaChatModel.stream(new Prompt(DEFAULT_PROMPT));
        return stream.mapNotNull(resp -> resp.getResult().getOutput().getText());
    }

    /**
     * 使用编程方式自定义 LLMs ChatOptions 参数， {@link OllamaOptions}。
     * 优先级高于在 application.yml 中配置的 LLMs 参数！
     */
    public String customChat() {
        OllamaOptions customOptions = OllamaOptions.builder()
                .topP(0.7)
                .model("qwen3:4b")
                .temperature(0.8)
                .build();
        return ollamaChatModel.call(new Prompt(DEFAULT_PROMPT, customOptions)).getResult().getOutput().getText();
    }
}
