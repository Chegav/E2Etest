# Automatizacion E2E  

## Pasos para Ejecutar la Prueba

1. **Clonar el Repositorio**:    
-Abro una terminal o línea de comandos.  
-Ejecuto el siguiente comando para clonar el repositorio desde GitHub:
     ```bash
     git clone https://github.com/Chegav/TestE2E.git
     ```

2. **Navegar al Directorio del Proyecto**:  
-Cambio al directorio del proyecto clonado:
     ```bash
     cd Compra
     ```
3. **Abrimos terminal de Visual Studio**:  
-Abrimos visual estudio y ejecutamos un terminal en el cual ingresamos lo siguiente: 
    
     ```bash
     mvn test -D"Param_environment=qa" -D"cucumber.filter.tags=@01a" -D"noHeadless=true"
     ```
aqui se ejecutará la prueba instalandose las dependencias necesarias para ejecutarse

4. **Instalar las Dependencias (SI ES NECESARIO)**:  
-Me aseguro de que `pip` esté instalado.  
-Ejecuto el siguiente comando para instalar las dependencias necesarias:
     ```bash
     pip install selenium
     ```

