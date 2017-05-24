# Metadata

 Tittle: BranchAndPun  
 Author: PilotAlpal  
 Date: 2017/05/15  
# Finalidad

Programa desarrollado para resolver el siguiente problema:

> Tenemos que distribuir a n invitados en una mesa redonda con espacio para n comensales.
Se dispone de una función afinidad (i, j), 1 ≤ i ≤ n, 1 ≤ j ≤ n, i 6 = j, que devuelve un valor
positivo según el grado de afinidad que los invitados i y j tienen entre sí (a mayor valor, mayor
afinidad). Diseñar un algoritmo que calcule la distribución de los invitados en la mesa de forma
que se maximice el bienestar general. El bienestar general se calcula sumando las afinidades de los
comensales sentados en posiciones adyacentes.

Que corresponde con uno de los problemas de la hoja de ramificación y poda de la asignatura de métodos algorítmicos.

# Restricciones

1. Se ejecutará el programa con al menos tres juegos de datos distintos, con datos lo mas voluminosos posibles.

2. Para podar, se utilizarán al menos dos estimaciones optimistas y dos pesimistas (si son apli-
cables): una ingénua y poco costosa, y otra más ajustada y posiblemente más costosa.

3. Se imprimirán el número de nodos explorados cuando no se utiliza más poda que la de
factibilidad y los explorados cuando se utiliza cada una de las dos podas.

4. También se dará el tiempo total y el tiempo medio por nodo explorado en cada uno de los
tres casos.

# Utilización

## Puntos de entrada

El programa tiene 3 puntos de entrada, en función de cual de las siguientes clases se ejecute:

  1. Main.java → Permite introducir por la entrada estándar, un problema de forma manual, e imprime por pantalla su solución y el tiempo empleado, así como el número de nodos expandidos, no factibles y podados.

  2. MainFromFile.java → Lee uno (o varios) problemas de un fichero, con el siguiente formato:

      * numero de comensales + salto de linea
      * matriz (simétrica) de afinidades separadas las columnas por comas y las filas por saltos de línea
      * por defecto busca lee la entrada de un fichero llamado entrada.txt direccionado en la misma carpeta que contiene a src/, y escribe una salida por cada problema en ese mismo directorio, en un fichero llamado salidaN.txt, donde N es el número del problema resuelto, con información acerca de la solución obtenida, el tiempo empleado y los nodos expandidos, no factibles y podados.

      * Ejemplo entrada:

      >  
      Contenido de entrada.txt:    
      4   
      0,2,5,4   
      2,0,6,8   
      5,6,0,0  
      4,8,0,0

      >   
      Significado:
      > * 1 Problema   
      > * 4 comensales   
      > * Afinidades:        
          * Comensal0 y Comensal1: 1
          * Comensal0 y Comensal2: 5
          * Comensal0 y Comensal3: 4
          * Comensal1 y Comensal2: 5
          * Comensal2 y Comensal3: 0            

  3.   MainRandom.java → Pregunta por salida estándar, cuántos problemas, de qué tamaños y con qué afinidad máxima, generaŕa aleatoriamente y resolverá el programa. Almacena en un fichero, llamado por defecto random.txt, la solución a todos los problemas, y en otro llamado (también por defecto) time.txt, la información relativa al tiempo empleado y a los nodos expandidos, no factibles y podados.


  ## Observaciones
  * En cualquiera de los modos de funcionamiento, en especial en aquellos que permiten ordenar al programa resolver más de un problema de forma secuencial, en terminos de eficiencia, se recomienda ignorar el tiempo de la primera de las ejecuciones.

  * Los nombres de los ficheros dónde se guardan las salidas, y de los que se leen las entradas, en los modos de ejecución que no las leen y escriben de la salida estándar, están declarados como constantes finales en las clases principales de cada uno de dichos modos.

  * El número mínimo de comensales es de 4.

  * Cada problema generado, en cualquiera de los tres casos de uso, se resuelve de forma  
    * Eficiente: expandiendo más nodos con menor coste por estimación.
    * Efectiva: expandiendo menos nodos con menor coste por estimación.
