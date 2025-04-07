# Application de Gestion d'une Salle de Sport

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen)](#) <!-- Remplacez '#' par l'URL de votre build status Jenkins -->
[![Docker Image Size](https://img.shields.io/docker/image-size/errachidy10/salle-de-sport-app)](https://hub.docker.com/r/errachidy10/salle-de-sport-app) <!-- Remplacez 'errachidy10/salle-de-sport-app' par le nom de votre image Docker Hub -->

## Description

Ce projet est une application web complète de gestion de salle de sport, conçue pour optimiser l'administration des installations sportives. Elle offre une gamme de fonctionnalités pour faciliter la gestion des membres, la planification des cours, la gestion des équipements et la génération de rapports pertinents.  Développée avec Java et Spring Boot, l'application est conçue pour être facilement déployable grâce à Docker et peut être intégrée dans un pipeline CI/CD via Jenkins pour une livraison continue et efficace.

## Fonctionnalités

*   **Gestion des Membres :**
    *   Inscription de nouveaux membres avec collecte des informations nécessaires.
    *   Suivi des abonnements (dates d'expiration, types d'abonnement).
    *   Gestion des paiements (enregistrement, suivi des impayés, génération de factures).
    *   Possibilité de gérer les informations personnelles des membres (adresse, contact, etc.).
*   **Planification des Cours :**
    *   Création et gestion des horaires des cours avec indication du type, de l'instructeur et de la capacité.
    *   Inscriptions en ligne aux cours avec gestion des listes d'attente.
    *   Affichage clair du calendrier des cours pour les membres et le personnel.
*   **Gestion des Équipements :**
    *   Inventaire complet des équipements de la salle de sport.
    *   Suivi de l'état des équipements (opérationnel, en maintenance, hors service).
    *   Historique des maintenances et réparations.
*   **Rapports :**
    *   Génération de rapports détaillés sur l'utilisation des installations (fréquentation par heure, par jour, par cours).
    *   Rapports sur les inscriptions (nouveaux membres, renouvellements, taux de rétention).
    *   Rapports financiers (chiffre d'affaires, paiements en attente).
    *   Possibilité de personnaliser les rapports.
*   **Authentification et Autorisation :**
    *   Système d'authentification robuste pour les administrateurs et les membres.
    *   Gestion des rôles et permissions pour contrôler l'accès aux fonctionnalités.

## Technologies Utilisées

*   **Backend:**
    *   **Java:** Langage de programmation principal pour la logique applicative.
    *   **Spring Boot:** Framework pour le développement rapide d'applications Java robustes.
    *   **Maven:** Outil de gestion de dépendances et de construction du projet.
    *   **Database:**  *À spécifier (ex: MySQL,)* -  Pour le stockage des données (membres, cours, etc.).
*   **Frontend:**
    *   **HTML:** Structure du contenu de l'application.
    *   **CSS:** Mise en forme et design de l'application.
    *   **JavaScript (Optional):**  Pour interactivité et dynamisme de l'interface utilisateur. Frameworks comme React, Angular ou Vue.js peuvent être utilisés.
*   **DevOps:**
    *   **Teamlyf ():**.
    *   **Docker:** Conteneurisation de l'application pour un déploiement facile et reproductible.
    *   **Jenkins:** Intégration continue et déploiement continu (CI/CD) pour automatiser le processus de build, test et déploiement.

## Prérequis

*   Java Development Kit (JDK) 21 ou supérieur.
*   Maven.
*   Docker (si vous souhaitez utiliser la conteneurisation).
*   Un IDE (Integrated Development Environment) comme IntelliJ IDEA ou Eclipse.
*   *Base de données (ex: MySQL) installée et configurée.*

## Installation

1.  **Clonez le dépôt :**

    ```bash
    git clone https://github.com/errachidy10/Application-salle-de-sport-main1.git](https://github.com/WISSALF52/Application-salle-de-sport.git
    ```

2.  **Naviguez dans le répertoire du projet :**

    ```bash
    cd Application-salle-de-sport-main1
    ```

3.  **Configurez la base de données :**

    *   Créez une base de données.
    *   Modifiez le fichier `src/main/resources/application.properties` (ou `application.yml`) pour configurer la connexion à votre base de données.  Exemple:

        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/salle_de_sport
        spring.datasource.username=votre_utilisateur
        spring.datasource.password=votre_mot_de-passe
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        ```

4.  **Compilez et exécutez l'application :**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

    L'application sera accessible à l'adresse `http://localhost:8080` (par défaut).

## Utilisation de Docker

Pour déployer l'application avec Docker, référez-vous à la documentation Docker pour construire une image et exécuter un conteneur. Assurez-vous de configurer correctement les variables d'environnement pour la connexion à la base de données.

## CI/CD avec Jenkins

Ce projet est conçu pour être intégré dans un pipeline CI/CD avec Jenkins. La configuration spécifique du pipeline dépend de votre environnement de déploiement.  Consultez la documentation Jenkins pour créer un pipeline qui automatise les étapes de build, de test et de déploiement.

## Contribuer

Les contributions sont les bienvenues !  Avant de contribuer, veuillez lire notre [Code de Conduite](CODE_OF_CONDUCT.md) (si vous en avez un).

Pour contribuer :

1.  Forkez le dépôt.
2.  Créez une branche pour votre fonctionnalité ou correction de bug ( `git checkout -b feature/AmazingFeature` ).
3.  Committez vos modifications ( `git commit -m 'Add some AmazingFeature'` ).
4.  Poussez votre branche ( `git push origin feature/AmazingFeature` ).
5.  Ouvrez une Pull Request.

## License

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Auteurs

*   [errachidy10](https://github.com/errachidy10)

## Remerciements

*   Merci à la communauté open source pour les outils et les bibliothèques utilisés dans ce projet.
