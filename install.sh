./gradlew shadowJar

native-image -jar build/libs/dist_sys_test-1.0-SNAPSHOT-all.jar dist_sys_test --no-fallback