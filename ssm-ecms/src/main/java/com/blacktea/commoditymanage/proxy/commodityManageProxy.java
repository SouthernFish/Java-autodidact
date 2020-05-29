package com.blacktea.commoditymanage.proxy;

import com.blacktea.common.page.PageForSystem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author TR
 * @Create 2020年05月28日
 * @Title: commodityManageProxy
 * @Description: 商品管理代理层
 */
@Repository("commodityManageProxy")
public interface commodityManageProxy {

	/**
	* @Author TR
	* @Create 2020/5/28 15:48
	* @Title: getCommodityList
	* @Params: [page, searchText]
	* @Description: 商品列表
	*/
	List<Map<String, Object>> getCommodityList(@Param("page") PageForSystem page,
											   @Param("searchText") String searchText);

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:00
	 * @Title: deleteCommodity
	 * @Params: [commodityId]
	 * @Description: 删除商品
	 */
	Integer deleteCommodity(@Param("commodityId") Integer commodityId);

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:20
	 * @Title: getCategoryList
	 * @Params: [page]
	 * @Description: 分类列表
	 */
	List<Map<String,Object>> getCategoryList(@Param("page") PageForSystem page);

	/**
	 * @Author TR
	 * @Create 2020/5/28 16:30
	 * @Title: deleteCategory
	 * @Params: [categoryId]
	 * @Description: 删除分类
	 */
	Integer deleteCategory(@Param("categoryId") Integer categoryId);
}
