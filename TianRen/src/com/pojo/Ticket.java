// 
// 
// 

package com.pojo;

import java.sql.Timestamp;
import java.io.Serializable;

public class Ticket implements Serializable
{
    private Integer ticketId;
    private Play play;
    private Member member;
    private String ticketCode;
    private Integer ticketSeat;
    private Double ticketPrice;
    private Timestamp ticketDate;
    private Boolean ticketFlag;
    
    public Ticket() {
    }
    
    public Ticket(final Play play, final Member member, final String ticketCode, final Integer ticketSeat, final Double ticketPrice, final Boolean ticketFlag) {
        this.play = play;
        this.member = member;
        this.ticketCode = ticketCode;
        this.ticketSeat = ticketSeat;
        this.ticketPrice = ticketPrice;
        this.ticketFlag = ticketFlag;
    }
    
    public Ticket(final Play play, final Member member, final String ticketCode, final Integer ticketSeat, final Double ticketPrice, final Timestamp ticketDate, final Boolean ticketFlag) {
        this.play = play;
        this.member = member;
        this.ticketCode = ticketCode;
        this.ticketSeat = ticketSeat;
        this.ticketPrice = ticketPrice;
        this.ticketDate = ticketDate;
        this.ticketFlag = ticketFlag;
    }
    
    public Integer getTicketId() {
        return this.ticketId;
    }
    
    public void setTicketId(final Integer ticketId) {
        this.ticketId = ticketId;
    }
    
    public Play getPlay() {
        return this.play;
    }
    
    public void setPlay(final Play play) {
        this.play = play;
    }
    
    public Member getMember() {
        return this.member;
    }
    
    public void setMember(final Member member) {
        this.member = member;
    }
    
    public String getTicketCode() {
        return this.ticketCode;
    }
    
    public void setTicketCode(final String ticketCode) {
        this.ticketCode = ticketCode;
    }
    
    public Integer getTicketSeat() {
        return this.ticketSeat;
    }
    
    public void setTicketSeat(final Integer ticketSeat) {
        this.ticketSeat = ticketSeat;
    }
    
    public Double getTicketPrice() {
        return this.ticketPrice;
    }
    
    public void setTicketPrice(final Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    public Timestamp getTicketDate() {
        return this.ticketDate;
    }
    
    public void setTicketDate(final Timestamp ticketDate) {
        this.ticketDate = ticketDate;
    }
    
    public Boolean getTicketFlag() {
        return this.ticketFlag;
    }
    
    public void setTicketFlag(final Boolean ticketFlag) {
        this.ticketFlag = ticketFlag;
    }
}
