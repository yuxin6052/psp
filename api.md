# Psp Api
## 1.login
###  url: POST /tenant/v1/user/login
### Request:
{
username:”admin” <string> // demo阶段有效用户为 admin 
password:”123456” <string> // 密码， demo阶段有效用户为的密码均为123456,麻烦app 写死固定值
}
### Response:
{
"data": {
"tenantId":11
"tenantName":"aaa"
"page": [//页面信息
{
"index": 1,
"id": 11,
"nameCode": "Overview",
"children": [
{
"index": 1,
"id": 12,
"nameCode": "Dashboard"
},
{
"index": 2,
"id": 13,
"nameCode": "Dashboard2"
},
{
"index": 3,
"id": 14,
"nameCode": "Dashboard3"
}
]
},
{
"index": 2,
"id": 15,
"nameCode": "User Analytics",
"children": [
{
"index": 1,
"id": 16,
"nameCode": "User"
}
]
},
{
"index": 3,
"id": 17,
"nameCode": "App Versions"
},
{
"index": 4,
"id": 18,
"nameCode": "Platform"
},
{
"index": 5,
"id": 19,
"nameCode": "Events"
},
{
"index": 6,
"id": 20,
"nameCode": "Management",
"children": [
{
"index": 1,
"id": 21,
"nameCode": "Application Management",
"children": [
{
"index": 0,
"id": 22,
"nameCode": "Create",
"enable": 1
},
{
"index": 1,
"id": 23,
"nameCode": "Edit",
"enable": 1
},
{
"index": 2,
"id": 24,
"nameCode": "View",
"enable": 1
},
{
"index": 3,
"id": 25,
"nameCode": "Delete",
"enable": 1
}
]
}
]
}
],
"userId": 4
},
"errorCode": 0,
"errorMsg": "Success"
}