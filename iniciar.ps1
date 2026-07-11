Write-Host ""
Write-Host "========================================"
Write-Host " Iniciando Ferreteria Microservicios"
Write-Host "========================================"
Write-Host ""

# Eureka
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-eureka'; mvn spring-boot:run"

Start-Sleep -Seconds 15

# Auth
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-auth'; mvn spring-boot:run"

Start-Sleep -Seconds 5

# Clientes
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-clientes'; mvn spring-boot:run"

Start-Sleep -Seconds 3

# Productos
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-producto'; mvn spring-boot:run"

Start-Sleep -Seconds 3

# Inventarios
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-inventarios'; mvn spring-boot:run"

Start-Sleep -Seconds 3

# Pedidos
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-pedidos'; mvn spring-boot:run"

Start-Sleep -Seconds 3

# Pagos
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-pagos'; mvn spring-boot:run"

Start-Sleep -Seconds 3

# Reportes
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\ms-reportes'; mvn spring-boot:run"

Start-Sleep -Seconds 3

# API Gateway
Start-Process powershell -ArgumentList "-NoExit","-Command","cd 'C:\Proyectos\ferreteria-microservicios\api-gateway'; mvn spring-boot:run"

Write-Host ""
Write-Host "Todos los microservicios fueron lanzados."