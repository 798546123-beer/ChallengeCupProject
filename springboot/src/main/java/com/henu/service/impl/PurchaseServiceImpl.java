package com.henu.service.impl;

import com.henu.dao.PurchaseDao;
import com.henu.entity.Purchase;
import com.henu.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public Purchase selectById(Integer purchaseId) {
        return purchaseDao.selectById(purchaseId);
    }

    @Override
    public List<Purchase> selectAll() {
        return purchaseDao.selectAll();
    }

    @Override
    public Boolean existsWithPrimaryKey(String purchaseNo) {
        return purchaseDao.existsWithPrimaryKey(purchaseNo);
    }

    @Override
    public Boolean insertData(Purchase purchase) {
        purchase.setPurchaseTime(new Date());
        return purchaseDao.insertData(purchase);
    }

    @Override
    public Boolean updateById(Purchase purchase) {
        return purchaseDao.updateById(purchase);
    }

    @Override
    public Boolean deleteById(Integer purchaseId) {
        return purchaseDao.deleteById(purchaseId);
    }
}
