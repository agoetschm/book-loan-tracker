all: back

front:
	cd frontend; npm run build; cp -r dist/* ../backend/src/main/resources/static

dev: front
	cd backend; ./gradlew bootRun

back: front
	cd backend; ./gradlew build

run: back
	java -jar backend/build/libs/backend-0.0.1-SNAPSHOT.jar
	
