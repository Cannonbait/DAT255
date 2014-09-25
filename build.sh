#!/bin/bash

gradle build -p ~/Programming/gitrepos/DAT255/project/ && adb install -r ~/Programming/gitrepos/DAT255/project/app/build/apk/app-debug-unaligned.apk
