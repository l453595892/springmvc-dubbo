package scc.cunsumer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.async.DeferredResult;

import com.alibaba.dubbo.rpc.RpcContext;
import com.common.inter.IUserService;
import com.common.util.FileUpload;
import com.common.util.Return.ReturnModel;
import com.common.vo.User;
/**
 * 测试类
 * @author l453595892
 *
 */
@Controller
@RequestMapping("/UserController")
public class UserController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserService userService;
	
	
	
	@RequestMapping("/testInsert.do")
	@ResponseBody
	public Map testInsert(User user){
		Map resultmap=new HashMap();
		try {
			RpcContext.getContext().setAttachment("index", "1");
			userService.async();
			resultmap=userService.insert(user);
		} catch (Exception e) {  
	        e.printStackTrace();  
	    }
		return resultmap;
	}
	
	//上传
	@RequestMapping(value="/fileUpLoad.do")
	@ResponseBody
	public DeferredResult<ReturnModel> changePic(@RequestParam("files") MultipartFile[] files) throws InterruptedException{
		final DeferredResult<ReturnModel> deferredResult=new DeferredResult<ReturnModel>(3000L);//3s以后则提示超时
		System.out.println("进入  /changePic.do");
		FileUpload fileUpload=new FileUpload();
		ReturnModel returnModel=new ReturnModel();
		String fileNameList="";
		try{
			 //判断file数组不能为空并且长度大于0  
	        if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                fileNameList+=fileUpload.saveFile(file, 10)+",";  
	            }
	            System.out.println("/changePic.do  异步调用执行完成！");
	        }
	        Map result=new HashMap();
	        result.put("fileName",fileNameList.substring(0, fileNameList.length()-1));
	        returnModel.setData(result);
	        returnModel.setMessage("调用成功");
			returnModel.setStatus(0);
	        deferredResult.setResult(returnModel);
		}catch(Exception e){
			returnModel.setMessage("调用出错");
			returnModel.setStatus(1);
			deferredResult.setResult(returnModel);
			e.printStackTrace();
		}
		//超时处理
		deferredResult.onTimeout(new Runnable(){
			public void run() {
				System.out.println("异步调用执行超时 ");
				ReturnModel returnModel=new ReturnModel();
				returnModel.setMessage("异步调用执行超时");
				returnModel.setStatus(2);
				deferredResult.setResult(returnModel);
			}
		});
//		System.out.println("进行延迟");
//		Thread.sleep(2000);
		return deferredResult;
	}
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	@ResponseBody
	public ReturnModel list(Integer offset, Integer limit) {
		LOG.info("invoke----------/user/list");
		offset = offset == null ? 0 : offset;//默认便宜0
		limit = limit == null ? 50 : limit;//默认展示50条
		List<User> list = userService.getUserList(offset, limit);
		return new ReturnModel(list,"测试一号",true);
	}
	
	
//	@RequestMapping("/getUserById.do")
//	public String getUserById(Integer id,Model model){
//		model.addAttribute("user", userService.getUserById(id));
//		return "info";
//	}
//	
//	@RequestMapping("/getUsers.do")
//	public String getUsers(Model model){
//		model.addAttribute("users",userService.getUsers());
//		return "index";
//	}
	
}
