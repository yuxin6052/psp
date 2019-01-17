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



## 2.list companyLabel
###  url: GET /tenant/v1/companyLabel/list
### Request:
{
}
### Response:
{
"data": {
"labels":[
{
"labelId":11,
"labelName":"工程监理"
}
]
},
"errorCode": 0,
"errorMsg": "Success"
}


## 3.list company
###  url: GET /tenant/v1/company/list?unifiedSocialCreditCode=aaa&companyName=bbb&companyLabel=1&pageNo=1&pageSize=10
### Request:
{
}
### Response:
{
"data": {
"companys":[
{
businessEndTime:1212412424124// 1970.1.1到该日期的毫秒数
businessScope:"asdasd" //业务范围
businessStartTime:1212412424124// 1970.1.1到该日期的毫秒数
commpanyNature:"afafsa" //公司性质
companyId:1 
companyName:"asdda"
 
legalPerson:"afasf" //法人
registerArea:"asfasfas" //注册地
registerCapital:"asfasf" //注册资本
registerNo:"asdasf" //注册号
registrationAuthority:"asasf" //登记机关
 
status:1 //启用禁用状态 1：启用 0：暂停 2：暂停中 3：注销
unifiedSocialCreditCode:"asdasd" //社会统一信用代码
url:"asdasasf"  //网址
companyLabels:[   //公司包含的职能标签
{
"labelId":11,
"labelName":"工程监理"
}
]
users:[
{
userId:1
username:"afsa" //登陆账号
realName:"asdas" //姓名
status:1 //启用禁用状态 1：启用 0：暂停 2：作废
}
]
}
]
},
"errorCode": 0,
"errorMsg": "Success"
}

## 4.list company users
###  url: GET /tenant/v1/company/{companyId}/listUsers 
### Request:
{
}
### Response:
{
"data": {
 
users:[
{
userId:1
username:"afsa" //登陆账号
realName:"asdas" //姓名
status:1 //启用禁用状态 1：启用 0：暂停 2：作废
}
]
 
},
"errorCode": 0,
"errorMsg": "Success"
}

## 5.create company 
###  url: POST /tenant/v1/company/create
### Request:
{
 
companyName:"asdda"
 
 
registerArea:"asfasfas" //注册地
 
unifiedSocialCreditCode:"asdasd" //社会统一信用代码
labelIds:[   //公司包含的职能标签
 11,22,33
]
}
### Response:
{
"data": {
 
companyId: 1
 
},
"errorCode": 0,
"errorMsg": "Success"
}

## 6.update company 
###  url: POST /tenant/v1/company/update
### Request:
{
 companyId：1
companyName:"asdda"
 
 
registerArea:"asfasfas" //注册地
 
unifiedSocialCreditCode:"asdasd" //社会统一信用代码
labelIds:[   //公司包含的职能标签
 11,22,33
]
}
### Response:
{
"data": {
},
"errorCode": 0,
"errorMsg": "Success"
}


## 7.list users
###  url: GET /tenant/v1/user/list?username=aaa&companyName=bbb&realName=ccc&pageNo=1&pageSize=10
### Request:
{
}
### Response:
{
"data": {
"users":[
{
realName:"asfas" //姓名
 username:"aasfsfa"
 status:1 //启用禁用状态 1：启用 0：暂停 2：作废
companyId:1 
companyName:"asdda"
companyLabels:[   //公司包含的职能标签
{
"labelId":11,
"labelName":"工程监理"
}
]
 
unifiedSocialCreditCode:"asdasd" //社会统一信用代码
 
labels:[   //公司包含的职能标签
{
"labelId":11,
"labelName":"工程监理"
}
]
 
}
]
},
"errorCode": 0,
"errorMsg": "Success"
}



## 8.create user 
###  url: POST /tenant/v1/user/create
### Request:
{
 
companyId:1
 
    
userName:"sadas" //登陆账号
 
 
realName:"sadsa" //姓名
 
contactNo:"2141224" // 手机号码
otherContactNo:"2141224" // 其他联系方式
address:"asdsad" // 地址
identityNo:"21415125" // 身份证号
officePhone:"13124" // 办公室电话
zipCode:"13124" // 邮编
email:"13124"
 

]
}
### Response:
{
"data": {
 
userId: 1
 
},
"errorCode": 0,
"errorMsg": "Success"
}




## 9.update user  status
###  url: POST /tenant/v1/user/update
### Request:
{
 
userId:1
 
    
status:1 //启用禁用状态 1：启用 0：暂停 2：作废
 
   
}
### Response:
{
"data": {
},
"errorCode": 0,
"errorMsg": "Success"
}


## 10.delete user  
###  url: GET /tenant/v1/user/delete/{userId}
### Request:
{

}
### Response:
{
"data": {
 
},
"errorCode": 0,
"errorMsg": "Success"
}