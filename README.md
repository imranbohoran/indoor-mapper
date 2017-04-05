# indoor-mapper  [![Build Status](https://travis-ci.org/imranbohoran/indoor-mapper.svg?branch=master)](https://travis-ci.org/imranbohoran/indoor-mapper)

An application demonstrating basic location logging and geo fencing features using Indoor Atlas (http://www.indooratlas.com/) APIs.

## Running the application

#### Prerequisites
* Indoor Atlas API key
* Firebase account

#### Build and run instructions
- The Indoor Atlas API key and the secret needs to be added to the gradle.properties file.
If this is not set, location logging and geofencing will not work.
- The url of the firebase instance need to be overridden in `LocationFirebaseStore.java`
- And then run the application. This should be run on a real device as the geomagnatic sensors are used to
get location information.

#### Running tests
`./gradlew test`


#### Building the apk
`./gradlew assembleRelease`
