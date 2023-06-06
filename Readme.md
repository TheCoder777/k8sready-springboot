# k8s ready Springboot template

This template is a preconfigured, scalable Springboot project with one minimal initial controller, ready to be
deployed to Kubernetes.
A single Gradle task outputs a finalised docker image that can be directly uploaded to an image registry of your choice.
The image can directly be deployed with the preconfigured Deployment, Service and Ingress in
the [k8sready-springboot.yaml](./k8sready-springboot.yaml) file.

This was tested with a [kind cluster](https://kind.sigs.k8s.io) for simplicity's sake. Kind is
a [CNCF certified](https://landscape.cncf.io/?selected=kind) compliant Kubernetes installer.
With some additional GitHub actions for example, this will perfectly integrate with many pipelines.

## Features

### Compilation Process

This Template provides a 'double stage docker image-creation' process.
The final image is compiled in a clean Docker gradle environment during stage one of the [Dockerfile](./Dockerfile).
In the second stage, the clean java-executable is copied to an OpenJDK 17 container.
That Container can e.g. be Deployed on a Kubernetes environment,
or you can do basically anything else you can do with a container these days.

### Gradle

All local tasks are grouped by the `spring local` group.
Kubernetes related tasks are grouped by the `k8s` group

This template defines gradle commands for:
- Development runs: `runSpringDev`
- production runs: `runSpringProd`
- testing runs: `testSpring`
- Full Docker build: `buildDockerImage`

With the provided tasks, you can easily build a task to deploy directly to your own k8s.

### Kubernetes

Also provided is a preconfigured Service, an Ingress and a Deployment to get you started.
If you're using OpenShift for example you will need to replace the Ingress component with a Route, but you probably know that.

### Database
You can add your Production Database in [application.properties](./src/main/resources/application.properties) since that configuration will apply to production use.
An extra database for testing can go into [application-test.properties](./src/main/resources/application-test.properties).
One for development into [application-dev.properties](./src/main/resources/application-dev.properties)

### Testing
Since springboot provides us with a variety of tests [illustrated here](https://spring.io/guides/gs/testing-web/)
I decided to provide some examples of `HttpRequestTest` and `HttpRequestMockTest` under [./src/test](./src/test/java/com/thecoder777/k8sreadyspringboot).

### Env dependant values
In [application.properties](./src/main/resources/application.properties),
I added an example `value.depending.on.environment` of a configuration value that holds different values per environment.
An example of how you use that in Java is provided in the main spring class [SpringKubernetesApplication](./src/main/java/com/thecoder777/k8sreadyspringboot/SpringKubernetesApplication.java)
using the `@Value` and `@PostConstruct` decorators.
If you don't like it, you can just remove it entirely from the two mentioned locations.

### tl;dr features

- Double Stage Docker image creation 
- Compiled in an extra gradle container as Stage 1
- clean java-executable in OpenJDK 17 container as Stage 2
- Gradle commands for development, production & testing
- Preconfigured Service, Ingress and Deployment for Kubernetes (adapt the ingress for openshift)
- Custom Testing database if needed
- Predefined example tests illustrating different testing techniques (HttpRequestTest, HttpRequestMockTest)
- Environment dependant values

## Usage

This part is very straightforward:

- Replace the name `k8sreadyspringboot` everywhere
- Adapt the `value.depending.on.environment` under [application.properties](./src/main/resources/application.properties)
- Run your first gradle command, for example `gradle runSpringProd` (that's locally)
- Continue with the template as you like
- Don't forget to read the Pitfalls first

## Pitfalls

1. Version number needs to be changed in 3 files: (commented)
    - [build.gradle](./build.gradle) `version` attribute
    - [Dockerfile](./Dockerfile) `ARTIFACT_NAME` variable
    - [Kubernetes Yaml](./k8sready-springboot.yaml) in Deployment

2. Server Port needs to be changed in 3 Locations: (commented)
    - [default application.properties](./src/main/resources/application.properties) `server.port` attribute
    - [Kubernetes Yaml](./k8sready-springboot.yaml) in Service and Ingress
    - [Dockerfile](./Dockerfile) under `Expose`

## License

The project is licensed under the [MIT license](./LICENSE).


## Maintainer

Current maintainer and owner: [TheCoder777](https://github.com/thecoder777)

**Cheers!**
