package com.microcode.apigateway.security;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Setter
@Service
public class Path {

    private String url;
    private List<String> authorized;
    private ArrayList<Path> myRoutesProtected = new ArrayList<>();
    private ArrayList<Path> myRoutesOpen = new ArrayList<>();
    private ArrayList<Path> myRoutesOpenJustToken = new ArrayList<>();
    private ArrayList<Path> myRoutesAll = new ArrayList<>();

    public Path(String url, List<String> authorized) {
        this.url = url;
        this.authorized = authorized;
    }

    public Path(){}

    @Value("${aut.console.platform}")
    public String autConsolePlatform;
    @Value("${aut.provider.super}")
    public String autProviderSuper;
    @Value("${aut.provider.admin}")
    public String autProviderAdmin;
    @Value("${aut.provider.reader}")
    public String autProviderReader;
    @Value("${aut.provider.provider}")
    public String autProviderProvider;
    @Value("${aut.payment.super}")
    public String autPaymentSuper;
    @Value("${aut.payment.admin}")
    public String autPaymentAdmin;
    @Value("${aut.agreement.super}")
    public String autAgreementSuper;
    @Value("${aut.agreement.admin}")
    public String autAgreementAdmin;
    @Value("${aut.agreement.reader}")
    public String autAgreementReader;
    @Value("${aut.agreement.provider}")
    public String autAgreementProvider;



    @PostConstruct
    private void init() {

//        Back audit

        myRoutesProtected.add(new Path("/api/audit/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/audit/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/audit/session/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/audit/session/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesOpenJustToken.add(new Path("/api/audit/create", null));
        myRoutesOpen.add(new Path("/api/audit/ping", null));

//       Back Authorization

        myRoutesAll.add(new Path("/api/authorization/myAuthorization", null));
        myRoutesOpen.add(new Path("/api/authorization/ping", null));
        myRoutesAll.add(new Path("/api/authorization/findMyAuthorization", null));
        myRoutesAll.add(new Path("/api/authorization/findForIdentities", null));
        myRoutesAll.add(new Path("/api/authorization/findApps", null));
        myRoutesAll.add(new Path("/api/authorization/findForUser", null));
        myRoutesAll.add(new Path("/api/authorization/findForType", null));
        myRoutesAll.add(new Path("/api/authorization/findForClient", null));
        myRoutesAll.add(new Path("/api/authorization/requestAppJoin", null));
        myRoutesOpenJustToken.add(new Path("/api/authorization/validateAccessRoute", null));
        myRoutesOpen.add(new Path("/api/authorization/health", null));
        myRoutesAll.add(new Path("/api/authorization/validatePermission", null));
        myRoutesAll.add(new Path("/api/authorization/findProcess", null));
        myRoutesProtected.add(new Path("/api/authorization/findAll", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/delete", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/active", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/addProcess", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/deleteProcess", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/allInformationFilters", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/create", Arrays.asList(autConsolePlatform,autProviderSuper, autAgreementSuper,autAgreementAdmin )));
        myRoutesProtected.add(new Path("/api/authorization/findPermission", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization/findAllProcess", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/authorization", Collections.singletonList(autConsolePlatform)));

//       Back client

        myRoutesOpenJustToken.add(new Path("/api/client/findClientForCode", null));
        myRoutesOpenJustToken.add(new Path("/api/client/findDataPrincipal", null));
        myRoutesOpenJustToken.add(new Path("/api/client/findDataProfile", null));
        myRoutesAll.add(new Path("/api/client/findMyClient", null));

        myRoutesProtected.add(new Path("/api/client/allInformation", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/delete", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/active", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/findFilter", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/findForId", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/create", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/updatedFolder", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/updatedFolderInternal", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/client/updated", Arrays.asList(autConsolePlatform,autProviderSuper) ));
        myRoutesProtected.add(new Path("/api/client", Collections.singletonList(autConsolePlatform)));

//        Back console

        myRoutesOpen.add(new Path("/api/console/applications/allInformation", null));
        myRoutesOpen.add(new Path("/api/console/agreement-accept/ping", null));
        myRoutesProtected.add(new Path("/api/console/applications/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/applications/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/applications/allInformationFilter", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/applications/create", Collections.singletonList(autConsolePlatform)));
        myRoutesOpen.add(new Path("/api/console/applications/health", null));
        myRoutesProtected.add(new Path("/api/console/applications/allInformation", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/applications", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/console/permission/allInformation", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/permission/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/permission/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/permission/delete", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/permission/active", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/permission/allInformationFilter", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/permission/create", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/permission", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/console/process/allInformation", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/delete", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/findForId", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/active", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/allInformationFilter", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/create", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process/updatedFolder", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/process", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/console/type/allInformation", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/type/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/type/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/type/delete", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/type/active", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/type/allInformationFilter", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/type/create", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/type", Collections.singletonList(autConsolePlatform)));

        myRoutesAll.add(new Path("/api/console/type/findForClient", null));

        myRoutesProtected.add(new Path("/api/console/agreement-accept", Arrays.asList( autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/console/agreement-accept/code_company", Arrays.asList( autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/console/agreement-accept/code_company_all", Arrays.asList( autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));

        myRoutesProtected.add(new Path("/api/console/agreement-observation", Arrays.asList( autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/console/agreement-observation/code_company", Arrays.asList( autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));

        myRoutesProtected.add(new Path("/api/console/agreement-document", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementProvider,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/console/agreement-document/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/console/agreement-document/download", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));

        myRoutesProtected.add(new Path("/api/console/agreement-authorization/create", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/agreement-authorization/delete", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/agreement-authorization/findForUser", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/console/agreement-authorization/allInformation", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/console/agreement-authorization/findIsAuthorizedSection", Arrays.asList(autConsolePlatform, autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/console/agreement-authorization/findIsAuthorized", Arrays.asList(autConsolePlatform, autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));

//       Back user

        myRoutesProtected.add(new Path("/api/user/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/delete", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/active", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/closeSession", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/activeBlock", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/allInformationFilter", Collections.singletonList(autConsolePlatform)));
        myRoutesOpenJustToken.add(new Path("/api/user/findDate", null));
        myRoutesOpenJustToken.add(new Path("/api/user/findDataPrincipal", null));
        myRoutesOpen.add(new Path("/api/user/ping", null));
        myRoutesProtected.add(new Path("/api/user/findForId", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/updateDriveId", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/user/findForUserId", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/user/create-admin", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesOpenJustToken.add(new Path("/api/user/update", null));
        myRoutesOpenJustToken.add(new Path("/api/user/findForMail", null));
        myRoutesOpenJustToken.add(new Path("/api/user/findName", null));


//        Back provider
        myRoutesOpen.add(new Path("/api/provider/provider/ping", null));
        myRoutesOpenJustToken.add(new Path("/api/provider/provider/createRequest", null));

        myRoutesProtected.add(new Path("/api/provider/authentication/quantity", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/authentication/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/authentication/findForPrvId", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/authentication/delete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/authentication/active", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/authentication", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesProtected.add(new Path("/api/provider/provider/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/quantity", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider/provider/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider/provider/findAllDataUser", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/activeUser", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/deleteUser", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/dataTableUser", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/quantityTableUser", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesProtected.add(new Path("/api/provider/provider/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderProvider,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider/provider/list-certificate", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderProvider,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider/provider/generate-certificate", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderProvider,autProviderReader)));

        myRoutesProtected.add(new Path("/api/provider/provider/delete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/active", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/updateFolder", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin)));
        myRoutesProtected.add(new Path("/api/provider/provider/providerUser", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider/provider/allProviderUser", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/providerUsers", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/providerForUser", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/providerGeneral", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/create", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/providerForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider/provider/createForCriticality", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider/provider/update", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider/provider", Arrays.asList(autConsolePlatform,autProviderSuper)));

//        Back provider document

        myRoutesOpen.add(new Path("/api/provider-document/provider-document/ping", null));
        myRoutesOpen.add(new Path("/api/provider-document/provider-document/sendMailPendDocument", null));
        myRoutesOpen.add(new Path("/api/provider-document/provider-document/update-document-status", null));

        myRoutesProtected.add(new Path("/api/provider-document/provider-document-file/download", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document-file/downloadInternalDocument", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));

        myRoutesProtected.add(new Path("/api/provider-document/provider-document-file/delete", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document-file/uploadDocument", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));

        myRoutesProtected.add(new Path("/api/provider-document/provider-document/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document/quantity", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document/allDataDocument", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document/saveFormulary", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document/findForPrvId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document/findForPrvIdAuthorized", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-document/verified", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin)));

        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/findResults", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/quantity", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/allDataEvaluation", Arrays.asList(autConsolePlatform,autProviderSuper, autProviderAdmin)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/downloadMail", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderReader,autProviderAdmin)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/findForPeriod", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/changeStatusPeriod", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/sendMailNotInicializated", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/sendMailIncomplete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/updatedPercentageEvaluation", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/saveFormulary", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/providerForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/provider-document/provider-evaluation/createForProvider", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderProvider)));


//      Back app provider general

        myRoutesProtected.add(new Path("/api/app-provider/general/criticality/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/criticality/quantity", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/criticality/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/criticality/delete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/criticality/active", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/criticality/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/criticality/create", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/criticality", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/quantity", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/delete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/active", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/findForFormulary", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/findForFormularyProcess", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/deleteAttach", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/updateAttachment", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/download", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/create", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail/uploadDocumentInternal", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-detail", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-process/changueStatus", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-process/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin, autProviderReader, autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-process/informationForPeriod", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary-process/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));

        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/quantity", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/delete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/active", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/findForType", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary/create", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/formulary", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesProtected.add(new Path("/api/app-provider/general/period/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/quantity", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/delete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/active", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/findForType", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/updateFolder", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period/create", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/period", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesProtected.add(new Path("/api/app-provider/general/process/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/process/quantity", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/process/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/process/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/process/updateFolder", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/allInformation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/quantity", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/dataTable", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/delete", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/active", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/findForId", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/create", Arrays.asList(autConsolePlatform,autProviderSuper)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess/findForProcess", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader,autProviderProvider)));
        myRoutesProtected.add(new Path("/api/app-provider/general/subProcess", Arrays.asList(autConsolePlatform,autProviderSuper)));

        myRoutesOpen.add(new Path("/api/app-provider/general/formulary-detail/ping", null));

//      Back provider indicator

        myRoutesProtected.add(new Path("/api/app-provider/indicator/quantityProviders", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/quantityPeriods", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/quantityProcess", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/dataProvidersEvaluation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/quantitySupplierEvaluation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/quantityFilterProcess", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/quantityDocument", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/quantityProvidersFilter", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/percentageEvaluation", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));
        myRoutesProtected.add(new Path("/api/app-provider/indicator/dataEvaluationsMonth", Arrays.asList(autConsolePlatform,autProviderSuper,autProviderAdmin,autProviderReader)));

        myRoutesOpen.add(new Path("/api/app-provider/indicator/ping", null));

//      Back agreement

        myRoutesProtected.add(new Path("/api/agreement/projected/empNdFil", Collections.singletonList(autAgreementSuper)));
        myRoutesProtected.add(new Path("/api/agreement/projected/findSalaries", Collections.singletonList(autAgreementSuper)));
        myRoutesProtected.add(new Path("/api/agreement/projected/detail", Collections.singletonList(autAgreementSuper)));

        myRoutesProtected.add(new Path("/api/agreement/agreement-cto-city/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-group-process/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));

        myRoutesProtected.add(new Path("/api/agreement/agreement-group/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-inform/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-inhouse/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-profile-rta/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-tributary/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-profile/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-office/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-ppal/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-dota/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-policy/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-responsability-client/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-observation/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement/companies", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-ric/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-ric/mail_commercial", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-ric/mail_represent", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-test-psico/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-detail-commercial/code_company", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesProtected.add(new Path("/api/agreement/agreement-authorization", Arrays.asList(autAgreementSuper,autAgreementAdmin,autAgreementReader,autAgreementProvider)));
        myRoutesOpen.add(new Path("/api/agreement/agreement-authorization/ping", null));

//      Back chat

        myRoutesOpen.add(new Path("/api/app", null));
        myRoutesOpen.add(new Path("/api/chat/company", null));
        myRoutesOpen.add(new Path("/api/chat/action/ping", null));

        myRoutesOpen.add(new Path("/api/chat/wp/status", null));
        myRoutesOpen.add(new Path("/api/chat/wp", null));

        myRoutesProtected.add(new Path("/api/chat/action", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/action/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/action/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/action/actions", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/action/create", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/chat/principal-data", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/principal-data/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/principal-data/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/principal-data/actions", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/principal-data/create", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/chat/chats/active/chats", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/chats/sendMessageChatId", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/chat/quantity/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/quantity/quantity", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/quantity/delete", Collections.singletonList(autConsolePlatform)));

        myRoutesProtected.add(new Path("/api/chat/register/dataTable", Collections.singletonList(autConsolePlatform)));
        myRoutesProtected.add(new Path("/api/chat/register/quantity", Collections.singletonList(autConsolePlatform)));



//      Back payment
        myRoutesProtected.add(new Path("/api/app-payment/contract", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract/dashboard-month-quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract/dashboard-quantity-role", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract/dashboard-month-quantity-role-payment", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract/dashboard-quantity-branch", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-absenteeism", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-absenteeism/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-absenteeism/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-absenteeism/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-branch", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-branch/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-branch/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-branch/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-programed", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-programed/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-programed/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-programed/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-programed/generate-day", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-programed/contract", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-center-cost", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-center-cost/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-center-cost/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-center-cost/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/dashboard-month-quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/dashboard-month-quantity-role-payment", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/dashboard-quantity-branch", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-payment-type", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-payment-type/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-payment-type/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-payment-type/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-role", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-role/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-role/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-role/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-salary", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-salary/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-salary/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/contract-salary/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/employee", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/employee/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/employee/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/employee/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/employee/dashboard-month-quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept/change-status-ids", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-contract-concept/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-concept", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-concept/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-concept/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-concept/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-header", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-header/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-header/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-header/contract", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment-header/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/table", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/id-generate", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/change-status", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/create-payment", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/add-payment", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/inactive", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/dashboard-month-quantity", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/dashboard-month-value", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/dashboard-month-value-employee", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesProtected.add(new Path("/api/app-payment/payment/prompt-payment", Arrays.asList(autConsolePlatform,autPaymentSuper,autPaymentAdmin)));
        myRoutesOpen.add(new Path("/api/app-payment/contract-absenteeism/ping", null));


    }

    public List<String> getAuthorizedForUrl(String url) {
        Optional<Path> pathOptional = myRoutesProtected.stream()
                .filter(path -> path.getUrl().equals(url))
                .findFirst();

        return pathOptional.map(path -> new ArrayList<>(path.getAuthorized()))
                .orElse(null);
    }

    public Boolean getOpenForUrl(String url) {
        return myRoutesOpen.stream()
                .anyMatch(path -> path.getUrl().equals(url));
    }

    public Boolean getOpenForUrlToken(String url) {
        return myRoutesOpenJustToken.stream()
                .anyMatch(path -> path.getUrl().equals(url));
    }

    public Boolean getOpenForAll(String url) {
        return myRoutesAll.stream()
                .anyMatch(path -> path.getUrl().equals(url));
    }





}
