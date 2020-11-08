package cn.imooc.jdbc.service;

import cn.imooc.jdbc.dao.MessageDAO;
import cn.imooc.jdbc.bean.Message;

import java.util.Date;
import java.util.List;

/**
 * 消息Service
 *
 * @version 1.0
 */
public class MessageService {

    private MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    /**
     * 添加留言
     * @param message
     * @return
     */
    public boolean addMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDAO.save(message);
    }

    /**
     * 删除留言
     * @param id
     * @return
     */
    public boolean deleteMessage(Long id) {
        return messageDAO.deleteMessage(id);
    }

    /**
     * 修改留言
     * @param message
     * @return
     */
    public boolean editMessage(Message message){return messageDAO.editMessage(message);}
    /**
     * 分页查询全部留言
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {
        return messageDAO.getMessages(page, pageSize);
    }

    /**
     * 计算所有留言数量
     * @return
     */
    public int countMessages() {
        return messageDAO.countMessages();
    }

}
