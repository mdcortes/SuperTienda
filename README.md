# Super Tienda app
Esta aplicación ha sido creada para una prueba técnica de Walmart Chile.

## Compilación
Para compilar la aplicación se utiliza el plugin de Gradle para Android. En particular, debería bastar con importar el proyecto a Android Studio utilizando New -> Import project...

## Arquitectura
Para el desarrollo de esta aplicación, se ha utilizado la arquitectura Modelo - Vista - Modelo de Vista (Model-View-ViewModel o MVVM).

Para las capas de modelo (Productos y Carro, `Product` y `Cart` en la aplicación) se tiene un repositorio que actua como controlador del modelo, quien se encaga de interactuar con los modelos y fuentes de datos correspondientes. Para el caso de los productos, la fuente de datos es la API provista por https://fakestoreapi.com/, mientras que para el carro de compras se usa una base de datos SQLite administrada localmente.

Las capas de vista fueron desarrolladas utilizando Jetpack Compose para la construcción de elementos gráficos. Se desarrolló una pantalla (`Screen`) para el home y el carro de compras, siendo administados mediante el componente de navegación para Compose de Android Jetpack.

Los modelos de datos fueron creados usando como base la clase `ViewModel` de Android Jetpack. Estos modelos obtienen la información requerida para mostrar en las vistas, le dan formato y la proveen a las vistas por medio de estados de UI utilizando un `StateFlow`.

Para proveer a cada componente de las dependencias que necesita para funcionar, se utilizó inyección de dependencias.
Finalmente, se agregaron test unitarios para tpda la capa de datos. No se agregaron a los ViewModels exclusivamente por falta de tiempo para implementarlos.

## Librerías
Para el desarrollo de esta aplicación, se han usado las siguientes librerías externas:
- **Librerías de Android Jetpack:** Como se mencionó anteriormente, esta aplicación hace uso de conponentes de arquitectura de Android Jetpack, como la clase ViewModel. Además, hace uso de Compose y Material 3 para la construcción de elementos visuales. Junto a esto, se hace uso de Room para la manipulación de la base de datos local en que se respalda la información del carro de compras.
- **Retrofit:** Se utiliza esta librería para realizar las peticiones HTTP a la API de https://fakestoreapi.com/, desde la que se otienen los datos a desplegar. Además, se usa el convertidor de Gson para Retrofit con la finalidad de convertir los JSON que responde la API en objetos manipulables por la app (POJOs, en términos de Java).
- **Glide:** Se utiliza esta librería para desplegar en pantalla imagenes disponibles en internet, accesibles a través de una URL.
- **Hilt:** Se utiliza esta librería para inyectar dependencias en las clases que las utilizan (como agregar reporitorios a los ViewModel), con la finalidad de reducir el acoplamieto entre clases y reducir el *boilerplate code*.
- **JUnit:** Se utiliza para la elaboración de test unitarios con los que se probó la capa de datos de la aplicación.
- **Mockito:** Se utiliza para crear *mocks* de clases para realizar tests sobre las clases que los utilizan, de forma de obtener una respuesta predecible.

## Consideraciones
Para el desarrollo de esta prueba se tomaron en cuenta las siguientes consideraciones:
- Al agregar al carro de compras un producto que ya se encontraba en el carro, el producto reemplaza al anterior. Es decir, si tengo agregadas tres unidades de un producto en el carro de compras y desde la vista principal agrego el producto nuevamente, en el carro de compras solo habrá una unidad de ese producto después de hecho esto.
- Se implementa un ícono con un carro de compras en lugar del botón con el texto *Cart* definido en los mockups. 
- Para el contador de carro de compras en la vista principal, se considera la cantidad de productos distintos agregados al carro. Por ejemplo, si en el carro hay 5 unidades del producto 1 y 3 unidades del producto 2, en el contador del carro de compra aparecerá un 2. Dicho contador solo será visible si hay productos en el carro.
- Para la visualización de más detalles de un producto, se utiliza un componente llamado `ModalBottomSheet`. Este componente aparece desplegándose desde abajo, y para quitarlo se debe desplazar hacia abajo nuevamente. En esta vista, además, para la visualización del rating del producto, se asume que este estará entre 1 y 5 y se desplegará media estrella para cualquier puntaje intermedio. Por ejemplo, para un puntaje de 3.3, aparecerán 3 estrellas rellenadas, una a medio llenar y una con solo el contorno.
- En la vista de carro, está permitido que un artículo tenga 0 unidades. Se controla que el número de unidades no pueda ser menor que 0 al no hacer nada si, en este caso, se presiona el botón de quitar unidad.
- Para el estilizado de la aplicación no se utilizó por razones de tiempo ningún estilo o color más allá de los predeterminados (salvo algunas ecepciones, como el contador de productos en el carro de la vista principal). Sin embargo, esta implementación estándar provee una variación en la vista dependiendo si el sistema está utilizando el modo claro o el modo oscuro.
