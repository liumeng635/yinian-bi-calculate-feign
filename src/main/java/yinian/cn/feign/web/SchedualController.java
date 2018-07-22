package yinian.cn.feign.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yinian.cn.feign.service.SchedualService;
import net.sf.json.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty; 

/**
 * 负载均衡的向外暴露的api接口
 * @author liumeng
 *
 */
@RestController
@RequestMapping(value="/YinianProject")
@CrossOrigin
public class SchedualController {

	    @Autowired
	    SchedualService schedualService;
	    
	    @CrossOrigin
	    @RequestMapping(value = "/test",method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	    @HystrixCommand(fallbackMethod = "errorTest", commandProperties = {
	            @HystrixProperty(name="execution.isolation.strategy", value = "THREAD"),
	            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "80000"),
	            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
	            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "40")
	    }, threadPoolProperties = {
	            @HystrixProperty(name = "coreSize", value = "200"),
	            @HystrixProperty(name = "maxQueueSize", value = "50"),
	            @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
	            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
	            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
	            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
	    })
	    public String test(){
	        return schedualService.test();
	    }
	    
	    @CrossOrigin
	 	@RequestMapping(value ="/count/countOperation", method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
		public JSONObject queryStage(
				 	 @RequestParam(value="userId",required=false) String userId
		    		,@RequestParam(value="fromUserId",required=false) String fromUserId
		    		,@RequestParam(value="createUserId",required=false) String createUserId
		    		,@RequestParam(value="groupId",required=false) String groupId
		    		,@RequestParam(value="port",required=false) String port
		    		,@RequestParam(value="operation",required=false) String operation
		    		,@RequestParam(value="remark",required=false) String remark
		    		,@RequestParam(value="userLastLoginTime",required=false) String userLastLoginTime
				) {
	 		return schedualService.produceBuryData(userId,fromUserId,createUserId,groupId,port,operation,remark,userLastLoginTime);
		}
	 	
	    public String errorTest(){
	    	return "熔断 test 生效！！！";
	    }
}
