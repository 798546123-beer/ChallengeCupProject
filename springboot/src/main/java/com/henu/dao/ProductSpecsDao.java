package com.henu.dao;

import com.henu.entity.ProductSpecs;

import java.util.List;


public interface ProductSpecsDao extends BaseDao<ProductSpecs> {
    /**
     * 根据商品id查询所有商品规格名称
     *
     * @param productId 商品编号
     * @return 商品规格名称
     */
    List<String> selectAllByProId(Integer productId);

    /**
     * 通过商品编号和商品规格编号删除
     *
     * @param productSpecs 商品规格信息
     * @return 是否删除成功
     */
    Boolean deleteById(ProductSpecs productSpecs);

    /**
     * 批量添加
     *
     * @param productSpecsList 商品规格列表
     * @return 是否添加成功
     */
    Boolean insertBatch(List<ProductSpecs> productSpecsList);

    /**
     * 根据产品ID查询规格
     *
     * @param productId 产品ID
     * @return 产品规格信息
     */
    ProductSpecs selectByProductId(Integer productId);
}
