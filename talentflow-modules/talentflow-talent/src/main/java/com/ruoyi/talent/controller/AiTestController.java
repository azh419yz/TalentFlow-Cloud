package com.ruoyi.talent.controller;

import com.ruoyi.common.ai.service.AgentChatService;
import com.ruoyi.common.ai.service.RagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiTestController {

    @Autowired
    private AgentChatService agentChatService;

    @Autowired
    private RagService ragService;

    @GetMapping("/chat")
    public String simpleChat() {
        return agentChatService.simpleChat();
    }

    @GetMapping("/doc")
    public String saveDocument() {
        ragService.saveDocument("我测试一下能不能保存。", "SaveTest");
        return "保存成功";
    }

    @GetMapping("/doc_search")
    public String searchDocument() {
        return ragService.ragQuery("能不能保存");
    }
}
