# Character App

This app consists of two screens: the Character Screen and the Character Details Screen. It follows the MVVM (Model-View-ViewModel) pattern for better separation of concerns and easier maintenance.

## Project Structure

### Packages

1. **model**:
    - Contains data classes for storing character information.

2. **activities**:
    - Contains activities that represent the UI screens.
        - `CharacterActivity`: Displays the list of characters.
        - `CharacterDetailsActivity`: Displays the details of a selected character.

3. **viewModel**:
    - Contains ViewModel classes that handle data and business logic.
        - `CharacterViewModel`: Manages the data for the CharacterActivity.
        - `CharacterDetailsViewModel`: Manages the data for the CharacterDetailsActivity.

### Data Management

- **SharedPreferences**:
    - Used to store the selected character ID.
    - Based on the stored ID, the app can perform add or remove functionality for a character.

### API Integration

- For the Character Details Screen, the app calls the same API as the Character Screen but with an additional query parameter (`id`) to fetch the details of a specific character.

## MVVM Pattern

### Model

The `model` package contains data classes that represent the data structure of the characters.

### View

The `activities` package contains activities that are responsible for displaying the UI.

### ViewModel

The `viewModel` package contains ViewModel classes that act as a bridge between the Model and View. They handle the business logic and prepare data for the UI.

## Adding and Removing Features

- The app uses SharedPreferences to store the ID of the selected character.
- Based on this ID, the app can add or remove a character.
- The Character Details Screen fetches data from the API using the stored ID to display specific character details.

## Usage

1. Launch the app to see the list of characters on the Character Screen.
2. Select a character to view its details on the Character Details Screen.
3. The app will use SharedPreferences to manage the selected character's ID and fetch relevant data from the API.

## Future Enhancements

- Improve error handling for network requests.
- Add caching to reduce API calls.
- Enhance the UI with animations and better user experience.
