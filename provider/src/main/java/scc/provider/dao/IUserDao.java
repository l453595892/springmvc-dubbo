package scc.provider.dao;

import java.util.List;

import com.common.vo.User;

public interface IUserDao {
	public void insert(User user);
	public User getUserById(Integer id);
	public List<User> getUsers();
}
