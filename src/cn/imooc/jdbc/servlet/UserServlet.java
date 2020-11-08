package cn.imooc.jdbc.servlet;

import cn.imooc.jdbc.bean.User;
import cn.imooc.jdbc.dao.UserDAO;
import cn.imooc.jdbc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * 用户Servlet
 *
 * @version 1.0
 */
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();
        if (Objects.equals("/userInfo.do", pathName)) {
            request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
        } else if (Objects.equals("/editUserPrompt.do", pathName)) {
            //1.从Session中获取，其实就是与上一个if同样的方式
            //2.可扩展的使用方式：根据页面ID从数据库中查询
            Long id = Long.valueOf(request.getParameter("id"));
            User user = userService.getUserById(id);
            if (null != user) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/views/biz/edit_user.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
            }
        } else if (Objects.equals("/editUser.do", pathName)) {
            Long id = Long.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String realName = request.getParameter("realName");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setPassword(password);
            user.setRealName(realName);
            try {
                user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
            } catch (ParseException e) {
                System.out.println("格式化Birthday字段失败");
                e.printStackTrace();
            }
            user.setPhone(phone);
            user.setAddress(address);
            boolean result = userService.updateUser(user);
            if (result) {
                request.getSession().setAttribute("user", user);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/biz/404.jsp").forward(request, response);
            }
        } else if (Objects.equals("/addUserPrompt.do", pathName)) {
            request.getRequestDispatcher("/WEB-INF/views/biz/register.jsp").forward(request, response);
        } else if (Objects.equals("/addUser.do", pathName)){
            //请求封装参数对应的值
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String real_name = request.getParameter("real_name");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            //实列化User对象，封装值
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            user.setRealName(real_name);
            user.setBirthday(new Date(System.currentTimeMillis()));
            user.setPhone(phone);
            user.setAddress(address);

            //实列化数据层
            UserDAO userDao = new UserDAO();

            if (userDao.addUser(user)) {
                System.out.println("注册成功");
                request.getRequestDispatcher("login.do").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
        }
    }

}
