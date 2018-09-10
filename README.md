# PayBike (TDA367, Group 24)
AirBnB for bikes!
## Installation

#### Installing PayBike to Emulator/Phone:
1. Clone this project to your computer.
2. Download ```google-services.json ``` from [project settings in Firebase console](https://console.firebase.google.com/project/paybike-f5a13/settings/general/android:tda367.paybike) and place it in the ```/app``` folder. (Have to be a member of the PayBike-project on Firebase.)
3. Start an Android emulator or connect an Android phone in [USB-debugging mode](https://developer.android.com/studio/run/device).
4. Open Terminal/CMD in the root directory of the project and execute the command ```./gradlew installDebug``` on MacOS or ```.\gradlew installDebug``` on Windows. (might need to add ANDROID_HOME environment variable on Windows)
5. You should now be able to find PayBike among your apps and launch it.

#### Open project in Android Studio:
1. Clone this project to your computer.
2. Download ```google-services.json ``` from [project settings in Firebase console](https://console.firebase.google.com/project/paybike-f5a13/settings/general/android:tda367.paybike) and place it in the ```/app``` folder. (Have to be a member of the PayBike-project on Firebase.)
3. Open project in Android Studio and click on ```File``` &rarr; ```Sync Project with Gradle Files```. (Press OK/Accept/Next on all pop-ups, default settings should work.)
4. You should now be able to run/debug and edit the project.
## Links
* [**Trello**](https://trello.com/b/hgos5Guu/eda367-payride)
* [**Google Drive**](https://drive.google.com/drive/folders/1UGm1TVGUnyhd5HZt6wGgRpqhehPjBxv0)
* [**Firebase Console**](https://console.firebase.google.com/project/paybike-f5a13/overview)
* [**Course page (TDA367)**](http://www.cse.chalmers.se/edu/course/tda367/)
## Workflow (Copied from course page)
1. ```git checkout master``` You are probably already on local master
2. ```git pull``` (```= git pull origin master```) To update local master
3. ```git checkout -b myTask``` Create and switch to branch myTask
4. Optional: ```Use git branch -a``` to show all branches and git status to see what branch you’re on.
5. Do some coding (some well defined (sub) task), after max 30 min. and then go to 6.
6. ```git commit -a -m “…a sensible message…”``` Commit on branch myTask
7. Goto 5. until task finished (max 2 hours before next point)
8. Ok, assume task finished. Project should be executable, all test should pass.
9. Now we start to integrate our changes into the project (Tip: if you are new to this, make a copy (zip) of the current code)

        git checkout master
        git pull
        git checkout myTask
        git rebase master
        git checkout master
        git merge myTask
        git push    ((= git push origin master) Push to remote repo)
        git branch -d myTask    (Delete branch)
10. Now everybody should be able to see your contribution (i.e. git pull).
11. Continue with next task, go to 2.
