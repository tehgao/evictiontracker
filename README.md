# DSA Cleveland Evictions Tracker
The DSA Cleveland evictions tracker is a web app designed to track, provide insight into, and analyze evictions occuring in the Cleveland area.

## Getting Started
This application uses Java 11 and Maven. If you do not have a local maven install, a maven wrapper is provided. The setup instructions will assume the use of the wrapper but feel free to use your own local install if you prefer.

Additionally, this application uses Postgresql to persist data. A docker-compose file with a preconfigured postgres instance and web interface is provided to assist in the setup of a local environment.

### Prerequisites
- JDK 11
- Docker

### Running The Application Locally
Clone the application and `cd` into the directory. Then run the following commands:

```
docker-compose up -d
./mvnw spring-boot:run -Dspring-boot.run.profiles=local-dev
```

If you wish to run integration tests copy the `environment_specific_template.yml` file to `application-local-integration.yml` and run
```
./mvnw spring-boot:run -Dspring-boot.run.profiles=local-integration
```

## Tests
To run the unit tests run the command `./mvnw test`.

## Contributing
Contributions are welcome! If you wish to contribute we ask you please first contact either the repository owners or [DSA Cleveland](mailto:info@dsacleveland.org).

### Pull Request Process
0. Please make sure you have added at least rudimentary unit and/or integration tests and update any existing unit tests that have been rendered nonfunctional.
1. Update the README.md to correct any information that has changed within the scope of your pull request.
2. Update the version number accordingly. The scheme we use is [SemVer](https://semver.org/).

### Code of Conduct
This project is adherent to both the DSA Cleveland Code of Conduct and the [Contributor Covenant Code of Conduct](code_of_conduct.md). In places where the two contradict, the DSA Code of Conduct takes precedence.

# Authors
- [**Alvin Gao**](https://github.com/tehgao): Initial Work

# License
This program is licensed under the terms of the GPLv3. See the [license](LICENSE.md) for more details.

# Acknowledgements
- DSA Cleveland Housing Justice Committee for providing guidance