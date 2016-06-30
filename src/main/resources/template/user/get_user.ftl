{
    "code": "0",
    "info": "",
    "expireTime": 10,
    "data": {
        "access_token": "301c5153-21ce-44eb-9459-3e9a735e23f6",
        "user": {
        	<#if user ??>
				"id": ${user.id?c},
    	   		"client_id": "${user.clientId}",
    	   		"app_code": "${user.appCode}",
    	   		"channel": "${user.appCode}",
    	   		"os": ${user.os?c},
    	   		"nick": "${user.nick}",
    	   		"portrait": "${user.portrait}",
    	   		"mobile": "${user.mobile}",
    	   		"gmt_create": "${user.gmtCreate?string('yyyy-MM-dd HH:mm:ss')}"
			</#if>
        }
    }
}