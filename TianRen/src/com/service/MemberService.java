// 
// 
// 

package com.service;

import com.util.PageBean;
import com.pojo.Member;
import com.dao.MemberDao;

public class MemberService
{
    private MemberDao memberDao;
    
    public void setMemberDao(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public Member login(final Member member) {
        if (this.memberDao.selectByExample(member).size() == 0) {
            return null;
        }
        return this.memberDao.selectByExample(member).get(0);
    }
    
    public boolean isExistEmail(final Member member) {
        return this.memberDao.selectByExample(member).size() != 0;
    }
    
    public void create(final Member member) {
        if (member.getMemberName() == null) {
            member.setMemberName("");
        }
        member.setMemberMoney(0.0);
        this.memberDao.insert(member);
    }
    
    public PageBean findLikeByEntityByPage(final Member member, final String[] propertyNames, final int currentPage, final int pageSize) {
        return this.memberDao.selectLikeByEntityByPage(member, propertyNames, currentPage, pageSize);
    }
    
    public Member modifyInfo(final Member curMember, final Member newMember) {
        final Member member = this.memberDao.selectById(curMember.getMemberId());
        if (newMember.getMemberName().toString().length() == 0) {
            member.setMemberName(null);
        }
        else {
            member.setMemberName(newMember.getMemberName());
        }
        member.setMemberGender(newMember.getMemberGender());
        if (newMember.getMemberPhone().toString().length() == 0) {
            member.setMemberPhone(null);
        }
        else {
            member.setMemberPhone(newMember.getMemberPhone());
        }
        this.memberDao.update(member);
        return member;
    }
    
    public Member modifyPhoto(final Member curMember, final Member newMember) {
        final Member member = this.memberDao.selectById(curMember.getMemberId());
        member.setMemberPhoto(newMember.getMemberPhoto());
        this.memberDao.update(member);
        return member;
    }
    
    public boolean checkOldPwd(final Member curMember, final Member member) {
        return member.getMemberPwd().trim().equals(curMember.getMemberPwd().trim());
    }
    
    public Member modifyPwd(final Member curMember, final Member newMember) {
        final Member member = this.memberDao.selectById(curMember.getMemberId());
        member.setMemberPwd(newMember.getMemberPwd());
        this.memberDao.update(member);
        return member;
    }
    
    public void remove(final Member member) {
        this.memberDao.delete(this.memberDao.selectById(member.getMemberId()));
    }
    
    public Member findById(final Member member) {
        return this.memberDao.selectById(member.getMemberId());
    }
    
    public void modify(final Member member) {
        final Member oldMember = this.memberDao.selectById(member.getMemberId());
        oldMember.setMemberEmail(member.getMemberEmail());
        oldMember.setMemberName(member.getMemberName());
        oldMember.setMemberPwd(member.getMemberPwd());
        oldMember.setMemberGender(member.getMemberGender());
        oldMember.setMemberPhone(member.getMemberPhone());
        this.memberDao.update(oldMember);
    }
}
