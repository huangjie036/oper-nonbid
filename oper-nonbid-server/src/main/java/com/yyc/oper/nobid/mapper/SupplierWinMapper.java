package com.yyc.oper.nobid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.supplier.SupplierBean;
import com.yyc.oper.nobid.supplier.SupplierResponse;
import com.yyc.oper.nobid.supplier.SupplierWinBean;
import com.yyc.oper.nobid.supplier.SupplierWinDetailBean;
import com.yyc.oper.nobid.supplier.SupplierWinRequest;

@Mapper
public interface SupplierWinMapper {
    int deleteByPrimaryKey(String supplierWinId);

    int insert(SupplierWinBean record);

    int insertSelective(SupplierWinBean record);

    List<SupplierWinBean> selectByPrimaryKey(SupplierWinBean record);

    int updateByPrimaryKeySelective(SupplierWinBean record);

    int updateByPrimaryKey(SupplierWinBean record);
    
    /**
     * 维护中标结果时维护附件表为附件表添加业务信息
     * @param map 业务信息
     * @return
     */
    int updateFile(Map<String,Object> map) ;

	SupplierWinDetailBean selectPurchaseResultDetail(SupplierWinBean record);

	

	List<SupplierBean> selectSupplier(String supplierId);

	List<ExpertBean> selectExpert(String[] expertCode);

	SupplierWinDetailBean selectPurchaseResultDetail(SupplierWinRequest record);

	List<SupplierBean> selectSupplier(String[] both);
	

    /**
      *   采购方案的推荐供应商和抽取供应商集合
     * @param record 合包id
     * @return
     */
    List<SupplierResponse> selectSupplierByMergeId(SupplierWinBean record);

	List<MatplanMatBean> selectMat(String[] matId);

	

	List<MatplanMatBean> selectMatPlanId(SupplierWinRequest record);

	

	

	
}