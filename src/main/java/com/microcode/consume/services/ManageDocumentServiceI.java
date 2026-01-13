package com.microcode.consume.services;

import com.microcode.consume.entities.RegisterDocument;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

public interface ManageDocumentServiceI {

    Mono<RegisterDocument> saveDocument(
            String documentId,
            byte[] fileBytes,
            String filename,
            Map<String, String> params
    );

    Mono<RegisterDocument> saveDocument(
            String documentId,
            MultipartFile file,
            Map<String, String> params
    ) throws IOException;

    Mono<String> getLinkDocument(String linkedDocumentId);
}
