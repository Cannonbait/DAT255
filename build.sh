#!/bin/bash

#Build script for PickUApp

echo "NOTE: You must cd to git root folder (eg MyGitRepos/DAT255) when running build.sh"
gradle build -p project/ && adb install -r project/app/build/apk/app-debug-unaligned.apk
