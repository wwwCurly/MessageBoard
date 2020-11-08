package cn.imooc.jdbc.dao;

import cn.imooc.jdbc.common.ConnectionUtil;
import cn.imooc.jdbc.bean.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息DAO
 *
 * @version 1.0
 */
public class MessageDAO {

    /**
     * 保存留言信息
     * @param message
     * @return
     */
    public boolean save(Message message) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "insert into message(user_id, username, title, content, create_time) values (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, message.getUserId());
            stmt.setString(2, message.getUsername());
            stmt.setString(3, message.getTitle());
            stmt.setString(4, message.getContent());
            stmt.setTimestamp(5, new Timestamp(message.getCreateTime().getTime()));
            stmt.execute();
        } catch (Exception e) {
            System.out.println("保存留言信息失败。");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, stmt, conn);
        }
        return true;
    }

    /**
     * 分页查询全部留言
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message order by create_time desc limit ?, ?";//limit m, n：从第m条开始，取出总共n条记录
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return messages;
    }

    /**
     * 计算所有留言数量
     * @return
     */
   public int countMessages() {
       Connection conn = ConnectionUtil.getConnection();
       String sql = "select count(*) total from message";
       PreparedStatement stmt = null;
       ResultSet rs = null;
       try {
           stmt = conn.prepareStatement(sql);
           rs = stmt.executeQuery();
           while (rs.next()) {
               return rs.getInt("total");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           ConnectionUtil.release(rs, stmt, conn);
       }
       return 0;
   }
    /**
     * 修改留言信息
     * @param message
     * @return
     */
    public boolean editMessage(Message message){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET username = ?,title = ?, context = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, message.getUsername());
            stmt.setString(2, message.getTitle());
            stmt.setString(3, message.getContent());
            stmt.setLong(4, message.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("修改留言信息失败。");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, stmt, conn);
        }
        return true;
    }

    /**
     * 根据消息Id删除留言
     * @param
     * @return
     */
    public boolean deleteMessage(Long id){
        Connection conn = ConnectionUtil.getConnection();
        String sql = "DELETE FROM message WHERE id = ?";
        PreparedStatement stmt = null;
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("删除留言失败。");
            e.printStackTrace();
            return  false;
        }finally {
            ConnectionUtil.release(null, stmt, conn);
        }
        return true;
    }
}
