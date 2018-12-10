package com.yyc.oper.nobid.service.mat;

import com.github.pagehelper.PageInfo;
import com.yyc.oper.nobid.mat.MatplanQueryRequest;
import com.yyc.oper.nobid.mat.MatplanQueryResponse;

public interface IMatplanQueryService {

    PageInfo<MatplanQueryResponse> selectByMatplanQueryRequest(MatplanQueryRequest record);
    
    PageInfo<MatplanQueryResponse> selectByNonMatplanQueryRequest(MatplanQueryRequest record);
    
}
