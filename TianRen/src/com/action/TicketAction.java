// 
// 
// 

package com.action;

import java.util.List;
import com.util.PageBean;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.pojo.Member;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.service.MovieService;
import com.service.TicketService;
import com.pojo.Ticket;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ActionSupport;

public class TicketAction extends ActionSupport implements ModelDriven<Ticket>
{
    private Ticket ticket;
    private TicketService ticketService;
    private MovieService movieService;
    
    public TicketAction() {
        this.ticket = new Ticket();
        this.ticketService = new TicketService();
        this.movieService = new MovieService();
    }
    
    public Ticket getModel() {
        return this.ticket;
    }
    
    public void setTicketService(final TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    public void setMovieService(final MovieService movieService) {
        this.movieService = movieService;
    }
    
    public String addTickets() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final int playId = Integer.valueOf(request.getParameter("playId"));
        final Double ticketPrice = Double.valueOf(request.getParameter("ticketPrice"));
        final int[] chooseSeatsNum = new int[request.getParameterValues("chooseSeatsNum").length];
        for (int i = 0; i < request.getParameterValues("chooseSeatsNum").length; ++i) {
            chooseSeatsNum[i] = Integer.valueOf(request.getParameterValues("chooseSeatsNum")[i]);
        }
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member member = (Member)session.get("curMember");
        this.ticketService.createTicket(member, playId, ticketPrice, chooseSeatsNum);
        return this.showOrder();
    }
    
    public String showOrder() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member member = (Member)session.get("curMember");
        final HttpServletRequest request = ServletActionContext.getRequest();
        int currentPage = 1;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage").toString());
        }
        final int pageSize = 4;
        final PageBean ticketsByMemberByPage = this.ticketService.findByMemberByPage(member, currentPage, pageSize);
        request.setAttribute("ticketsByMemberByPage", (Object)ticketsByMemberByPage);
        return "myOrder";
    }
    
    public String showTodayOrder() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member member = (Member)session.get("curMember");
        final HttpServletRequest request = ServletActionContext.getRequest();
        final List<Ticket> lstTickets = this.ticketService.findTodayTicket(member);
        request.setAttribute("lstTickets", (Object)lstTickets);
        final int afterCurrentPage = 1;
        final int afterPageSize = 2;
        final PageBean afterMovieByPage = this.movieService.findAfterByPage(afterCurrentPage, afterPageSize);
        request.setAttribute("afterMovieByPage", (Object)afterMovieByPage);
        return "memberCenter";
    }
    
    public String returnTicket() throws Exception {
        final Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
        final Member member = (Member)session.get("curMember");
        final HttpServletRequest request = ServletActionContext.getRequest();
        final int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        this.ticket.setTicketId(ticketId);
        this.ticketService.modifyReturn(this.ticket, member);
        return this.showOrder();
    }
    
    public String searchTickets() throws Exception {
        final HttpServletRequest request = ServletActionContext.getRequest();
        final List<Ticket> lstTickets = this.ticketService.findTickets(this.ticket);
        final List<Ticket> lstReturnTickets = this.ticketService.findReturnTickets(this.ticket);
        if (lstTickets.size() != 0) {
            final Ticket ticket = lstTickets.get(0);
            request.setAttribute("baseInfo", (Object)ticket);
            request.setAttribute("lstTickets", (Object)lstTickets);
        }
        if (lstReturnTickets.size() != 0) {
            final Ticket ticket = lstReturnTickets.get(0);
            request.setAttribute("baseInfo", (Object)ticket);
            request.setAttribute("lstReturnTickets", (Object)lstReturnTickets);
        }
        return "showTickets";
    }
}
