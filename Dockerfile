FROM murad/java8
MAINTAINER Dennis Vriend <dnvriend@gmail.com>

ADD /target/dist /appl
# Export the cluster IP in runtime through the start script when the docker is started
RUN sed "2s/.*/export CLUSTER_IP=\$\(ifconfig eth0 \| grep 'inet addr:' \| cut -d: -f2 \| awk '{ print \$1}'\)/" /appl/bin/start > /appl/bin/start.sh
RUN chmod u+x /appl/bin/start.sh

ADD /docker/jstatd.sh /appl
RUN chmod u+x /appl/jstatd.sh

EXPOSE 1099 2552 8080 12345

ENV BIND_ADDRESS 0.0.0.0
ENV BIND_PORT 8080

CMD /appl/jstatd.sh && /appl/bin/start.sh
