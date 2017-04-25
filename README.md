build:
mvn clean install
(if mysql database not setted up, use -DskipTest) 

deploy:
docker-compose up

run:
http://localhost:8888/detailservice/?cell_id=11111
