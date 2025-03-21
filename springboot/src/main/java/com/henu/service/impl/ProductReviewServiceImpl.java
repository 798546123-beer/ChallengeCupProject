package com.henu.service.impl;

import com.henu.dao.ProductReviewDao;
import com.henu.entity.ProductReview;
import com.henu.service.ProductReviewService;
import com.henu.util.general.WordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ProductReviewDao productReviewDao;

    @Override
    public ProductReview selectById(Integer reviewId) {
        return productReviewDao.selectById(reviewId);
    }

    @Override
    public List<Map<String, Object>> selectAll(String productNo) {
        return productReviewDao.selectAll(productNo);
    }

    @Override
    public Boolean insertData(ProductReview productReview) {
        productReview.setReviewTime(new Date());
        String review = WordFilter.replaceWords(productReview.getProductReview());
        productReview.setProductReview(review);
        return productReviewDao.insertData(productReview);
    }

    @Override
    public Boolean updateById(ProductReview productReview) {
        return productReviewDao.updateById(productReview);
    }

    @Override
    public Boolean deleteById(Integer reviewId) {
        return productReviewDao.deleteById(reviewId);
    }


}
