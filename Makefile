export TEMPLATECONF=$(PWD)/meta-senic/conf
export BUILDDIR=$(PWD)/build/
export BBPATH=$(PWD)/build:$(PWD)/meta-senic/
export PATH:=$(PWD)/oe/bitbake/bin:$(PATH)

oe/README:  .repo/manifest.xml
	./bin/repo sync -j3

sync:  .repo/manifest.xml
	./bin/repo sync -j3

.repo/manifest.xml: bin/repo
	./bin/repo init -u . -m manifests/default.xml

bin/repo:
	wget https://storage.googleapis.com/git-repo-downloads/repo -O bin/repo
	chmod a+x bin/repo

build: .repo/manifest.xml
	bitbake senic-os senic-os-dev

clean:
	git clean -fXd

.PHONY: clean sync build
