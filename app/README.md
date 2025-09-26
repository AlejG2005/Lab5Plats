# Lab5 – PokeAPI con Retrofit + Compose + Navigation

Aplicación Android (Kotlin) que consume la **PokeAPI** para:
- Listar los **primeros 100 Pokémon** (`pokemon?limit=100`).
- Mostrar una pantalla de **detalle** con **4 sprites** del Pokémon (front/back y front/back shiny).

## Tech stack

- **Kotlin**
- **Jetpack Compose** (UI)
- **Navigation Compose** (Navegación)
- **ViewModel + Coroutines**
- **Retrofit + Gson** (HTTP/JSON)
- **Coil** (carga de imágenes)
- **Material 3**

## Estructura del proyecto (paquetes principales)
├─ data/
│ ├─ model/
│ │ ├─ PokeListResponse.kt
│ │ └─ PokemonDetail.kt
│ ├─ remote/
│ │ ├─ PokeApi.kt
│ │ └─ RetrofitModule.kt
│ └─ PokemonRepository.kt
├─ ui/
│ ├─ list/
│ │ ├─ PokemonListViewModel.kt
│ │ └─ PokemonListScreen.kt
│ └─ detail/
│ ├─ PokemonDetailViewModel.kt
│ └─ PokemonDetailScreen.kt
├─ ui/theme/
│ ├─ Color.kt
│ ├─ Type.kt
│ └─ Theme.kt
└─ util/
└─ PokeUtil.kt

## Flujo

1. **MainActivity** define un `NavHost` con dos rutas:
   - `list` → `PokemonListScreen`
   - `detail/{name}` → `PokemonDetailScreen`
2. **Lista**: `PokemonListViewModel` llama `GET /pokemon?limit=100` y muestra nombre + miniatura.
   - La miniatura usa el **id** extraído de la `url` de cada Pokémon y arma:
     `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/{id}.png`
3. **Detalle**: `PokemonDetailViewModel` llama `GET /pokemon/{name}` y muestra `sprites.front_default`, `back_default`, `front_shiny`, `back_shiny`.

## Dependencias clave (build.gradle del módulo `app`)

```kotlin
implementation(platform("androidx.compose:compose-bom:2024.10.01"))
implementation("androidx.compose.material3:material3:1.3.0")
implementation("androidx.navigation:navigation-compose:2.8.2")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

implementation("com.squareup.retrofit2:retrofit:2.11.0")
implementation("com.squareup.retrofit2:converter-gson:2.11.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

implementation("io.coil-kt:coil-compose:2.6.0")
implementation("androidx.compose.material:material-icons-extended")

Y en AndroidManifest.xml:
<uses-permission android:name="android.permission.INTERNET" />


[Screen_recording_20250926_151525.webm](https://github.com/user-attachments/assets/224b9b6b-b9f9-4fe9-8aa4-dcd779883e2b)
<img width="151" height="339" alt="Captura de pantalla 2025-09-26 a las 3 14 50 p  m" src="https://github.com/user-attachments/assets/5ba89c06-f593-4954-8e0f-9aa8a96bb0d0" />
<img width="151" height="339" alt="Captura de pantalla 2025-09-26 a las 3 14 31 p  m" src="https://github.com/user-attachments/assets/4746d4d3-4cc3-4773-b5e2-15adc32d1e40" />





