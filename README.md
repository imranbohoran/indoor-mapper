# indoor-mapper  [![Build Status](https://travis-ci.org/imranbohoran/indoor-mapper.svg?branch=master)](https://travis-ci.org/imranbohoran/indoor-mapper)

An application demonstrating basic location logging and geo fencing features using Indoor Atlas (http://www.indooratlas.com/) APIs.

## Running the application

#### Prerequisites
* Indoor Atlas API key
* Firebase account

#### Build and run instructions
- The Indoor Atlas API key and the secret needs to be added to the gradle.properties file
- The url of the firebase instance need to be overriden in `LocationFirebaseStore.java`
*TODO: This needs to be moved to an environment variable*
- The google-services.json from firebase needs to be placed under the `app` folder.
*This file is not added to the repo api keys, secret keys, oauth client info etc, that we don't want to be in source repo.*
- And then run the application. This should be run on a real device as the geomagnatic sensors.


