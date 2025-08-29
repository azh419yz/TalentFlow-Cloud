package com.ruoyi.common.ai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentTransformer;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RagService {

    private final DocumentTransformer documentTransformer;
    private final VectorStore vectorStore;

    public RagService(DocumentTransformer documentTransformer, ChromaVectorStore chromaVectorStore) {
        this.documentTransformer = documentTransformer;
        this.vectorStore = chromaVectorStore;
    }

    public void importData(Resource resource) {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(resource);
        List<Document> documentList = tikaDocumentReader.get();
        List<Document> transformerDocs = documentTransformer.apply(documentList);
        vectorStore.accept(transformerDocs);
    }

    public void saveDocument(String content, String source) {
        Document document = new Document(content, Map.of("source", source));
        vectorStore.add(List.of(document));
        System.out.println("文档已保存到 ChromaDB: " + source);
    }

    /**
     * 从 ChromaDB 检索最相关的文档
     */
    public List<String> retrieve(String query) {
        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(3)
                .build();

        List<Document> documents = vectorStore.similaritySearch(request);

        assert documents != null;
        return documents.stream()
                .map(doc -> "【来源: " + doc.getMetadata().get("source") + "】" + doc.getText())
                .collect(Collectors.toList());
    }

    /**
     * RAG 查询：检索 + 生成
     */
    public String ragQuery(String question) {
        List<String> contexts = retrieve(question);
        if (contexts.isEmpty()) {
            return "知识库中未找到相关信息。";
        }
        return String.join("\n\n", contexts);
    }
}
