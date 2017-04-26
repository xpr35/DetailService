build:
mvn clean install
(if mysql database not set up yet, use -DskipTest) 

deploy:
docker-compose build
docker-compose up

run:
http://localhost:8888/detailservice/?cell_id=11111
