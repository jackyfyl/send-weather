package guestbook;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import guestbook.MailList;
import guestbook.PMF;

@SuppressWarnings("serial")
public class AddMailServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
        String emailAddress = req.getParameter("mail_address");
        String nickName = req.getParameter("nickname");
        MailList mail = new MailList(emailAddress, nickName);

        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(mail);
        } finally {
            pm.close();
        }

        resp.getWriter().print(emailAddress+" has been added!");
        resp.sendRedirect("/MailListManager.jsp");
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		doPost(request, response);
	}
}