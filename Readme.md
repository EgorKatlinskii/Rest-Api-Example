# Spring-Rest

REST API for based on <a href="https://github.com/spring-projects/spring-boot">Spring Boot 2.5.6

Supports Java 11 and above.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/EgorKatlinskii/TestTaskNtiTeam.git
```

**2. Create Mysql database**
```bash
create database LordsProject;
```
**3. Create table lords**
```bash
create table lords(
lord_id   int auto_increment primary key,
lord_name varchar(25) charset utf8 not null,
lord_age  int not null                 
);
```
**4. Create table planets**
```bash
create table planets(
    planet_id int auto_increment primary key,
    planet_name varchar(25) charset utf8 not null,
    lord_id int null,
    constraint lord_id 
      foreign key (lord_id) references lords (lord_id)
);
```
**5. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

## REST Endpoints

The following REST endpoints are available:

### Lord

| HTTP Method  | Path                       | Description  |
|--------------|----------------------------|--------------|
| GET          | /getLords                  | All Lords     |
| GET          | /getYoungestLords/{count}  | Top of the youngest overlords (count parameter determines the number of overlords)  |
| GET          | /getIdlers                 | List of Lords of No planets
| POST         | /saveLord                  | Creation of the Lord 
### Planet

| HTTP Method  | Path                   | Description  |
|--------------|------------------------|--------------|
| POST         | /savePlanet            |  Creation of the Planet           |
| DELETE       | /deletePlanet/{id}    | Delete planet |
| PUT          | /addLord/{lordId}/{planetId}| Appointment of the Planet to the Lord |

