# How to do Portal Management's Setup 

  ## Requirement
   - Blade
   - Docker
   - Gradle
   - Java11

  ## Prepare Environment

  - Run the command:
  
    ```
    blade gw initBundle
    ```

  ### Document Library

  - Download document library: [DL](https://drive.google.com/drive/folders/16zYwNSLNeX9PHvIBtbVykoANGiucUZ3j)
  
  - Replace the `document_library` directory in `bundles/data/document_library` with the Document Library downloaded.
  
  ### Database
  
  - Download dump file from: [DUMP](https://drive.google.com/drive/folders/119ru6sVE5WrFPiHwVzfaK-j5ELtqvFOC)

  - Put int `/docker-compose/database/dump/` directory
  
  ## Deploy
  
   - Go to the modules folder and run the command:
   
     ```
       blade gw clean deploy
     ```
      
  ## Up The Portal
  
  - Back to the root project folder and run the command:
    ```
    
      docker compose -p management up --b  build -d
    ```

    and
    
    ```
      docker compose logs -f liferay
    ```

  
  ## Access to the Portal

  - User Portal Admin

        Email: test@liferay.com
        Password: test1

  ## Users for business rule:

  1. Human-Resources Department User:
      
          Email: mariah.silva@liferay.com
          Password: test
  
  2. Manager Employee User:
  
          Email: albert.gomes@liferay.com
          Password: batman1
  
  3. User Employee
  
          Email: mario.pereira@liferay.com
          Password: robbin
  
