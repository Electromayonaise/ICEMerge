# **Informe sobre la Implementación de un Sistema Distribuido de Ordenamiento con ICE**

## **Introducción**

El objetivo de este informe es describir la implementación de un sistema distribuido de ordenamiento utilizando ICE (Internet Communications Engine). El sistema distribuido permite ordenar grandes conjuntos de datos de manera eficiente utilizando múltiples servidores de ayuda.

## **Descripción del Problema**

Ordenar grandes conjuntos de datos puede ser un desafío computacionalmente costoso. En un entorno distribuido, la tarea de ordenar estos datos se puede dividir entre varios servidores, lo que permite un procesamiento más rápido y eficiente.

## **Estrategia de Distribución**

La estrategia de distribución implementada en este sistema se basa en el algoritmo de ordenamiento Merge Sort. El algoritmo Merge Sort divide el conjunto de datos en subconjuntos más pequeños, los ordena individualmente y luego fusiona estos subconjuntos ordenados para obtener el conjunto de datos ordenado final. 

En este sistema, el conjunto de datos se divide en K subconjuntos, donde K es el número de servidores de ayuda disponibles. Cada subconjunto se envía a un servidor de ayuda para ser ordenado de manera independiente. Una vez que todos los subconjuntos han sido ordenados por los servidores de ayuda, los subconjuntos ordenados se fusionan para obtener el conjunto de datos ordenado final.

## **Diseño de la Implementación utilizando ICE**

El diseño de la implementación se divide en tres componentes principales: el servidor principal, los servidores de ayuda y el cliente.

1. **Servidor Principal (Primary Server):**
   - El servidor principal actúa como el punto de entrada del sistema distribuido.
   - Se encarga de inicializar ICE y crear un adaptador de objetos para exponer la funcionalidad de ordenamiento.
   - Cuando un cliente solicita ordenar un conjunto de datos, el servidor principal divide el conjunto de datos en subconjuntos y los envía a los servidores de ayuda para ser ordenados.
   - Una vez que los subconjuntos han sido ordenados por los servidores de ayuda, el servidor principal los fusiona y devuelve el conjunto de datos ordenado al cliente.

2. **Servidores de Ayuda (Helper Servers):**
   - Los servidores de ayuda son responsables de ordenar los subconjuntos de datos recibidos del servidor principal.
   - Cada servidor de ayuda utiliza el algoritmo Merge Sort para ordenar los subconjuntos de datos de manera independiente.
   - Una vez que un servidor de ayuda ha ordenado un subconjunto de datos, devuelve el subconjunto ordenado al servidor principal.

3. **Cliente (Client):**
   - El cliente permite a los usuarios cargar conjuntos de datos para ser ordenados por el sistema distribuido.
   - El cliente se conecta al servidor principal utilizando ICE y envía el conjunto de datos al servidor principal para su procesamiento.
   - Una vez que el conjunto de datos ha sido ordenado, el cliente recibe el conjunto de datos ordenado del servidor principal y lo muestra al usuario.

## Complejidad del algoritmo de Merge Sort implementado con distribución en k servidores

El algoritmo de Merge Sort implementado distribuye el arreglo original en "k" subarreglos, donde "k" es el número de servidores secundarios disponibles. Cada servidor secundario ordena su subarreglo utilizando el algoritmo Merge Sort de manera independiente, y luego el servidor principal fusiona los subarreglos ordenados para obtener el arreglo final.

$i$. **División del arreglo en k subarreglos:**

El arreglo original se divide en "k" subarreglos de tamaño aproximadamente "n/k" en cada paso. Esto se realiza de manera distribuida en los "k" servidores secundarios.

- Complejidad de tiempo: $O(\frac{n}{k})$

$ii$. **Ordenación de los subarreglos en paralelo:**

Cada servidor secundario ordena su subarreglo utilizando el algoritmo Merge Sort de manera independiente.

- Complejidad de tiempo: $O(\frac{n}{k} log(\frac{n}{k}))$

$iii$. **Fusión de los subarreglos ordenados:**

El servidor principal fusiona los "k" subarreglos ordenados para obtener el arreglo final.

- Complejidad de tiempo: $O(k)$

### Complejidad total:

La complejidad de tiempo total del algoritmo de Merge Sort con distribución en "k" servidores es:

$O\left(\frac{n}{k} + k \cdot \left(\frac{n}{k} \log\left(\frac{n}{k}\right)\right) + k\right)$

### Análisis de la implementación

La implementación distribuida del algoritmo de Merge Sort aprovecha la capacidad de procesamiento de "k" servidores secundarios para ordenar de manera eficiente y escalable grandes conjuntos de datos. La división del arreglo en "k" subarreglos distribuidos permite paralelizar la ordenación y reducir el tiempo de ejecución total del algoritmo.

## **Conclusión**

La implementación de un sistema distribuido de ordenamiento utilizando ICE ofrece una solución eficiente y escalable para ordenar grandes conjuntos de datos. El algoritmo de Merge Sort implementado con distribución en "k" servidores proporciona un rendimiento ágil en entornos distribuidos. La estrategia de distribución basada en Merge Sort reduce significativamente la complejidad de tiempo al distribuir la carga de trabajo entre múltiples servidores secundarios, lo que permite ordenar grandes conjuntos de datos de manera más rápida y eficiente. En conjunto, la combinación de la estrategia de distribución y el diseño de la implementación utilizando ICE proporciona una solución robusta y flexible para el problema de ordenamiento distribuido.
