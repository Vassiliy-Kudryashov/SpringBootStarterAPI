package springbootstarter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static springbootstarter.ProjectDependencyCategory.*;

public enum StandardProjectDependency {
    Spring_for_Apache_ActiveMQ_5("activemq", "Spring for Apache ActiveMQ 5", "Spring JMS support with Apache ActiveMQ 'Classic'.", Messaging),
    Spring_Boot_Actuator("actuator", "Spring Boot Actuator", "Supports built in (or custom) endpoints that let you monitor and manage your application - such as application health, metrics, sessions, etc.", Ops),
    Spring_for_RabbitMQ("amqp", "Spring for RabbitMQ", "Gives your applications a common platform to send and receive messages, and your messages a safe place to live until received.", Messaging),
    Spring_for_Apache_ActiveMQ_Artemis("artemis", "Spring for Apache ActiveMQ Artemis", "Spring JMS support with Apache ActiveMQ Artemis.", Messaging),
    Azure_Active_Directory("azure-active-directory", "Azure Active Directory", "Spring Security integration with Azure Active Directory for authentication.", Microsoft_Azure),
    Azure_Cosmos_DB("azure-cosmos-db", "Azure Cosmos DB", "Fully managed NoSQL database service for modern app development, including Spring Data support.", Microsoft_Azure),
    Azure_Key_Vault("azure-keyvault", "Azure Key Vault", "All key vault features are supported, e.g. manage application secrets and certificates.", Microsoft_Azure),
    Azure_Storage("azure-storage", "Azure Storage", "All Storage features are supported, e.g. blob, fileshare and queue.", Microsoft_Azure),
    Azure_Support("azure-support", "Azure Support", "Auto-configuration for Azure Services (Service Bus, Storage, Active Directory, Key Vault, and more).", Microsoft_Azure),
    Spring_Batch("batch", "Spring Batch", "Batch applications with transactions, retry/skip and chunk based processing.", I__O),
    Spring_Cache_Abstraction("cache", "Spring Cache Abstraction", "Provides cache-related operations, such as the ability to update the content of the cache, but does not provide the actual data store.", I__O),
    Apache_Camel("camel", "Apache Camel", "Apache Camel is an open source integration framework that empowers you to quickly and easily integrate various systems consuming or producing data.", Messaging),
    Cloud_Bus("cloud-bus", "Cloud Bus", "Links nodes of a distributed system with a lightweight message broker which can used to broadcast state changes or other management instructions (requires a binder, e.g. Apache Kafka or RabbitMQ).", Spring_Cloud_Messaging),
    Cloud_Foundry_Discovery("cloud-cloudfoundry-discovery", "Cloud Foundry Discovery", "Service discovery with Cloud Foundry.", Spring_Cloud_Discovery),
    Config_Client("cloud-config-client", "Config Client", "Client that connects to a Spring Cloud Config Server to fetch the application's configuration.", Spring_Cloud_Config),
    Config_Server("cloud-config-server", "Config Server", "Central management for configuration via Git, SVN, or HashiCorp Vault.", Spring_Cloud_Config),
    Contract_Stub_Runner("cloud-contract-stub-runner", "Contract Stub Runner", "Stub Runner for HTTP/Messaging based communication. Allows creating WireMock stubs from RestDocs tests.", Testing),
    Contract_Verifier("cloud-contract-verifier", "Contract Verifier", "Moves TDD to the level of software architecture by enabling Consumer Driven Contract (CDC) development.", Testing),
    Eureka_Discovery_Client("cloud-eureka", "Eureka Discovery Client", "A REST based service for locating services for the purpose of load balancing and failover of middle-tier servers.", Spring_Cloud_Discovery),
    Eureka_Server("cloud-eureka-server", "Eureka Server", "spring-cloud-netflix Eureka Server.", Spring_Cloud_Discovery),
    OpenFeign("cloud-feign", "OpenFeign", "Declarative REST Client. OpenFeign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations.", Spring_Cloud_Routing),
    Function("cloud-function", "Function", "Promotes the implementation of business logic via functions and supports a uniform programming model across serverless providers, as well as the ability to run standalone (locally or in a PaaS).", Spring_Cloud),
    Gateway("cloud-gateway", "Gateway", "Provides a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as security, monitoring/metrics, and resiliency.", Spring_Cloud_Routing),
    GCP_Support("cloud-gcp", "GCP Support", "Contains auto-configuration support for every Spring Cloud GCP integration. Most of the auto-configuration code is only enabled if other dependencies are added to the classpath.", Google_Cloud_Platform),
    GCP_Messaging("cloud-gcp-pubsub", "GCP Messaging", "Adds the GCP Support entry and all the required dependencies so that the Google Cloud Pub/Sub integration work out of the box.", Google_Cloud_Platform),
    GCP_Storage("cloud-gcp-storage", "GCP Storage", "Adds the GCP Support entry and all the required dependencies so that the Google Cloud Storage integration work out of the box.", Google_Cloud_Platform),
    Cloud_LoadBalancer("cloud-loadbalancer", "Cloud LoadBalancer", "Client-side load-balancing with Spring Cloud LoadBalancer.", Spring_Cloud_Routing),
    Resilience4J("cloud-resilience4j", "Resilience4J", "Spring Cloud Circuit breaker with Resilience4j as the underlying implementation.", Spring_Cloud_Circuit_Breaker),
    Cloud_Bootstrap("cloud-starter", "Cloud Bootstrap", "Non-specific Spring Cloud features, unrelated to external libraries or integrations (e.g. Bootstrap context and @RefreshScope).", Spring_Cloud),
    Consul_Configuration("cloud-starter-consul-config", "Consul Configuration", "Enable and configure the common patterns inside your application and build large distributed systems with Hashicorp’s Consul. The patterns provided include Service Discovery, Distributed Configuration and Control Bus.", Spring_Cloud_Config),
    Consul_Discovery("cloud-starter-consul-discovery", "Consul Discovery", "Service discovery with Hashicorp Consul.", Spring_Cloud_Discovery),
    Vault_Configuration("cloud-starter-vault-config", "Vault Configuration", "Provides client-side support for externalized configuration in a distributed system. Using HashiCorp's Vault you have a central place to manage external secret properties for applications across all environments.", Spring_Cloud_Config),
    Apache_Zookeeper_Configuration("cloud-starter-zookeeper-config", "Apache Zookeeper Configuration", "Enable and configure common patterns inside your application and build large distributed systems with Apache Zookeeper based components. The provided patterns include Service Discovery and Configuration.", Spring_Cloud_Config),
    Apache_Zookeeper_Discovery("cloud-starter-zookeeper-discovery", "Apache Zookeeper Discovery", "Service discovery with Apache Zookeeper.", Spring_Cloud_Discovery),
    Cloud_Stream("cloud-stream", "Cloud Stream", "Framework for building highly scalable event-driven microservices connected with shared messaging systems (requires a binder, e.g. Apache Kafka, Apache Pulsar, RabbitMQ, or Solace PubSub+).", Spring_Cloud_Messaging),
    Task("cloud-task", "Task", "Allows a user to develop and run short lived microservices using Spring Cloud. Run them locally, in the cloud, and on Spring Cloud Data Flow.", Spring_Cloud),
    codecentric_Spring_Boot_Admin_Client("codecentric-spring-boot-admin-client", "codecentric's Spring Boot Admin (Client)", "Required for your application to register with a Codecentric's Spring Boot Admin Server instance.", Ops),
    codecentric_Spring_Boot_Admin_Server("codecentric-spring-boot-admin-server", "codecentric's Spring Boot Admin (Server)", "A community project to manage and monitor your Spring Boot applications. Provides a UI on top of the Spring Boot Actuator endpoints.", Ops),
    Spring_Configuration_Processor("configuration-processor", "Spring Configuration Processor", "Generate metadata for developers to offer contextual help and \"code completion\" when working with custom configuration keys (ex.application.properties/.yml files).", Developer_Tools),
    Spring_Data_for_Apache_Cassandra("data-cassandra", "Spring Data for Apache Cassandra", "A free and open-source, distributed, NoSQL database management system that offers high-scalability and high-performance.", NoSQL),
    Spring_Data_Reactive_for_Apache_Cassandra("data-cassandra-reactive", "Spring Data Reactive for Apache Cassandra", "Access Cassandra NoSQL Database in a reactive fashion.", NoSQL),
    Spring_Data_Couchbase("data-couchbase", "Spring Data Couchbase", "NoSQL document-oriented database that offers in memory-first architecture, geo-distributed deployments, and workload isolation.", NoSQL),
    Spring_Data_Reactive_Couchbase("data-couchbase-reactive", "Spring Data Reactive Couchbase", "Access Couchbase NoSQL database in a reactive fashion with Spring Data Couchbase.", NoSQL),
    Spring_Data_Elasticsearch_AccessPlusDriver("data-elasticsearch", "Spring Data Elasticsearch (Access+Driver)", "A distributed, RESTful search and analytics engine with Spring Data Elasticsearch.", NoSQL),
    Spring_Data_JDBC("data-jdbc", "Spring Data JDBC", "Persist data in SQL stores with plain JDBC using Spring Data.", SQL),
    Spring_Data_JPA("data-jpa", "Spring Data JPA", "Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.", SQL),
    Spring_LDAP("data-ldap", "Spring LDAP", "Makes it easier to build Spring based applications that use the Lightweight Directory Access Protocol.", Security),
    Spring_Data_MongoDB("data-mongodb", "Spring Data MongoDB", "Store data in flexible, JSON-like documents, meaning fields can vary from document to document and data structure can be changed over time.", NoSQL),
    Spring_Data_Reactive_MongoDB("data-mongodb-reactive", "Spring Data Reactive MongoDB", "Provides asynchronous stream processing with non-blocking back pressure for MongoDB.", NoSQL),
    Spring_Data_Neo4j("data-neo4j", "Spring Data Neo4j", "An open source NoSQL database that stores data structured as graphs consisting of nodes, connected by relationships.", NoSQL),
    Spring_Data_R2DBC("data-r2dbc", "Spring Data R2DBC", "Provides Reactive Relational Database Connectivity to persist data in SQL stores using Spring Data in reactive applications.", SQL),
    Spring_Data_Redis_AccessPlusDriver("data-redis", "Spring Data Redis (Access+Driver)", "Advanced and thread-safe Java Redis client for synchronous, asynchronous, and reactive usage. Supports Cluster, Sentinel, Pipelining, Auto-Reconnect, Codecs and much more.", NoSQL),
    Spring_Data_Reactive_Redis("data-redis-reactive", "Spring Data Reactive Redis", "Access Redis key-value data stores in a reactive fashion with Spring Data Redis.", NoSQL),
    Rest_Repositories("data-rest", "Rest Repositories", "Exposing Spring Data repositories over REST via Spring Data REST.", Web),
    Rest_Repositories_HAL_Explorer("data-rest-explorer", "Rest Repositories HAL Explorer", "Browsing Spring Data REST repositories in your browser.", Web),
    Datadog("datadog", "Datadog", "Publish Micrometer metrics to Datadog, a dimensional time-series SaaS with built-in dashboarding and alerting.", Observability),
    IBM_DB2_Driver("db2", "IBM DB2 Driver", "A JDBC driver that provides access to IBM DB2.", SQL),
    Apache_Derby_Database("derby", "Apache Derby Database", "An open source relational database implemented entirely in Java.", SQL),
    Spring_Boot_DevTools("devtools", "Spring Boot DevTools", "Provides fast application restarts, LiveReload, and configurations for enhanced development experience.", Developer_Tools),
    Distributed_Tracing("distributed-tracing", "Distributed Tracing", "Enable span and trace IDs in logs.", Observability),
    Docker_Compose_Support("docker-compose", "Docker Compose Support", "Provides docker compose support for enhanced development experience.", Developer_Tools),
    Dynatrace("dynatrace", "Dynatrace", "Publish Micrometer metrics to Dynatrace, a platform featuring observability, AIOps, application security and analytics.", Observability),
    Embedded_MongoDB_Database("flapdoodle-mongo", "Embedded MongoDB Database", "Provides a platform neutral way for running MongoDB in unit tests.", Testing),
    Flyway_Migration("flyway", "Flyway Migration", "Version control for your database so you can migrate from any version (incl. an empty database) to the latest version of the schema.", SQL),
    Apache_Freemarker("freemarker", "Apache Freemarker", "Java library to generate text output (HTML web pages, e-mails, configuration files, source code, etc.) based on templates and changing data.", Template_Engines),
    Graphite("graphite", "Graphite", "Publish Micrometer metrics to Graphite, a hierarchical metrics system backed by a fixed-size database.", Observability),
    Spring_for_GraphQL("graphql", "Spring for GraphQL", "Build GraphQL applications with Spring for GraphQL and GraphQL Java.", Web),
    Groovy_Templates("groovy-templates", "Groovy Templates", "Groovy templating engine.", Template_Engines),
    H2_Database("h2", "H2 Database", "Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser based console application.", SQL),
    Spring_HATEOAS("hateoas", "Spring HATEOAS", "Eases the creation of RESTful APIs that follow the HATEOAS principle when working with Spring / Spring MVC.", Web),
    Hilla("hilla", "Hilla", "An open source framework that integrates a Spring Boot Java backend with a reactive TypeScript frontend.", Web),
    HyperSQL_Database("hsql", "HyperSQL Database", "Lightweight 100% Java SQL Database Engine.", SQL),
    Influx("influx", "Influx", "Publish Micrometer metrics to InfluxDB, a dimensional time-series server that support real-time stream processing of data.", Observability),
    Spring_Integration("integration", "Spring Integration", "Adds support for Enterprise Integration Patterns. Enables lightweight messaging and supports integration with external systems via declarative adapters.", Messaging),
    JDBC_API("jdbc", "JDBC API", "Database Connectivity API that defines how a client may connect and query a database.", SQL),
    Jersey("jersey", "Jersey", "Framework for developing RESTful Web Services in Java that provides support for JAX-RS APIs.", Web),
    JOOQ_Access_Layer("jooq", "JOOQ Access Layer", "Generate Java code from your database and build type safe SQL queries through a fluent API.", SQL),
    Spring_for_Apache_Kafka("kafka", "Spring for Apache Kafka", "Publish, subscribe, store, and process streams of records.", Messaging),
    Spring_for_Apache_Kafka_Streams("kafka-streams", "Spring for Apache Kafka Streams", "Building stream processing applications with Apache Kafka Streams.", Messaging),
    Liquibase_Migration("liquibase", "Liquibase Migration", "Liquibase database migration and source control library.", SQL),
    Lombok("lombok", "Lombok", "Java annotation library which helps to reduce boilerplate code.", Developer_Tools),
    Java_Mail_Sender("mail", "Java Mail Sender", "Send email using Java Mail and Spring Framework's JavaMailSender.", I__O),
    MariaDB_Driver("mariadb", "MariaDB Driver", "MariaDB JDBC and R2DBC driver.", SQL),
    Spring_Modulith("modulith", "Spring Modulith", "Support for building modular monolithic applications.", Developer_Tools),
    Mustache("mustache", "Mustache", "Logic-less templates for both web and standalone environments. There are no if statements, else clauses, or for loops. Instead there are only tags.", Template_Engines),
    MyBatis_Framework("mybatis", "MyBatis Framework", "Persistence framework with support for custom SQL, stored procedures and advanced mappings. MyBatis couples objects with stored procedures or SQL statements using a XML descriptor or annotations.", SQL),
    MySQL_Driver("mysql", "MySQL Driver", "MySQL JDBC driver.", SQL),
    GraalVM_Native_Support("native", "GraalVM Native Support", "Support for compiling Spring applications to native executables using the GraalVM native-image compiler.", Developer_Tools),
    New_Relic("new-relic", "New Relic", "Publish Micrometer metrics to New Relic, a SaaS offering with a full UI and a query language called NRQL.", Observability),
    OAuth2_Authorization_Server("oauth2-authorization-server", "OAuth2 Authorization Server", "Spring Boot integration for Spring Authorization Server.", Security),
    OAuth2_Client("oauth2-client", "OAuth2 Client", "Spring Boot integration for Spring Security's OAuth2/OpenID Connect client features.", Security),
    OAuth2_Resource_Server("oauth2-resource-server", "OAuth2 Resource Server", "Spring Boot integration for Spring Security's OAuth2 resource server features.", Security),
    Okta("okta", "Okta", "Okta specific configuration for Spring Security/Spring Boot OAuth2 features. Enable your Spring Boot application to work with Okta via OAuth 2.0/OIDC.", Security),
    Oracle_Driver("oracle", "Oracle Driver", "A JDBC driver that provides access to Oracle.", SQL),
    Picocli("picocli", "Picocli", "Build command line applications with picocli.", I__O),
    PostgreSQL_Driver("postgresql", "PostgreSQL Driver", "A JDBC and R2DBC driver that allows Java programs to connect to a PostgreSQL database using standard, database independent Java code.", SQL),
    Prometheus("prometheus", "Prometheus", "Expose Micrometer metrics in Prometheus format, an in-memory dimensional time series database with a simple built-in UI, a custom query language, and math operations.", Observability),
    Spring_for_Apache_Pulsar("pulsar", "Spring for Apache Pulsar", "Build messaging applications with Apache Pulsar", Messaging),
    Spring_for_Apache_Pulsar_Reactive("pulsar-reactive", "Spring for Apache Pulsar (Reactive)", "Build reactive messaging applications with Apache Pulsar", Messaging),
    Quartz_Scheduler("quartz", "Quartz Scheduler", "Schedule jobs using Quartz.", I__O),
    Spring_REST_Docs("restdocs", "Spring REST Docs", "Document RESTful services by combining hand-written with Asciidoctor and auto-generated snippets produced with Spring MVC Test.", Testing),
    RSocket("rsocket", "RSocket", "RSocket.io applications with Spring Messaging and Netty.", Messaging),
    Config_Client_TAS("scs-config-client", "Config Client (TAS)", "Config client on VMware Tanzu Application Service.", VMware_Tanzu_Application_Service),
    Service_Registry_TAS("scs-service-registry", "Service Registry (TAS)", "Eureka service discovery client on VMware Tanzu Application Service.", VMware_Tanzu_Application_Service),
    Spring_Security("security", "Spring Security", "Highly customizable authentication and access-control framework for Spring applications.", Security),
    Sentry("sentry", "Sentry", "Application performance monitoring and error tracking that help software teams see clearer, solve quicker, and learn continuously.", Ops),
    Spring_Session("session", "Spring Session", "Provides an API and implementations for managing user session information.", Web),
    Solace_PubSubPlus("solace", "Solace PubSub+", "Connect to a Solace PubSub+ Advanced Event Broker to publish, subscribe, request/reply and store/replay messages", Messaging),
    Spring_Shell("spring-shell", "Spring Shell", "Build command line applications with spring.", I__O),
    MS_SQL_Server_Driver("sqlserver", "MS SQL Server Driver", "A JDBC and R2DBC driver that provides access to Microsoft SQL Server and Azure SQL Database from any Java application.", SQL),
    Testcontainers("testcontainers", "Testcontainers", "Provide lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.", Testing),
    Thymeleaf("thymeleaf", "Thymeleaf", "A modern server-side Java template engine for both web and standalone environments. Allows HTML to be correctly displayed in browsers and as static prototypes.", Template_Engines),
    Timefold_Solver("timefold-solver", "Timefold Solver", "AI solver to optimize operations and scheduling.", I__O),
    Embedded_LDAP_Server("unboundid-ldap", "Embedded LDAP Server", "Provides a platform neutral way for running a LDAP server in unit tests.", Testing),
    Vaadin("vaadin", "Vaadin", "A web framework that allows you to write UI in pure Java without getting bogged down in JS, HTML, and CSS.", Web),
    Validation("validation", "Validation", "Bean Validation with Hibernate validator.", I__O),
    Wavefront("wavefront", "Wavefront", "Publish metrics and optionally distributed traces to Tanzu Observability by Wavefront, a SaaS-based metrics monitoring and analytics platform that lets you visualize, query, and alert over data from across your entire stack.", Observability),
    Spring_Web("web", "Spring Web", "Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.", Web),
    Spring_Web_Services("web-services", "Spring Web Services", "Facilitates contract-first SOAP development. Allows for the creation of flexible web services using one of the many ways to manipulate XML payloads.", Web),
    Spring_Reactive_Web("webflux", "Spring Reactive Web", "Build reactive web applications with Spring WebFlux and Netty.", Web),
    WebSocket("websocket", "WebSocket", "Build Servlet-based WebSocket applications with SockJS and STOMP.", Messaging),
    Zipkin("zipkin", "Zipkin", "Enable and expose span and trace IDs to Zipkin.", Observability);

    private final String id;
    private final String name;
    private final String description;
    private final ProjectDependencyCategory category;

    StandardProjectDependency(String id, String name, String description, ProjectDependencyCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectDependencyCategory getCategory() {
        return category;
    }

    public static StandardProjectDependency find(String id) {
        return Arrays.stream(values()).filter(d -> d.id.equals(id)).findAny().orElse(null);
    }

    static List<StandardProjectDependency> getDependencies(ProjectDependencyCategory category) {
        return Arrays.stream(values()).filter(d -> d.category == category).collect(Collectors.toList());
    }

    static List<StandardProjectDependency> getAll() {
        List<StandardProjectDependency> result = new ArrayList<>();
        Arrays.stream(ProjectDependencyCategory.values()).forEach(category -> result.addAll(getDependencies(category)));
        return result;
    }
}
