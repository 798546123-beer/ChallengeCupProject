package com.henu.service.impl;

import com.henu.dao.ShoppingCartDao;
import com.henu.entity.ShoppingCart;
import com.henu.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart selectById(Integer cartId) {
        return shoppingCartDao.selectById(cartId);
    }

    @Override
    public List<Map<String, Object>> selectAll(String accountNumber) {
        return shoppingCartDao.selectAll(accountNumber);
    }

    @Override
    public Boolean insertData(ShoppingCart shoppingCart) {
        return shoppingCartDao.insertData(shoppingCart);
    }

    @Override
    public Boolean updateById(ShoppingCart shoppingCart) {
        return shoppingCartDao.updateById(shoppingCart);
    }

    @Override
    public Boolean deleteById(Integer cartId) {
        return shoppingCartDao.deleteById(cartId);
    }

    @Override
    public Boolean deleteByUser(String accountNumber) {
        return shoppingCartDao.deleteByUser(accountNumber);
    }
}
