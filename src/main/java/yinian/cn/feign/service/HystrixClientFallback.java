package yinian.cn.feign.service;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

/**
 * 断路器
 * @author liumeng
 * 
 */
@Component
public class HystrixClientFallback implements SchedualService{


	@Override
	public String test() {
		return "对不起  , 服务出现故障！";
	}


	@Override
	public JSONObject produceBuryData(String userId,String fromUserId,String createUserId,String groupId,String port,String operation,String remark,String userLastLoginTime) {
		JSONObject rs = new JSONObject();
		rs.put("msg", "对不起  , 服务出现故障！");
		return rs;
	}

}
