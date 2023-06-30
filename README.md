# News REST app
### Features
---
- SpringBoot
- Java 17
- Maven
- PostgreSQL
- REST API

### About
---
Application for demo.

### API
---
| Method | URL | Description | Request | Response |
| ------ | ------ | ------ | ------ | -------- |
| GET | /api/news/list | Get news list of existing records | | List<NewsResponseDto> | 
| POST| /api/news/save | Save news | NewsRequestDto | |
| GET | /api/news/items/{newsId} | Get news by id | | NewsResponseDto
| PUT | /api/news/items/{newsId} | Update news | NewsId, NewsRequestDto |
| DELETE | /api/news/items/{newsId} | Delete news | NewsId
| GET | /api/users/list | Get users list | | List<UserResponseDto>
| POST | /api/users/save | Save new user| UserRequestDto | |
| GET | /api/users/items/{userId} | Get user by id | | UserResponseDto
| PUT | /api/users/items/{userId} | Update user | UserId, UserRequestDto |
| POST | /api/public/sms | Init sms by phone | SmsConfRequestDto |
| POST | /api/public/validate | Validate sms code | CodeConfRequestDto | CodeConfResponseDto
| POST | /api/public/login | Log in with username and password | LoginRequestDto | LoginResponseDto

### Installation
---
1. Download source
2. Create database **news** in PostgreSQL
3. All needed file placed in dir **"jar"**
4. Change needed variable in **"application.yml"** (etc. db_name, db_login, db_password, port)
4. Run archive with command **java -jar news-0.0.1-SNAPSHOT.jar --spring.config.location=_path_/application.yml**
5. The application will run on port , ex. 8050

### How to use
---
After run application, you need send REST request via, e.g. Postman.

### DTO description
``` cpp
NewsResponseDto {
    private Long id;
    private String name;
    private String description;
    private String body;
    private String imagePath;
    private String newsStatus;
}
```

``` cpp
NewsRequestDto {
    private String name;
    private String description;
    private String body;
    private String imagePath;
    private String newsStatus;
}
```

``` cpp
UserRequestDto {
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String userStatus;
    private String roleName;
}
```

``` cpp
UserResponseDto {
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String userStatus;
    private String roleName;
}
```

``` cpp
SmsConfRequestDto {
    private String phone;
}
```

``` cpp
CodeConfRequestDto {
    private String phone;
    private String code;
}
```

``` cpp
CodeConfResponseDto {
    private String sessionId;
}
```

``` cpp
LoginRequestDto {
    private String email;
    private String password;
    private String sessionId;
}
```

``` cpp
LoginResponseDto {
    private Long userId;
    private String email;
    private String token;
}
```

### Login logic

1. You need init send sms **POST /api/public/sms** - as result you get **SMS CODE**
2. Validate sms **POST /api/public/validate** - as result you get **SESSION_ID**
3. Login **POST /api/public/login** - as result you get **Bearer JWT TOKEN**
>eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb2RlcmF0b3JAbWFpbC5jb20iLCJyb2xlcyI6Ik1PREVSQVRPUiIsImlhdCI6MTY4ODA3OTg1MiwiZXhwIjoxNjg4MDgzNDUyfQ.LPvZlJ3VPrLSxkU__qVlZnmPGWgPqM5iEHK9D1LEUpo)