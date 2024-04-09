# Bio_Stat Project

## Overview

The Bio_Stat project is designed to assist users in making statistical inferences based on their data. It provides a user-friendly interface where users can input their data and answer a series of questions to determine the appropriate statistical method for their analysis. The project incorporates a flowchart mechanism that guides users through the decision-making process, resulting in a recommended statistical inference method tailored to their data characteristics and analysis goals.

## Purpose

The purpose of the Bio_Stat project is to simplify the process of choosing the correct statistical inference method for various types of data analysis tasks. By providing a guided decision-making process through a flowchart interface, the project aims to empower users with limited statistical knowledge to make informed decisions about their data analysis approach.

## Features

- **Flowchart Mechanism:** Guides users through a series of questions to determine the appropriate statistical inference method.
- **Inference Result:** Provides the recommended statistical inference method based on user input and data characteristics.
- **Interactive Interface:** User-friendly interface for inputting data and navigating through the decision-making process.
- **Supports Various Data Types:** Capable of handling different types of data distributions and analysis scenarios.

## Dependencies

To run the Bio_Stat project, the following dependencies are required:

- Kotlin
- Android Studio
- Apache Commons CSV library
- OpenCSV library

## Setup and Running

Follow these steps to set up and run the Bio_Stat project locally:

1. **Clone the Repository**
    https://github.com/DIvineJMd/Bio_stat_Inferance.git
2. **Open Project in Android Studio:**
- Launch Android Studio.
- Select "Open an existing Android Studio project."
- Navigate to the directory where you cloned the repository and select the `bio_stat` directory.

3. **Configure Dependencies:**
- Add the Apache Commons CSV library and OpenCSV library to your project's dependencies. You can do this by adding the library dependencies to your `build.gradle` file.

4. **Run the Project:**
- Connect an Android device or use an emulator.
- Click on the "Run" button in Android Studio to build and run the project on your device/emulator.

5. **Interact with the App:**
- Once the app is running, follow the on-screen instructions to input your data and answer the questions presented in the flowchart interface.
- Based on your responses, the app will recommend a suitable statistical inference method for your data analysis task.

## Special Instructions

- Ensure that the required permissions are granted for accessing and reading files if using the file picker feature.
- Follow the prompts and instructions provided by the app during the data input and decision-making process.
