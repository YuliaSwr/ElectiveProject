package com.sida.electivefinalproject.command;

/**
 * The {@code Router} class
 * contains two fields -
 * page and RouteType,
 * that are used with a controller to find out where and how
 * a request and response should be processed after the controller.
 *
 */
public class Router {

    private String pagePath;
    private RouteType type;

    public Router() {
        this.type = RouteType.FORWARD;
    }

    public Router(String pagePath, RouteType type) {
        this.pagePath = pagePath;
        this.type = type;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setType(RouteType type) {
        this.type = type;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouteType getType() {
        return type;
    }

}
