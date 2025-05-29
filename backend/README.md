TO RUN WITH MYSQL YOU NEED MYSQL AND WORKBENCH FOR DASHBOARD

1. Create a db schema called "grievance_db"
   
2. No need to create any table (spring will do it for us)
   
3. In application.prop change your username and password for 

   spring.datasource.username=root,
   spring.datasource.password=
   as per your system 

4. Just run  mvn spring-boot:run
