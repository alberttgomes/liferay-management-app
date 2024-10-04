# How to do Portal Management's Setup 

  ## Requirement
   - Blade
   - Docker
   - Gradle
   - Java11

  ## Artifacts
  - Download dump from [Drive]("[https://?](https://drive.google.com/drive/folders/119ru6sVE5WrFPiHwVzfaK-j5ELtqvFOC)")
  - Download document library from [Drive]("https://drive.google.com/drive/folders/16zYwNSLNeX9PHvIBtbVykoANGiucUZ3j")

  ## Prepare Environment
  - Put dump file into /management-env/database/
  - Put document library into /management-ent/liferay/data/

  ## Deploy
   - Go to the modules folder and run the command:
     ```
       blade gw clean deploy
     ```
      
  ## Up
  - Back to the root project folder and run the command:
    ```
      docker compose -p management up --b  build -d
    ```
    and
    ```
      docker compose logs -f liferay
    ```

  
  ## Access
  - User Portal Admin

        Email: test@liferay.com
        Password: test1

  - User Employee

        Email: albert.gomes@management.br
        Password: batman1
  
