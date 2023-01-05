# Movie-Database
A movie database similar to IMDb where users can look up actors to see a list of their movie appearances.

## Installation and usage instructions:
1. Clone this repository
    ```sh
    git clone git@github.com:/your_github_id/Movie-Database.git
    cd Movie-Database
    ```
1. Unzip the dataset
    ```sh
    unzip movie-dataset.zip
    ```
1. Compile and run the program with the necessary dependencies
    ```sh
    javac -cp .:opencsv-5.7.0.jar:json-simple-1.1.1.jar:commons-lang3-3.12.0.jar MovieWallDriver.java
    java -cp .:opencsv-5.7.0.jar:json-simple-1.1.1.jar:commons-lang3-3.12.0.jar MovieWallDriver movie-dataset.csv
    ```
1. When program is run, user will be prompted to enter an actor to search for or EXIT to exit. If the actor is in the database, the program displays all movies they were in as what characters. If the input is similar to an actor in the database, the user is asked if they would like to display the similar actor's movies and roles. If the input is not similar at all, the user is prompted to try again.
1. Type ```EXIT``` to close the program
