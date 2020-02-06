FROM hirokimatsumoto/alpine-openjdk-11

ENV VERTICLE_NAME com.mzk.controller.ControllerProduto

# Set the location of the verticles
ENV VERTICLE_HOME /usr/verticles

EXPOSE 8080

COPY $VERTICLE_FILE $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar mzk.jar"]