package com.tibame.tga104.g2.oladesign.intermail.model;

import java.util.List;


public interface Intermail_qnDAO_interface {
    public void insert(Intermail_qnVO intermail_qnVO);
    public void update(Intermail_qnVO intermail_qnVO);
    public void delete(String numQue);
    public Intermail_qnVO findByPrimaryKey(String numQue);
    public List<Intermail_qnVO> getAll(); 
    public List<Intermail_qnVO> getType(); 
}
