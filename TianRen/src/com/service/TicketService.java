// 
// 
// 

package com.service;

import java.util.List;
import com.util.PageBean;
import com.pojo.Play;
import com.util.SendHtmlMail;
import java.sql.Timestamp;
import com.pojo.Ticket;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.pojo.Member;
import com.dao.MemberDao;
import com.dao.PlayDao;
import com.dao.TicketDao;

public class TicketService
{
    private TicketDao ticketDao;
    private PlayDao playDao;
    private MemberDao memberDao;
    
    public TicketService() {
        this.ticketDao = new TicketDao();
        this.playDao = new PlayDao();
        this.memberDao = new MemberDao();
    }
    
    public void setTicketDao(final TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }
    
    public void setPlayDao(final PlayDao playDao) {
        this.playDao = playDao;
    }
    
    public void setMemberDao(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public void createTicket(final Member member, final Integer playId, final Double ticketPrice, final int[] chooseSeatsNum) {
        String toEmail = "";
        String movieName = "";
        final Date time = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5 E HH\u65f6mm\u5206");
        String seat = "";
        Double money = 0.0;
        final String ticketCode = String.valueOf(member.getMemberEmail().substring(0, member.getMemberEmail().indexOf("@"))) + "_" + System.nanoTime();
        for (final int ticketSeat : chooseSeatsNum) {
            final Play play = this.playDao.selectById(playId);
            final Ticket ticket = new Ticket();
            ticket.setMember(member);
            ticket.setPlay(play);
            ticket.setTicketCode(ticketCode);
            ticket.setTicketDate(new Timestamp(new Date().getTime()));
            ticket.setTicketPrice(ticketPrice);
            ticket.setTicketSeat(ticketSeat);
            ticket.setTicketFlag(true);
            this.ticketDao.insert(ticket);
            toEmail = ticket.getMember().getMemberEmail();
            movieName = String.valueOf(ticket.getPlay().getMovie().getMovieName()) + "(" + ticket.getPlay().getMovie().getEdition().getEditionName() + ")";
            time.setTime(ticket.getPlay().getPlayTime().getTime());
            seat = String.valueOf(seat) + ticket.getTicketSeat() + "\u53f7\u5ea7\u4f4d ";
            money += ticket.getTicketPrice();
            final Double memberMoney = member.getMemberMoney() - ticket.getTicketPrice();
            if (memberMoney < 0.0) {
                System.out.println(1 / 0);
            }
            else {
                member.setMemberMoney(memberMoney);
                this.memberDao.update(member);
            }
        }
        final String HtmlMsg = "\u5c0a\u656c\u7684 " + member.getMemberName() + (member.getMemberGender() ? "\u5148\u751f" : "\u5973\u58eb") + " \u60a8\u597d\uff1a\u8bf7\u4fdd\u5b58\u597d\u4f60\u7684\u8ba2\u7968\u4fe1\u606f\uff01<br/>" + "\u8ba2\u5355\u53f7\uff1a" + ticketCode + " \u7535\u5f71\uff1a" + movieName + " \u573a\u6b21\u65f6\u95f4\uff1a" + sdf.format(time) + " " + seat + "\u5171" + money + "\u5143";
        new SendHtmlMail().sendHtmlMail(toEmail, HtmlMsg);
    }
    
    public PageBean findByMemberByPage(final Member member, final int currentPage, final int pageSize) {
        return this.ticketDao.selectByMemberIdByPage(member.getMemberId(), currentPage, pageSize);
    }
    
    public List<Ticket> findTodayTicket(final Member member) {
        return this.ticketDao.selectTodayOrderByMemberId(member.getMemberId());
    }
    
    public void modifyReturn(final Ticket ticket, final Member member) {
        final Ticket oldTicket = this.ticketDao.selectById(ticket.getTicketId());
        oldTicket.setTicketFlag(false);
        this.ticketDao.update(oldTicket);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5 E HH\u65f6mm\u5206");
        final String toEmail = oldTicket.getMember().getMemberEmail();
        final String HtmlMsg = "\u5c0a\u656c\u7684 " + member.getMemberName() + (member.getMemberGender() ? "\u5148\u751f" : "\u5973\u58eb") + " \u60a8\u597d\uff1a\u9000\u7968\u6210\u529f\uff01\u4ee5\u4e0b\u662f\u9000\u7968\u4fe1\u606f\uff1a<br/>" + "\u8ba2\u5355\u53f7\uff1a" + oldTicket.getTicketCode() + " \u7535\u5f71\uff1a" + oldTicket.getPlay().getMovie().getMovieName() + " \u573a\u6b21\u65f6\u95f4\uff1a" + sdf.format(oldTicket.getPlay().getPlayTime()) + " " + oldTicket.getTicketSeat() + "\u5ea7";
        new SendHtmlMail().sendHtmlMail(toEmail, HtmlMsg);
        final Member oldMember = this.memberDao.selectById(member.getMemberId());
        oldMember.setMemberMoney(member.getMemberMoney() + oldTicket.getTicketPrice());
        this.memberDao.update(oldMember);
    }
    
    public List<Ticket> findTickets(final Ticket ticket) {
        return this.ticketDao.selectByCode(ticket.getTicketCode());
    }
    
    public List<Ticket> findReturnTickets(final Ticket ticket) {
        return this.ticketDao.selectReturnByCode(ticket.getTicketCode());
    }
}
