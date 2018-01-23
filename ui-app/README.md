This project is intended to package up the ui artifact into an easy to use js file to be consumed by a webapp. The ui-app subproject provides the main entry point for the ui module. 

Additionally it downloads node library dependencies, then combines all, including ui code, into a single js file. 

There is a dev configuration which provides a unminified but combined js file using artifact `devUiApp`. Also there is a minified artifact for prod `prodUiApp`.

The gradle file also runs the kotlin DCE automatically which maintains the source maps for the dev artifact.