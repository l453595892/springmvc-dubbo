package com.common.inter;

import java.util.List;
import java.util.Map;

import com.common.vo.User;


public interface IUserService {
	public Map insert(User user);
	public User getUserById(Integer id);
	public void async();
	public List<User> getUsers();
	List<User> getUserList(int offset, int limit);
}
