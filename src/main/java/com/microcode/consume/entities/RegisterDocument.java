package com.microcode.consume.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class RegisterDocument {

    private String _id;

    private String documentId;

    private String linkedDocumentId;

    public String registerTypeFile;

    public String registerSizeFile;

    public String registerDetail;



}