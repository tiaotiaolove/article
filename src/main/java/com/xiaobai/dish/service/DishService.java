package com.xiaobai.dish.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobai.dish.dao.IDishDao;
import com.xiaobai.dish.request.DishQueryReq;
import com.xiaobai.dish.response.DishRes;
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
        PageHelper.startPage(dishQueryReq.getPageNum(), dishQueryReq.getPageSize());
        return new PageInfo<>(dishDao.queryDishList(dishQueryReq));
    }
}
