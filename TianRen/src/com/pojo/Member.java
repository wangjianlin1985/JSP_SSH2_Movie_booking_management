// 
// 
// 

package com.pojo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

public class Member implements Serializable
{
    private Integer memberId;
    private String memberEmail;
    private String memberPwd;
    private String memberName;
    private String memberPhone;
    private Double memberMoney;
    private Boolean memberGender;
    private String memberPhoto;
    private Set tickets;
    
    public Member() {
        this.tickets = new HashSet(0);
    }
    
    public Member(final String memberEmail, final String memberPwd) {
        this.tickets = new HashSet(0);
        this.memberEmail = memberEmail;
        this.memberPwd = memberPwd;
    }
    
    public Member(final String memberEmail, final String memberPwd, final String memberName, final String memberPhone, final Double memberMoney, final Boolean memberGender, final String memberPhoto, final Set tickets) {
        this.tickets = new HashSet(0);
        this.memberEmail = memberEmail;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberMoney = memberMoney;
        this.memberGender = memberGender;
        this.memberPhoto = memberPhoto;
        this.tickets = tickets;
    }
    
    public Integer getMemberId() {
        return this.memberId;
    }
    
    public void setMemberId(final Integer memberId) {
        this.memberId = memberId;
    }
    
    public String getMemberEmail() {
        return this.memberEmail;
    }
    
    public void setMemberEmail(final String memberEmail) {
        this.memberEmail = memberEmail;
    }
    
    public String getMemberPwd() {
        return this.memberPwd;
    }
    
    public void setMemberPwd(final String memberPwd) {
        this.memberPwd = memberPwd;
    }
    
    public String getMemberName() {
        return this.memberName;
    }
    
    public void setMemberName(final String memberName) {
        this.memberName = memberName;
    }
    
    public String getMemberPhone() {
        return this.memberPhone;
    }
    
    public void setMemberPhone(final String memberPhone) {
        this.memberPhone = memberPhone;
    }
    
    public Double getMemberMoney() {
        return this.memberMoney;
    }
    
    public void setMemberMoney(final Double memberMoney) {
        this.memberMoney = memberMoney;
    }
    
    public Boolean getMemberGender() {
        return this.memberGender;
    }
    
    public void setMemberGender(final Boolean memberGender) {
        this.memberGender = memberGender;
    }
    
    public String getMemberPhoto() {
        return this.memberPhoto;
    }
    
    public void setMemberPhoto(final String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }
    
    public Set getTickets() {
        return this.tickets;
    }
    
    public void setTickets(final Set tickets) {
        this.tickets = tickets;
    }
}
