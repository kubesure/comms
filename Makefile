GOCMD=go
GOBUILD=$(GOCMD) build
GORUN=$(GOCMD) run
GOINSTALL=$(GOCMD) install
GOCLEAN=$(GOCMD) clean
GOTEST=$(GOCMD) test
GRADLE=gradle 
DOCKER=docker
DBUILD=$(DOCKER) build
DTAG= $(DOCKER) tag
DPUSH= $(DOCKER) push

BINARY_NAME=comms
BINARY_VERSION=$(shell git rev-parse HEAD)
BINARY_UNIX=$(BINARY_NAME)
TAG_LOCAL = $(BINARY_NAME):$(BINARY_VERSION)
TAG_HUB = bikertales/$(BINARY_NAME):$(BINARY_VERSION)

.PHONY: pull # Pull latest from master
pull:
	git pull

.PHONY: build # - Builds linux arch go binary
build:
	gradle build -x test

.PHONY: run # - Runs the service without build
run: build
	java -jar ./build/libs/comms-${BINARY_VERSION}.jar

.PHONY: dbuild  # - Builds docker image
dbuild: build
	gradle dockertag

.PHONY: dtag # - Tags local image to docker hub tag
dtag: dbuild
	$(DTAG) $(TAG_LOCAL) $(TAG_HUB)

.PHONY: dpush # - Pushes tag to docker hub
dpush: dtag
	$(DPUSH) $(TAG_HUB)

.PHONY: tasks
tasks:
	@grep '^.PHONY: .* #' Makefile | sed 's/\.PHONY: \(.*\) # \(.*\)/\1 \2/' | expand -t20
