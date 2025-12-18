package com.microcode.consume.services;

import com.microcode.consume.entities.RegisterDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

@Service
public class ManageDocumentServices {

    private final WebClient webClient;

    public ManageDocumentServices(@Value("${api.environment}") String apiEnvironment) {

        String baseUrl;
        if ("prod".equalsIgnoreCase(apiEnvironment)) baseUrl = "https://back-manage-document-842209943869.us-east1.run.app";
        else baseUrl = "https://back-manage-document-871565840020.us-east1.run.app";

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * Registra documentos enviando multipart/form-data
     *
     * @param documentId Id del documento
     * @param fileBytes       Archivo físico
     * @param params     Lista de mapas con los parámetros a enviar
     * @return Mono<String> con la respuesta del endpoint
     */
    public Mono<RegisterDocument> registerDocument(String documentId, byte[] fileBytes, String filename, Map<String, Object> params) {

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("documentId", documentId);

        // ByteArrayResource para mantener el nombre del archivo
        body.add("file", new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return filename;
            }
        });

        // Params a JSON
        StringBuilder jsonBuilder = new StringBuilder("{");
        params.forEach((k, v) -> jsonBuilder.append("\"").append(k).append("\":\"").append(v).append("\","));
        if (!params.isEmpty()) jsonBuilder.setLength(jsonBuilder.length() - 1);
        jsonBuilder.append("}");
        body.add("params", jsonBuilder.toString());

        return webClient.post()
                .uri("/api/manage-document/register")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(body))
                .retrieve()
                .bodyToMono(RegisterDocument.class);
    }

    /**
     * Sobrecarga: MultipartFile (Spring MVC)
     */
    public Mono<RegisterDocument> registerDocument(String documentId, MultipartFile file, Map<String, Object> params) throws IOException {
        return registerDocument(documentId, file.getBytes(), file.getOriginalFilename(), params);
    }

    /**
     * Retorna documento en url
     *
     * @param linkedDocumentId Id de ubicacón del documento
     * @return Mono<String> con la url del documento
     */
    public Mono<String> getLinkDocument(String linkedDocumentId) {
        return webClient.get()
                .uri("/api/manage-document/register/view-document?linkIdDocument="+linkedDocumentId)
                .retrieve()
                .bodyToMono(String.class);
    }


}
