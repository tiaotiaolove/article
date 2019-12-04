package com.xiaobai.dish.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobai.dish.dao.IDishDao;
import com.xiaobai.dish.request.DishAddReq;
import com.xiaobai.dish.request.DishDelReq;
import com.xiaobai.dish.request.DishQueryReq;
import com.xiaobai.dish.request.DishUpdateReq;
import com.xiaobai.dish.response.DishRes;
import com.xiaobai.util.LoginUtil;
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
    public PageInfo<DishRes> queryDishPage(DishQueryReq dishQueryReq) {
        dishQueryReq.setCreateUserId(LoginUtil.getUserId());
        PageHelper.startPage(dishQueryReq.getPageNum(), dishQueryReq.getPageSize());
        return new PageInfo<>(dishDao.queryDishList(dishQueryReq));
    }

    /**
     * 添加菜品
     */
    public void addDish(DishAddReq dishAddReq) {
        dishAddReq.setCreateUserId(LoginUtil.getUserId());
        dishDao.addDish(dishAddReq);
    }

    /**
     * 编辑菜品
     */
    public void updateDish(DishUpdateReq dishUpdateReq) {
        dishUpdateReq.setCreateUserId(LoginUtil.getUserId());
        dishDao.updateDish(dishUpdateReq);
    }

    /**
     * 删除菜品
     */
    public void deleteDish(DishDelReq dishDelReq) {
        dishDelReq.setCreateUserId(LoginUtil.getUserId());
        dishDao.deleteDish(dishDelReq);
    }
}
