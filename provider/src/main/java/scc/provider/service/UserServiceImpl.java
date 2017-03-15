package scc.provider.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import scc.provider.cache.RedisCache;
import scc.provider.dao.IUserDao;

import com.common.inter.IUserService;
import com.common.vo.User;

@Service
public class UserServiceImpl implements IUserService{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private RedisCache cache;
	
	public Map insert(User user) {
		System.out.println("ͬ执行同步方法");
		userDao.insert(user);
		Map map=new HashMap();
		map.put("Message", "123");
		return map;
	}
	
	@Async  
    public void async(){  
        System.out.println("开始执行异步方法---------------------------");  
        try {  
            Thread.sleep(10*1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("异步方法执行结束---------------------------");  
    }  
	
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public List<User> getUserList(int offset, int limit) {
		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+offset+"|"+limit;
		//先去缓存中取
		List<User> result_cache=cache.getListCache(cache_key, User.class);
		if(result_cache==null){
			//缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
			result_cache=userDao.getUsers();
			cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
			LOG.info("put cache with key:"+cache_key);
		}else{
			LOG.info("get cache with key:"+cache_key);
		}
		return result_cache;
	}
	
}
