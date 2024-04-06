
# Post Board Project

A simple but effectively functioning post board application. Developed with JAVA 17 and implements mariaDB. It saves data on a local MySQL workbench. 

User can be verified using the JWT token and freely sign-up, login and logout. Using the token, the user can register posts and comments, and obviously getting his sign-up data, posts and comments that the user himself registered. The user can logout as the token will be saved in the blacklist.

The user can have one of two roles, "USER" or "ADMIN". The user table and role entity is connected with the user role table with user_id and role_id. The role can be changed, and the user will have different authroization according to the roles. 

The posts are organized in the post table, and the comments in the comment table accordingly. The post is connected to the comments with the ids. 

Regards getting posts, we can request for all posts that have implemented pageable and get partly information about a post, or can request for the whole post also getting comments that are tagged along with the post. Also, it is available to request for certain posts that contain "keyword". The application will return posts that contain "keyword" in their title or content. The "keyword" can be uppercase or lowercase. 

The application also comes with CRUD functions not only for registering and getting posts, comments but also for updating and deleting them. Also, we can count the number of likes that a certain post has recived. 


## ERD
<img width="860" alt="Screenshot 2024-04-02 at 13 59 44" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/485cd5cf-35dd-405a-8feb-c507ce47294b">

## Environment Variables

To run this project, you will need to add the following environment variables to your .yaml file

`DATABASE_USERNAME` local database username

`DATABASE_PASSWORD` local database password

`JWT_SECRET_KEY` jwt password 


## API Reference


#### Signup

```
  POST /auth/sign-up
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | **Required**. email |
| `password` | `String` | **Required**. password |
| `name` | `String` | **Required**. name |
| `phoneNumber` | `String` | **Required**. phoneNumber |


#### Login

```
  POST /auth/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | **Required**. email |
| `password` | `String` | **Required**. password |


#### Logout

```
  POST /auth/logout
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |


#### Get user sign-up information

```
  GET /user/my-page
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |

#### Get user posts

```
  GET /user/my-post
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |


#### Get user comments

```
  GET /user/my-comment
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |


#### Change user role

```
  PUT /user/changeRole
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |


#### Register Post

```
  POST /post/register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |
| `title` | `string` | **Required**. title |
| `content` | `string` | **Required**. content |

#### Get all posts

```
  GET /post/findAll
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `pageable`      | `pageable` |  |


#### Get one post with certain id

```
  GET /post/find/{postId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId`      | `Integer` |  id of a certain post to get|



#### Get post with keyword

```
  GET /post/find/{keyword}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `keyword`      | `String` |  keyword to search in title or content of a post|



#### Increase likes of a post

```
  POST /post/likes/{postId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |
| `postId`      | `Integer` |  id of a certain post to increase likes count by one|



#### Update Post

```
  PUT /post/update/{postId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |
| `postId`      | `Integer` |  id of a certain post to update|
| `title` | `string` | **Required**. title to update|
| `content` | `string` | **Required**. content to update|

#### Delete Post

```
  DELETE /post/delete/{postId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |
| `postId`      | `Integer` |  id of a certain post to delete|



#### Register Comment

```
  POST /comment/register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |
| `content` | `string` | **Required**. content |


#### Update Comment

```
  PUT /comment/update/{commentId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |
| `commentId`      | `Integer` |  **Required**. id of a certain comment to update|
| `content` | `string` | **Required**. content to update|

#### Delte comment

```
  DELETE /comment/delete/{commentId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `CustomUserDetails` | `token` | **Required**. Your JWT token |
| `commentId`      | `Integer` |  **Required**. id of a certain comment to delete|


## Result Screenshots

### USER API

#### Signup
<img width="555" alt="Screenshot 2024-04-05 at 17 38 21" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/adaab762-8d0b-465c-89c4-742ab44b72e4">

#### Login
<img width="540" alt="Screenshot 2024-04-05 at 17 38 57" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/6b31c9ae-779f-4329-8815-166f82628fd1">

#### Logout
<img width="553" alt="Screenshot 2024-04-05 at 17 39 23" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/63b4d5f5-2327-4a6f-aea7-74aea0e9a6c6">

#### Get user sign-up information
<img width="546" alt="Screenshot 2024-04-05 at 17 40 20" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/bb485a28-5222-47b1-b08e-fac1f0693807">

#### Get user posts
<img width="556" alt="Screenshot 2024-04-05 at 23 02 37" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/201fa87f-9b70-49c1-bb2d-4059d264c12a">

#### Get user comments
<img width="548" alt="Screenshot 2024-04-05 at 23 03 03" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/5b148cf1-f20b-4b96-9daf-bf57ec43d493">

#### Change user role
<img width="548" alt="Screenshot 2024-04-05 at 23 05 18" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/38a10378-11ec-4f6d-a0b3-4971fd78f419">

### POST API
#### Register Post
<img width="560" alt="Screenshot 2024-04-05 at 22 59 00" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/94e107e3-51a1-4ca3-bc2f-dd23f7728ab6">

#### Get all posts
<img width="598" alt="Screenshot 2024-04-05 at 23 04 09" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/3300d90c-eaa5-419f-b76d-402111b305ec">

#### Get one post with certain id
<img width="548" alt="Screenshot 2024-04-05 at 23 06 22" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/062bd772-f870-48fd-9772-eb63fa3aa816">

#### Get post with keyword
<img width="548" alt="Screenshot 2024-04-05 at 23 08 28" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/2902f36b-7eea-4bb4-88af-fe64702ef73a">

#### Increase likes of a post
<img width="640" alt="Screenshot 2024-04-05 at 23 11 48" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/6eb9e753-6067-4eca-97e3-bbebda46481a">

#### Update Post
<img width="646" alt="Screenshot 2024-04-05 at 23 12 47" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/138bb4cb-f04e-4cdb-bd13-f8a891b94348">

#### Delete Post
<img width="640" alt="Screenshot 2024-04-05 at 23 13 14" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/ac4f4b34-67ca-4fe7-9945-f1424858ead0">

### COMMENT API

#### Register Comment
<img width="552" alt="Screenshot 2024-04-05 at 23 01 42" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/ab5b3626-2a61-4d0c-a172-a603bb8d32ef">

#### Update Comment
<img width="650" alt="Screenshot 2024-04-05 at 23 13 56" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/defb07bc-38fa-4a1e-b868-dc2b14683994">

#### Delte comment
<img width="639" alt="Screenshot 2024-04-05 at 23 15 34" src="https://github.com/soheeparklee/sc_project01_April2024_verSoh/assets/97790983/6a07d559-469c-4509-81d6-cf8974f20c69">


## How to improve 
- Only the author of the post or comment should be able to modify the post or comment.Currently, all users who logged in can update or delete the comment or post
- Save JWT on redis.



