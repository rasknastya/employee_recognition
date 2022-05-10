.PHONY : create-db-and-user remove-db-and-user build test start run

create-db-and-user:
	sudo -u postgres psql -f create-user-and-db.sql

remove-db-and-user:
	sudo -u postgres dropdb books_shop ; sudo -u postgres dropuser badmin

build:
	mvn clean package

test:
	mvn test

start: build run

run:
	java -jar ./target/employee_recognition-*.jar