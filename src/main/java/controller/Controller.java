package controller;


import service.Service;

public abstract class Controller {
    protected Service service;

    public void setService(Service service) {
        this.service = service;
    }
}
