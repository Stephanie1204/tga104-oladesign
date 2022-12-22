package com.tibame.tga104.g2.oladesign.intermail.model;

import java.util.List;

public interface IntermailDAO_interface {
    public void insert(IntermailVO intermailVO);
    public void update(IntermailVO intermailVO);
    public void delete(String interMailId);
//    public void getreply (IntermailVO intermailVO);
    public IntermailVO findByPrimaryKey(String interMailId);
    public List<IntermailVO> getAll();
    public List<IntermailVO> getReceive();
    public IntermailVO getCheck(String interMailId);
    public void getReply(IntermailVO intermailVO);
//    public IntermailVO getReply(String interMailId,Boolean isSend);
    
    public void meminsert(IntermailVO intermailVO);
    public List<IntermailVO> getMemReceive();
    public IntermailVO getMemCheck(String interMailId);
//    public List<IntermailVO> getAllmem();
}
