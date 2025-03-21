package com.henu.dao;

import com.henu.entity.ShoppingCart;

import java.util.List;
import java.util.Map;


public interface ShoppingCartDao extends BaseDao<ShoppingCart> {
    /**
     * 查询用户购物车信息
     *
     * @param accountNumber 用户账号
     * @return 购物车信息
     */
    List<Map<String, Object>> selectAll(String accountNumber);

    /**
     * 通过用户账号删除
     *
     * @param accountNumber 用户账号
     * @return 是否删除成功
     */
    Boolean deleteByUser(String accountNumber);
}
