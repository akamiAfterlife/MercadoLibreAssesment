1. PARA EJECUTAR EL PROGRAMA DE PRUEBA.
El siguiente programa permite crear un archivo XML de un poco más de 500 MB con la siguiente estructura. 
Es un programa en consola.

<feed><row><title>Título 1</title><description>Description 1</description> </row><row><title>Título 2</><description>Description 2</description> </row><row><title>Título 3</><description>Description 3</description> </row>.... .... ....</feed>

1.1 Ejecución sin argumentos

El nombre del archivo es feed.xml y se creará, en la carpeta
en donde se corra el programa.

Conforme se va creando el archivo, el programa envía un mensaje que indica cuanta memoria se está usando.

Al terminar de escribir el archivo, el programa enviará un mensaje que indique que ha llegado el fin de la escritura,
e inmediatamente comenzará a desplegar los resultados.

1.2 Ejecución con un argumento
Se especificará el nombre del archivo con extensión

1.3 Ejecución con dos argumentos
El pirmer argumentos es el nombre del archivo con extensión, y el segundo es el tamaño del archivo

1.4 Se incluye una pequeña muestra de un archivo generado por el programa, con un peso de 1 MB, con el nombre de feed1.xml

2. SOBRE EL CÓDIGO

2.1. El código fue creado utilizando Netbeans, así que el proyecto tiene la estructura de Netbeans, por lo tanto podrá encontrar los archivos fuente dentro de la carpeta MercadoLibreAssesment/src.

El código está empaquetado en com.MercadoLibre.Mimb.XMLFeed, así que deberá abrir la ruta src/com/MercadoLibre/Mimb/XMLFeed para ver las clases

2.2 SOBRE LA CLASE PRINCIPAL. La clase main se encuentra en el archivo MercadoLibreAssesment.java, dentro de la clase hay comentarios, si usted quiere cambiar algunos parámetros de la prueba, como el nombre del archivo o el tamaño del archivo.

2.2 Clase FeedWriter. Es una clase singleton que permite escribir archivos xml del tipo presentado al inicio, de diferentes tamaños, y diferentes nombres.
ES EL CÓDIGO QUE SE SOLICITÓ ESCRIBIR EN LA PRIMERA PARTE DEL EJERCICIO.

2.3 Clase FeedParser. Esta clase permite abrir un archivo con la estructura presentada al inicio, y luego leerlo línea por línea utilizando el método getNextRow()
ES EL CÓDIGO QUE SE SOLICITÓ ESCRIBIR EN LA SEGUNDA PARTE DEL EJERCICIO.

2.4 Clase FeedConstants. Esta interfaz contiene las constantes usadas por las otras dos clases.

2.5 Prácticas utilizadas
Se utiliza la nomeclatura estándar de java.
Se utiliza javadoc para la documentación.
Todos los comentarios del archivo se encuentran en inglés.

3. SOBRE TRABAJO A FUTURO.
Se dejan algunos “TODO” dentro del código, para identificar algunas cosas que pudieran mejorarse en el código.

Este código NO considera todas las validaciones posibles, como nombres ilegales de los archivos o archivos mayores a 2 GB, o que se acabe el espacio en disco mientras se está escribiendo, debido a que es un código sencillo de prueba. 

Tampoco se considera procesamiento en PARALELO con archivos.

En el caso de que el archivo no exista, será simplemente mostrado un mensaje en rojo, en la consola, debido a que se lanzan las excepciones por default de java.

Se utilizaron excepciones estándar, y no se crearon extensiones de las excepciones por que el ejemplo es pequeño.

No quedé satisfecha con la velocidad de ejecución, puesto que en una máquina con disco de estado sólido tardó 30 minutos en escribir 500MB (eso sí lo veo difícil de mejorar), pero tardó muchísimo en leer (otros 30 minutos casi). Así que si tuviera opción a hacer cambios, el más importante para mi sería mejorar esa velocidad.

En cuanto al uso de memoria durante la escritura, es bastante bueno, pero no así durante la lectura. Ya no me hice tiempo para optimizarlo, creyendo que mi aproximación era óptima en sí.

También, me gustaría agregar un estimador de tiempo.

En cuanto a la estructura del código, decidí hacer una clase singleton y una clase normal. La clase singleton es porque pienso que writer es como una impresora que crea el archivo (se puede usar una sola instancia de esa máquina para varias personas), pero reader pueden ser muchas instancias, como varias personas que están leyendo y copiando el archivo.

4. EL REPOSITORIO
Se incluyen los archivos ejecutables, y otros archivos que se deberían excluir, por efectos de FACILITAR la demostración.

5. TIEMPO
La prueba con un archivo de 500 MB corrió con escritura y lectura en un tiempo aproximado de 1 hora.
