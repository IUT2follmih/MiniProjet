# MiniProjet

Application Android (Java) réalisée dans le cadre d’un mini-projet universitaire (IUT).

C’est un cahier d’exercices scolaires multi-utilisateurs : tables de multiplication, tables d’addition et QCM (français, histoire, géographie).

## Fonctionnalités

- Comptes utilisateurs (nom / prénom) stockés en local avec Room
- Mode anonyme
- Suppression d’un compte
- 3 exercices :
  - **Tables de multiplication** : choix de la table, réponses, timer 60 s
  - **Tables d’addition** : mêmes règles, timer 60 s
  - **QCM** : 10 questions, 4 réponses, thèmes Français / Histoire / Géographie
- Écran de résultat (bonnes réponses / erreurs) avec option de recommencer

## Stack

- Java 8
- Android Gradle Plugin 8.3.0 / Gradle 8.4
- `minSdk` 24, `compileSdk` / `targetSdk` 34
- AndroidX (AppCompat, Material, ConstraintLayout)
- Room 2.6.1

Package : `com.example.miniprojet`

## Prérequis

- Android Studio compatible AGP 8.3
- SDK Android 34
- Émulateur ou téléphone (Android 7+) avec le débogage USB

## Lancer l’application

### Android Studio

1. **File → Open** et sélectionner le dossier du projet (celui qui contient `settings.gradle.kts`).
2. Attendre la sync Gradle (un fichier `local.properties` est créé automatiquement, il n’est pas versionné).
3. Choisir un appareil (émulateur ou téléphone).
4. **Run** (`Shift+F10`) sur le module `app`.

L’écran de démarrage est `MainActivity` (« Choisir son compte »).

### Ligne de commande

Device ou émulateur déjà démarré :

```bash
./gradlew installDebug
adb shell am start -n com.example.miniprojet/.MainActivity
```

## Structure

```
app/src/main/java/com/example/miniprojet/
├── MainActivity.java                 # liste des comptes
├── CreationDeCompteActivity.java
├── ListeExoActivity.java             # choix de l’exercice
├── TableDeMultiplicationActivity.java
├── TableDeMultiplicationReponsesActivity.java
├── TableAdditionReponsesActivity.java
├── QCMActivity.java
├── QCMReponsesActivity.java
├── ResultatActivity.java
├── dataBase/                         # Room (users + questions)
├── tableMultiplication/
└── tableAddition/
```
