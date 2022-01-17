# CourseMutualSelection
课程双选系统

## 后端技术

| 技术       |                 |
| ---------- | --------------- |
| 核心框架   | Spring Boot     |
| 安全框架   | Spring security |
| Token 认证 | JWT             |
| 持久层框架 | MyBatisPlus     |
| Java版本   | JDK8            |

## 需求分析

### 用户端

#### 学生

**登录：**

1. 登录
2. 注销

**个人信息：**

1. 修改

**选题：**

1. 浏览选题
2. 选题
3. 查看已选选题

**Q:**其实我觉得查看已选选题和浏览选题可以放一个页面的没必要分开

### 管理后台

**登录：**

1. 登录
2. 注销

**个人信息：**

1. 修改

#### 教务员

导入学生信息

#### 系主任



#### 教师

**选题：**

1. 添加选题
2. 修改选题
3. 选择学生

## 设计数据库表

#### 学生

**学生信息表（cms_user）**

| **字段**            | **字段名称** | **数据类型** |
| ------------------- | ------------ | ------------ |
| id                  | 主键         | bigint       |
| account             | 账号-学号    | varchar(64)  |
| nickname            | 姓名         | varchar(255) |
| sex                 | 性别         | bit(1)       |
| grade               | 年级         | bit(1)       |
| professional_class  | 专业班级     | varchar(255) |
| avatar              | 头像         | varchar(255) |
| email               | 邮箱         | varchar(128) |
| mobile_phone_number | 手机号       | varchar(20)  |
| password            | 密码         | varchar(64)  |
| salt                | 加密盐       | varchar(255) |
| status              | 状态         | varchar(255) |
| last_login          | 最后登录时间 | bigint       |
| create_user_id      | 创建人       | bigint       |
| create_time         | 创建时间     | bigint       |
| last_user_id        | 最后修改人   | bigint       |
| last_time           | 最后修改时间 | bigint       |
| deleted             | 是否删除     | bit(1)       |

**选题表(cms_topic_information)**

| **字段**       | **字段名称** | **数据类型**  |
| -------------- | ------------ | ------------- |
| id             | 主键         | bigint        |
| teacher_id     | 教师id       | bigint        |
| topic          | 题目         | varchar(128)  |
| english_topic  | 英文题目     | varchar(128)  |
| description    | 简介         | varchar(1024) |
| note           | 备注         | varchar(128)  |
| create_user_id | 创建人       | bigint        |
| create_time    | 创建时间     | bigint        |
| last_user_id   | 最后修改人   | bigint        |
| last_time      | 最后修改时间 | bigint        |
| deleted        | 是否删除     | bit(1)        |

**学生选题表(cms_user_topic)**

| **字段**       | **字段名称**                   | **数据类型** |
| -------------- | ------------------------------ | ------------ |
| id             | 主键                           | bigint       |
| user_id        | 学生id                         | bigint       |
| topic_id       | 选题id                         | bigint       |
| topic_status   | 选题状态(待确认、驳回、已确认) | bit(1)       |
| create_user_id | 创建人                         | bigint       |
| create_time    | 创建时间                       | bigint       |
| last_user_id   | 最后修改人                     | bigint       |
| last_time      | 最后修改时间                   | bigint       |
| deleted        | 是否删除                       | bit(1)       |

**管理员信息表(cms_admin)**

| **字段**            | **字段名称**                 | **数据类型** |
| ------------------- | ---------------------------- | ------------ |
| id                  | 主键                         | bigint       |
| role                | 角色（教务员、系主任、教师） | bit(1)       |
| account             | 账号-工号                    | varchar(64)  |
| nickname            | 姓名                         | varchar(255) |
| sex                 | 性别                         | bit(1)       |
| avatar              | 头像                         | varchar(255) |
| email               | 邮箱                         | varchar(128) |
| mobile_phone_number | 手机号                       | varchar(20)  |
| password            | 密码                         | varchar(64)  |
| salt                | 加密盐                       | varchar(255) |
| status              | 状态                         | varchar(255) |
| last_login          | 最后登录时间                 | bigint       |
| create_user_id      | 创建人                       | bigint       |
| create_time         | 创建时间                     | bigint       |
| last_user_id        | 最后修改人                   | bigint       |
| last_time           | 最后修改时间                 | bigint       |
| deleted             | 是否删除                     | bit(1)       |

**Q:**是否需要为不同专业老师怎加有关专业等的字段？
