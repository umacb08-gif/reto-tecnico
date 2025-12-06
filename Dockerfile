FROM registry.access.redhat.com/ubi9/openjdk-21:1.23

WORKDIR /workspace

COPY target/quarkus-app/ /deployments/

EXPOSE 8080
USER 185

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"


# Crea un archivo temporal del secreto
RUN --mount=type=secret,id=api_key \
    export API_KEY=$(cat /run/secrets/api_key) && \
    echo "El secreto fue cargado correctamente"

# ENV API_KEY="2f5ae96c-b558-4c7b-a590-a501ae1c3f6c"

ENTRYPOINT ["/opt/jboss/container/java/run/run-java.sh"]
