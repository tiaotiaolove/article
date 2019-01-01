package com.xiaobai.dish.dao;

import com.github.pagehelper.Page;
import com.xiaobai.dish.entity.Dish;
import com.xiaobai.dish.request.DishReq;
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
     * @param dishReq 参数
     * @return 菜品分页数据
     */
    List<Dish> queryDishList(DishReq dishReq);
}
