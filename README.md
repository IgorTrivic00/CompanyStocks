Komande za pokretanje aplikacije
1. mvn clean package
2. Pre komande   docker-compose up --build 
Gašenje i potpuno uklanjanje svega sto je docker-compose up napravio.
docker-compose down -v
3. docker-compose up --build 

Podaci su uneti u tabele Company i Stock


Napravio sam test metodu izracunavanje profita akcija ali u lokalu. Ako zelite da testirate preko postmana:
http://localhost:8081/api/stocks/profit
Json parametri:

{
  "symbol": "EPL",
  "fromDate": "2018-04-20",
  "toDate": "2018-04-30"
}

Sto se tice CSV fajlova, za konkretan zadatak potrebne su kolone date i close pa sam insertovao samo njih. 
