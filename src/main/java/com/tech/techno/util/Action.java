package com.tech.techno.util;

import lombok.Data;

public class Action {
    private String home;
    private String add;
    private String editView;
    private String authorized;
    private String report;

    public Action(String home, String add, String editView, String authorized, String report) {
        this.home = home;
        this.add = add;
        this.editView = editView;
        this.authorized = authorized;
        this.report = report;
    }

    public Action() {
    }

    public void setHome(String home) {
        this.home = home;
    }
}
