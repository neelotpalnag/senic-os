#!/usr/bin/env bash
export TEMPLATECONF=../meta-senic/conf
source oe/oe-init-build-env build/
bitbake senic-os senic-os-dev

