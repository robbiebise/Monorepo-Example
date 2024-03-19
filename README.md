# Monorepo Example with SonarQube and GitHub Actions

This repository provides an example of using GitHub Actions to scan a monorepo with SonarQube. It contains three separate projects: `comp-cli` (Python), `comp-dotnet` (C#), and `comp-maven` (Java Maven).

## Workflow Separation

Each project has its own workflow file in the `.github/workflows` directory. This separation ensures that only the relevant project scans are triggered when changes are made.

### `comp-cli` (Python Project)

This project uses the standard sonar-scanner. The workflow is triggered on push events to the `merge_requests`, `master`, and `develop` branches, or when changes are made to the specified paths.
```yaml
on:
  push:
    branches:
      - merge_requests
      - master
      - develop
    paths:
      - '.github/workflows/cli-scan.yaml'
      - '.gitignore'
      - 'comp-cli/**/*'
```

The SonarQube scan is run with `comp-cli` as the base directory.

```yaml
- uses: sonarsource/sonarqube-scan-action@master
    with: 
        projectBaseDir: ./comp-cli
    env:
        SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        SONAR_HOST_URL: ${{ secrets.SONAR_HOST_URL }}
        SONAR_WORKING_DIRECTORY: ./comp-cli
```

### `comp-dotnet` (C# Project)

This project uses the SonarScanner for .NET. The workflow is triggered on push events to the specified branches, or when changes are made to the specified paths.
```yaml
on:
    push:
        branches:
            - merge_requests
            - master
            - develop
        paths:
            - '.github/workflows/dotnet-scan.yaml'
            - '.gitignore'
            - 'comp-dotnet/*.sln'
            - 'comp-dotnet/project/*.cs*'
```
The .NET build and SonarQube scan are run in the `comp-dotnet` directory.
```yaml
- name: Build and analyze
    shell: powershell
    run: |
        cd .\comp-dotnet
        ..\.sonar\scanner\dotnet-sonarscanner begin /k:"demo:mono-dotnet" /n:"Monorepo .Net Core" /d:sonar.token="${{ secrets.SONAR_TOKEN }}" /d:sonar.host.url="${{ secrets.SONAR_HOST_URL }}"
        dotnet build dotnetcore-sample.sln
        ..\.sonar\scanner\dotnet-sonarscanner end /d:sonar.token="${{ secrets.SONAR_TOKEN }}"
```
### `comp-maven` (Java Maven Project)

This project uses the Maven SonarQube scanner. The workflow is triggered on push events to the specified branches, or when changes are made to the specified paths.
```yaml
on:
    push:
        branches:
            - merge_requests
            - master
            - develop
        paths:
            - '.github/workflows/maven-scan.yaml'
            - '.gitignore'
            - 'comp-maven/src/**/*'
            - 'comp-maven/pom.xml'
```
The Maven build and SonarQube scan are run in the `comp-maven` directory.
```yaml
- name: Build and Analyze with Maven
    env:
        SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        SONAR_HOST_URL: ${{ secrets.SONAR_HOST_URL }}
    run: |
        cd ./comp-maven
        mvn -B clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report sonar:sonar
```

## SonarQube Setup

1. Group the three projects (`comp-cli`, `comp-dotnet`, and `comp-maven`) into an Application in SonarQube for aggregated metrics.
2. Enable monorepo support in SonarQube to prevent PR decoration from overwriting existing PR comments from SonarQube.