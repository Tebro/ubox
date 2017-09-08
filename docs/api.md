# API Documentation


## Registration

#### Request


Path: `/api/register`

Method: `POST`

Body:

    {
        "username": "<username>",
        "password": "<password"
    }



#### Responses

##### Ok

Body:
    
    {
        "code": "OK"
    }   
    
##### Username exists

Body:

    {
        "code": "BAD_REQUEST",
        "message": "Username in use"
    }
    
    
## Login

#### Request

Path: `/api/login`

Method: POST

Body:

    {
        "username": "<username>",
        "password": "<password"
    }
    
#### Responses

##### Ok

Body:

    {
        "code": "OK",
        "token": "<access_token>"
    }
    
##### Invalid credentials

Body:

    {
        "code": "NOT_FOUND",
        "message": "Invalid credentials"
    }
    
##### Server error

Body:
    
    {
        "code": "SERVER_ERROR"
    }
    
    
## User information

#### Request

Path: `/api/user`

Method: `GET`

URL Parameter: `token=<token>`

#### Response

Body:
    
    {
        "username": "<username>"
    }
