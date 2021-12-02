java -jar openapi-generator-cli.jar generate ^
-g spring -i ..\src\main\resources\SecretSanata.yaml ^
-c openapiGenConfig.json ^
--skip-validate-spec ^
--global-property models,apis