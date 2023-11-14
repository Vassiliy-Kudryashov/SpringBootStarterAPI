package springbootstarter;

public enum ProjectDependencyCategory {
    Developer_Tools,
    Web,
    Template_Engines,
    Security,
    SQL,
    NoSQL,
    Messaging,
    I__O,
    Ops,
    Observability,
    Testing,
    Spring_Cloud,
    Spring_Cloud_Config,
    Spring_Cloud_Discovery,
    Spring_Cloud_Routing,
    Spring_Cloud_Circuit_Breaker,
    Spring_Cloud_Messaging,
    VMware_Tanzu_Application_Service,
    Microsoft_Azure,
    Google_Cloud_Platform;

    @Override
    public String toString() {
        return super.toString().replace("__", "/").replace("_", " ");
    }
}
