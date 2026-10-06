# 📱 Prototipo 2 — Intents Android

Aplicación Android desarrollada en **Java** para demostrar el uso de intents implícitos y explícitos, validación de datos, permisos y funciones del dispositivo.

El proyecto incorpora los **8 intents solicitados en la evaluación**: 5 implícitos y 3 explícitos. También conserva funciones adicionales de ubicación y linterna.

## 🎯 Objetivo

Implementar distintas formas de comunicación en Android:

- Abrir aplicaciones y configuraciones externas mediante intents implícitos.
- Navegar entre pantallas internas mediante intents explícitos.
- Enviar información de una Activity a otra con `putExtra()`.
- Validar los datos antes de ejecutar cada acción.
- Controlar situaciones donde no exista una aplicación compatible.

## 🛠️ Tecnologías utilizadas

- Java 11.
- Android SDK 36.
- Android Gradle Plugin 9.0.1.
- Gradle 9.2.1.
- AndroidX.
- Material Components.
- Versión mínima: Android 12, API 31.

## 🔗 Intents implícitos

### 1. Abrir una ubicación en el mapa

Utiliza `Intent.ACTION_VIEW` junto con una dirección `geo:` para abrir la ubicación obtenida mediante GPS en una aplicación de mapas.

**Cómo probarlo:**

1. Presiona **Obtener ubicación**.
2. Acepta el permiso de ubicación.
3. Espera hasta que aparezcan la latitud y longitud.
4. Presiona **Abrir mapa**.

**Validaciones:**

- Comprueba que el permiso de ubicación esté autorizado.
- Solicita activar el GPS cuando está deshabilitado.
- Evita abrir el mapa si todavía no existe una ubicación.
- Verifica que exista una aplicación de mapas disponible.

### 2. Abrir una página web

Utiliza `Intent.ACTION_VIEW` con una dirección web `https://`.

**Cómo probarlo:**

1. Ingresa una dirección, por ejemplo `google.com`.
2. Presiona **Abrir página web**.

**Validaciones:**

- Comprueba que el campo no esté vacío.
- Agrega `https://` cuando sea necesario.
- Comprueba que la dirección tenga un formato válido.
- Verifica que exista un navegador instalado.

### 3. Abrir el marcador telefónico

Utiliza `Intent.ACTION_DIAL` con una dirección `tel:`. La aplicación prepara el número, pero no inicia la llamada automáticamente.

**Cómo probarlo:**

1. Ingresa un número, por ejemplo `+56912345678`.
2. Presiona **Abrir marcador telefónico**.

**Validaciones:**

- Comprueba que el campo no esté vacío.
- Permite números nacionales e internacionales de entre 8 y 15 dígitos.
- Elimina espacios, guiones y paréntesis.
- Verifica que exista una aplicación de teléfono.

> Esta función no necesita el permiso `CALL_PHONE`.

### 4. Preparar un correo electrónico

Utiliza `Intent.ACTION_SENDTO` junto con una dirección `mailto:`. El destinatario, asunto y mensaje quedan preparados, pero el usuario debe enviarlos manualmente.

**Cómo probarlo:**

1. Ingresa un correo, por ejemplo `ejemplo@gmail.com`.
2. Presiona **Preparar correo**.

**Validaciones:**

- Comprueba que el campo no esté vacío.
- Valida el formato del correo electrónico.
- Verifica que exista una aplicación de correo instalada.

### 5. Abrir la configuración de Wi-Fi

Utiliza `Settings.ACTION_WIFI_SETTINGS` para abrir directamente los ajustes de Wi-Fi del dispositivo.

**Cómo probarlo:**

1. Presiona **Abrir configuración de Wi-Fi**.
2. Comprueba que Android muestre los ajustes de Wi-Fi.

**Validación:** verifica que la pantalla de configuración esté disponible antes de abrirla.

## 📲 Intents explícitos

### 1. MainActivity → SegundaActivity

Abre una segunda pantalla indicando directamente la Activity de destino.

**Cómo probarlo:** presiona **Ir a SegundaActivity** y utiliza el botón Atrás para regresar.

### 2. MainActivity → DetalleActivity

Abre la pantalla de detalle y envía los siguientes datos mediante `putExtra()`:

- Nombre del prototipo.
- Cantidad total de intents.

**Cómo probarlo:**

1. Presiona **Ir a DetalleActivity con datos**.
2. Comprueba que aparezca `Prototipo 2 - Intents`.
3. Comprueba que la cantidad recibida sea `8`.
4. Presiona **Volver**.

### 3. MainActivity → AyudaActivity

Abre una pantalla interna con instrucciones para utilizar las funciones de la aplicación.

**Cómo probarlo:** presiona **Ir a AyudaActivity**, revisa las instrucciones y utiliza el botón **Volver**.

## 💡 Funciones adicionales

- Encender y apagar la linterna del dispositivo.
- Solicitar el permiso de cámara durante la ejecución.
- Obtener latitud y longitud mediante GPS.
- Solicitar permisos de ubicación durante la ejecución.

## ✅ Validaciones implementadas

- Campos obligatorios.
- Formato de URL.
- Formato de teléfono.
- Formato de correo electrónico.
- Permisos de cámara y ubicación.
- Estado del GPS.
- Ubicación disponible antes de abrir el mapa.
- Existencia de una aplicación compatible mediante `resolveActivity()`.
- Datos predeterminados al recibir extras en `DetalleActivity`.

## 📸 Evidencias

Antes de entregar, crea la carpeta `docs/screenshots` y agrega al menos cuatro capturas con estos nombres:

```text
docs/screenshots/
├── pantalla-principal.png
├── mapa.png
├── detalle-activity.png
└── ayuda-activity.png
```

Después elimina este aviso y habilita las siguientes imágenes quitando los comentarios:

<!--
![Pantalla principal](docs/screenshots/pantalla-principal.png)

![Ubicación abierta en el mapa](docs/screenshots/mapa.png)

![Datos recibidos en DetalleActivity](docs/screenshots/detalle-activity.png)

![Pantalla de ayuda](docs/screenshots/ayuda-activity.png)
-->

## 📂 Estructura principal

```text
Semana7/
├── app/
│   ├── src/main/java/com/devst/semana7/
│   │   ├── MainActivity.java
│   │   ├── SegundaActivity.java
│   │   ├── DetalleActivity.java
│   │   └── AyudaActivity.java
│   ├── src/main/res/layout/
│   │   ├── activity_main.xml
│   │   ├── activity_segunda.xml
│   │   ├── activity_detalle.xml
│   │   └── activity_ayuda.xml
│   └── build.gradle.kts
├── docs/screenshots/
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## 🔐 Permisos utilizados

El proyecto declara los siguientes permisos en `AndroidManifest.xml`:

- `CAMERA`: permite controlar la linterna.
- `ACCESS_FINE_LOCATION`: permite obtener la ubicación precisa.
- `ACCESS_COARSE_LOCATION`: permite obtener una ubicación aproximada.

Los permisos se solicitan cuando el usuario intenta utilizar la función correspondiente.

## ▶️ Cómo ejecutar el proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/max32323232/Semana7.git
   ```

2. Abre la carpeta del proyecto en Android Studio.
3. Espera a que Gradle sincronice las dependencias.
4. Conecta un teléfono Android o inicia un emulador.
5. Ejecuta la aplicación con **Run app**.
6. Acepta los permisos solicitados.

Para probar la linterna y la ubicación se recomienda utilizar un teléfono físico. Algunos emuladores no incluyen flash, GPS, marcador telefónico o una aplicación de correo configurada.

## 📦 Generar el APK

En Android Studio selecciona:

```text
Build → Build APK(s)
```

También puedes ejecutar:

```bash
./gradlew assembleDebug
```

El APK se genera normalmente en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## 🌿 Ramas del proyecto

- `main`: versión estable y lista para entregar.
- `feature/intents`: rama utilizada para implementar los intents y validaciones.

## 👥 Integrantes

- Máximo Rojas

## 📄 Estado del proyecto

Prototipo 2 desarrollado para la evaluación sumativa de Programación Android.
