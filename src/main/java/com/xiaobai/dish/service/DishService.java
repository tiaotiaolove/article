package com.xiaobai.dish.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaobai.dish.dao.IDishDao;
import com.xiaobai.dish.entity.Dish;
import com.xiaobai.dish.request.DishReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜品查询Service
 *
 * @author bail
 * @date 2018/12/31.13:09
 */
@Service
@Slf4j
public class DishService {
    @Autowired
    private IDishDao dishDao;

    /**
     * 查询菜品分页数据
     */
    public Page<Dish> queryDishPage(DishReq dishReq) {
        PageHelper.startPage(dishReq.getPageNum(), dishReq.getPageSize());
        return dishDao.queryDishPage(dishReq);
    }
}
