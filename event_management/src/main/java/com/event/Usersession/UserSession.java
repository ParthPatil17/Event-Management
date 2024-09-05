
package com.event.Usersession;

public class UserSession {
    private static UserSession instance;
    private String username;
    private String bookingStartDate;
    private String bookingEndDate;
    private String bookingSection;

    private UserSession() {
        // private constructor to prevent instantiation
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setBookingStartDate(String startDate) {
        this.bookingStartDate = startDate;
    }

    public String getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingEndDate(String endDate) {
        this.bookingEndDate = endDate;
    }

    public String getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingSection(String section) {
        this.bookingSection = section;
    }

    public String getBookingSection() {
        return bookingSection;
    }
}
