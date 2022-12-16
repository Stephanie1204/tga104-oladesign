package com.tibame.tga104.g2.oladesign.admin.model;

import java.util.*;

public interface AdminDAO_interface {
	
    public void insert(AdminVO adminVO);
    public void update(AdminVO adminVO);
    public void delete(String adminId);
    public AdminVO findByPrimaryKey(String adminId);
    public List<AdminVO> getAll();
	public AdminVO getAdmin(String account, String password);
}