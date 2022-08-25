# Task Management
Task Management App for Surjomukhi Ltd.

# Create PostgresDB in Local Machine

- $ sudo su - postgres
- $ psql
- postgres=# 


- postgres=# CREATE USER mehrabratul WITH PASSWORD '700800900.';
- postgres=# CREATE DATABASE tmsdb;
- postgres=# GRANT ALL PRIVILEGES ON DATABASE tmsdb to mehrabratul;
- postgres=# \q

# Swagger - API Documentation

-  http://localhost:9001/swagger-ui.html

# API Lists

- http://localhost:9001/api/v1/player/add
    # Request Body
{
"playerName": "mehrab",
"playerBio": "Right Hand Batsman and Spin Bowler"
}
- http://localhost:9001/api/v1/task/add
- http://localhost:9001/api/v1/task/assignTask
  # Request Body
[
{
"startDate": "2022-08-24T03:49:13.933Z",
"endDate": "2022-08-26T03:49:13.933Z",
"player": {
"id": 1
},
"task": {
"id": 2
}
},
{
"startDate": "2022-08-24T03:49:13.933Z",
"endDate": "2022-08-26T03:49:13.933Z",
"player": {
"id": 3
},
"task": {
"id": 4
}
}
]
- http://localhost:9001/api/v1/task/getTaskByPlayerId
    # Request Body
{
"id": 1
}
- http://localhost:9001/api/v1/task/getTaskByTimeRange
    # Request Body
{
"startDate": "2022-08-24T03:40:13.933Z",
"endDate": "2022-08-26T05:00:13.933Z",
"pageNo": 0,
"size": 10
}