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
		                "totalCount":${item.total_count?c},
		                "curCount":${item.cur_count?c},
		                "detailUrl":"http://front.aliulian.com/v1/crowdfunding/211354_PeriodDetail.html",
		                "limitCount":${item.limit_count?c},
		                "startTime":${item.start_time_long?c},
		                "status":${item.status?c},
		                "awardTime":${item.award_time_long!0},
		                "percent":38,
		                "periodId":${item.id?c},
		                "listImg":[
		                	<#if list_img_arr ??>
		                		<#list list_img_arr as item>
		                			"${item}"
		                			<#if item_has_next>,</#if>
		                		</#list>
		                	</#if>
		                ],
		                "loopImageList":[
		                    <#if loop_img_arr ??>
		                		<#list loop_img_arr as item>
		                			"${item}"
		                			<#if item_has_next>,</#if>
		                		</#list>
		                	</#if>
		                ],
		                "productId":${item.product_id?c},
		                "title":"${item.title!}",
		                "newImg":"http://image.aliulian.com/grab_ten_yuan.png",
		                "hotImg":""
		            }
	            	<#if item_has_next>,</#if>
				</#list>
            </#if>
        ]
    }
}