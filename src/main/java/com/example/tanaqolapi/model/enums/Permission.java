package com.example.tanaqolapi.model.enums;


public enum Permission {

    // General Permissions
    CAN_REGISTER,
    CAN_LOGIN,
    CAN_RECEIVE_NOTIFICATIONS,

    // Customer Permissions
    CAN_VIEW_AVAILABLE_VEHICLES,
    CAN_BOOK_RIDE,
    CAN_TRACK_RIDE,
    CAN_CANCEL_RIDE,
    CAN_MAKE_PAYMENT,
    CAN_RATE_DRIVER,

    // Driver Permissions
    CAN_SET_AVAILABILITY,
    CAN_VIEW_ASSIGNED_RIDES,
    CAN_ACCEPT_RIDE_REQUEST,
    CAN_REJECT_RIDE_REQUEST,
    CAN_START_RIDE,
    CAN_COMPLETE_RIDE,

    // Admin Permissions
    CAN_MANAGE_USERS,
    CAN_MANAGE_VEHICLES,
    CAN_VIEW_RIDES,
    CAN_VIEW_NOTIFICATIONS,
    CAN_GENERATE_REPORTS,
    CAN_MONITOR_SYSTEM
}
