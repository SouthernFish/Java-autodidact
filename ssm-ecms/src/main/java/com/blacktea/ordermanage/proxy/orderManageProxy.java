package com.blacktea.ordermanage.proxy;

import com.blacktea.common.page.PageForSystem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: orderManageProxy
 * @Description: 订单管理代理层
 */
@Repository("orderManageProxy")
public interface orderManageProxy {

	/**
	 * @Author TR
	 * @Create 2020/5/28 17:07
	 * @Title: getOrderList
	 * @Params: [page, searchText]
	 * @Description: 订单列表
	 */
	List<Map<String,Object>> getOrderList(PageForSystem page, String searchText);
}
