package com.example.trajectoryfuture.models;

public class ServiceModel {

    private String iconService, titleService, descriptionService, linkService;

    public ServiceModel(String iconService, String titleService, String descriptionService, String linkService) {
        this.iconService = iconService;
        this.titleService = titleService;
        this.descriptionService = descriptionService;
        this.linkService = linkService;
    }

    public String getIconService() {
        return iconService;
    }

    public void setIconService(String iconService) {
        this.iconService = iconService;
    }

    public String getTitleService() {
        return titleService;
    }

    public void setTitleService(String titleService) {
        this.titleService = titleService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public String getLinkService() {
        return linkService;
    }

    public void setLinkService(String linkService) {
        this.linkService = linkService;
    }
}
