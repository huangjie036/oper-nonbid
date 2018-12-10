package com.yyc.oper.nobid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yyc.oper.nobid.supplier.InvitationSupplierBean;

@Mapper
public interface InvitationSupplierMapper {
    int deleteByPrimaryKey(String invitationId);

    int insert(List<InvitationSupplierBean> record);

    int insertSelective(InvitationSupplierBean record);

    List<InvitationSupplierBean> selectByPrimaryKey(InvitationSupplierBean record);

    int updateByPrimaryKeySelective(InvitationSupplierBean record);

    int updateByPrimaryKey(InvitationSupplierBean record);
    
    int deleteByMatplanId(String matplanId);
}