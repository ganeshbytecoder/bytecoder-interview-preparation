package com.gschoudhary.constant;

public class ApiConstants {

    private ApiConstants() {
    }

    public static final String AUTHENTICATION_API = "/api/v1/authenticate";
    public static final String LOGIN_API = "/favicon.ico";
    public static final String HEALTH_CHECK_API = "/app/health";
    public static final String JOB_LAUNCHER = "/job/launch";
    public static final String JOB_OPERATOR = "/jobs/{id}";
    public static final String SERVICE_API = "/api/v1/services";
    public static final String SERVICE_BY_ID_API = SERVICE_API + "/{serviceId}";
    public static final String SERVICE_DEPLOYMENT_API = SERVICE_BY_ID_API + "/deployments";
    public static final String ALL_SERVICES_WITH_LATEST_DEPLOYMENT_API = "/api/v1/services/deployments";
    public static final String TECHNOLOGIES_API = "/api/v1/technologies";
    public static final String ENV_API = "/api/v1/envs";
    public static final String DEPLOYMENT_STATS_API = "/api/v1/deployment-stats";


}
