{
    "code":"${code}",
    "info":"${info!}",
    "data":{
        "page":{
        	<#if result.pager ??>
	            "toPage":${result.pager.pageNumber?c},
	            "totalPage":${result.pager.pageCount?c}
            </#if>
        },
        "periodList":[
            <#if result.list ??>
            	<#list result.list as item> 
	            	{
		                "totalCount":11,
		                "curCount":152040,
		                "detailUrl":"http://front.aliulian.com/v1/crowdfunding/211354_PeriodDetail.html",
		                "limitCount":1,
		                "startTime":1465633732000,
		                "status":0,
		                "awardTime":1465633733000,
		                "percent":38,
		                "periodId":211354,
		                "listImg":"http://image.aliulian.com/20160513L3T8S8M8.jpg",
		                "loopImageList":[
		                    "http://image.aliulian.com/20160513XGMHMBLW.jpg",
		                    "http://image.aliulian.com/20160513NNJMIW1T.jpg",
		                    "http://image.aliulian.com/20160513471F3GAP.jpg",
		                    "http://image.aliulian.com/201605133FSYD71T.jpg"
		                ],
		                "productId":1305,
		                "title":"2016款 宝马 BMW 320i 时尚型轿车 随心所动 悦无止境 （商品价格仅包含裸车价）",
		                "newImg":"http://image.aliulian.com/grab_ten_yuan.png",
		                "hotImg":""
		            }
	            	<#if item_has_next>,</#if>
				</#list>
            </#if>
        ]
    }
}