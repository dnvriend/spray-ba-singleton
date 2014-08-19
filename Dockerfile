FROM murad/java8
MAINTAINER Dennis Vriend <dnvriend@gmail.com>

ADD /target/dist /appl
RUN chmod u+x /appl/bin/start

EXPOSE 8080
EXPOSE 2552
ENV BIND_ADDRESS 0.0.0.0
ENV BIND_PORT 8080

CMD [ "/appl/bin/start" ]