package yinian.cn.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import yinian.cn.feign.web.FeignMultipartSupportConfig;
import net.sf.json.JSONObject;

/**
 * 负载均衡的向外暴露的服务接口
 * @FeignClient 参数为注册到eureka上面的服务名
 * @date 2018-07-11
 * @author liumeng
 *
 */
@FeignClient(value = "SERVICEYINIANBI1",fallback = HystrixClientFallback.class,configuration = FeignMultipartSupportConfig.class)
public interface  SchedualService {
	
	 @RequestMapping(value = "/test",method = RequestMethod.GET)
	 public String test();
	 
	 
	@RequestMapping(value ="/count/countOperation", method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public JSONObject produceBuryData(
				 @RequestParam(value="userId",required=false) String userId
	    		,@RequestParam(value="fromUserId",required=false) String fromUserId
	    		,@RequestParam(value="createUserId",required=false) String createUserId
	    		,@RequestParam(value="groupId",required=false) String groupId
	    		,@RequestParam(value="port",required=false) String port
	    		,@RequestParam(value="operation",required=false) String operation
	    		,@RequestParam(value="remark",required=false) String remark
	    		,@RequestParam(value="userLastLoginTime",required=false) String userLastLoginTime
			);

}
	