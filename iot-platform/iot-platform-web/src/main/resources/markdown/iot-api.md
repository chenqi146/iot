

# 接口文档

## 用户模块

### 登陆



```
POST /user/login
```

```
参数
loginName: string
password: string(前端Base32加密)
```

```json
{
    "message": "成功",
    "status": 200,
    "body": {
        "token": "cqcqqcqcqcqcq"
    }
}
```



### 登出



```
POST /user/logout
```

#### 参数

```
无
```

```json
{
    "message": "成功",
    "status": 200,
    "body": {
    }
}
```

### 创建用户



```
POST /user/create
```

#### 参数

```json

{
    "userName": "张三",
    "loginName": "cqmike",
    "password": "sdajkqwj(Base32加密)",
    "email": "123213@qq.com",
}
```

```json
{
    "message": "成功",
    "status": 200,
    "body": {
    }
}
```



------



## 产品模块

### 产品列表



```
GET /procudts
```

#### 参数

```
name: string
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
    "content": [
        {
            "id": "wqd3sdf23",
            "name": "摄像头",
            "type": "DEVICE",
            "typeName": "设备",
            "createTime": "2020-03-23 14:00:00"
        },
        {
            "id": "wqd3sdf23",
            "name": "摄像头",
            "type": "DEVICE",
            "typeName": "设备",
            "createTime": "2020-03-23 14:00:00"
        }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 2,
      "pageNumber": 1,
      "pageSize": 2,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "number": 1,
    "size": 2,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 0,
    "first": false,
    "empty": true
  }
}
```



### 产品详情



```
GET /procudt/{id}
```

#### 参数

```
id: string
```

```json
{
    "message": "成功",
    "status": 200,
    "body":{
        "id": "wqd3sdf23",
        "name": "摄像头",
        "type": "DEVICE",
        "typeName": "设备",
        "vendor": "海康威视",
        "model": "HK",
        "accessType": "SOCKET",
        "accessTypeName": "Socket接入",
        "deviceNumber": 23,
        "description": ""
     }
}
```



### 产品Topic列表



```
GET /rules
```

#### 参数

```
id: string
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
    "content": [
        {
            "id": "wqd3ssaddf23",
            "name": "流转规则",
            "type": "CIRCULATION",
            "typeName": "流转",
            "status": "ENABLE",
            "statusName": "启用",
            "topic": "qqq",
            "description": "撒大青蛙是"
        },
        {
            "id": "wqd3sdfww23",
            "name": "流转规则",
            "type": "CIRCULATION",
            "typeName": "流转",
            "status": "DISABLE",
            "statusName": "禁用",
            "topic": "ccc",
            "description": "撒大青蛙"
        }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 2,
      "pageNumber": 1,
      "pageSize": 2,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "number": 1,
    "size": 2,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 0,
    "first": false,
    "empty": true
  }
}
```



### 产品属性列表



```
GET /productProperties
```

#### 参数

```
id: string
name: string
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
    "content": [
        {
            "id": "wqd3ssaddf23",
            "name": "流转规则",
            "type": "CIRCULATION",
            "typeName": "流转",
            "identifier": "temperature",
            "value": "qqq",
            "unit": "摄氏度/℃"
        },
        {
            "id": "wqd3sdfww23",
            "name": "流转规则",
            "type": "CIRCULATION",
            "typeName": "流转",
            "identifier": "water",
            "value": "ccc",
            "unit": "立方米/m³"
        }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 2,
      "pageNumber": 1,
      "pageSize": 2,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "number": 1,
    "size": 2,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 0,
    "first": false,
    "empty": true
  }
}
```



### 产品解析脚本详情



```
GET /parsers
```

#### 参数

```
productId: string
```

```json
{
    "message": "成功",
    "status": 200,
    "body":{
        "id": "wqd3sdf23",
        "script": "function() { var a = 'asdsa';  return a;}"
     }
}
```



## 设备模块

### 设备列表

```
GET /devices
```

#### 参数

```
name: "摄像头"
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
    "content": [
        {
            "id": "asdqwlkd",
            "name": "摄像头1号",
            "productName": "摄像头",
            "status": "ONLINE",
            "statusName": "在线",
            "lastOnlineTime": "2020-03-24 14:00:00"
        },
        {
            "id": "asdsdqwlkd",
            "name": "摄像头1号",
            "productName": "摄像头",
            "status": "ONLINE",
            "statusName": "在线",
            "lastOnlineTime": "2020-03-24 14:00:00"
        }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 2,
      "pageNumber": 1,
      "pageSize": 2,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "number": 1,
    "size": 2,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 0,
    "first": false,
    "empty": true
  }
}
```



### 设备详情

```
GET /device
```

#### 参数

```
id: string
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
      "id": "asdqwlkd",
      "sn": "ssss",
      "name": "摄像头1号",
      "productName": "摄像头",
      "status": "ONLINE",
      "installationDate": "2020-02-02 14:00:00",
      "installationLocation": "深圳市",
      "statusName": "在线",
      "lastOnlineTime": "2020-03-24 14:00:00"
  }
}
```



### 子设备列表

```
GET /child/devices
```

#### 参数

```
parentId: "sad2dsa2"
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
    "content": [
        {
            "id": "asdqwlkd",
            "name": "摄像头1号",
            "parentProductName": "摄像头",
            "parentDeviceName": "",
            "status": "ONLINE",
            "statusName": "在线",
            "lastOnlineTime": "2020-03-24 14:00:00"
        },
        {
            "id": "asdsdqwlkd",
            "name": "摄像头1号",
            "parentProductName": "摄像头",
            "parentDeviceName": "",
            "status": "ONLINE",
            "statusName": "在线",
            "lastOnlineTime": "2020-03-24 14:00:00"
        }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 2,
      "pageNumber": 1,
      "pageSize": 2,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "number": 1,
    "size": 2,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 0,
    "first": false,
    "empty": true
  }
}
```



### 属性上报列表

```
GET /records
```

#### 参数

```
deviceId: "asd2"
startTime: "2002-02-02 14:00:00"
endTime: "2003-02-02 14:00:00"
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
    "content": [
        {
            "id": "asdqwlkd",
            "dataJson": "{'name': 'asdsada'}",
            "receiveTime": "2020-03-24 14:00:00"
        },
        {
            "id": "asdqwlkd",
            "dataJson": "{'name': 'asdsada'}",
            "receiveTime": "2020-03-24 14:00:00"
        }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 2,
      "pageNumber": 1,
      "pageSize": 2,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "number": 1,
    "size": 2,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 0,
    "first": false,
    "empty": true
  }
}
```





## 规则模块

### 规则列表

```
GET /rules
```

#### 参数

```
name: "sadsa"
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
    "content": [
        {
            "id": "asdqwlkd",
            "name": "摄像头1号",
            "type": "CIRCULATION",
            "typeName": "流转",
            "status": "ENABLE",
            "statusName": "启用",
            "topic": "wdqd",
            "description": "dwqdwqd",
            "middlewareType": "KAFKA",
            "productName": "摄像头"
        },
        {
            "id": "asdqwlkd",
            "name": "摄像头1号",
            "type": "CIRCULATION",
            "typeName": "流转",
            "status": "ENABLE",
            "statusName": "启用",
            "topic": "wdqd",
            "description": "dwqdwqd",
            "middlewareType": "KAFKA",
            "productName": "摄像头"
        }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
      },
      "offset": 2,
      "pageNumber": 1,
      "pageSize": 2,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "number": 1,
    "size": 2,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "numberOfElements": 0,
    "first": false,
    "empty": true
  }
}
```



### 规则详情

```
GET /rule/{id}
```

#### 参数

```
id: string
```

```json
{
  "code": "200",
  "message": "成功!",
  "body": {
      "id": "asdqwlkd",
      "name": "摄像头1号",
      "type": "CIRCULATION",
      "typeName": "流转",
      "status": "ENABLE",
      "statusName": "启用",
      "topic": "wdqd",
      "description": "dwqdwqd",
      "middlewareType": "KAFKA",
      "productName": "摄像头"
  }
}
```

