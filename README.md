build:
mvn clean install

deploy:
docker-compose up

run:
http://localhost:8888/detailservice/?cell_id=11111
