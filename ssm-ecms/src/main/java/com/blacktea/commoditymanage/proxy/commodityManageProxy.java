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

	/**
	 * @Author TR
	 * @Create 2020/6/9 10:49
	 * @Title: addCommodity
	 * @Description: 添加商品
	 */
	Integer addCommodity(@Param("commodityWeight") String commodityWeight,
						 @Param("commodityPrice") String commodityPrice,
						 @Param("commodityName") String commodityName,
						 @Param("commodityNumber") Integer commodityNumber,
						 @Param("categoryId") Integer categoryId,
						 @Param("commodityIntroduce") String commodityIntroduce,
						 @Param("commodityBigLogo") String commodityBigLogo,
						 @Param("commoditySmallLogo") String commoditySmallLogo);

	/**
	 * @Author TR
	 * @Create 2020/6/9 11:42
	 * @Title: judgeCommodityExist
	 * @Description: 查询商品是否存在
	 */
	Integer judgeCommodityExist(@Param("commodityWeight") String commodityWeight,
								@Param("commodityPrice") String commodityPrice,
								@Param("commodityName") String commodityName,
								@Param("commodityNumber") Integer commodityNumber,
								@Param("categoryId") Integer categoryId,
								@Param("commodityIntroduce") String commodityIntroduce,
								@Param("commodityBigLogo") String commodityBigLogo,
								@Param("commoditySmallLogo") String commoditySmallLogo);

	/**
	 * @Author TR
	 * @Create 2020/6/9 11:47
	 * @Title: updateCommodityDel
	 * @Description: 修改商品状态
	 */
	Integer updateCommodityDel(@Param("commodityId") Integer commodityId);

	/**
	 * @Author TR
	 * @Create 2020/6/9 10:55
	 * @Title: modifyCommodity
	 * @Description: 编辑商品信息
	 */
	Integer modifyCommodity(@Param("commodityId") Integer commodityId,
							@Param("commodityWeight") String commodityWeight,
							@Param("commodityPrice") String commodityPrice,
							@Param("commodityName") String commodityName,
							@Param("commodityNumber") Integer commodityNumber,
							@Param("categoryId") Integer categoryId,
							@Param("commodityIntroduce") String commodityIntroduce,
							@Param("commodityBigLogo") String commodityBigLogo,
							@Param("commoditySmallLogo") String commoditySmallLogo);

}
