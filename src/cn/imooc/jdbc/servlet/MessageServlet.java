package cn.imooc.jdbc.servlet;

import cn.imooc.jdbc.bean.Message;
import cn.imooc.jdbc.bean.User;
import cn.imooc.jdbc.dao.MessageDAO;
import cn.imooc.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 留言处理Servlet
 *
 * @version 1.0
 */
public class MessageServlet extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageService();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();
        //添加留言
        if (Objects.equals("/addMessagePrompt.do", pathName)) {
            request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request, response);
        } else if (Objects.equals("/addMessage.do", pathName)) {
            User user = (User) request.getSession().getAttribute("user");
            if (null == user) {
                request.getRequestDispatcher("/message/list.do").forward(request, response);
            } else {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                boolean result = messageService.addMessage(new Message(user.getId(), user.getName(), title, content));
                if (result) {
                    request.getRequestDispatcher("/message/list.do").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request, response);
                }
            }
        } else if (Objects.equals("/editMessagePrompt.do", pathName)) {     // 修改留言的网页界面没做，还不能交互
            request.getRequestDispatcher("/WEB-INF/views/biz/edit_message.jsp").forward(request, response);
        } else if (Objects.equals("/deleteMessage.do", pathName)) {     // 删除留言
            Long id = Long.valueOf(request.getParameter("id"));
            boolean result =messageService.deleteMessage(id);
            if (result) {
                request.getRequestDispatcher("/message/list.do").forward(request, response);
            } else {
                request.getRequestDispatcher("/message/list.do").forward(request, response);
            }
        }
    }
}

