#!/bin/sh

export COMPOSE_FILE_PATH=${PWD}/target/classes/docker/docker-compose.yml

if [ -z "${M2_HOME}" ]; then
  export MVN_EXEC="mvn"
else
  export MVN_EXEC="${M2_HOME}/bin/mvn"
fi

start() {
    docker volume create sentiment-analysis-share-acs-volume
    docker volume create sentiment-analysis-share-db-volume
    docker volume create sentiment-analysis-share-ass-volume
    docker-compose -f $COMPOSE_FILE_PATH up --build -d
}

start_share() {
    docker-compose -f $COMPOSE_FILE_PATH up --build -d sentiment-analysis-share-share
}

down() {
    docker-compose -f $COMPOSE_FILE_PATH down
}

purge() {
    docker volume rm sentiment-analysis-share-acs-volume
    docker volume rm sentiment-analysis-share-db-volume
    docker volume rm sentiment-analysis-share-ass-volume
}

build() {
    docker rmi alfresco-share-sentiment-analysis-share:development
    $MVN_EXEC clean install -DskipTests=true
}

build_share() {
    docker-compose -f $COMPOSE_FILE_PATH kill sentiment-analysis-share-share
    yes | docker-compose -f $COMPOSE_FILE_PATH rm -f sentiment-analysis-share-share
    docker rmi alfresco-share-sentiment-analysis-share:development
    $MVN_EXEC clean install -DskipTests=true
}

tail() {
    docker-compose -f $COMPOSE_FILE_PATH logs -f
}

tail_all() {
    docker-compose -f $COMPOSE_FILE_PATH logs --tail="all"
}

test() {
    $MVN_EXEC verify
}

case "$1" in
  build_start)
    down
    build
    start
    tail
    ;;
  start)
    start
    tail
    ;;
  stop)
    down
    ;;
  purge)
    down
    purge
    ;;
  tail)
    tail
    ;;
  reload_share)
    build_share
    start_share
    tail
    ;;
  build_test)
    down
    build
    start
    test
    tail_all
    down
    ;;
  test)
    test
    ;;
  *)
    echo "Usage: $0 {build_start|start|stop|purge|tail|reload_share|build_test|test}"
esac