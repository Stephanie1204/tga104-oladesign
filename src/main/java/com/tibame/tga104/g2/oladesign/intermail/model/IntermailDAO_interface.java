package com.tibame.tga104.g2.oladesign.intermail.model;

import java.util.List;

public interface IntermailDAO_interface {
    public void insert(IntermailVO intermailVO);
    public void update(IntermailVO intermailVO);
    public void delete(String interMailId);
    public IntermailVO findByPrimaryKey(String interMailId);
    public List<IntermailVO> getAll();
}
