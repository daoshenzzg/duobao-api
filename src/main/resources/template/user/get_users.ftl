{
    "code": "0",
    "info": "",
    "expireTime": 10,
    "data": {
        "access_token": "301c5153-21ce-44eb-9459-3e9a735e23f6",
        "users": [
        	<#if users ??>
				<#list users as item> 
					{
		    	   		"id": ${item.id?c},
		    	   		"client_id": "${item.clientId}",
		    	   		"app_code": "${item.appCode}",
		    	   		"channel": "${item.appCode}",
		    	   		"os": ${item.os?c},
		    	   		"nick": "${item.nick}",
		    	   		"portrait": "${item.portrait}",
		    	   		"mobile": "${item.mobile}",
		    	   		"gmt_create": "${item.gmtCreate?string('yyyy-MM-dd HH:mm:ss')}"
		    	   	}
		    	   	<#if item_has_next>,</#if>
				</#list>
			</#if>
        ]
    }
}