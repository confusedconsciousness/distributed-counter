# distributed-counter
A service to register likes, can be extended for views as well.  The service lacks few checks such as only one user can like a media, will be incorporating those checks in future builds.

To run this application, you need to have docker installed on your machine.
Follow the steps below to run the application:
1. Clone the repository:
   ```bash
   git clone https://github.com/confusedconsciousness/distributed-counter.git
   
2. Navigate to the project directory:
   ```cd distributed-counter```
3. Build the Docker image:
   ```docker compose up -d --build```
4. To view the logs:
   ```docker compose logs -f```
5. To stop the application:
   ```docker compose down```
6. To check the no of likes for a media:
   ```curl http://localhost:8000/likes/<content-id>```



