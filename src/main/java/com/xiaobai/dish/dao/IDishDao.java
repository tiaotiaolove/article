package com.xiaobai.dish.dao;

import com.xiaobai.dish.request.DishAddReq;
import com.xiaobai.dish.request.DishDelReq;
import com.xiaobai.dish.request.DishQueryReq;
import com.xiaobai.dish.request.DishUpdateReq;
import com.xiaobai.dish.response.DishRes;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜品管理Dao
 *
 * @author bail
 * @date 2018/12/31.13:09
 */
@Repository
public interface IDishDao {

    /**
     * 根据参数查询菜品分页数据
     *
     * @param dishQueryReq 参数
     * @return 菜品分页数据
     */
    List<DishRes> queryDishList(DishQueryReq dishQueryReq);

    /**
     * 添加菜品
     *
     * @param dishAddReq 参数
     */
    void addDish(DishAddReq dishAddReq);

    /**
     * 编辑菜品
     *
     * @param dishUpdateReq 参数
     */
    void updateDish(DishUpdateReq dishUpdateReq);

    /**
     * 删除菜品
     *
     * @param dishDelReq 参数
     */
    void deleteDish(DishDelReq dishDelReq);
}
