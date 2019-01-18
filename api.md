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
"role":"proxy" //分六种角色 每种角色看到的菜单按钮不一样 admin:系统管理员 、auditDepartment:审计部、engineeringDepartment:工程部、operator:操作员、proxyCompany:招标代理公司、commonCompany：投标公司
"userId": 4
"username": "admin"
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
"labelType":1  // 1:施工类企业 2:货物类 3：服务类
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
total:100
"companys":[
{
businessScope:"asdasd" //业务范围
companyId:1
companyName:"asdda"
companyQualification:"sadsadsa"//企业资质
legalPerson:"afasf" //法人
legalPersonIdentityNo:"afasf" //法人身份证号
legalPersonAuthorizer："sadasd" //法人授权人
authorizerPhoneNo:"sadsaf"//授权人手机号
authorizerEmail:"asdsadsa"//授权人邮箱
registerArea:"asfasfas" //注册地
registerCapital:"asfasf" //注册资本
addressNj:"asfsafas"// 驻宁地址
 
status:1 //启用禁用状态 1：启用 0：暂停 2：暂停中 3：注销
audit_status：0 // 审核状态  0:未提交审核 1:待审核 1：审核通过 2：审核不通过
unifiedSocialCreditCode:"asdasd" //社会统一信用代码
remark:"阿斯顿撒"  //多行文本区域(备注)
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
role:"proxy"
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
role:"proxy"
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
legalPersonAuthorizer："sadasd" //法人授权人
authorizerPhoneNo:"sadsaf"//授权人手机号
remark:"阿斯顿撒"  //多行文本区域(备注)
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
--1.第一部分是政务部门内部操作员更新的字段
companyId:1
companyName:"asdda"
legalPersonAuthorizer："sadasd" //法人授权人
authorizerPhoneNo:"sadsaf"//授权人手机号
remark:"阿斯顿撒"  //多行文本区域(备注)
unifiedSocialCreditCode:"asdasd" //社会统一信用代码
labelIds:[   //公司包含的职能标签
 11,22,33
]


--2.第二部分是企业自己更新的字段
businessScope:"asdasd" //业务范围
companyQualification:"sadsadsa"//企业资质
legalPerson:"afasf" //法人
legalPersonIdentityNo:"afasf" //法人身份证号
authorizerEmail:"asdsadsa"//授权人邮箱
registerArea:"asfasfas" //注册地
registerCapital:"asfasf" //注册资本
addressNj:"asfsafas"// 驻宁地址




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
total:100
"users":[
{
role:"proxy"
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
contactNo:"2141224" // 办公电话
address:"asdsad" // 地址
identityNo:"21415125" // 身份证号
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




## 9.update user
###  url: POST /tenant/v1/user/update
### Request:
{
userId:1
userName:"sadas" //登陆账号
realName:"sadsa" //姓名
contactNo:"2141224" // 办公电话
address:"asdsad" // 地址
identityNo:"21415125" // 身份证号
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