package com.yyc.oper.nobid.service.expert.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.yyc.brace.bean.ResultMessage;
import com.yyc.brace.util.UUIDUtils;
import com.yyc.oper.nobid.base.StateDictionary;
import com.yyc.oper.nobid.expert.ExtractSupplier;
import com.yyc.oper.nobid.mapper.ExtractSupplierMapper;
import com.yyc.oper.nobid.mapper.MergeRecordMapper;
import com.yyc.oper.nobid.mapper.SupplierMapper;
import com.yyc.oper.nobid.merge.MergeRecordBean;
import com.yyc.oper.nobid.service.expert.IExtractSupplierService;
import com.yyc.oper.nobid.supplier.SupplierBean;

@Service
public class ExtractSupplierServiceImpl implements IExtractSupplierService {

	@Autowired
	private ExtractSupplierMapper	extractSupplierMapper;

	@Autowired
	private SupplierMapper			supplierMapper;
	
	@Autowired
	private MergeRecordMapper mergeRecordMapper;



	@Override
	public ResultMessage saveSupplierExtractManual(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount,Integer recommendSupplierCount, List<String> recommendSupplierIdList, List<String> supplierIdList) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			/** 判断供应商需求数量的合法性 **/
			if (supplierCount <= 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("供应商的需求数量不能小于或者等于0");
				return resultMessage;
			}
			/** 判断供应商抽取数量和选中数量是否相等 **/
			supplierCount = supplierCount-recommendSupplierCount;
			if (supplierCount != supplierIdList.size()) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("提供的供应商与需要抽取的供应商数量不符，无法抽取，请核对后再抽取");
				return resultMessage;
			}
			//验证是否已抽取
			MergeRecordBean temp = new MergeRecordBean();
			temp.setMergeId(mergeId);
			List<MergeRecordBean> listTemp = mergeRecordMapper.selectByPrimaryKey(temp);
			if(null != listTemp && listTemp.size() > 0) {
				if("1".equals(listTemp.get(0).getSupplierExtractState())) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("供应商已抽取，请勿重复抽取");
					return resultMessage;
				}
			}
			
			

			// 声明抽中的供应商
			List<String> selectedSupplier = Lists.newArrayList();

			/** 获取采购计划的推荐供应商主键编号列表及其数量 **/
			List<String> recommandedSupplierList = extractSupplierMapper
					.selectRecommandedSupplierIdListByMergeId(mergeId);
			int recommandedSupplierCount = 0;
			if (recommandedSupplierList.size()==1&&null==recommandedSupplierList.get(0)||recommandedSupplierList.size()==0) {
				
			}else{
				recommandedSupplierCount = recommandedSupplierList.size();
			}

			/** 判断已推荐的供应商是否已经满足需求 **/
			if (recommandedSupplierCount > supplierCount) {
				/* 如果满足，直接在推荐的供应商里面抽取 */
				selectedSupplier = getRandomList(recommandedSupplierList, supplierCount);
			} else if (recommandedSupplierCount == supplierCount) {
				/* 如果相等，直接使用推荐的供应商 */
				selectedSupplier = recommandedSupplierList;
			} else {
				/* 如果不满足，则需要使用传入的供应商中抽取剩余的 */
				if (supplierIdList.size()==1&&null==supplierIdList.get(0)||supplierIdList.size()==0) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("请传入供应商的备选名单！");
					return resultMessage;
				}

				/* 检查供应商是否存在 */
				List<String> checkSupplierList = extractSupplierMapper.selectSupplierBySupplierIdList(supplierIdList);

				/* 如果都不存在，则直接返回错误提示 */
				if (CollectionUtils.isEmpty(checkSupplierList)) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("您提供的供应商不存在，请核对后再进行抽取！");
					return resultMessage;
				}

				/* 如果部分不存在，则直接返回哪些供应商不存在的消息 */
				if (checkSupplierList.size() != supplierIdList.size()) {
					supplierIdList.removeAll(checkSupplierList);
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("您提供的供应商编号为:" + JSONArray.toJSONString(supplierIdList) + "不存在，请核对后再进行抽取！");
					return resultMessage;
				}

				/* 如果都存在，则直接返回哪些供应商不存在的消息 */
				if (checkSupplierList.size() == supplierIdList.size()) {
					supplierCount = supplierCount - recommandedSupplierCount;
					selectedSupplier = getRandomList(supplierIdList, supplierCount);
					if (recommendSupplierIdList.size()==1&&null==recommendSupplierIdList.get(0)||recommendSupplierIdList.size()==0) {
					}else{
						selectedSupplier.addAll(recommendSupplierIdList);
					}
				}
			}

			/** 组装保存参数 **/
			// 组装抽取结果
			Map<String, Object> mergeRecord = extractSupplierMapper.selectMergeRecordByMergeId(mergeId);
			if (mergeRecord == null || mergeRecord.isEmpty()) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("您提供的采购方案不存在，请核对后再进行抽取！");
				return resultMessage;
			}
			ExtractSupplier extractSupplier = new ExtractSupplier();
			extractSupplier.setExtractId(UUIDUtils.getUUID());
			extractSupplier.setMergeId(mergeId);
			extractSupplier.setExtractNum(supplierCount);
			extractSupplier.setPurchaseSchmeId(mergeRecord.get("purchase_schme_id").toString());
			extractSupplier.setPurchaseSchmeName(mergeRecord.get("purchase_schme_name").toString());
			extractSupplier.setPurchaseSchmeCreateTime((Timestamp) mergeRecord.get("create_time"));
			extractSupplier.setExtractDate(new Timestamp(new Date().getTime()));
			extractSupplier.setCreateDate(new Timestamp(new Date().getTime()));
//			extractSupplier.setExtractedBy(mergeRecord.get("ope_by").toString());
//			extractSupplier.setCreateBy(mergeRecord.get("ope_by").toString());
//			extractSupplier.setExtractedBy(CurrentPrincipalHolder.getAttribute("name").toString());
//			extractSupplier.setCreateBy(CurrentPrincipalHolder.getAttribute("name").toString());
			// 保存基础信息
			int insertrExtractSupplierExtract = extractSupplierMapper.insertrExtractSupplierExtract(extractSupplier);
			if (insertrExtractSupplierExtract < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存基础信息失败）！");
				return resultMessage;
			}

			// 保存对应的供应商信息
			List<ExtractSupplier> extractSupplierMappingList = Lists.newArrayList();
			
			for (String supplierId : selectedSupplier) {
				ExtractSupplier extractSupplierMapping =new ExtractSupplier();
				extractSupplierMapping.setSupplierId(supplierId);
				extractSupplier.setSupplierId(extractSupplierMapping.getSupplierId());
				//合并两个bean的属性
				BeanUtils.copyProperties(extractSupplier, extractSupplierMapping);
				extractSupplierMappingList.add(extractSupplierMapping);
			}
			int insertSupplierExtractChild = extractSupplierMapper
					.insertSupplierExtractChild(extractSupplierMappingList);
			if (insertSupplierExtractChild < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存对应的供应商失败）！");
				return resultMessage;
			}
			//抽取完成之后将抽取状态设置为已抽取完成
			extractSupplier.setState("1");
			int extractState = extractSupplierMapper.insertExtractSupplierState(extractSupplier);
			if (extractState < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存对应的供应商失败）！");
				return resultMessage;
			}

			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(selectedSupplier);
			resultMessage.setMsg("抽取成功！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}

	}



	/**
	 * @function:从list中随机抽取若干不重复元素
	 *
	 * @param paramList:被抽取list
	 * @param count:抽取元素的个数
	 * @return:由抽取元素组成的新list
	 */
	private static List<String> getRandomList(List<String> paramList, int count) {
		if (paramList.size() < count) {
			return paramList;
		}
		Random random = new Random();
		List<Integer> tempList = Lists.newArrayList();
		List<String> newList = Lists.newArrayList();
		int temp = 0;
		for (int i = 0; i < count; i++) {
			// 将产生的随机数作为被抽list的索引
			temp = random.nextInt(paramList.size());
			if (!tempList.contains(temp)) {
				tempList.add(temp);
				newList.add(paramList.get(temp));
			} else {
				i--;
			}
		}
		return newList;
	}



	@Override
	public List<SupplierBean> getSupplierByParam(SupplierBean record) {
		List<SupplierBean> mmNonbidExpertList = supplierMapper.selectByPrimaryKey(record);
		return mmNonbidExpertList;
	}



	@Override
	public ResultMessage saveSupplierExtract(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount, List<String> supplierIdList) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResultMessage saveSupplierExtractRandom(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount,Integer recommendSupplierCount,List<String> recommendSupplierIdList, List<String> supplierIdList) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			/** 判断供应商需求数量的合法性 **/
			if (supplierCount <= 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("供应商的需求数量不能小于或者等于0");
				return resultMessage;
			}
			/** 判断供应商数量是否大于等于需要抽取的供应商数量 **/
			if (supplierCount > supplierIdList.size()) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("提供的供应商与需要抽取的供应商数量不符，无法抽取，请核对后再抽取");
				return resultMessage;
			}
			//验证是否已抽取
			MergeRecordBean temp = new MergeRecordBean();
			temp.setMergeId(mergeId);
			List<MergeRecordBean> listTemp = mergeRecordMapper.selectByPrimaryKey(temp);
			if(null != listTemp && listTemp.size() > 0) {
				if("1".equals(listTemp.get(0).getSupplierExtractState())) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("供应商已抽取，请勿重复抽取");
					return resultMessage;
				}
			}
			

			// 声明抽中的供应商
			List<String> selectedSupplier = Lists.newArrayList();

			/** 获取采购计划的推荐供应商主键编号列表及其数量 **/
			List<String> recommandedSupplierList = extractSupplierMapper
					.selectRecommandedSupplierIdListByMergeId(mergeId);
			int recommandedSupplierCount = 0;
			if (recommandedSupplierList.size()==1&&null==recommandedSupplierList.get(0)||recommandedSupplierList.size()==0) {
				
			}else{
				recommandedSupplierCount = recommandedSupplierList.size();
			}

			/** 判断已推荐的供应商是否已经满足需求 **/
			if (recommandedSupplierCount > supplierCount) {
				/* 如果满足，直接在推荐的供应商里面抽取 */
				selectedSupplier = getRandomList(recommandedSupplierList, supplierCount);
			} else if (recommandedSupplierCount == supplierCount) {
				/* 如果相等，直接使用推荐的供应商 */
				selectedSupplier = recommandedSupplierList;
			} else {
				/* 如果不满足，则需要使用传入的供应商中抽取剩余的 */
				if (supplierIdList.size()==1&&null==supplierIdList.get(0)||supplierIdList.size()==0) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("请传入供应商的备选名单！");
					return resultMessage;
				}

				/* 检查供应商是否存在 */
				List<String> checkSupplierList = extractSupplierMapper.selectSupplierBySupplierIdList(supplierIdList);

				/* 如果都不存在，则直接返回错误提示 */
				if (CollectionUtils.isEmpty(checkSupplierList)) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("您提供的供应商不存在，请核对后再进行抽取！");
					return resultMessage;
				}

				/* 如果部分不存在，则直接返回哪些供应商不存在的消息 */
				if (checkSupplierList.size() != supplierIdList.size()) {
					supplierIdList.removeAll(checkSupplierList);
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("您提供的供应商编号为:" + JSONArray.toJSONString(supplierIdList) + "不存在，请核对后再进行抽取！");
					return resultMessage;
				}

				/* 如果都存在，则直接返回哪些供应商不存在的消息 */
				if (checkSupplierList.size() == supplierIdList.size()) {
					supplierCount = supplierCount - recommendSupplierCount;
					for(String str : recommendSupplierIdList){
						if(supplierIdList.contains(str)){
							supplierIdList.remove(str);
						}
					}
					selectedSupplier = getRandomList(supplierIdList, supplierCount);
					if (recommendSupplierIdList.size()==1&&null==recommendSupplierIdList.get(0)||recommendSupplierIdList.size()==0) {
					}else{
						selectedSupplier.addAll(recommendSupplierIdList);
					}
				}
			}

			/** 组装保存参数 **/
			// 组装抽取结果
			Map<String, Object> mergeRecord = extractSupplierMapper.selectMergeRecordByMergeId(mergeId);
			if (mergeRecord == null || mergeRecord.isEmpty()) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("您提供的采购方案不存在，请核对后再进行抽取！");
				return resultMessage;
			}
			ExtractSupplier extractSupplier = new ExtractSupplier();
			extractSupplier.setExtractId(UUIDUtils.getUUID());
			extractSupplier.setMergeId(mergeId);
			extractSupplier.setExtractNum(supplierCount);
			extractSupplier.setPurchaseSchmeId(mergeRecord.get("purchase_schme_id").toString());
			extractSupplier.setPurchaseSchmeName(mergeRecord.get("purchase_schme_name").toString());
			extractSupplier.setPurchaseSchmeCreateTime((Timestamp) mergeRecord.get("create_time"));
			extractSupplier.setExtractDate(new Timestamp(new Date().getTime()));
			extractSupplier.setCreateDate(new Timestamp(new Date().getTime()));
//			extractSupplier.setExtractedBy(mergeRecord.get("ope_by").toString());
//			extractSupplier.setCreateBy(mergeRecord.get("ope_by").toString());
//			extractSupplier.setExtractedBy(CurrentPrincipalHolder.getAttribute("name").toString());
//			extractSupplier.setCreateBy(CurrentPrincipalHolder.getAttribute("name").toString());
			// 保存基础信息
			int insertrExtractSupplierExtract = extractSupplierMapper.insertrExtractSupplierExtract(extractSupplier);
			if (insertrExtractSupplierExtract < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存基础信息失败）！");
				return resultMessage;
			}

			// 保存对应的供应商信息
			List<ExtractSupplier> extractSupplierMappingList = Lists.newArrayList();
			
			for (String supplierId : selectedSupplier) {
				ExtractSupplier extractSupplierMapping =new ExtractSupplier();
				extractSupplierMapping.setSupplierId(supplierId);
				extractSupplier.setSupplierId(extractSupplierMapping.getSupplierId());
				//合并两个bean的属性
				BeanUtils.copyProperties(extractSupplier, extractSupplierMapping);
				extractSupplierMappingList.add(extractSupplierMapping);
			}
			int insertSupplierExtractChild = extractSupplierMapper
					.insertSupplierExtractChild(extractSupplierMappingList);
			if (insertSupplierExtractChild < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存对应的供应商失败）！");
				return resultMessage;
			}
			//抽取完成之后将抽取状态设置为已抽取完成
			
			int extractState = extractSupplierMapper.insertExtractSupplierState(extractSupplier);
			if (extractState < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存对应的供应商失败）！");
				return resultMessage;
			}

			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(selectedSupplier);
			resultMessage.setMsg("抽取成功！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
	}



	@Override
	public List<String> selectSupplierIdList(SupplierBean supplierBean) {
		// TODO Auto-generated method stub
		return extractSupplierMapper.selectSupplierIdList(supplierBean);
	}



	@Override
	public List<String> selectSupplierBySupplierNameList(List<String> supplierNameList) {
		// TODO Auto-generated method stub
		return extractSupplierMapper.selectSupplierBySupplierNameList(supplierNameList);
	}



	@Override
	public List<String> selectNewSupplierBySupplierIdList(List<String> supplierIdList) {
		// TODO Auto-generated method stub
		return extractSupplierMapper.selectNewSupplierBySupplierIdList(supplierIdList);
	}



	@Override
	public ResultMessage saveSupplierExtractImport(String purchaseSchmeId, String mergeId, String opertionMethod,
			Integer supplierCount, List<String> supplierIdList) {
		ResultMessage resultMessage = new ResultMessage();
		try {
			/** 判断供应商需求数量的合法性 **/
			if (supplierCount <= 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("供应商的需求数量不能小于或者等于0");
				return resultMessage;
			}
			/** 判断供应商数量是否大于等于需要抽取的供应商数量 **/
			if (supplierCount > supplierIdList.size()) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("提供的供应商与需要抽取的供应商数量不符，无法抽取，请核对后再抽取");
				return resultMessage;
			}
			//验证是否已抽取
			MergeRecordBean temp = new MergeRecordBean();
			temp.setMergeId(mergeId);
			List<MergeRecordBean> listTemp = mergeRecordMapper.selectByPrimaryKey(temp);
			if(null != listTemp && listTemp.size() > 0) {
				if("1".equals(listTemp.get(0).getSupplierExtractState())) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("供应商已抽取，请勿重复抽取");
					return resultMessage;
				}
			}
			

			// 声明抽中的供应商
			List<String> selectedSupplier = Lists.newArrayList();

			/** 获取采购计划的推荐供应商主键编号列表及其数量 **/
			List<String> recommandedSupplierList = extractSupplierMapper
					.selectRecommandedSupplierIdListByMergeId(mergeId);
			int recommandedSupplierCount = 0;
			if (recommandedSupplierList.size()==1&&null==recommandedSupplierList.get(0)||recommandedSupplierList.size()==0) {
				
			}else{
				recommandedSupplierCount = recommandedSupplierList.size();
			}

			/** 判断已推荐的供应商是否已经满足需求 **/
			if (recommandedSupplierCount > supplierCount) {
				/* 如果满足，直接在推荐的供应商里面抽取 */
				selectedSupplier = getRandomList(recommandedSupplierList, supplierCount);
			} else if (recommandedSupplierCount == supplierCount) {
				/* 如果相等，直接使用推荐的供应商 */
				selectedSupplier = recommandedSupplierList;
			} else {
				/* 如果不满足，则需要使用传入的供应商中抽取剩余的 */
				if (supplierIdList.size()==1&&null==supplierIdList.get(0)||supplierIdList.size()==0) {
					resultMessage.setCode(StateDictionary.ERROR);
					resultMessage.setMsg("请传入供应商的备选名单！");
					return resultMessage;
				}


				/* 如果都存在，则直接返回哪些供应商不存在的消息 */
				
					supplierCount = supplierCount - recommandedSupplierCount;
					selectedSupplier = getRandomList(supplierIdList, supplierCount);
					if (recommandedSupplierList.size()==1&&null==recommandedSupplierList.get(0)||recommandedSupplierList.size()==0) {
					}else{
						selectedSupplier.addAll(recommandedSupplierList);
					}
				}
			

			/** 组装保存参数 **/
			// 组装抽取结果
			Map<String, Object> mergeRecord = extractSupplierMapper.selectMergeRecordByMergeId(mergeId);
			if (mergeRecord == null || mergeRecord.isEmpty()) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("您提供的采购方案不存在，请核对后再进行抽取！");
				return resultMessage;
			}
			ExtractSupplier extractSupplier = new ExtractSupplier();
			extractSupplier.setExtractId(UUIDUtils.getUUID());
			extractSupplier.setMergeId(mergeId);
			extractSupplier.setExtractNum(supplierCount);
			extractSupplier.setPurchaseSchmeId(mergeRecord.get("purchase_schme_id").toString());
			extractSupplier.setPurchaseSchmeName(mergeRecord.get("purchase_schme_name").toString());
			extractSupplier.setPurchaseSchmeCreateTime((Timestamp) mergeRecord.get("create_time"));
			extractSupplier.setExtractDate(new Timestamp(new Date().getTime()));
			extractSupplier.setCreateDate(new Timestamp(new Date().getTime()));
//			extractSupplier.setExtractedBy(mergeRecord.get("ope_by").toString());
//			extractSupplier.setCreateBy(mergeRecord.get("ope_by").toString());
//			extractSupplier.setExtractedBy(CurrentPrincipalHolder.getAttribute("name").toString());
//			extractSupplier.setCreateBy(CurrentPrincipalHolder.getAttribute("name").toString());
			// 保存基础信息
			int insertrExtractSupplierExtract = extractSupplierMapper.insertrExtractSupplierExtract(extractSupplier);
			if (insertrExtractSupplierExtract < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存基础信息失败）！");
				return resultMessage;
			}

			// 保存对应的供应商信息
			List<ExtractSupplier> extractSupplierMappingList = Lists.newArrayList();
			
			for (String supplierId : selectedSupplier) {
				ExtractSupplier extractSupplierMapping =new ExtractSupplier();
				extractSupplierMapping.setSupplierId(supplierId);
				extractSupplier.setSupplierId(extractSupplierMapping.getSupplierId());
				//合并两个bean的属性
				BeanUtils.copyProperties(extractSupplier, extractSupplierMapping);
				extractSupplierMappingList.add(extractSupplierMapping);
			}
			int insertSupplierExtractChild = extractSupplierMapper
					.insertSupplierExtractChild(extractSupplierMappingList);
			
			if (insertSupplierExtractChild < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存对应的供应商失败）！");
				return resultMessage;
			}
			//抽取完成之后将抽取状态设置为已抽取完成
			extractSupplier.setState("1");
			int extractState = extractSupplierMapper.insertExtractSupplierState(extractSupplier);
			if (extractState < 0) {
				resultMessage.setCode(StateDictionary.ERROR);
				resultMessage.setMsg("抽取失败（保存对应的供应商失败）！");
				return resultMessage;
			}
			resultMessage.setCode(StateDictionary.OK);
			resultMessage.setData(selectedSupplier);
			resultMessage.setMsg("抽取成功！");
			return resultMessage;
		} catch (Exception e) {
			resultMessage.setCode(StateDictionary.ERROR);
			resultMessage.setMsg(e.getMessage());
			return resultMessage;
		}
	}









}
