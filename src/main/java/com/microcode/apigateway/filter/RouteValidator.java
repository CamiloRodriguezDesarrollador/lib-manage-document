package com.microcode.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/auth",

            "/api/audit",


            "/api/mail/sendMailRegister",
            "/api/mail/sendMailRememberPassword",

            "/api/authorization/myAuthorization",
            "/api/authorization/findMyAuthorization",
            "/api/authorization/findForIdentities",
            "/api/authorization/findForUser",
            "/api/authorization/findForType",
            "/api/authorization/findApps",
            "/api/authorization/requestAppJoin",
            "/api/authorization/validatePermission",
            "/api/authorization/findProcess",
            "/api/authorization/findForClient",

            "/api/user/findDate",
            "/api/user/findName",
            "/api/user/findForMail",
            "/api/user/findDataPrincipal",
            "/api/user/update",

            "/api/client/findClientForCode",
            "/api/client/findDataProfile",
            "/api/client/findMyClient",
            "/api/client/findDataPrincipal",
            "/api/client/findClient",
            "/api/client/updated",

            "/api/console/applications/allInformation",
            "/api/console/applications/health",
            "/api/console/type/findForClient"

    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
