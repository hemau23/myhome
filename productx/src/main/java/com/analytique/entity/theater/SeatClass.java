package com.analytique.entity.theater;

import org.springframework.data.annotation.Id;

public class SeatClass {

    @Id
    String seatClassId;
    String className;
    String seatCode;

    public String getSeatClassId() {
        return this.seatClassId;
    }

    public void setSeatClassId(String seatClassId) {
        this.seatClassId = seatClassId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }
}
