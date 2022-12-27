package com.tibame.tga104.g2.oladesign.intermail.model;

import java.util.List;

public interface IntermailDAO_interface {
    public void insert(IntermailVO intermailVO); //管理員新增站內信
    public void update(IntermailVO intermailVO); 
    public void delete(Integer interMailId);	//管理員刪除站內信
    public void Memdelete(Integer interMailId);	//會員刪除站內信
//    public void getreply (IntermailVO intermailVO);
    public IntermailVO findByPrimaryKey(Integer interMailId); 
    public List<IntermailVO> getAll();	 //管理員查詢所有站內信
    public List<IntermailVO> getReceive();	//管理員查詢未回覆會員的站內信
    public IntermailVO getCheck(Integer interMailId); //管理員查看未回覆站內信的內容
    public IntermailVO getCheckAll(Integer interMailId); //管理員查看所有站內信內容
    public void getReply(IntermailVO intermailVO); //管理員回覆會員信件更改 回復狀態
    
    public Integer meminsert(IntermailVO intermailVO); // 會員新增站內信
    public List<IntermailVO> getMemReceive(Integer memId); //會員查看自己所有的站內信
    public IntermailVO getMemCheck(Integer interMailId); //會員查看自己所有站內信內容
//    public List<IntermailVO> getAllmem();
}
