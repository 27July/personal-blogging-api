<h1 align="center">Personal Blogging API ✍🏼</h1>

## About:
Personal Blogging API is a lightweight RESTful backend powering a simple personal blogging platform. 
It includes a full CRUD for articles with ownership enforcement. The whole stack is dockerised and runs with `docker compose up`. 

## Technology and Tools:
[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-%23C71A36.svg?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23316192.svg?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-%230db7ed.svg?logo=docker&logoColor=white)](https://www.docker.com/)

## Gallery:
<p align="left">
  <img src="gallery/personalBloggingApi.png" alt="Actuator Pic" width="300" style="margin-right: 10px;" />
</p>

## Running the Application:
### Prerequisites:
Make sure you have the following installed:
- Docker Desktop (includes Docker Compose)
- Git

### Setup Instructions:
```bash
# 1) Clone the repo
git clone https://github.com/27July/personal-blogging-api.git
cd personal-blogging-api

# 2) Create your environment file
cp .env.local .env

# 3) Start the API + Postgres
docker compose up --build
```

## Features:
- User registration + login (basic token auth, NOT SECURE)
- Create articles (requires auth)
- List articles (supports filtering by current user)
- Get article by ID
- Update/Delete articles with ownership enforcement
- Clean error handling with proper HTTP statuses (400/401/403/404/409)

## API endpoints:
- POST /api/users/register
- POST /api/users/login
- POST /api/articles (requires Token)
- GET /api/articles
- GET /api/articles?mine=true (requires Token)
- GET /api/articles/{id}
- PUT /api/articles/{id} (requires Token, author only)
- DELETE /api/articles/{id} (requires Token, author-only)

## Made with ❤️ by [Wee Zi Hao](https://github.com/27July)
[![GitHub](https://img.shields.io/badge/GitHub-27July-181717?logo=github&logoColor=white)](https://github.com/27July)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Wee%20Zi%20Hao-%230077B5?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/wee-zi-hao)
[![Email](https://img.shields.io/badge/Email-weezihao@gmail.com-D14836?logo=gmail&logoColor=white)](mailto:weezihao@gmail.com)
[![Personal Site](https://img.shields.io/badge/Website-Wee%20Zi%20Hao-1E40AF?logo=aboutdotme&logoColor=white)](https://27july.github.io/)

