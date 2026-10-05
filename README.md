# Intents y funciones Android

Aplicación Android desarrollada en Java para practicar el uso de permisos, funciones del dispositivo e `Intent` explícitos e implícitos.

## Funcionalidades

- Encender y apagar la linterna del dispositivo.
- Solicitar permisos de cámara y ubicación durante la ejecución.
- Obtener y mostrar la latitud y longitud mediante GPS.
- Abrir la ubicación obtenida en una aplicación de mapas.
- Navegar desde `MainActivity` hacia `SegundaActivity` con un `Intent` explícito.

## Tecnologías utilizadas

- Java 11
- Android SDK 36
- Android Gradle Plugin 9.0.1
- Gradle 9.2.1
- AndroidX y Material Components

## Requisitos

- Android Studio compatible con Android Gradle Plugin 9.0.1.
- JDK 11 o superior.
- Android SDK 36 instalado.
- Dispositivo o emulador con Android 12 (API 31) o superior.

Para probar todas las funciones se recomienda un dispositivo físico con cámara, flash y GPS. El emulador puede no ofrecer todas estas características.

## Cómo ejecutar el proyecto

1. Clona este repositorio:

   ```bash
   git clone https://github.com/max32323232/Semana7.git
   ```

2. Abre la carpeta del proyecto en Android Studio.
3. Espera a que Gradle sincronice las dependencias.
4. Conecta un dispositivo Android o inicia un emulador.
5. Ejecuta la aplicación con **Run app**.
6. Acepta los permisos de cámara y ubicación cuando la aplicación los solicite.

## Estructura principal

```text
Semana7/
├── app/
│   ├── src/main/java/com/devst/semana7/
│   │   ├── MainActivity.java
│   │   └── SegundaActivity.java
│   ├── src/main/res/layout/
│   │   ├── activity_main.xml
│   │   └── activity_segunda.xml
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## Permisos utilizados

La aplicación declara los siguientes permisos en `AndroidManifest.xml`:

- `CAMERA`: permite controlar la linterna.
- `ACCESS_FINE_LOCATION`: permite obtener la ubicación precisa.
- `ACCESS_COARSE_LOCATION`: permite obtener una ubicación aproximada.

Los permisos de cámara y ubicación se solicitan al usuario cuando se intenta utilizar cada función.
