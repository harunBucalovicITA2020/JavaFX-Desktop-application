/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.event;

/**
 *
 * @author Harun
 */
public class EventBus {
    private final LoginEvent loginEvent = new LoginEvent();
    private final LogoutEvent logoutEvent = new LogoutEvent();
    private final CancleEvent cancleEvent = new CancleEvent();

    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    public LogoutEvent getLogoutEvent() {
        return logoutEvent;
    }

    public CancleEvent getCancleEvent() {
        return cancleEvent;
    }
    
    
}
