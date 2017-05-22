env=build

oe/README:  .repo/manifest.xml
	./bin/repo sync -j3

sync:  .repo/manifest.xml
	./bin/repo sync -j3
	git pull

.repo/manifest.xml: bin/repo
	./bin/repo init -u . -m manifests/default.xml

bin/repo:
	wget https://storage.googleapis.com/git-repo-downloads/repo -O bin/repo
	chmod a+x bin/repo

build: .repo/manifest.xml
	./buildit

clean:
	git clean -fXd

upload:
	rsync -av manifests meta-senic --chown=senic osbuild:/mnt/data/senic-os/

$(env)/tmp-glibc/deploy/images/senic-hub-beta/:
	mkdir -p $(env)/tmp-glibc/deploy/images/senic-hub-beta/

download: $(env)/tmp-glibc/deploy/images/senic-hub-beta/
	rsync -avzh osbuild:/mnt/data/senic-os/$(env)/tmp-glibc/deploy/images/senic-hub-beta/ $(env)/tmp-glibc/deploy/images/senic-hub-beta/

.PHONY: clean sync build upload download
