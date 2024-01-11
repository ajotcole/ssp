# Build Docker images for frontend and backend
install:
	docker build -t ssp_frontend ./ssp_frontend
	docker build -t ssp_backend ./ssp_backend 

# Run Docker container
start:
	docker-compose up -d

# Stop and remove Docker container
stop:
	docker-compose down 
