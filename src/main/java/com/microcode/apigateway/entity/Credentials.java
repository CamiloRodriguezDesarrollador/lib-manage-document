package com.microcode.apigateway.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Credentials {

    private String currentMail;
    private Integer currentType;
    private Integer currentClient;
    private Integer currentUser;
    private Integer currentPermission;
}
