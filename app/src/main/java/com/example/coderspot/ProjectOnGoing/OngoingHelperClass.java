package com.example.coderspot.ProjectOnGoing;

public class OngoingHelperClass {

    private String projectname;
    private String tvproject;
    private String tvdis;
    private String tvdate;
    private String tvassign;

    public OngoingHelperClass(String projectname, String tvproject, String tvdis, String tvdate, String tvassign) {
        this.projectname = projectname;
        this.tvproject = tvproject;
        this.tvdis = tvdis;
        this.tvdate = tvdate;
        this.tvassign = tvassign;
    }

    public String getProjectname() {
        return projectname;
    }

    public String getTvproject() {
        return tvproject;
    }

    public String getTvdis() {
        return tvdis;
    }

    public String getTvdate() {
        return tvdate;
    }

    public String getTvassign() {
        return tvassign;
    }
}
